package com.markandersonix.photoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.markandersonix.photoapp.Models.Photo.Photo;
import com.markandersonix.photoapp.Models.Photo.PhotoAdapter;
import com.markandersonix.photoapp.Models.Photo.PhotoData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.picture) ImageView picture;
    @BindView(R.id.photo_recycler_view) RecyclerView photoRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @BindString(R.string.application_id) String application_id;
    @BindString(R.string.url_photos) String url_photos;
    @BindString(R.string.url_base) String url_base;
    ArrayList<Photo> photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        photos = new ArrayList<>();
        getPhotos();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.main_favorites){
            Toast.makeText(this,"Favorites",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.main_search){
            Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.main_random){
            Toast.makeText(this,"Random",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    protected void getPhotos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UnsplashService service = retrofit.create(UnsplashService.class);
        Call<List<PhotoData>> data = service.listPhotos(application_id);
        try {
            data.enqueue(new Callback<List<PhotoData>>() {
                @Override
                public void onResponse(Call<List<PhotoData>> call, Response<List<PhotoData>> response) {
                    List<PhotoData> photoData = response.body();
                    for(PhotoData data: photoData){
                        photos.add(
                                new Photo(
                                        data.getId(),
                                        data.getUser().getName(),
                                        data.getUser().getUsername(),
                                        data.getUrls().getThumb(),
                                        data.getUrls().getFull()
                                ));
                        //Log.e("PhotoData: ", data.getUser().getUsername());
                    }
                    for(Photo p : photos){
                        Log.e("Photo: ", p.getSmall());
                    }
                    photoRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    photoRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new PhotoAdapter(getApplicationContext(), photos);
                    photoRecyclerView.setAdapter(mAdapter);
//                    photoRecyclerView.addOnItemTouchListener(
//                            new RecyclerView.OnItemTouchListener() {
//                                @Override
//                                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                                    return false;
//                                }
//
//                                @Override
//                                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                                    View child = rv.findChildViewUnder((float)e.getX(),(float)e.getY());
//                                    int position = photoRecyclerView.getChildLayoutPosition(child);
//                                    Photo photo = photos.get(position);
//                                    if(photo != null){
//                                        Intent intent = new Intent(getApplicationContext(), ImageDetail.class);
//                                        intent.putExtra("author",photo.getAuthor());
//                                        intent.putExtra("large",photo.getLarge());
//                                        intent.putExtra("small",photo.getSmall());
//                                        intent.putExtra("url",photo.getUrl());
//                                        intent.putExtra("username",photo.getUsername());
//                                        startActivity(intent);
//                                    }
//                                }
//
//                                @Override
//                                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//                                }
//                            });
//                    Picasso.with(getApplicationContext()).load(photos.get(0).getSmall())
//                            .resize(picture.getWidth(),picture.getHeight()).centerInside()
//                            .into(picture);

                }

                @Override
                public void onFailure(Call<List<PhotoData>> call, Throwable t) {
                    //Picasso.with(getApplicationContext()).load("https://images.unsplash.com/profile-fb-1461218156-c196eaad09a4.jpg").into(picture);
                }
            });
        }catch(Exception ex){}
    }
}
