package nl.pellegroot.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class MenuAdapter extends ArrayAdapter<ArrayList>  {
    private ArrayList<MenuItem> menuItems;
    private int layoutResourceId;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        menuItems = objects;
        layoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        // declaring the textviews and imageview
        TextView name = (TextView) convertView.findViewById(R.id.item_name);
        TextView price = (TextView) convertView.findViewById(R.id.item_price);
        ImageView image = (ImageView) convertView.findViewById(R.id.item_image);

        // getting the menu item
        MenuItem item = (MenuItem) menuItems.get(position);

        // setting the textviews for name and price
        name.setText(item.getName());
        price.setText(String.format(Locale.getDefault(),"€%.0f", item.getPrice()));

        // set image using picasso
        Picasso.get().load(item.getImageUrl()).into(image);

        return convertView;

    }
}
