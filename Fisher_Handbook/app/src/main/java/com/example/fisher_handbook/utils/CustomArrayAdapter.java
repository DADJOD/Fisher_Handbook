package com.example.fisher_handbook.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fisher_handbook.R;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<ListItemClass> {
    private LayoutInflater inflater;
    private List<ListItemClass> listItem = new ArrayList<>();
    private Context context;

    public CustomArrayAdapter(@NonNull Context context, int resource, List<ListItemClass> listItem, LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItemClass listItemMain = listItem.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item_1, null, false);
            viewHolder = new ViewHolder();
            viewHolder.nameFish = convertView.findViewById(R.id.textViewName);
            viewHolder.secondNameFish = convertView.findViewById(R.id.textViewSecondName);
            viewHolder.image_id = convertView.findViewById(R.id.imageViewItem);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameFish.setText(listItemMain.getNameFish());
        viewHolder.secondNameFish.setText(listItemMain.getSecondNameFish());
        viewHolder.image_id.setImageResource(listItemMain.getImage_id());

        return convertView;
    }

    private class ViewHolder {
        TextView nameFish;
        TextView secondNameFish;
        ImageView image_id;
    }
}
