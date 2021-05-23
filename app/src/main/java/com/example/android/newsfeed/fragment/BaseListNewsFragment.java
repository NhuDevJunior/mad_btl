package com.example.android.newsfeed.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.newsfeed.R;
import com.example.android.newsfeed.adapter.MyNewsAdapter;
import com.example.android.newsfeed.databinding.FragmentListNewsBinding;
import com.example.android.newsfeed.model.News;
import com.example.android.newsfeed.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseListNewsFragment extends Fragment {

    private static final String LOG_TAG = BaseListNewsFragment.class.getName();
    protected FragmentListNewsBinding binding;
    protected NewsViewModel newsViewModel;
    protected MyNewsAdapter newsAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public BaseListNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListNewsBinding.inflate(inflater, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        newsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
        observeListNews();

        // Set the color scheme of the SwipeRefreshLayout
        binding.swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));

        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        binding.swipeRefresh.setOnRefreshListener(() -> {
            Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
            // restart the loader
            initiateRefresh();
            Toast.makeText(getActivity(), getString(R.string.updated_just_now),
                    Toast.LENGTH_SHORT).show();
        });

        newsAdapter = new MyNewsAdapter(getActivity(), new ArrayList<>());
        initiateRefresh();
        binding.recyclerView.setAdapter(newsAdapter);

        return binding.getRoot();
    }

    /**
     * Get the list news of the current tab
     * @return listNews
     */
    protected abstract List<News> getListNews();

    /**
     * Set up observer for the newsViewModel of the current tab
     */
    protected abstract void observeListNews();

    /**
     * Reload the list news of the current tab
     */
    protected abstract void initiateRefresh();
}