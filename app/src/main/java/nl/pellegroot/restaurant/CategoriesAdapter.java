package nl.pellegroot.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter<ArrayList> {
    private ArrayList categories;
    private int layoutResourceId;

    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        categories = objects;
        layoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.textView);

        String categorie = categories.get(position).toString();

        textView.setText(categorie);

        return convertView;
    }
}
