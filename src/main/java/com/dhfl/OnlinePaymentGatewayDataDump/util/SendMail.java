package com.dhfl.OnlinePaymentGatewayDataDump.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMail {
	static Logger logger = LoggerFactory.getLogger(SendMail.class);

	public static void main(String[] args) {
		//sendMail();
		sendEmailWithAttachment("digumarthi.bala@gmail.com", "7895826431", "error");
	}

	public static void sendMail() {
		try {
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
					+ "<h2>System Data</h2>\n" + "<p>Hi Bala, Your were recently @</p>\n" + "<p>Public IP :  " + "Test"
					+ "</p>\n" + "<p>Private IP :  \n" + "Test" + "</p>\n" + "<p> Machine Name :  " + "Test" + "</p>"
					+ "<p> MAC Address :  " + "Test" + "</p>" + "<p>Location :  " + "Test" + "</p>\n" + "<p>Day :  "
					+ "Test" + "</p>\n" + "<p>Date :  " + "Test" + "</p>\n" + "<p>Time :  " + "Test" + "</p>\n"
					+ "</div>\n" + "\n"
					+ "<div id='footer' style='background-color: rgb(84, 139, 6);color:white;clear:both;text-align:center;padding:5px;'>\n"
					+ "Copyright © find your system\n" + "</div>\n" + "\n" + "</body>\n" + "</html>\n" + "\n";
			final String from = "catchallmail032@gmail.com";
			final String password = "Welcome@4321";
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "10.200.10.47");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			// props.put("mail.smtp.socketFactory.fallback", "false");
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

	public static void sendEmailWithAttachment(String email, String fileRefNum, String error) {
		logger.debug("SendMail.sendEmailWithAttachment():="+email+ " File Reference Number="+fileRefNum);
		System.out.println("SendMail.sendEmailWithAttachment():="+email+ " File Reference Number="+fileRefNum);
		// Sender's email ID needs to be mentioned
		String from = "noreply@dhfl.com";
		// Assuming you are sending email from localhost
		String host = "10.200.10.47";
		String port = "25";
		//String host = PropertyReader.getProperty("mailHost");
		//String port = PropertyReader.getProperty("mailPort");
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		// Get the default Session object.
		// javax.mail.Session session
		// =javax.mail.Session.getDefaultInstance(properties);
		Session session = Session.getDefaultInstance(properties);
		String body = "<html><title>DHFL</title><body>"
				+ "<p>Dear User,<br><br>File upload has completed with below refrenece number. "
				+ "Please find the uploaded status report in the below link.<br><br>"
				+ "Refrence Number : " + fileRefNum +"<br>"
				//+ "Error : "+ error +"<br>"
				+ "<a href='https://clicktopay.dhfl.com/data/download?fileName="+fileRefNum+"' target='_blank'>Click to download report.</a><br><br>"
				+ "This is a system generated e-mail and please do not reply.<br><br>"
				+ "Warm regards,<br>"
				+ "DHFL Administrator<br><br>"
				+ "</p></body></html>";
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// set CC mail ids
			// message.addRecipient(Message.RecipientType.CC,new InternetAddress(emailid));
			message.addRecipients(Message.RecipientType.CC, email);
			// Set Subject: header field
			message.setSubject(fileRefNum+" - File Upload Status Report.");
			// Now set the actual message
			// message.setText("This is actual message");
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			//messageBodyPart.setText("");
			messageBodyPart.setContent(body, "text/html");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			//messageBodyPart = new MimeBodyPart();
			// String filename = "F:/Credit PD/ireport/test report/abc.pdf";
			
			/*InputStream is = ftpClient.retrieveFileStream(attPath);
			byte[] data = CreditPdUtil.readBytes(is);
			// byte data[]=null;
			DataSource source = new ByteArrayDataSource(data, "application/pdf");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(appNo + ".pdf");*/
			// sending attachment
			// messageBodyPart.setDataHandler(new DataHandler(new
			// URL("http://www.google.co.in/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png")));
			// messageBodyPart.setFileName("google.png");
			// messageBodyPart.setFileName(filename);
			//multipart.addBodyPart(messageBodyPart);
			// Send the complete message parts
			message.setContent(multipart);
			message.saveChanges();
			// message.setContent(content, "text/html");
			// Send message
			Transport.send(message);
			logger.debug(":Sent message successfully....="+fileRefNum);
			System.out.println(":Sent message successfully....="+fileRefNum);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

}
