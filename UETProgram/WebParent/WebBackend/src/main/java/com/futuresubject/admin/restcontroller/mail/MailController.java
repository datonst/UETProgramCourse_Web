package com.futuresubject.admin.restcontroller.mail;

import com.futuresubject.admin.dto.mail.MailObject;
import com.futuresubject.admin.dto.mail.MailStructure;
import com.futuresubject.admin.service.mail.EmailServiceImpl;
import com.futuresubject.admin.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

//    @Autowired
//    private MailService mailService;
//
//    @PostMapping("/send/{mail}")
//    public String sendMail(@PathVariable("mail") String mail,
//                           @RequestBody MailStructure mailStructure) {
//        mailService.sendMail(mail,mailStructure);
//        return "SUCCESS SEND MAIL";
//    }
    @Autowired
    private EmailServiceImpl emailService;
    @PostMapping("/sends")
    public String sendMails(@RequestBody MailObject mailObject) {
        emailService.sendSimpleMessage(mailObject.getTo()
                ,mailObject.getSubject()
                ,mailObject.getText());
        return "SUCCESS SEND MAIL";
    }
}
