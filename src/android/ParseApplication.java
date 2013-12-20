package org.apache.cordova.plugin;

import android.app.Application;

import com.parse.*;

public class ParseApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		Parse.initialize(this, "PARSE_APPLICATION_ID", "PARSE_CLIENT_KEY");
		PushService.setDefaultPushCallback(this, ParseActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}
}
