
package com.markandersonix.localpets.Models.Search;

import java.util.HashMap;
import java.util.Map;

public class SearchData {

    private String encoding;
    private String version;
    private Petfinder petfinder;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 
     * @param encoding
     *     The @encoding
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The @version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The petfinder
     */
    public Petfinder getPetfinder() {
        return petfinder;
    }

    /**
     * 
     * @param petfinder
     *     The petfinder
     */
    public void setPetfinder(Petfinder petfinder) {
        this.petfinder = petfinder;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
