import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAttachmentSender {

	public static void sendEmailWithAttachments(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message, String[] attachFiles)
			throws AddressException, MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates multi-part
		Multipart multipart = new MimeMultipart();

		{
			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(message, "text/html");
			multipart.addBodyPart(messageBodyPart);
		}

		// adds attachments
		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles)
				if (filePath != null) {
					MimeBodyPart attachPart = new MimeBodyPart();

					try {
						File f = new File(filePath);
						System.out.println(f.getAbsolutePath());
						// attachPart.attachFile(f);

						String s = EmailAttachmentSender.readFile(
								f.getAbsolutePath(), Charset.defaultCharset());
						MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
						System.out.println(s.length() + " chars, "
								+ mimeTypesMap.getContentType(f));
						attachPart
								.setContent(s, mimeTypesMap.getContentType(f));
						attachPart.setFileName(f.getName());
						attachPart.setContentID(f.getName());

						// // messageBodyPart.setDataHandler(new
						// DataHandler(ds));
						// // messageBodyPart.setHeader("Content-Type",
						// // ds.getContentType());
						// // messageBodyPart.setHeader("Content-ID",
						// f.getName());
						attachPart.setDisposition(Part.ATTACHMENT);

					} catch (IOException ex) {
						ex.printStackTrace();
					}

					multipart.addBodyPart(attachPart);
				}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	/**
	 * Test sending e-mail with attachments
	 */
	public static void main(String[] args) {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "infntest@gmail.com";
		String password = "infn2014";

		// message info
		String mailTo = "turella@lnf.infn.it";
		String subject = "TESTING FE - invio NE - " + (new Date());
		String message = "vuoto";

		// attachments
		String[] attachFiles = new String[3];
		attachFiles[0] = "IT01234567890_11111_NE_001.xml";
		// attachFiles[1] = "e:/Test/Music.mp3";
		// attachFiles[2] = "e:/Test/Video.mp4";

		try {
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
					subject, message, attachFiles);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
}