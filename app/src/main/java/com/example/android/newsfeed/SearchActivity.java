package com.example.android.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.android.newsfeed.adapter.MyNewsAdapter;
import com.example.android.newsfeed.databinding.ActivitySearchBinding;
import com.example.android.newsfeed.viewmodels.NewsViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String LOG_TAG = SearchActivity.class.getName();
    protected NewsViewModel newsViewModel;
    protected MyNewsAdapter newsAdapter;
    private ActivitySearchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //delete tile and add button back home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        binding.recyclerViewSearch.setLayoutManager(layoutManager);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        observeListNews();

        // Set the color scheme of the SwipeRefreshLayout
        binding.swipeRefreshSearch.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));

        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        binding.swipeRefreshSearch.setOnRefreshListener(() -> {
            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
            // restart the loader
            initiateRefresh("hàng không");

            Toast.makeText(getBaseContext(), getString(R.string.updated_just_now),
                    Toast.LENGTH_SHORT).show();
        });

        newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
        initiateRefresh("");
        // add listener for "tìm nhanh"
        binding.fastKey01.setOnClickListener(this);
        binding.fastKey02.setOnClickListener(this);
        binding.fastKey03.setOnClickListener(this);
        binding.fastKey04.setOnClickListener(this);
        binding.fastKey05.setOnClickListener(this);
        binding.fastKey06.setOnClickListener(this);
        binding.fastKey07.setOnClickListener(this);
        // set text for title fastkey
        binding.fastKey01.setText(" "+getResources().getString(R.string.fastKey01)+" ");
        binding.fastKey02.setText(" "+getResources().getString(R.string.fastKey02)+" ");
        binding.fastKey03.setText(" "+getResources().getString(R.string.fastKey03)+" ");
        binding.fastKey04.setText(" "+getResources().getString(R.string.fastKey04)+" ");
        binding.fastKey05.setText(" "+getResources().getString(R.string.fastKey05)+" ");
        binding.fastKey06.setText(" "+getResources().getString(R.string.fastKey06)+" ");
        binding.fastKey07.setText(" "+getResources().getString(R.string.fastKey07)+" ");
        binding.recyclerViewSearch.setAdapter(newsAdapter);

    }
// set keyword for searching
    private void initiateRefresh(String keyword) {
        newsAdapter.clearAll();
        if(keyword.equals(""))
        {
            keyword = "Vi phạm";
        }
        newsViewModel.getNewsByKeyword(keyword);
    }
// get list view from api
    private void observeListNews() {
        newsViewModel.getListNewsByKeyword().observe(this, news -> {
            if (news != null) {
                newsAdapter.addAll(news);
                binding.swipeRefreshSearch.setRefreshing(false);
                binding.loadingIndicatorSearch.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search_menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        //  auto focus into the text
        searchView.onActionViewExpanded();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("Tìm kiếm tin mới");
        //set listener for search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()>2)
                {
                    Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT).show();
                    // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
                        Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                        // restart the loader
                        newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
                        initiateRefresh(query);
                        binding.recyclerViewSearch.setAdapter(newsAdapter);
                        Toast.makeText(getBaseContext(), getString(R.string.updated_just_now),
                                Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(SearchActivity.this,newText,Toast.LENGTH_SHORT).show();
                // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.

                    Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                    // restart the loader
                    newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
                    initiateRefresh(newText);
                    binding.recyclerViewSearch.setAdapter(newsAdapter);
                    Toast.makeText(getBaseContext(), getString(R.string.updated_just_now),
                            Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == binding.fastKey01) {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey01),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey01));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }else if(view == binding.fastKey02)
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey02),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey02));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }else if(view ==binding.fastKey03)
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey03),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey03));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }else if(view ==binding.fastKey04)
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey04),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey04));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }else if(view ==binding.fastKey05)
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey05),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey05));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }else if(view ==binding.fastKey06)
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey06),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey06));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }else if(view == binding.fastKey07)
        {
            Toast.makeText(getBaseContext(),getResources().getString(R.string.fastKey07),Toast.LENGTH_SHORT).show();
            newsAdapter = new MyNewsAdapter(getBaseContext(), new ArrayList<>());
            initiateRefresh(getResources().getString(R.string.fastKey07));
            binding.recyclerViewSearch.setAdapter(newsAdapter);
        }

    }
}
