import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.bouncycastle.cms.CMSException;

public class InboxReader {

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		String server = "imap.gmail.com"; // args[0];
		String username = "infntest@gmail.com"; // args[1];
		String password = "infn2014"; // args[2];
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect(server, username, password);
			System.out.println(store);

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			// Message messages[] = inbox.getMessages();
			FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
			Message messages[] = inbox.search(ft);

			int i = 0;
			for (Message message : messages) {

				Multipart mp = (Multipart) message.getContent();
				System.out.println("(idx = " + i + ") " + message.getSubject()
						+ "\n");
				i++;
				for (int b = 0; b < mp.getCount(); b++) {
					BodyPart p = mp.getBodyPart(b);
					if ((p.getFileName() != null && p.getFileName().endsWith(
							".xml"))
							|| p.getContentType().startsWith("TEXT/XML")) {
						Object pp = p.getContent();
						String q = pp.toString();// object has the body content
						System.out.println(p.getFileName() + " ["
								+ p.getContentType() + "] (" + q.length()
								+ " chars)");// prints the body
						System.out.println(q);// prints the body
					} else if ((p.getFileName() != null && p.getFileName()
							.endsWith(".xml.p7m"))
							|| p.getContentType().startsWith(
									"APPLICATION/PKCS7-MIME")) {
						// Object pp = p.getContent();
						// String q = pp.toString();// object has the body
						// content

						InputStream is = (InputStream) p.getContent();
						BufferedReader br = null;
						String totalLine = new String(), line;
						br = new BufferedReader(new InputStreamReader(is));
						while ((line = br.readLine()) != null)
							totalLine += line + "";
						byte[] bytes = totalLine.getBytes();
						// totalLine = new String(bytes, "UTF-8");

						P7mUtilities u = new P7mUtilities(totalLine.getBytes());
						System.out.println(p.getFileName() + " ["
								+ p.getContentType() + "] (" + bytes.length
								+ " chars)");
						try {
							boolean r = u.verifySignature(null);
							if (r)
								System.out.println("FIRMA OK");
							else
								System.out.println("FIRMA FASULLA");
							String xml = u.getXml();
							System.out.println(xml);
						} catch (CMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}