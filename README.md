# livecenter-android-sample


## Download
Define via Gradle:
```groovy

repositories {
    maven {
        url  "http://dl.bintray.com/thoughtchimp/maven"
    }
}

dependencies {
    compile 'com.chimps:livecenter:0.0.8'
}
```
## Usage

### Integrate With Your Application
```java
package com.example.app;

import android.app.Application;

public class LiveCenterApp extends Application {
    @Override
        public void onCreate(){
            super.onCreate();
            instance = this;
            //initialize sdk using consumer key and consumer secret
            Livecenter.initialize(this,"your_consumer_key",
                    "your_consumer_secret");
            // define tag to get log
            Livecenter.setLogTag(Constant.APP_TAG);
        }
}
```
### Api Endpoint usages
- Load Match list
```java
LivecenterService service = (new LivecenterApi()).getLiveCenterService();
service.getAllMatches(new Callback<List<Match>>() {
    @Override
    public void success(Result<List<Match>> result) {
        List<Match> matchList = result.data;
    }

    @Override
    public void failure(LivecenterException error, int code) {
        Log.i(Constant.APP_TAG,error.getMessage());
    }
});
```
- Load Match detail and Teams using match id
```java
LivecenterService service = (new LivecenterApi()).getLiveCenterService();
service.getMatchData("match_id", new Callback<MatchData>() {
    @Override
    public void success(Result<MatchData> result) {
        MatchData matchData = result.data;
    }

    @Override
    public void failure(LivecenterException error, int code) {
        Log.i(Constant.APP_TAG,error.getMessage());
    }
});
```
- Load Goal list of match using match id
```java
LivecenterService service = (new LivecenterApi()).getLiveCenterService();
service.getMatchGoals("match_id", new Callback<List<Goal>>() {
    @Override
    public void success(Result<List<Goal>> result) {
        List<Goal> goalList = result.data;
    }

    @Override
    public void failure(LivecenterException error, int code) {
        Log.i(Constant.APP_TAG,error.getMessage());
    }
});
```
- Load Ticker list of match using match id
```java
LivecenterService service = (new LivecenterApi()).getLiveCenterService();
service.getMatchTicker("" + currentMatch.getId(), new Callback<List<MatchTicker>>() {
    @Override
    public void success(Result<List<MatchTicker>> result) {
        List<MatchTicker> matchTickerList = result.data;
    }

    @Override
    public void failure(LivecenterException error, int code) {
        Log.i(Constant.APP_TAG,error.getMessage());
    }
});
```
### Display Liveblog and Social feed View - LivecenterView 

##### By adding LivecenterView in layout.xml file

```xml
    <com.livecenter.ui.LivecenterView
        android:id="@+id/livecenterView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

```java
LivecenterView livecenterView = (LivecenterView) findViewById(R.id.livecenterView);
```
**OR**
#####By Creating object of LivecenterView
```java
LivecenterView livecenterView = new LivecenterView(context);
FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
frameLayout.addView(new LivecenterView(this));
```

#### Loading feed in LivecenterView
```java
livecenterView.loadLiveCenter("match_id", LivecenterType.LiveBlog, feed_frequency);
```
#### Load new feed in LivecenterView
```java
livecenterView.loadNewFeed();
```

#### Getting Callback methods from LivecenterView 
```java
livecenterView.setLivecenterViewListener(new LivecenterViewListener() {
            @Override
            public void onLiveCenterViewLoadingStart() {
                // TODO on feed loading started
            }

            @Override
            public void onLiveCenterViewEmptyPage() {
                // TODO on no feed available
            }

            @Override
            public void onLiveCenterViewLoadingFinished() {
                // TODO on feed loading finished
            }

            @Override
            public void onLiveCenterRefreshed() {
                // TODO on feed data refreshed
            }

            @Override
            public void onLiveCenterViewLoadingError(int errorCode, String message) {
                // TODO on feed loading failed
            }
        });
```