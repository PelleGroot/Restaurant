package nl.pellegroot.restaurant;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Locale;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // get the intent and extract the menu item
        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("CLICKED_MENU_ITEM");

        // get the textviews and the imageview
        TextView itemName = (TextView) findViewById(R.id.ItemDetailsName);
        TextView itemDescription = (TextView) findViewById(R.id.ItemDetailsDescription);
        TextView itemPrice = (TextView) findViewById(R.id.ItemDetailsPrice);
        ImageView itemImage = (ImageView) findViewById(R.id.ItemDetailsImage);

        // set the menu item texts and image
        itemName.setText(item.getName());
        itemDescription.setText(item.getDescription());
        // format the double so it shows properly in a textview
        itemPrice.setText(String.format(Locale.getDefault(),"€%.0f", item.getPrice()));
        // get the image using picasso
        Picasso.get().load(item.getImageUrl()).into(itemImage);

    }
}
