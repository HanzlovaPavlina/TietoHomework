//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.20 at 01:29:47 AM CEST 
//


package pl.org.grzybek.tieto.endpoint.weather.soap.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.org.grzybek.tieto.endpoint.weather.soap.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WeatherDetails_QNAME = new QName("http://model.soap.weather.endpoint.tieto.grzybek.org.pl", "WeatherDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.org.grzybek.tieto.endpoint.weather.soap.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WeatherDetails }
     * 
     */
    public WeatherDetails createWeatherDetails() {
        return new WeatherDetails();
    }

    /**
     * Create an instance of {@link WeatherDetailsRequest }
     * 
     */
    public WeatherDetailsRequest createWeatherDetailsRequest() {
        return new WeatherDetailsRequest();
    }

    /**
     * Create an instance of {@link WeatherDetailsResponse }
     * 
     */
    public WeatherDetailsResponse createWeatherDetailsResponse() {
        return new WeatherDetailsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WeatherDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.soap.weather.endpoint.tieto.grzybek.org.pl", name = "WeatherDetails")
    public JAXBElement<WeatherDetails> createWeatherDetails(WeatherDetails value) {
        return new JAXBElement<WeatherDetails>(_WeatherDetails_QNAME, WeatherDetails.class, null, value);
    }

}
