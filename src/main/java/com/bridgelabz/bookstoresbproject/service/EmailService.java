package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.ResponseDTO;
import com.bridgelabz.bookstoresbproject.model.EmailModel;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    public ResponseEntity<ResponseDTO> sendEmail(EmailModel email) {
        final String fromEmail = "abc@xyz.com";
        final String fromPwd = "1234";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication

            getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(fromEmail, fromPwd);
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        MimeMessage mail = new MimeMessage(session);
        try {
            mail.addHeader("Content-type", "text/HTML;charset=UTF-8");
            mail.addHeader("format", "flowed");
            mail.addHeader("Content-Transfer-Encoding", "8bit");
            mail.setFrom(new InternetAddress(fromEmail));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            mail.setText(email.getBody(), "UTF-8");
            mail.setSubject(email.getSubject(), "UTF-8");

            Transport.send(mail);
            ResponseDTO responseDTO = new ResponseDTO("Sent email ", mail, null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ResponseDTO responseDTO = new ResponseDTO("ERROR: Couldn't send email", null, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
