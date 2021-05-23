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

package com.example.android.newsfeed.utils;

/**
 * Store Constants for the NewsFeed app.
 */

public class Constants {

    /**
     * Create a private constructor because no one should ever create a {@link Constants} object.
     */
    private Constants() {
    }

    /**  Extract the key associated with the JSONObject */
    public static final String JSON_KEY_FACEBOOK_ID = "id";
    public static final String JSON_KEY_FACEBOOK_NAME = "name";
    public static final String JSON_KEY_FACEBOOK_PICTURE = "picture";
    public static final String JSON_KEY_FACEBOOK_DATA = "data";
    public static final String JSON_KEY_FACEBOOK_URL = "url";

    /** URL for news data from the guardian data set */
    public static final String FACEBOOK_REQUEST_URL = "https://graph.facebook.com";

    /** Parameters */
    public static final String FACEBOOK_FIELD_PARAM = "fields";

    /** Facebook permissions */
    public static final String FACEBOOK_PERMISSION_PUBLIC_PROFILE = "public_profile";
    public static final String FACEBOOK_PERMISISON_EMAIL = "email";

    /** Share preferences key */
    public static final String SHARE_PREFERENCE_KEY_TOKEN = "token";

    /** Intent extra key */
    public static final String INTENT_EXTRA_NEWS = "news";

}
