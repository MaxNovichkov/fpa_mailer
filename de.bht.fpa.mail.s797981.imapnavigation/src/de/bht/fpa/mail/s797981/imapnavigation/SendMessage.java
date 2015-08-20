package de.bht.fpa.mail.s797981.imapnavigation;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
/**
 * This class is responsible for creating and sending new message 
 *  
 *@author Novichkov Maxim
 */
public final class SendMessage {
	/**
	 * Information for debugging sending of message.
	 */
	final static boolean debug = true;
	/**
	 * Sends created message with provided by {@link MessageView} information. 
	 * @param account Provided imap account
	 * @param recipient Provided recipient information
	 * @param subject Provided subject information
	 * @param text Provided text information for sending
	 */
	public static void send(final Account account, final String recipient, final String subject, 
			final String text) {
		
		// Get a Properties object, configure our connection
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.ssl.auth", "true");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.port", "465");
//		props.put("mail.smtp.host", account.getHost());
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.host", account.getHost());
		
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.host", account.getHost());
		
		Authenticator authenticator = new Authenticator() {
		        private PasswordAuthentication pa = new PasswordAuthentication(account.getUsername(), account.getPassword());
		        @Override
		        public PasswordAuthentication getPasswordAuthentication() {
		            return pa;
		    };
		};
		/**
		 * Creates new session with corresponding connection properties and credentials 
		 */
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		/**
		 * Creates new MimeMessage
		 */
		Message msg = new MimeMessage(session);
		
		try {
			/**
			 * Fill message with corresponding information
			 */
			msg.setHeader("X-Mailer", "smtpsend");
			msg.setFrom(new InternetAddress(account.getUsername()));
			msg.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(recipient));
			msg.setSentDate(new Date());
			msg.setSubject(subject);
			msg.setText(text);
			
			
			if(debug){
			System.out.println("account.getPassword(): " + account.getPassword());
			System.out.println("msg.setFrom(new InternetAddress(account.getUsername())): " + (new InternetAddress(account.getUsername()).toString()));
			System.out.println("InternetAddress.parse(recipient): " + recipient);
			System.out.println("subject: " + subject);
			System.out.println("text: " + text);
			}
			
			Transport transport = session.getTransport("smtp");
			
			System.out.println("Connecting...");
			transport.connect();
			
			System.out.println("Sending...");
			
			transport.sendMessage(msg, msg.getAllRecipients());
			
			System.out.println("Closing");
			transport.close();
			
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
