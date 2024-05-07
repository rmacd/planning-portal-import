package com.rmacd.factories;

import com.rmacd.models.DocumentDetails;
import com.rmacd.models.mdb.ResponseCache;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentDetailsFactory {

    private static final Logger logger = LoggerFactory.getLogger(DocumentDetailsFactory.class);
    private static final Pattern p = Pattern.compile("([A-Z0-9]{32})");

    public static List<DocumentDetails> create(ResponseCache input) {
        List<DocumentDetails> detailsList = new ArrayList<>();

        Document html = Jsoup.parse(input.getDocument());
        List<Element> docs = html.select("table[id='Documents'] tr");
        for (Element doc : docs) {
            if (!doc.select("th").isEmpty()) {
                continue;
            }
            DocumentDetails documentDetails = new DocumentDetails();
            documentDetails.setKeyVal(input.getRef());
            documentDetails.setAuthority(input.getAuthority());
            documentDetails.setDatePublished(parseDate(doc.select("td").get(1).text()));
            documentDetails.setDocumentType(doc.select("td").get(2).text());
            documentDetails.setDescription(doc.select("td").get(4).text());
            documentDetails.setUrl(doc.select("td").get(5).select("a").attr("href"));
            String attachmentId = getAttachmentId(documentDetails.getUrl());
            if (null == attachmentId) {
                logger.warn("Unable to extract attachment_id: generating based on URL");
                attachmentId = DigestUtils.md5Hex(documentDetails.getUrl()).toUpperCase();
            }
            documentDetails.setAttachmentId(attachmentId);
            documentDetails.setId("%s_%s".formatted(input.getRef(), attachmentId));
            detailsList.add(documentDetails);
        }
        return detailsList;
    }

    static String getAttachmentId(String input) {
        if (null == input || input.isEmpty()) return null;
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    static LocalDate parseDate(String input) {
        return LocalDate.from(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH).parse(input));
    }

}
