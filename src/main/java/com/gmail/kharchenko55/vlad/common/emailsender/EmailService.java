package com.gmail.kharchenko55.vlad.common.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Service
public class EmailService {

    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "email/email.html";

    @Autowired
    private JavaMailSender mailSender;

    @Qualifier("templateEngine")
    @Autowired
    private TemplateEngine htmlTemplateEngine;

    public void sendSimpleMail(
            final String recipientName, final String recipientEmail, final Locale locale)
            throws MessagingException {

        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Example HTML email (simple)");
        message.setFrom("testVladOnlineShop@gmail.com");
        message.setTo(recipientEmail);

        final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
        message.setText(htmlContent, true);

        this.mailSender.send(mimeMessage);
    }
}