/*
 * MIT License
 *
 * Copyright (c) 2018 Soojeong Shin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.android.newsfeed.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;

import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.newsfeed.R;
import com.example.android.newsfeed.fragment.CuisineNewsFragment;
import com.example.android.newsfeed.fragment.EntertainmentNewsFragment;
import com.example.android.newsfeed.fragment.FootballInterNewsFragment;
import com.example.android.newsfeed.fragment.FootballVNNewsFragment;
import com.example.android.newsfeed.fragment.HighlightNewsFragment;
import com.example.android.newsfeed.fragment.LawsNewsFragment;
import com.example.android.newsfeed.fragment.NewestNewsFragment;
import com.example.android.newsfeed.fragment.NewsTimeFragment;
import com.example.android.newsfeed.fragment.TechnologyNewsFragment;
import com.example.android.newsfeed.fragment.TravelingNewsFragment;
import com.example.android.newsfeed.fragment.WorldNewsFragment;
import com.example.android.newsfeed.utils.Constants;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {



    /** Context of the app */
    private final Context mContext;

    /**
     * Create a new {@link CategoryFragmentPagerAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     * across swipes.
     */
    public CategoryFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    @NonNull
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.HIGHLIGHT:
                return new HighlightNewsFragment();
            case Constants.NEWEST:
                return new NewestNewsFragment();
            case Constants.NEWSTIME:
                return new NewsTimeFragment();
            case Constants.FOOTBALLVN:
                return new FootballVNNewsFragment();
            case Constants.FOOTBALLINTER:
                return new FootballInterNewsFragment();
            case Constants.ENTERTAINMENT:
                return new EntertainmentNewsFragment();
            case Constants.WORLD:
                return new WorldNewsFragment();
            case Constants.LAW:
                return new LawsNewsFragment();
            case Constants.TECHNOLOGY:
                return new TechnologyNewsFragment();
            case Constants.CUISINE:
                return new CuisineNewsFragment();
            case Constants.TRAVEL:
                return  new TravelingNewsFragment();
            default:
                return null;
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 11;
    }

    /**
     * Return page title of the tap
     */
    @Override
    public CharSequence getPageTitle(int position) {
        int titleResId;
        switch (position) {
            case Constants.HIGHLIGHT:
                titleResId = R.string.nav_title_highlight;
                break;
            case Constants.NEWEST:
                titleResId = R.string.nav_title_new;
                break;
            case Constants.NEWSTIME:
                titleResId = R.string.nav_title_newsTime;
                break;
            case Constants.FOOTBALLVN:
                titleResId = R.string.nav_title_footballVN;
                break;
            case Constants.FOOTBALLINTER:
                titleResId = R.string.nav_title_footballInter;
                break;
            case Constants.ENTERTAINMENT:
                titleResId = R.string.nav_title_entertainment;
                break;
            case Constants.WORLD:
                titleResId = R.string.nav_title_world;
                break;
            case Constants.LAW:
                titleResId = R.string.nav_title_law;
                break;
            case Constants.TECHNOLOGY:
                titleResId = R.string.nav_title_technology;
                break;
            case Constants.CUISINE:
                titleResId = R.string.nav_title_cuisine;
                break;
            default:
                titleResId = R.string.nav_title_travel;
                break;
        }
        return mContext.getString(titleResId);
    }
}