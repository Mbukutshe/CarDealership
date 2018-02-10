package com.wiseman.cardealership.Services;

import com.wiseman.cardealership.Objects.VehicleObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiseman on 2018-02-02.
 */

public class VehiclesDataset {

    static List<VehicleObject> itemList;

    public static List<VehicleObject> parseData(String content) {

        JSONArray items_arry = null;
        VehicleObject items = null;
        try {

            items_arry = new JSONArray(content);
            itemList = new ArrayList<>();

            for (int i = 0; i < items_arry.length(); i++) {

                JSONObject obj = items_arry.getJSONObject(i);
                items = new VehicleObject(obj.getString("vehicle_price"),obj.getString("vehicle_transmission"),obj.getString("vehicle_mileage"),obj.getString("vehicle_province"),obj.getString("vehicle_features"),obj.getString("vehicle_make")+" "+obj.getString("vehicle_model"),obj.getString("vehicle_link"));
                itemList.add(items);
            }

        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return itemList;
    }
}
