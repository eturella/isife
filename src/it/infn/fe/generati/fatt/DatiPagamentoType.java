//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.11 at 07:13:21 PM CEST 
//


package it.infn.fe.generati.fatt;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Blocco relativo ai dati di Pagamento della Fattura	Elettronica
 * 			
 * 
 * <p>Java class for DatiPagamentoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiPagamentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CondizioniPagamento" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}CondizioniPagamentoType"/>
 *         &lt;element name="DettaglioPagamento" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}DettaglioPagamentoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiPagamentoType", propOrder = {
    "condizioniPagamento",
    "dettaglioPagamento"
})
public class DatiPagamentoType {

    @XmlElement(name = "CondizioniPagamento", required = true)
    protected CondizioniPagamentoType condizioniPagamento;
    @XmlElement(name = "DettaglioPagamento", required = true)
    protected List<DettaglioPagamentoType> dettaglioPagamento;

    /**
     * Gets the value of the condizioniPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link CondizioniPagamentoType }
     *     
     */
    public CondizioniPagamentoType getCondizioniPagamento() {
        return condizioniPagamento;
    }

    /**
     * Sets the value of the condizioniPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CondizioniPagamentoType }
     *     
     */
    public void setCondizioniPagamento(CondizioniPagamentoType value) {
        this.condizioniPagamento = value;
    }

    /**
     * Gets the value of the dettaglioPagamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dettaglioPagamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDettaglioPagamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DettaglioPagamentoType }
     * 
     * 
     */
    public List<DettaglioPagamentoType> getDettaglioPagamento() {
        if (dettaglioPagamento == null) {
            dettaglioPagamento = new ArrayList<DettaglioPagamentoType>();
        }
        return this.dettaglioPagamento;
    }

}