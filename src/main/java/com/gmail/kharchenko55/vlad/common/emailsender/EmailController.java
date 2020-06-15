package com.gmail.kharchenko55.vlad.common.emailsender;

import com.gmail.kharchenko55.vlad.model.search.SearchHistory;
import com.gmail.kharchenko55.vlad.service.search.SearchHistoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/sendEmail"})
public class EmailController {

    @Autowired
    private SearchHistoryImpl historyService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = GET)
    public String sendSimpleMail() throws MessagingException, IOException {
        Map<String, List<SearchHistory>> receiversForNewCars = new HashMap<>();
        List<SearchHistory> histories = historyService.getAllHistory();

        for (SearchHistory history : histories) {
            String email = history.getEmail();
            List<SearchHistory> historyForCurrentUser = histories.stream()
                    .filter(el -> el.getEmail().equals(email))
                    .collect(Collectors.toList());
            receiversForNewCars.put(email, historyForCurrentUser);

        }

        this.emailService.sendSimpleMail(receiversForNewCars);
        return "email/sent";
    }
}
