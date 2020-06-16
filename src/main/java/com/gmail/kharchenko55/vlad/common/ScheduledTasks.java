package com.gmail.kharchenko55.vlad.common;

import com.gmail.kharchenko55.vlad.common.emailsender.EmailController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ScheduledTasks {

    @Autowired
    private EmailController emailController;

    @Scheduled(fixedRate =259200000)
    public void sendEmail() throws MessagingException {
       emailController.sendSimpleMail();
    }
}