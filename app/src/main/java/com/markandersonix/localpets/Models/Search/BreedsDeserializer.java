package com.markandersonix.localpets.Models.Search;

/**
 * Created by Mark on 12/7/2016.
 */
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
public class BreedsDeserializer implements JsonDeserializer<Breed>{
    @Override
    public Breeds deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final List<Breed> breed = new List<();
        JsonObject jsonObject = json.getAsJsonObject();
        breed.set$t(jsonObject.get("$t").getAsString());

        return breed;
    }
}
