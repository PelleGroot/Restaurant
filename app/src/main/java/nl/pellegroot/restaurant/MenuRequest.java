package nl.pellegroot.restaurant;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

        activity.gotMenu();
    }

    public void getMenu(Callback activityMenu){
        activity = activityMenu;

        // create a request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // create a JsonObjectRequest And add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);
    }
}
