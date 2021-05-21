package com.dhfl.OnlinePaymentGatewayDataDump.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONObject;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

public class SendMail {
	static Logger logger = LoggerFactory.getLogger(SendMail.class);
	
	public static void main(String[] args) {
		sendMail();
	}

	public static void sendMail() {
		try {
			TimeZone timeZone = TimeZone.getTimeZone("Asia/Calcutta");
			String dateFormat = "MMMM dd,yyyy G"; // MMMM dd,yyyy G
			String timeFormat = "hh:mm:ss.SSS a zzzz";
			String dayFormat = "EEEEEE";

			
			String to = "digumarthi.bala@gmail.com";
			String subject = "Uploaded data validation report";
			String msg = "<html>\n" + "<style>\n" + "#header-main {\n" + "    background-color:black;\n"
					+ "    color:white;\n" + "    text-align:center;\n" + "    padding:5px;\n" + "}\n" + "#nav-bar {\n"
					+ "    line-height:30px;\n" + "    background-color:#eeeeee;\n" + "    height:300px;\n"
					+ "    width:100px;\n" + "    float:left;\n" + "    padding:5px;\n" + "}\n" + "#section-block {\n"
					+ "    width:350px;\n" + "    float:left;\n" + "    padding:10px;\n" + "}\n" + "</style>\n"
					+ "<body>\n"
					+ "<div id='nav-bar' style='background-color: #548b06;color:white;text-align:center;padding-top:2px'>\n"
					+ "<img src=\"cid:image\" height='100px' width='100px'><h1>Current Location</h1>\n" + "<br>"
					+ "</div>\n" + "\n"
					+ "<div id='section-block' style='border: solid 1px rgb(223, 233, 209);background-color: rgb(223, 233, 209);color:black;padding-left:50px;padding-top:10px;padding-bottom:10px;padding-right:10px'>\n"
					+ "<h2>System Data</h2>\n" + "<p>Hi Bala, Your were recently @</p>\n" + "<p>Public IP :  "
					+ "Test" + "</p>\n" + "<p>Private IP :  \n"
					+ "Test" + "</p>\n" + "<p> Machine Name :  "
					+ "Test" + "</p>" + "<p> MAC Address :  "
					+ "Test" + "</p>" + "<p>Location :  " + "Test" + "</p>\n"
					+ "<p>Day :  " + "Test" + "</p>\n" + "<p>Date :  " + "Test" + "</p>\n" + "<p>Time :  "
					+ "Test" + "</p>\n" + "</div>\n" + "\n"
					+ "<div id='footer' style='background-color: rgb(84, 139, 6);color:white;clear:both;text-align:center;padding:5px;'>\n"
					+ "Copyright © find your system\n" + "</div>\n" + "\n" + "</body>\n" + "</html>\n" + "\n";
			final String from = "catchallmail032@gmail.com";
			final String password = "Welcome@4321";
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(msg, "text/html");

			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.attachFile("C:\\Users\\sparton\\Downloads\\honda-t360.jpg");
			multipart.addBodyPart(imagePart);

			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource("C:\\Program Files\\FindingSystem\\location_icon.png");
//			   DataSource fdsNew = new FileDataSource(
//					   "C:\\Program Files\\FindingSystem\\cartoon-robot1.gif");

			messageBodyPart.setDataHandler(new DataHandler(fds));
//			   messageBodyPart.setDataHandler(new DataHandler(fdsNew));
			messageBodyPart.addHeader("Content-ID", "<image>");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);
			Transport transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			MimeMessage message = new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			message.setContent(multipart);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			transport.connect();
			Transport.send(message);
			transport.close();
			Thread.sleep(10);
		} catch (Exception e) {
			logger.debug("[ Xception:sendMail ] " + e);
			e.printStackTrace(System.out);
		}
	}
}
