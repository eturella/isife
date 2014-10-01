import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.sun.mail.util.BASE64DecoderStream;

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
						// Object pp = p.getContent();
						// String q = pp.toString();// object has the body
						// content
						byte[] q = scaricaAllegato(p);
						System.out.println(p.getFileName() + " ["
								+ p.getContentType() + "] (" + q.length
								+ " chars)");// prints the body
						System.out.println(q);// prints the body
					} else if ((p.getFileName() != null && p.getFileName()
							.endsWith(".xml.p7m"))
							|| p.getContentType().startsWith(
									"APPLICATION/PKCS7-MIME")) {
						byte[] q = scaricaAllegato(p);
						// byte[] file = Files.readAllBytes(Paths
						// .get("IT01234567890_11111.xml.p7m"));
						// byte[] imap = q; // .getBytes();
						// PrintStream out = new PrintStream(new
						// FileOutputStream(
						// "testP7M.txt"));
						// out.print(q);

						P7mUtilities u = new P7mUtilities(q);
						System.out.println(p.getFileName() + " ["
								+ p.getContentType() + "] (" + q.length
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
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	private static byte[] scaricaAllegato(BodyPart p) throws IOException,
			MessagingException, UnsupportedEncodingException {
		Object o = p.getContent();
		if (o instanceof BASE64DecoderStream) {
			BASE64DecoderStream is = (BASE64DecoderStream) o;
			ByteArrayOutputStream mReadBuffer = new ByteArrayOutputStream();
			byte[] b = new byte[1000];
			int tot = 0;
			while ((tot = is.read(b, 0, b.length)) > 0) {
				mReadBuffer.write(b, 0, tot);
			}
			b = mReadBuffer.toByteArray();
			System.out.println("LETTI: " + b.length + " bytes");
			return b;
		} else if (o instanceof String) {
			return ((String) o).getBytes();
		}
		return null;
	}

}