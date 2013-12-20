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
	
	@Override
	public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
		boolean result = false;
		
		if(action.equals(ACTION_SUBSCRIBE)) {
			result = subscribe(args.getString(0));
		}
		else if(action.equals(ACTION_UNSUBSCRIBE)) {
			result = unsubscribe(args.getString(0));
		}
				
		return result;
	}
	
	public boolean subscribe(String channel)
	{
		PushService.subscribe(this.cordova.getActivity(), channel, YOUR_ACTIVITY_CLASS_NAME.class); //TODO: dynamically choose class		
		return true;
	}
	
	public boolean unsubscribe(String channel)
	{
		PushService.unsubscribe(this.cordova.getActivity(), channel);
		return true;
	}
}
