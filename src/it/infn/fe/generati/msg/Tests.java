package it.infn.fe.generati.msg;

import it.infn.fe.generati.FeNamespacePrefixMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

/**
 * 
 */

/**
 * @author turella
 *
 */
public class Tests {

	// protected static Logger logger = Logger.getLogger(Tests.class.getName());

	/**
	 * 
	 */
	public Tests() {
		// TODO Auto-generated constructor stub
	}

	public void decodeXml(File f) {
		try {
			// create a JAXBContext capable of handling classes generated into
			// the com.abhi.xml.jaxb.generated package
			JAXBContext jc = JAXBContext.newInstance("it.infn.fe.generati.msg");

			// create an Unmarshaller
			Unmarshaller u = jc.createUnmarshaller();

			// unmarshal a FosterHome instance document into a tree of Java
			// content
			// objects composed of classes from the com.abhi.xml.jaxb.generated
			// package.
			JAXBElement<?> neElement = (JAXBElement<?>) u
					.unmarshal(new FileInputStream(f.getAbsolutePath()));
			NotificaEsitoType NE = (NotificaEsitoType) neElement.getValue();
			// System.out.println(FH.getFatturaElettronicaHeader().getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getDenominazione());
			Tests.stampaCampi(NE, 0);
			// so on ..you can get all elements based on generated objects

		} catch (JAXBException je) {
			je.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public String encodeXml(Object f) {
		try {
			JAXBContext jc = JAXBContext.newInstance("it.infn.fe.generati.msg");
			Marshaller u = jc.createMarshaller();
			
//			Map<String, String> urisToPrefixes = new HashMap<String, String>();
//			urisToPrefixes.put("http://www.w3.org/2001/XMLSchema", "xsd");
//			urisToPrefixes.put("http://www.w3.org/2000/09/xmldsig#", "ds");
//			urisToPrefixes.put("http://www.fatturapa.gov.it/sdi/messaggi/v1.0",
//					"types");
//			u.setProperty(MarshallerProperties.NAMESPACE_PREFIX_MAPPER, prefixesToUris);

			NamespacePrefixMapper mapper = new FeNamespacePrefixMapper();  
		    u.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", mapper); 
		    
			// unmarshal a FosterHome instance document into a tree of Java
			// content
			// objects composed of classes from the com.abhi.xml.jaxb.generated
			// package.
			OutputStream os = new ByteArrayOutputStream();
			u.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );  
			u.marshal(f, os);
			System.out.println(os.toString());
			return os.toString();
		} catch (JAXBException je) {
			je.printStackTrace();
		}
		return null;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tests t = new Tests();
		// File f = null;
		// f = new File("IT01234567890_11111_NE_001.xml");
		// if (f.exists()) {
		// System.out.println(f.getAbsolutePath());
		// t.decodeXml(f);
		// }
		NotificaEsitoType ne = new NotificaEsitoType();
		ne.setIdentificativoSdI(new BigInteger("9999"));
		ne.setNomeFile("IT99999999999_aaaa.xml");
		{
			NotificaEsitoCommittenteType nec = new NotificaEsitoCommittenteType();
			nec.setIdentificativoSdI(ne.getIdentificativoSdI());
			{
				RiferimentoFatturaType rf = new RiferimentoFatturaType();
				rf.setAnnoFattura(new BigInteger("2014"));
				rf.setNumeroFattura("999 test");
				rf.setPosizioneFattura(new BigInteger("1"));
				nec.setRiferimentoFattura(rf);
			}
			nec.setEsito(EsitoCommittenteType.EC_02);
			nec.setDescrizione("testing da javax mail");
			ne.setEsitoCommittente(nec);
		}
		ne.setNote("testing da javax mail");
		String s = t.encodeXml(ne);
		{
			// SMTP info
			String host = "smtp.gmail.com";
			String port = "587";
			String mailFrom = "infntest@gmail.com";
			String password = "infn2014";

			// message info
			String mailTo = "sysinfo.lnf@lists.lnf.infn.it"; // "turella@lnf.infn.it";
			String subject = "TESTING FE - invio NE - " + (new Date());
			String message = "mi confermate please che riuscite a vedere l'allegato?";

			try {
				sendEmailWithAttachments(host, port, mailFrom, password,
						mailTo, subject, message, s,
						ne.getNomeFile().replace(".xml", "_NE.xml"));
				System.out.println("Email sent.");
			} catch (Exception ex) {
				System.out.println("Could not send email.");
				ex.printStackTrace();
			}
		}
	}

	public static void sendEmailWithAttachments(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message, String attachString,
			String attachName) throws AddressException, MessagingException {
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
		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");
		multipart.addBodyPart(messageBodyPart);
		if (attachString != null) {
			MimeBodyPart attachPart = new MimeBodyPart();
			String s = attachString;
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			System.out.println(s.length() + " chars, ");
			attachPart.setContent(s, "text/xml");
			attachPart.setFileName(attachName);
			attachPart.setContentID(attachName);
			attachPart.setDisposition(Part.ATTACHMENT);
			multipart.addBodyPart(attachPart);
		}
		msg.setContent(multipart);
		Transport.send(msg);
	}

	public static void stampaCampi(Object o, int livello) {
		if (o == null)
			return;
		Class c = o.getClass();
		if (c == Integer.class || c == int.class) {
			for (int i = 0; i < livello + 1; i++)
				System.out.print("\t");
			System.out.println("(" + c.getName() + ") : " + o);
			return;
		}
		if (livello == 0)
			System.out.println("Partenza: " + o.getClass().getName());
		livello++;
		for (Field f : o.getClass().getDeclaredFields()) {
			for (int i = 0; i < livello; i++)
				System.out.print("\t");
			Class<?> ft = f.getType();
			System.out.print(f.getName() + ": (" + ft.getName() + ") ");
			XmlElement x = f.getAnnotation(XmlElement.class);
			// if( f.isAccessible() && f.getName().startsWith("get")) {
			if (ft == List.class) {
				List<?> l;
				try {
					l = (List<?>) f.get(o);
					if (l != null) {
						System.out.println();
						for (int i = 0; i < l.size(); i++) {
							for (int j = 0; j < livello + 1; j++)
								System.out.print("\t");
							System.out
									.println("ELEMENTO " + i + " della lista");
							Tests.stampaCampi(l.get(i), livello);
						}
					} else {
						System.out.println();
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (x != null && ft != String.class && !ft.isEnum()
						&& ft != ElementNSImpl.class
						&& ft != XMLGregorianCalendar.class
						&& ft != BigDecimal.class && ft != BigInteger.class
						&& ft != Integer.class && ft != Double.class
						&& ft != int.class && ft != long.class
						&& ft != char[].class) {
					try {
						System.out.println();
						Tests.stampaCampi(f.get(o), livello);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				} else {
					try {
						// if( f.isAccessible() )
						System.out.println(f.get(o));
						// else
						// System.out.println("non accessibile");
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// e.printStackTrace();
						System.out.println("non accessibile");
					}
				}
			}
		}
	}
}
