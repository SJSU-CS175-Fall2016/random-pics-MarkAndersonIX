
package com.markandersonix.localpets.Models.Search;

import java.util.HashMap;
import java.util.Map;

public class Petfinder {

    private String xmlnsXsi;
    private LastOffset lastOffset;
    private Pets pets;
    private Header header;
    private String xsiNoNamespaceSchemaLocation;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The xmlnsXsi
     */
    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    /**
     * 
     * @param xmlnsXsi
     *     The @xmlns:xsi
     */
    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }

    /**
     * 
     * @return
     *     The lastOffset
     */
    public LastOffset getLastOffset() {
        return lastOffset;
    }

    /**
     * 
     * @param lastOffset
     *     The lastOffset
     */
    public void setLastOffset(LastOffset lastOffset) {
        this.lastOffset = lastOffset;
    }

    /**
     * 
     * @return
     *     The pets
     */
    public Pets getPets() {
        return pets;
    }

    /**
     * 
     * @param pets
     *     The pets
     */
    public void setPets(Pets pets) {
        this.pets = pets;
    }

    /**
     * 
     * @return
     *     The header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * 
     * @param header
     *     The header
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * 
     * @return
     *     The xsiNoNamespaceSchemaLocation
     */
    public String getXsiNoNamespaceSchemaLocation() {
        return xsiNoNamespaceSchemaLocation;
    }

    /**
     * 
     * @param xsiNoNamespaceSchemaLocation
     *     The @xsi:noNamespaceSchemaLocation
     */
    public void setXsiNoNamespaceSchemaLocation(String xsiNoNamespaceSchemaLocation) {
        this.xsiNoNamespaceSchemaLocation = xsiNoNamespaceSchemaLocation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
