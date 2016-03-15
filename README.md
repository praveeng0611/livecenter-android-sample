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
### Display Liveblog and Social feed View

-By adding LivecenterView in layout.xml file

```xml
    <com.livecenter.ui.LivecenterView
        android:id="@+id/livecenterView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

```java
LivecenterView livecenterView = (LivecenterView) findViewById(R.id.livecenterView);
```

->OR<-