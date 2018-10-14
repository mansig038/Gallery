package gallery.mansi;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gallery.mansi.adapter.MyAdapter;
import gallery.mansi.adapter.SearchAdapter;
import gallery.mansi.recentImagesModel.ImageList;
import gallery.mansi.searchModel.Photo;
import gallery.mansi.searchModel.Search;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentPhotosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    private Context context;
    private RecyclerView recyclerView;
    private ImageList list;
    private Search searchList;
    private List<Photo> newList;
    private MyAdapter adapter;
    private SearchAdapter searchAdapter;
    private String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_photos);
        context = RecentPhotosActivity.this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.imageList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        getData();

    }

    private void getData() {
        Call<ImageList> imageList = GalleryApi.getImageService().getImageList();
        imageList.enqueue(new Callback<ImageList>() {

            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Fetching Images..", Toast.LENGTH_SHORT).show();
                    list = response.body();
                    adapter = new MyAdapter(context, list.getPhotos().getPhoto());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {
                Toast.makeText(RecentPhotosActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem SearchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(SearchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        userInput = s.toLowerCase();
            newList = new ArrayList<>();

        Call<Search> imageList = GalleryApi.getImageService().searchTitle();
        imageList.enqueue(new Callback<Search>() {

            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.isSuccessful()) {
                    searchList = response.body();
                    for (Photo p : searchList.getPhotos().getPhoto()) {
                        if (p.getTitle().toLowerCase().contains(userInput)) {
                            newList.add(p);
                        }
                    }
                    searchAdapter = new SearchAdapter(context, newList);
                    recyclerView.setAdapter(searchAdapter);
                    Toast.makeText(context, "Search Results..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Toast.makeText(RecentPhotosActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

