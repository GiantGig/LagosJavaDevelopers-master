package com.dapsakinola.lagosjavadevelopersapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by delaroy on 3/6/17.
 * adapted by akinola dapo on 9/16/2017(mm/dd/yyyy).
 */
public class DetailActivity extends AppCompatActivity {
    private ImageLoader varImageLoader;

    private TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Display of the username
        username = (TextView)findViewById(R.id.text1);

        String data = getIntent().getExtras().getString("login");
        username.setText("GitHub User: " + data);

        //Creating the Link to users Github Page
        String html = getIntent().getExtras().getString("html_url");

        TextView textView = (TextView) findViewById(R.id.textlink);
        textView.setText(html);
        Linkify.addLinks(textView, Linkify.WEB_URLS);

        //Creating User Profile Picture
        String avatar = getIntent().getExtras().getString("avatar_url");

        NetworkImageView imageView = (NetworkImageView) findViewById(R.id.image1);
        varImageLoader = new ImageLoader(VolleyApplication.getInstance().getRequestQueue(), new BitmapLruCache());
        imageView.setImageUrl(avatar, varImageLoader);


    }

    private Intent createShareForecastIntent() {
        String data2 = getIntent().getExtras().getString("html_url");
        String data = getIntent().getExtras().getString("login");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome Lagos based java developer @" + data + ", " + data2)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


}
