package com.example.android.newsfeed.model;

public class Section {

    private String id;
    private String webTitle;
    private String apiUrl;

    public Section(String id, String webTitle, String apiUrl) {
        this.id = id;
        this.webTitle = webTitle;
        this.apiUrl = apiUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
