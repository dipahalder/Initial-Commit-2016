package me.dipsikha.initialcommit;

import android.app.Application;
import com.parse.Parse;

/**
 * Created by dipsikhahalder on 9/27/15.
 */
public class InitialCommit extends Application {
    public static final String APPLICATION_ID ="ZwZ4axC04V7xU5WnKFGCTJup6OZg7NFtxsGU3bq6";
    public static final String CLIENT_KEY = "TPzhrrZMBLjsW566loSvoZF0FDJMSskWsNVsqcOP";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
    }
}