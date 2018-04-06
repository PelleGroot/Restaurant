package nl.pellegroot.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
String clickedCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // het the intent and stored category
        Intent intent = getIntent();
        clickedCategory = (String) intent.getStringExtra("CLICKED_CATEGORY");

        // request the menu
        MenuRequest menReq = new MenuRequest(this);
        menReq.getMenu(this, clickedCategory);

        // make the listview and set the click listener
        ListView menuView = (ListView) findViewById(R.id.MenuView);
        menuView.setOnItemClickListener(new ListViewItemClicked());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        // set the listview and set the adapter
        ListView listView = (ListView) findViewById(R.id.MenuView);
        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.menu_item, menu);
        listView.setAdapter(menuAdapter);
    }

    @Override
    public void gotMenuError(String message) {
        // create a toast message of the error returned
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class ListViewItemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // create the on item click listener
            MenuItem clickedMenuItem = (MenuItem) adapterView.getItemAtPosition(i);

            // start the new activity and pass the menu item along
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("CLICKED_MENU_ITEM" , clickedMenuItem);
            startActivity(intent);
        }
    }
}
