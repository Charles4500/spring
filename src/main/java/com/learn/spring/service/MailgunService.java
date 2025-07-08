package com.learn.spring.service;

import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunService {

    @Value("${mailgun.api.key}")      // Add to application.properties
    private String apiKey;

    @Value("${mailgun.domain}")       // e.g., "sandbox123.mailgun.org"
    private String domain;

    @Value("${mailgun.mailing-list}") // e.g., "devs@yourdomain.com"
    private String mailingList;

    public String sendToList(String subject, String text) {
        return Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
                .basicAuth("api", apiKey)
                .field("from", "Spring Boot App <no-reply@" + domain + ">")
                .field("to", mailingList)
                .field("subject", subject)
                .field("text", text)
                .asString()
                .getBody();
    }
}