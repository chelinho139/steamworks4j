package com.codedisaster.steamworks;

public class SteamGameServerApps extends SteamNetworking {

	SteamGameServerApps(SteamAppsCallback callback) {
		super(SteamGameServerAPINative.getSteamGameServerAppsPointer(),
				SteamGameServerAppsNative.createCallback(new SteamAppsCallbackAdapter(callback)));
	}

}
