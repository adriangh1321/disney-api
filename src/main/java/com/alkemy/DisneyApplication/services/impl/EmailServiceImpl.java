package com.alkemy.DisneyApplication.services.impl;

import com.alkemy.DisneyApplication.services.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class EmailServiceImpl implements EmailService {

    @Value("${sendgrid.api.key}")
    private String EMAIL_API_KEY;
    
    @Value("${sendgrid.email.sender}")
    private String emailSender;

    @Value("${sendgrid.email.enabled}")
    private Boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to) {
        if (!enabled) {
            return;
        }
        
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain", "Bienvenido/a a Alkemy Disney"
        );
        String subject = "Alkemy Disney";
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sq = new SendGrid(EMAIL_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sq.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            System.out.println("Error trying to send the email");
        }
    }

}
