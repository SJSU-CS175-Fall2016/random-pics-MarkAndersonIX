
package com.markandersonix.localpets.Models.Search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Breeds {

    private List<Breed> breed;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public Breeds(Breed ... breed){
        this.breed = Arrays.asList(breed);
    }
    /**
     * 
     * @return
     *     The breed
     */
    public List<Breed> getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        String result = "";
        for(Breed b: breed){
            result+=b.get$t().toString()+" ";
        }
        return result;
    }

    /**
     * 
     * @param breed
     *     The breed
     */
    public void setBreed(Breed breed) {
        this.breed.add(breed);
    }
    public void setBreed(List<Breed> breed) {
        this.breed = breed;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
