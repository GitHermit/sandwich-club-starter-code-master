package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        List<String> otherNames = new ArrayList<>();
        List<String> ingredient = new ArrayList<>();

        try{
           JSONObject sandwichParser = new JSONObject(json);
           String sandwichDescription = sandwichParser.getString("description");
           String sandwichOrigin = sandwichParser.getString("placeOfOrigin");
           String sandwichImage = sandwichParser.getString("image");
           sandwich.setImage(sandwichImage);
           sandwich.setDescription(sandwichDescription);
           sandwich.setPlaceOfOrigin(sandwichOrigin);

           JSONObject sandwichNames = sandwichParser.getJSONObject("name");
           String mainName = sandwichNames.getString("mainName");
           sandwich.setMainName(mainName);

           JSONArray otherNamesArray = sandwichNames.getJSONArray("alsoKnownAs");
           for (int i = 0; i < otherNamesArray.length();i++) {
                   otherNames.add(otherNamesArray.getString(i));
           }
            JSONArray ingredients = sandwichParser.getJSONArray("ingredients");
            for (int i = 0; i < ingredients.length();i++) {
                ingredient.add(ingredients.getString(i));
            }
            sandwich.setAlsoKnownAs(otherNames);
            sandwich.setIngredients(ingredient);


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JsonUtils", "Problem parsing the sandwich JSON results", e);
        }



        return sandwich;
    }
}
