package it.infn.fe.generati;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class FeNamespacePrefixMapper extends NamespacePrefixMapper {

	private Map<String, String> urisToPrefixes;

	@Override
	public String getPreferredPrefix(String uri, String suggestion, boolean requirePrefix) {
		if( urisToPrefixes.containsKey(uri))
			return urisToPrefixes.get(uri);
		return null;
	}

	public FeNamespacePrefixMapper() {
		super();
		urisToPrefixes = new HashMap<String, String>();
		// xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		// xmlns:ds="http://www.w3.org/2000/09/xmldsig#"
		// xmlns:types="http://www.fatturapa.gov.it/sdi/messaggi/v1.0"
		urisToPrefixes.put("http://www.w3.org/2001/XMLSchema", "xsd");
		urisToPrefixes.put("http://www.w3.org/2000/09/xmldsig#", "ds");
		urisToPrefixes.put("http://www.fatturapa.gov.it/sdi/messaggi/v1.0",
				"types");
	}
}
