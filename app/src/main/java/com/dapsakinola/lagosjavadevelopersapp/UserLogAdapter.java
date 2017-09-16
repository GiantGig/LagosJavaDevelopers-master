package com.dapsakinola.lagosjavadevelopersapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class UserLogAdapter extends ArrayAdapter<UserLog> {
    private ImageLoader varImageLoader;

    public UserLogAdapter(Context context) {
        super(context, R.layout.users_list_item);

        varImageLoader = new ImageLoader(VolleyApplication.getInstance().getRequestQueue(), new BitmapLruCache());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.users_list_item, parent, false);
        }

        // Users Records that populates the list view
        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.image1);
        TextView textView = (TextView) convertView.findViewById(R.id.text1);
        TextView textView1 = (TextView) convertView.findViewById(R.id.text2);
        TextView textView2 = (TextView) convertView.findViewById(R.id.text3);

        UserLog imageRecord = getItem(position);

        imageView.setImageUrl(imageRecord.getAvatarUrl(), varImageLoader);
        textView.setText(imageRecord.getLogin());
        textView1.setText(imageRecord.getHtmlUrl());
        textView2.setText(imageRecord.getAvatarUrl());


        return convertView;
    }

    public void swapImageRecords(List<UserLog> objects) {
        clear();

        for(UserLog object : objects) {
            add(object);
        }

        notifyDataSetChanged();
    }
}
