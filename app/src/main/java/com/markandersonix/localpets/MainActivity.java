package com.markandersonix.localpets;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.markandersonix.localpets.Models.Favorites.FavoritesDbHelper;
import com.markandersonix.localpets.Models.Search.Breed;
import com.markandersonix.localpets.Models.Search.Breeds;
import com.markandersonix.localpets.Models.Search.BreedsDeserializer;
import com.markandersonix.localpets.Models.Search.Pet;
import com.markandersonix.localpets.Models.Search.SearchData;

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
    @BindView(R.id.pet_recycler_view) RecyclerView petRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Pet> pets;

    @BindString(R.string.application_id) String application_id;
    @BindString(R.string.url_base) String url_base;

    int randPageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pets = new ArrayList<>();
        if(savedInstanceState != null && savedInstanceState.containsKey("pets")){
            pets.addAll( (ArrayList) savedInstanceState.getSerializable("pets"));
            attachRecyclerAdapter();
        }else {
            getPets();
        }
        //load favorites database
        SQLiteDatabase db = new FavoritesDbHelper(this).getWritableDatabase();
        //db.execSQL(FavoritesDbHelper.getSqlDeleteEntries());
        db.execSQL(FavoritesDbHelper.getSqlCreateEntries());
    }
    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction()) &&
                intent.hasExtra("query")) {
            getPets(); //intent.getStringExtra("query"), 1);
        }else {
            getPets();
        }
    }
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Menu handling
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.main_favorites){
            //Toast.makeText(this,"FavoritesActivity",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.main_search){
            //Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
            //onSearchRequested();
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.main_random){
            randPageNumber++;
            //Toast.makeText(this, "Page: "+randPageNumber, Toast.LENGTH_SHORT).show();
            if(!getPets()){
                randPageNumber = 1;
                getPets();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("pets", pets);
    }

    protected boolean getPets(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(customConverterWithBreedDeserializer()) //add modified factory to handle PetFinder API..
                .build();
        PetFinderService service = retrofit.create(PetFinderService.class);
        Call<SearchData> data = service.getListings("95117");
        boolean success = true;
        try {
            data.enqueue(new Callback<SearchData>() {
                @Override
                public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                    SearchData searchData = response.body();
                    Log.e("data:",searchData.toString());
                    List<Pet> petResults = searchData.getPetfinder().getPets().getPet();
                    for(Pet petResult: petResults){
                        pets.add(petResult);
                        Log.e("Pet: ", petResult.getName().get$t());
                    }
                    for(Pet p : pets){
                        Log.e("Pet: ", p.getName().get$t());
                    }
                    petRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    petRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new PetAdapter(getApplicationContext(), pets);
                    petRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onFailure(Call<SearchData> call, Throwable t) {
                    Log.e("data:",t.getMessage());
                }
            });
        }catch(Exception ex){
            Log.e("Exception:","getPets() Exception");
            success = false;
        }
        return success;
    }
    protected void attachRecyclerAdapter(){
        petRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        petRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PetAdapter(getApplicationContext(), pets);
        petRecyclerView.setAdapter(mAdapter);
    }
    //creates a ConverterFactory which handles the abiguous case for PetFinder API breed response.
    private GsonConverterFactory customConverterWithBreedDeserializer(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Breeds.class, new BreedsDeserializer());
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
