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

module.exports = new ParsePushNotifications();