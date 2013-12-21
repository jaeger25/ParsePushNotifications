# Cordova  Plugin #

### Description ###

This plugin allows for Android phonegap applications to receive push notifications from the Parse.com Push Service.

### Usage ###

/*
 * channel - The parse push channel to subscribe this device to
 */
window.plugins.parsePushNotifications.subscribe(channel);


/*
 * channel - The parse push channel to unsubscribe this device from
 */
window.plugins.parsePushNotifications.unsubscribe(channel);

/*
 * callback - function that will be called with one bool parameter indicating whether or not the app was launched via a notification
 */
window.plugins.parsePushNotifications.isAppLaunchedFromNotification(function(launchedFromNotification){
    ...
});

/*
 * callback - function with parameters
 *      channel - the Parse push channel that sent the notification
 *      data - data object containing any extra data sent with the notification
 */
window.plugins.parsePushNotifications.getNotificationInfo(function(channel, data){
    ...
});


Example:

window.addEventListener("deviceready", function(){
    window.plugins.parsePushNotifications.isAppLaunchedFromNotification(function(launchedFromNotification){
    if(launchedFromNotification){
        window.plugins.parsePushNotifications.getNotificationInfo(function(channel, data){
             //Notification launch logic. Use channel and data to do deep linking, etc.
        });
    } else {
        //normal app launch logic
    }
});
}, false);

### Android Install ###

 ``cordova plugin add https://github.com/jaeger25/ParsePushNotifications.git ``

In (PROJECT_ROOT)\plugins\jaeger.ParsePushNotifications\src\android\ParseApplication.java and (PROJECT_ROOT)\platforms\android\src\org\apache\cordova\plugin\ParseApplication.java do the following:

    In the onCreate method, Replace PARSE_APPLICATION_ID and PARSE_CLIENT_KEY with the corresponding keys from your Parse application dashboard

In (PROJECT_ROOT)\platforms\android\AndroidManifest.xml, add the following attribute to your <application> tag
    
    android:name="org.apache.cordova.plugin.ParseApplication"



NOTE: If your application already contains a class that extends Application, you will have to merge your existing class with ParseApplication to make one class and update your AndroidManifest.xml accordingly.
