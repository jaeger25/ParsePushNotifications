package org.apache.cordova.plugin;

import android.app.Application;

import com.parse.*;

import PACKAGE_THAT_CONTAINS_YOUR_ACTIVITY

public class ParseApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		Parse.initialize(this, "PARSE_APPLICATION_ID", "PARSE_CLIENT_KEY");
		PushService.setDefaultPushCallback(this, YOUR_ACTIVITY_CLASS.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
}
