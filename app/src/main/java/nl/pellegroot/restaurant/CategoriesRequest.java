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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Context context;
    public Callback activity;

    // add the callback methods
    public interface Callback{
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // the constructor
    public CategoriesRequest(Context activityContext){
        context = activityContext;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // foward the error message
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray;
        ArrayList<String> arrayList = new ArrayList();
        try {
            // get the data as an jsonArray
            jsonArray = response.getJSONArray("categories");

            // loop through the Jsonarray and put all values in an arraylist
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(jsonArray.getString(i));
            }
        }catch(JSONException e){
                e.printStackTrace();
            }

       activity.gotCategories(arrayList);

    }

    public void getCategories(Callback activityCategories) {
        activity = activityCategories;
        // create a request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // create a JsonObjectRequest And add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }
}
