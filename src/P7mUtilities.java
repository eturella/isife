import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.util.Store;

public class P7mUtilities {

	private static char[] Base64Map = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', '+', '/', '=' };

	// Restituisce il contenuto originale del file
	public static String getXml(byte[] p7m) throws CMSException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		CMSSignedData sdp = new CMSSignedData(p7m);
		CMSProcessable cmsp = sdp.getSignedContent();
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		ObjectOutputStream oStream = new ObjectOutputStream(bStream);
		oStream.writeObject(cmsp.getContent());
		String result = new String((byte[]) cmsp.getContent());
		return result;
	}

	// Verifica la firma.
	@SuppressWarnings("rawtypes")
	public static boolean verifySignature(byte[] sigbytes,
			X509Certificate signercert) {

		Security.addProvider(new BouncyCastleProvider());

		if (isBase64Encoded(sigbytes)) {
			try {
				sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
				sigbytes = dec.decodeBuffer(new String(sigbytes));
			} catch (IOException ioe) {
				System.out.println("Problem decoding from b64");
			}
		}

		try {

			CMSSignedData s = new CMSSignedData(sigbytes);
			Store certs = s.getCertificates();

			SignerInformationStore signers = s.getSignerInfos();
			Collection c = signers.getSigners();
			Iterator it = c.iterator();

			int verified = 0;

			while (it.hasNext()) {
				X509Certificate cert = null;
				SignerInformation signer = (SignerInformation) it.next();
				Collection certCollection = certs.getMatches(null);
				X509CertificateHolder certificateHolder = null;
				if (certCollection.isEmpty() && signercert == null)
					continue;
				else if (signercert != null) // use a signer cert file for
												// verification, if it was
												// provided
					cert = signercert;
				else { // use the certificates included in the signature for
						// verification
					Iterator certIt = certCollection.iterator();
					certificateHolder = (X509CertificateHolder) certIt.next();
					cert = new JcaX509CertificateConverter().setProvider("BC")
							.getCertificate(certificateHolder);
				}

				// if(true)
				// System.out.println("Current certificate " + cert.toString())
				// ;

				SignerInformationVerifier siv = new BcRSASignerInfoVerifierBuilder(
						new DefaultCMSSignatureAlgorithmNameGenerator(),
						new DefaultSignatureAlgorithmIdentifierFinder(),
						new DefaultDigestAlgorithmIdentifierFinder(),
						new BcDigestCalculatorProvider())
						.build(certificateHolder);

				if (signer.verify(siv))
					verified++;
			}

			if (verified == 0)
				return false;
			else if (signercert != null)
				return true;
			else
				return true;

		} catch (Exception ex) {
			System.out
					.println("Couldn't verify included-content CMS signature\n"
							+ ex.toString());
		}

		return false;
	}

	private static final boolean isBase64Encoded(byte[] data) {
		Arrays.sort(Base64Map);
		for (int i = 0; i < data.length; i++) {
			// System.out.println("data[" + i + "] " + (char)data[i]) ;
			if (Arrays.binarySearch(Base64Map, (char) data[i]) < 0
					&& !Character.isWhitespace((char) data[i]))
				return false;
		}
		return true;
	}
}
