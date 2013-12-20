package org.apache.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;

import com.parse.*;

public class ParsePushReceiver extends CordovaPlugin {
	public final String ACTION_SUBSCRIBE = "subscribe";
	public final String ACTION_UNSUBSCRIBE = "unsubscribe";
	public final String ACTION_LAUNCH_STATUS = "launchStatus";

	@Override
	public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
		if (action.equals(ACTION_SUBSCRIBE)) {
			subscribe(args.getString(0), callbackContext);
		} else if (action.equals(ACTION_UNSUBSCRIBE)) {
			unsubscribe(args.getString(0), callbackContext);
		} else if (action.equals(ACTION_LAUNCH_STATUS)) {
			getLaunchStatus(callbackContext);
		}

		return true;
	}

	public void getLaunchStatus(CallbackContext callbackContext) {
		callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, (this.cordova.getActivity() instanceof ParseActivity)));
	}

	public void subscribe(String channel, CallbackContext callbackContext) {
		cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				PushService.subscribe(this.cordova.getActivity(), channel, ParseActivity.class);
				callbackContext.success(); // Thread-safe.
			}
		});
		return true;
	}

	public void unsubscribe(String channel, CallbackContext callbackContext) {
		cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				PushService.unsubscribe(this.cordova.getActivity(), channel);
				callbackContext.success(); // Thread-safe.
			}
		});
		return true;
	}
}
