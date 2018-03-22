package nl.pellegroot.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    @Override
    public void gotCategories(ArrayList<String> categories) {

        ListView listView = (ListView) findViewById(R.id.CatView);
        CategoriesAdapter catAdapter = new CategoriesAdapter(this, R.layout.list_item, categories);
        listView.setAdapter(catAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest catReq = new CategoriesRequest(this);
        catReq.getCategories(this);
    }
}
