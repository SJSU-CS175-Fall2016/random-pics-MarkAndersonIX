
package com.markandersonix.localpets.Models.Search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Options {

    private List<Option> option = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The option
     */
    public List<Option> getOption() {
        return option;
    }

    /**
     * 
     * @param option
     *     The option
     */
    public void setOption(List<Option> option) {
        this.option = option;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
