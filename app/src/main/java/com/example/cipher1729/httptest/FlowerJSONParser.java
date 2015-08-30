package com.example.cipher1729.httptest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cipher1729 on 8/29/2015.
 */
public class FlowerJSONParser {

    public static List<flowers> parse(String content)
    {
        List<flowers> flowersList= new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(content);
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                flowers newFlower= new flowers();
                newFlower.setProductId(obj.getInt("productId"));
                newFlower.setCategory(obj.getString("category"));
                newFlower.setInstructions(obj.getString("instructions"));
                newFlower.setPrice((float)obj.getDouble("price"));
                newFlower.setPhoto(obj.getString("photo"));
                newFlower.setName(obj.getString("name"));
                flowersList.add(newFlower);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flowersList;
    }
}
