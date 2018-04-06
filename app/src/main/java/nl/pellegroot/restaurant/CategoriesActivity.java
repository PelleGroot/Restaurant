package nl.pellegroot.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    @Override
    public void gotCategories(ArrayList<String> categories) {
        // set the listview and set the adapter
        ListView listView = (ListView) findViewById(R.id.CatView);
        CategoriesAdapter catAdapter = new CategoriesAdapter(this, R.layout.category_item, categories);
        listView.setAdapter(catAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        // create a toast message from the error returned
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // create a new json request
        CategoriesRequest catReq = new CategoriesRequest(this);
        catReq.getCategories(this);

        // set the listview and the onclick listener
        ListView LV = (ListView) findViewById(R.id.CatView);
        LV.setOnItemClickListener(new ListViewItemClicked());
    }

    private class ListViewItemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // create the onclick listener
            String clickedCategory = (String) adapterView.getItemAtPosition(i);

            // create intent and start the new activity passing along the clicked category
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("CLICKED_CATEGORY" , clickedCategory);
            startActivity(intent);
        }
    }
}
