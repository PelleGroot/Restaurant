package nl.pellegroot.restaurant;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Context context;
    public Callback activity;

    public MenuRequest(Context contextMenu) {
        context = contextMenu;
    }

    public interface Callback{
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray;
        ArrayList<MenuItem> menuList = new ArrayList<>();

        try{
            jsonArray = response.getJSONArray("items");

            for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MenuItem item = new MenuItem();

                    // set all the json object attributes to the menu item attributes
                    item.setName(jsonObject.getString("name"));
                    item.setDescription(jsonObject.getString("description"));
                    item.setImageUrl(jsonObject.getString("image_url"));
                    item.setPrice(jsonObject.getDouble("price"));
                    item.setCategory(jsonObject.getString("category"));

                    // add the menu item to the menulist
                    menuList.add(item);
            }
        } catch (JSONException e){
                e.printStackTrace();
            }

        activity.gotMenu(menuList);
    }

    public void getMenu(Callback activityMenu, String category){
        activity = activityMenu;

        // create a request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://resto.mprog.nl/menu?category=" + category;
        // create a JsonObjectRequest And add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }
}
