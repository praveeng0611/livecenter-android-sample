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
                matchList = result.data;
            }

            @Override
            public void failure(LivecenterException error, int code) {
                Log.i(Constant.APP_TAG,error.getMessage());
            }
        });
```