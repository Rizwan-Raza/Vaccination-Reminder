package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	private static final String user = "rizwan.raza987@gmail.com";
	private static final String pass = "********";

	class MyAuthenticator extends javax.mail.Authenticator {
		String User;
		String Password;

		public MyAuthenticator(String user, String password) {
			User = user;
			Password = password;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new javax.mail.PasswordAuthentication(User, Password);
		}
	}

	public static boolean send(String to, String sub, String msg) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setContent(msg, "text/html; charset=utf-8");
			// message.setText(msg[1]);

			Transport.send(message);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
