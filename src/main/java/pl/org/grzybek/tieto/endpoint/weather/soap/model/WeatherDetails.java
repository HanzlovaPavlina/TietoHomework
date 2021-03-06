//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.20 at 01:29:47 AM CEST 
//


package pl.org.grzybek.tieto.endpoint.weather.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeatherDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeatherDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WindDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WindDirection" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Weather" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RelativeHumidity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Temperature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObservationTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeatherDetails", namespace = "http://model.soap.weather.endpoint.tieto.grzybek.org.pl", propOrder = {
    "cityName",
    "windDescription",
    "windDirection",
    "weather",
    "relativeHumidity",
    "temperature",
    "observationTime"
})
public class WeatherDetails {

    @XmlElement(name = "CityName", required = true)
    protected String cityName;
    @XmlElement(name = "WindDescription", required = true)
    protected String windDescription;
    @XmlElement(name = "WindDirection", required = true)
    protected String windDirection;
    @XmlElement(name = "Weather", required = true)
    protected String weather;
    @XmlElement(name = "RelativeHumidity", required = true)
    protected String relativeHumidity;
    @XmlElement(name = "Temperature", required = true)
    protected String temperature;
    @XmlElement(name = "ObservationTime", required = true)
    protected String observationTime;

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the windDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWindDescription() {
        return windDescription;
    }

    /**
     * Sets the value of the windDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWindDescription(String value) {
        this.windDescription = value;
    }

    /**
     * Gets the value of the windDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Sets the value of the windDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWindDirection(String value) {
        this.windDirection = value;
    }

    /**
     * Gets the value of the weather property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeather() {
        return weather;
    }

    /**
     * Sets the value of the weather property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeather(String value) {
        this.weather = value;
    }

    /**
     * Gets the value of the relativeHumidity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    /**
     * Sets the value of the relativeHumidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelativeHumidity(String value) {
        this.relativeHumidity = value;
    }

    /**
     * Gets the value of the temperature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Sets the value of the temperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemperature(String value) {
        this.temperature = value;
    }

    /**
     * Gets the value of the observationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservationTime() {
        return observationTime;
    }

    /**
     * Sets the value of the observationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservationTime(String value) {
        this.observationTime = value;
    }

}
