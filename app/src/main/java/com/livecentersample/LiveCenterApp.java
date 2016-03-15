package com.livecentersample;

import android.app.Application;

import com.livecenter.Livecenter;
import com.livecenter.core.model.Match;

/**
 * Created by King on 12/03/16.
 */
public class LiveCenterApp extends Application{

    private static LiveCenterApp instance;
    private Match currentMatch;

    public static LiveCenterApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        Livecenter.initialize(this,"your_consumer_key",
                "your_consumer_secret");
        Livecenter.setLogTag(Constant.APP_TAG);
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }
}
