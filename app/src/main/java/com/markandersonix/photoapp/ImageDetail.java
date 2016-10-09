package com.markandersonix.photoapp;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetail extends AppCompatActivity {

    Bundle bundle;
    @BindView(R.id.detail_image) ImageView detailImage;
    boolean cropped;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        cropped = false;

        detailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundle != null) {
                    String imageUrl = bundle.getString("url");
                    String author = bundle.getString("author");
                    String username = bundle.getString("username");
                    String large = bundle.getString("large");
                    if(cropped) {
                        Picasso.with(getApplicationContext()).load(large)
                                .resize(size.x, size.y)
                                .centerInside()
                                .into(detailImage);
                        cropped = false;
                    }else{
//                        Toast.makeText(getApplicationContext(), Integer.toString(detailImage.getWidth())+" "
//                                +Integer.toString(detailImage.getHeight()),
//                                Toast.LENGTH_LONG).show();
                        Picasso.with(getApplicationContext()).load(large)
                                .resize(detailImage.getWidth(),detailImage.getHeight())
                                .centerCrop()
                                .into(detailImage);
                        cropped = true;
                    }
            }
        }});

        if(bundle != null) {
            String imageUrl = bundle.getString("url");
            String author = bundle.getString("author");
            String username = bundle.getString("username");
            String large = bundle.getString("large");
            Picasso.with(this).load(large).centerInside()
            .resize(size.x,size.y)
            .into(detailImage);
        }

    }

}
