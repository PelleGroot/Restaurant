package nl.pellegroot.restaurant;

import android.content.Context;
import android.util.Log;
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

    // the constructor
    public CategoriesRequest(Context context) {
        // TODO: I feel like I'm not doing something right here.
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // TODO: Make a clear and working error message
        Log.d("onErrorResponse:", "Error");
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = null;
        ArrayList<String> arrayList = new ArrayList();
        try {
            // transform the response to an arraylist
            jsonArray = response.getJSONArray("categories");
        } catch(JSONException e){
            e.printStackTrace();
        }
        for(int i=0; i<jsonArray.length(); i++){
            try{
                arrayList.add(jsonArray.getString(i));
            }catch(JSONException e){
                e.printStackTrace();
            }
            Log.d("ArrayList :", "" + arrayList.get(i));
        }
    }

    // add the callback methods
    public interface Callback{
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public void getCategories(Callback activity) {
        // create a request queue
        RequestQueue queue = Volley.newRequestQueue(context);
        // create a JsonObjectRequest And add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }
}
