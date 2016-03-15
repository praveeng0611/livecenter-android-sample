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
            Livecenter.initialize(this,"your_consumer_key",
                    "your_consumer_secret");
            Livecenter.setLogTag(Constant.APP_TAG);
        }
}
```
