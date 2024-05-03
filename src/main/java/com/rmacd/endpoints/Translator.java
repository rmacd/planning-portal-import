package com.rmacd.endpoints;

import com.rmacd.models.ProxymanRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Translator {

    final ApplicationContext context;

    public Translator(ApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/api/requests")
    public String addRequest(@RequestBody ProxymanRequest request) {
        JmsTemplate template = context.getBean(JmsTemplate.class);
        template.setDefaultDestinationName("plans");
        template.convertAndSend(request);
        return "got responses";
    }

}
