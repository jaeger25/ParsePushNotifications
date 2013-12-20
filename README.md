# Cordova  Plugin #

### Description ###

This plugin allows for Android phonegap applications to receive push notifications from the Parse.com Push Service.

### Usage ###

### Android Install ###

 ``cordova plugin add https://github.com/jaeger25/ParsePushNotifications.git ``

In (PROJECT_ROOT)\plugins\jaeger.ParsePushNotifications\src\android\ParseApplication.java do the following:

	In the onCreate method, Replace PARSE_APPLICATION_ID and PARSE_CLIENT_KEY with the corresponding keys from your Parse application dashboard

In (PROJECT_ROOT)\platforms\android\AndroidManifest.xml, add the following attribute to your <application> tag
	
	android:name="org.apache.cordova.plugin.ParseApplication"



NOTE: If your application already contains a class that extends Application, you will have to merge your existing class with ParseApplication to make one class and update your AndroidManifest.xml accordingly.
