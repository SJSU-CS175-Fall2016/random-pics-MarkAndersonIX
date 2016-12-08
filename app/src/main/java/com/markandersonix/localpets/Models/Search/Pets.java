
package com.markandersonix.localpets.Models.Search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pets {

    private List<Pet> pet = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The pet
     */
    public List<Pet> getPet() {
        return pet;
    }

    /**
     * 
     * @param pet
     *     The pet
     */
    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
