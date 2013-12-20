var exec = require("cordova/exec");
var ParsePushNotifications = function() {};

ParsePushNotifications.prototype._videos = {};

/*
 * channel - The parse push channel to subscribe this device to
 */
ParsePushNotifications.prototype.subscribe = function (channel) {
    return cordova.exec(
        function (result) {
        },
        function (err) {
            console.error("Failed to subscribe to Parse channel");
        }, "ParsePushNotifications", "subscribe", [channel]);
}

/*
 * channel - The parse push channel to unsubscribe this device from
 */
ParsePushNotifications.prototype.unsubscribe = function (channel) {
    return cordova.exec(
        function (result) {
        },
        function (err) {
            console.error("Failed to unsubscribe from Parse channel");
        }, "ParsePushNotifications", "unsubscribe", [channel]);
}

/*
 * callback - function that will be called with one bool parameter indicating whether or not the app was launched via a notification
 */
ParsePushNotifications.prototype.isAppLaunchedFromNotification = function (callback) {
    return cordova.exec(
        function (result) {
            callback(result);
        },
        function (err) {
            console.error("Failed to get whether or not app was launched from notification");
        }, "ParsePushNotifications", "launchStatus", []);
}

/*
 * data - the data received from the push notification
 */
ParsePushNotifications.prototype._received = function (channel, data) {
    data = JSON.parse(data);

    var event = document.createEvent("CustomEvent");
    event.initCustomEvent("ParsePushReceived", false, false, {
        channel: channel,
        data: data
    });
    window.dispatchEvent(event);
}

module.exports = new ParsePushNotifications();