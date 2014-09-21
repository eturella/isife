package it.infn.fe.generati.fatt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

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
		// try {
		// byte[] encoded = Files.readAllBytes(f.toPath());
		// String invoiceXMLString = new String(encoded);
		// Invoice invoice = InvoiceXmlToJava.xmlToJava(invoiceXMLString);
		// System.out.println(invoice.getSupplier().getSupplierName());
		// } catch (XPathExpressionException x) {
		// // logger.error(x.getMessage());
		// x.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {
			// create a JAXBContext capable of handling classes generated into
			// the com.abhi.xml.jaxb.generated package
			JAXBContext jc = JAXBContext
					.newInstance("it.infn.fe.generati.fatt");

			// create an Unmarshaller
			Unmarshaller u = jc.createUnmarshaller();

			// unmarshal a FosterHome instance document into a tree of Java
			// content
			// objects composed of classes from the com.abhi.xml.jaxb.generated
			// package.
			JAXBElement<?> fhElement = (JAXBElement<?>) u
					.unmarshal(new FileInputStream(f.getAbsolutePath()));
			FatturaElettronicaType FH = (FatturaElettronicaType) fhElement
					.getValue();
			// System.out.println(FH.getFatturaElettronicaHeader().getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getDenominazione());
			Tests.stampaCampi(FH, 0);
			// so on ..you can get all elements based on generated objects

		} catch (JAXBException je) {
			je.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tests t = new Tests();
		File f = null;
		f = new File("IT01234567890_11111.xml");
		if (f.exists()) {
			System.out.println(f.getAbsolutePath());
			t.decodeXml(f);
		}
		f = new File("IT01234567890_22222.xml");
		if (f.exists()) {
			System.out.println(f.getAbsolutePath());
//			t.decodeXml(f);
		}
		f = new File("IT01234567890_33333.xml");
		if (f.exists()) {
			System.out.println(f.getAbsolutePath());
	//		t.decodeXml(f);
		}
	}

	public static void stampaCampi(Object o, int livello) {
		if (o == null)
			return;
		Class c = o.getClass();
		if( c == Integer.class || c == int.class) {
			for (int i = 0; i < livello+1; i++)
				System.out.print("\t");
			System.out.println("("+c.getName()+") : "+o);
			return;
		}
		if (livello == 0)
			System.out.println("Partenza: " + o.getClass().getName());
		livello++;
		for (Field f : o.getClass().getDeclaredFields()) {
			for (int i = 0; i < livello; i++)
				System.out.print("\t");
			Class<?> ft = f.getType();
			System.out
					.print(f.getName() + ": (" + ft.getName() + ") ");
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
						&& ft != XMLGregorianCalendar.class && ft != BigDecimal.class && ft != Integer.class && ft != Double.class && ft != int.class && ft != char[].class) {
					try {
						System.out.println();
						Tests.stampaCampi(f.get(o), livello);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				} else {
					try {
						System.out.println(f.get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
