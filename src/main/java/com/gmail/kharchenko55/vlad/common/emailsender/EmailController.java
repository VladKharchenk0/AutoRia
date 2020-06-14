package com.gmail.kharchenko55.vlad.common.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/sendMailSimple"})
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = GET)
    public String sendSimpleMail(final Locale locale
//            @RequestParam("recipientName") final String recipientName,
//            @RequestParam("recipientEmail") final String recipientEmail,
//            final Locale locale)
    )
            throws MessagingException {

        this.emailService.sendSimpleMail("Vlad", "xapchenko2000@gmail.com", locale);
        return "email/sent";
    }
}
