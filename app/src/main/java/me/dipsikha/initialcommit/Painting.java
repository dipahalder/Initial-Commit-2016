package me.dipsikha.initialcommit;
/**
 * Created by dipsikhahalder on 9/27/15.
 */
public class Painting {
    private String mCaption;
    private String mLocation;
    private String mUrl;

    public Painting(String url, String location, String caption) {
        mCaption = caption;
        mLocation = location;
        mUrl = url;
    }

    public String getCaption() {
        return mCaption;
    }

    public String getLocation() {
        return mLocation;
    }


    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return mCaption;
    }
}
