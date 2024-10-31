package com.example.labactivitieselective1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class myCustomAdapter extends BaseAdapter {
    private Context context;
    private String[] items;

    public myCustomAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length; // Returns the number of items in the source
    }

    @Override
    public Object getItem(int position) {
        return items[position]; // Returns unique identifier for the item at a given position
    }

    @Override
    public long getItemId(int position) {
        return position; // Returns unique identifier for the item at a given position
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Inflate the layout for the spinner item
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.spinnerItemText); // Make sure the ID matches your layout
            convertView.setTag(holder); // Store the holder with the view
        } else {
            // Reuse the recycled view
            holder = (ViewHolder) convertView.getTag();
        }

        // Set the data for the current item
        holder.textView.setText(items[position]);

        return convertView; // Return the completed view to be displayed
    }

    static class ViewHolder {
        // Hold references to the views within the item layout
        TextView textView; // Make sure this ID matches your spinner_item layout
    }
}
