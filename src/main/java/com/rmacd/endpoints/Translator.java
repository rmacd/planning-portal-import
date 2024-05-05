package com.rmacd.endpoints;

import com.rmacd.models.IncomingRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Translator {

    final ApplicationContext context;

    public Translator(ApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/api/requests")
    public String addRequest(@RequestBody IncomingRequest request, @RequestParam String authority) {
        // add local authority to request
        request.setAuthority(authority);
        JmsTemplate template = context.getBean(JmsTemplate.class);
        template.setDefaultDestinationName("plans");
        template.convertAndSend(request);
        return "got responses";
    }

}
