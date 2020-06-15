package com.gmail.kharchenko55.vlad.common.emailsender;

import com.gmail.kharchenko55.vlad.controller.searchcontroller.SearchHelper;
import com.gmail.kharchenko55.vlad.model.car.Car;
import com.gmail.kharchenko55.vlad.model.search.SearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private static final String EMAIL_SIMPLE_TEMPLATE_NAME = "email/email.html";

    @Autowired
    private JavaMailSender mailSender;

    @Qualifier("templateEngine")
    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private SearchHelper searchHelper;

    public void sendSimpleMail(Map<String, List<SearchHistory>> receiversForNewCars)
            throws MessagingException{

        final Context ctx = new Context();
        List<Car> cars = new ArrayList<>();

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Auto Ria helper");
        message.setFrom("testVladOnlineShop@gmail.com");

        receiversForNewCars.entrySet().forEach(entry->{
            String recipientEmail = entry.getKey();
            for (int i = 0; i < entry.getValue().size(); i++) {
                int carBody = entry.getValue().get(i).getCarBody();
                int carBrand = entry.getValue().get(i).getCarBrand();
                int carModel = entry.getValue().get(i).getCarModel();
                try {
                    cars.addAll(searchHelper.getCarsByPeriod(carBody, carBrand, carModel));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                ctx.setVariable("email", recipientEmail);
                message.setTo(recipientEmail);
                ctx.setVariable("cars", cars);

                final String htmlContent = this.htmlTemplateEngine.process(EMAIL_SIMPLE_TEMPLATE_NAME, ctx);
                message.setText(htmlContent, true);

                this.mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            cars.clear();
        });
    }
}