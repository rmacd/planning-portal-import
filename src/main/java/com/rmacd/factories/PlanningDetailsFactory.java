package com.rmacd.factories;

import com.rmacd.models.AppealStatusEnum;
import com.rmacd.models.PlanningDetails;
import com.rmacd.models.PlanningStatusEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PlanningDetailsFactory {

    private static final Logger logger = LoggerFactory.getLogger(PlanningDetailsFactory.class);

    public static PlanningDetails create(String input) {
        Document doc = Jsoup.parse(input);

        PlanningDetails details = new PlanningDetails();
        List<Element> rows = doc.select("table tr");
        for (Element row : rows) {
            Element head = row.selectFirst("th");
            Element item = row.selectFirst("td");
            if (null == head || null == item) {
                logger.error("header / item is null");
                continue;
            }
            if (head.text().equalsIgnoreCase("reference")) {
                details.setReference(item.text().toUpperCase());
                continue;
            }
            if (head.text().equalsIgnoreCase("application validated")) {
                details.setValidated(parseDate(item.text()));
                continue;
            }
            if (head.text().equalsIgnoreCase("address")) {
                details.setAddress(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("proposal")) {
                details.setProposal(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("status")) {
                details.setStatus(PlanningStatusEnum.valueOf(toEnum(item.text())));
                continue;
            }
            if (head.text().equalsIgnoreCase("appeal status")) {
                details.setAppealStatus(AppealStatusEnum.valueOf(toEnum(item.text())));
                continue;
            }
            if (head.text().equalsIgnoreCase("appeal decision")) {
                if (item.text().equals("-")) {
                    continue;
                }
                continue;
            }
            if (head.text().equalsIgnoreCase("application type")) {
                details.setApplicationType(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("expected decision level")) {
                details.setDecisionLevel(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("community council")) {
                details.setCommunityCouncil(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("ward")) {
                details.setWard(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("applicant name")) {
                if (item.text().equals("-")) {
                    continue;
                }
                details.setApplicantName(normaliseName(item.text()));
                continue;
            }
            if (head.text().equalsIgnoreCase("agent name")) {
                if (item.text().equals("-")) {
                    continue;
                }
                details.setAgentName(normaliseName(item.text()));
                continue;
            }
            if (head.text().equalsIgnoreCase("agent company name")) {
                if (item.text().equals("-")) {
                    continue;
                }
                details.setAgentCompanyName(normaliseName(item.text()));
                continue;
            }
            if (head.text().equalsIgnoreCase("agent address")) {
                if (item.text().equals("-")) {
                    continue;
                }
                details.setAgentAddress(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("phone")) {
                if (item.text().equals("-")) {
                    continue;
                }
                details.setPhone(normalisePhone(item.text()));
                continue;
            }
            if (head.text().equalsIgnoreCase("e-mail")) {
                details.setEmail(item.text());
                continue;
            }
            if (head.text().equalsIgnoreCase("application received date")) {
                if (item.text().equals("-")) {
                    continue;
                }
                details.setApplicationReceived(parseDate(item.text()));
            }
        }

        return details;
    }

    static String toEnum(String input) {
        return input.toUpperCase().replaceAll("[^A-Z]", "_");
    }

    static String normaliseName(String input) {
        return input.replaceAll("[^a-zA-Z0-9 ]", "").strip();
    }

    static String normalisePhone(String input) {
        return input.replaceAll("[^0-9+]", "");
    }

    static LocalDate parseDate(String input) {
        return LocalDate.from(DateTimeFormatter.ofPattern("EEE dd MMM yyyy").parse(input));
    }

}
