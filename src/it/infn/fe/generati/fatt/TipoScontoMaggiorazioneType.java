//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.11 at 07:13:21 PM CEST 
//


package it.infn.fe.generati.fatt;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoScontoMaggiorazioneType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoScontoMaggiorazioneType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="MG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoScontoMaggiorazioneType")
@XmlEnum
public enum TipoScontoMaggiorazioneType {


    /**
     * 
     * 						SC = Sconto
     * 					
     * 
     */
    SC,

    /**
     * 
     * 						MG = Maggiorazione
     * 					
     * 
     */
    MG;

    public String value() {
        return name();
    }

    public static TipoScontoMaggiorazioneType fromValue(String v) {
        return valueOf(v);
    }

}
