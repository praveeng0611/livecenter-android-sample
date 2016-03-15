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

## Usage

### Integrate With Your Application

package com.example.app;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Example: single kit
        // TwitterAuthConfig authConfig =  new TwitterAuthConfig("consumerKey", "consumerSecret");
        // Fabric.with(this, new TwitterCore(authConfig));

        // Example: multiple kits
        // Fabric.with(this, new TwitterCore(authConfig), new TweetUi());
    }
}
