//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.11 at 07:13:21 PM CEST 
//


package it.infn.fe.generati.fatt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DatiGeneraliDocumentoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiGeneraliDocumentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoDocumento" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}TipoDocumentoType"/>
 *         &lt;element name="Divisa" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}DivisaType"/>
 *         &lt;element name="Data" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}DataFatturaType"/>
 *         &lt;element name="Numero" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}String20Type"/>
 *         &lt;element name="DatiRitenuta" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}DatiRitenutaType" minOccurs="0"/>
 *         &lt;element name="DatiBollo" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}DatiBolloType" minOccurs="0"/>
 *         &lt;element name="DatiCassaPrevidenziale" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}DatiCassaPrevidenzialeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ScontoMaggiorazione" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}ScontoMaggiorazioneType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ImportoTotaleDocumento" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}Amount2DecimalType" minOccurs="0"/>
 *         &lt;element name="Arrotondamento" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}Amount2DecimalType" minOccurs="0"/>
 *         &lt;element name="Causale" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}String200LatinType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Art73" type="{http://www.fatturapa.gov.it/sdi/fatturapa/v1.1}Art73Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiGeneraliDocumentoType", propOrder = {
    "tipoDocumento",
    "divisa",
    "data",
    "numero",
    "datiRitenuta",
    "datiBollo",
    "datiCassaPrevidenziale",
    "scontoMaggiorazione",
    "importoTotaleDocumento",
    "arrotondamento",
    "causale",
    "art73"
})
public class DatiGeneraliDocumentoType {

    @XmlElement(name = "TipoDocumento", required = true)
    protected TipoDocumentoType tipoDocumento;
    @XmlElement(name = "Divisa", required = true)
    protected String divisa;
    @XmlElement(name = "Data", required = true)
    protected XMLGregorianCalendar data;
    @XmlElement(name = "Numero", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numero;
    @XmlElement(name = "DatiRitenuta")
    protected DatiRitenutaType datiRitenuta;
    @XmlElement(name = "DatiBollo")
    protected DatiBolloType datiBollo;
    @XmlElement(name = "DatiCassaPrevidenziale")
    protected List<DatiCassaPrevidenzialeType> datiCassaPrevidenziale;
    @XmlElement(name = "ScontoMaggiorazione")
    protected List<ScontoMaggiorazioneType> scontoMaggiorazione;
    @XmlElement(name = "ImportoTotaleDocumento")
    protected BigDecimal importoTotaleDocumento;
    @XmlElement(name = "Arrotondamento")
    protected BigDecimal arrotondamento;
    @XmlElement(name = "Causale")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected List<String> causale;
    @XmlElement(name = "Art73")
    protected Art73Type art73;

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoType }
     *     
     */
    public TipoDocumentoType getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoType }
     *     
     */
    public void setTipoDocumento(TipoDocumentoType value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the divisa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * Sets the value of the divisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivisa(String value) {
        this.divisa = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the datiRitenuta property.
     * 
     * @return
     *     possible object is
     *     {@link DatiRitenutaType }
     *     
     */
    public DatiRitenutaType getDatiRitenuta() {
        return datiRitenuta;
    }

    /**
     * Sets the value of the datiRitenuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiRitenutaType }
     *     
     */
    public void setDatiRitenuta(DatiRitenutaType value) {
        this.datiRitenuta = value;
    }

    /**
     * Gets the value of the datiBollo property.
     * 
     * @return
     *     possible object is
     *     {@link DatiBolloType }
     *     
     */
    public DatiBolloType getDatiBollo() {
        return datiBollo;
    }

    /**
     * Sets the value of the datiBollo property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiBolloType }
     *     
     */
    public void setDatiBollo(DatiBolloType value) {
        this.datiBollo = value;
    }

    /**
     * Gets the value of the datiCassaPrevidenziale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiCassaPrevidenziale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiCassaPrevidenziale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiCassaPrevidenzialeType }
     * 
     * 
     */
    public List<DatiCassaPrevidenzialeType> getDatiCassaPrevidenziale() {
        if (datiCassaPrevidenziale == null) {
            datiCassaPrevidenziale = new ArrayList<DatiCassaPrevidenzialeType>();
        }
        return this.datiCassaPrevidenziale;
    }

    /**
     * Gets the value of the scontoMaggiorazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scontoMaggiorazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScontoMaggiorazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScontoMaggiorazioneType }
     * 
     * 
     */
    public List<ScontoMaggiorazioneType> getScontoMaggiorazione() {
        if (scontoMaggiorazione == null) {
            scontoMaggiorazione = new ArrayList<ScontoMaggiorazioneType>();
        }
        return this.scontoMaggiorazione;
    }

    /**
     * Gets the value of the importoTotaleDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotaleDocumento() {
        return importoTotaleDocumento;
    }

    /**
     * Sets the value of the importoTotaleDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotaleDocumento(BigDecimal value) {
        this.importoTotaleDocumento = value;
    }

    /**
     * Gets the value of the arrotondamento property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArrotondamento() {
        return arrotondamento;
    }

    /**
     * Sets the value of the arrotondamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArrotondamento(BigDecimal value) {
        this.arrotondamento = value;
    }

    /**
     * Gets the value of the causale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the causale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCausale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCausale() {
        if (causale == null) {
            causale = new ArrayList<String>();
        }
        return this.causale;
    }

    /**
     * Gets the value of the art73 property.
     * 
     * @return
     *     possible object is
     *     {@link Art73Type }
     *     
     */
    public Art73Type getArt73() {
        return art73;
    }

    /**
     * Sets the value of the art73 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Art73Type }
     *     
     */
    public void setArt73(Art73Type value) {
        this.art73 = value;
    }

}
