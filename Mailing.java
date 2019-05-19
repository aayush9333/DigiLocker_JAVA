import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailing {

	boolean sendMail(String r,String t){

		final String username = "agoel4230@gmail.com"; // enter your mail id
		final String password = "aayush772744";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("agoel4230@gmail.com")); // same email id
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(r));// whome u have to send mails that person id
			message.setSubject("DIGILOCKER OTP");
			message.setText(template(t));

			Transport.send(message);

			return true;

		} catch (Exception e) {
			return false;
		}
	}
	String template(String o)
	{
	    return "OTP : "+o;
	}
}