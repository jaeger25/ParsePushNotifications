package org.apache.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.parse.*;

public class ParsePushReceiver extends CordovaPlugin {
	public final String ACTION_SUBSCRIBE = "subscribe";
	public final String ACTION_UNSUBSCRIBE = "unsubscribe";
	public final String ACTION_LAUNCH_STATUS = "launchStatus";
	public final String ACTION_NOTIFICATION_INFO = "notificationInfo";

	@Override
	public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
		boolean status = true;
		
		if (action.equals(ACTION_SUBSCRIBE)) {
			subscribe(args.getString(0), callbackContext, this.cordova);
		} else if (action.equals(ACTION_UNSUBSCRIBE)) {
			unsubscribe(args.getString(0), callbackContext, this.cordova);
		} else if (action.equals(ACTION_LAUNCH_STATUS)) {
			getLaunchStatus(callbackContext);
		} else if(action.equals(ACTION_NOTIFICATION_INFO)){
			getNotificationInfo(callbackContext);
		} else {
			status = false;
		}
		
		return status;
	}
	
	public void getNotificationInfo(CallbackContext callbackContext) {
		Intent intent = this.cordova.getActivity().getIntent();
		
		String channel = intent.getExtras().getString("com.parse.Channel");
		String data = intent.getExtras().getString("com.parse.Data");
		
		JSONObject result = new JSONObject();
		
		try {
			result.put("channel", channel);
			result.put("data", data);
		} catch (JSONException e) {
			callbackContext.error("Error creating json object");
		}
		
		callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
	}

	public void getLaunchStatus(CallbackContext callbackContext) {
		callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (this.cordova.getActivity() instanceof ParseActivity)));
	}

	public void subscribe(final String channel, final CallbackContext callbackContext, final CordovaInterface cordova) {
		cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				PushService.subscribe(cordova.getActivity(), channel, ParseActivity.class);
				callbackContext.success(); // Thread-safe.
			}
		});
	}

	public void unsubscribe(final String channel, final CallbackContext callbackContext, final CordovaInterface cordova) {
		cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				PushService.unsubscribe(cordova.getActivity(), channel);
				callbackContext.success(); // Thread-safe.
			}
		});
	}
}
