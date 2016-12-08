
package com.markandersonix.localpets.Models.Search;

import java.util.HashMap;
import java.util.Map;

public class Header {

    private Timestamp timestamp;
    private Status_ status;
    private Version version;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Status_ getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status_ status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
