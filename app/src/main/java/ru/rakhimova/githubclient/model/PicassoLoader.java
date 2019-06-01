package ru.rakhimova.githubclient.model;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.rakhimova.githubclient.R;

public class PicassoLoader {

    public PicassoLoader() {
    }

    public void loadImage(String url, ImageView imageView) {
        Picasso
                .get()
                .load(url)
                .placeholder(R.drawable.user_placeholder)
                .into(imageView);
    }
}
