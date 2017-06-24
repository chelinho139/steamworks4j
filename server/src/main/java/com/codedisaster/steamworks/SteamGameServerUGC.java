package com.codedisaster.steamworks;

public class SteamGameServerUGC extends SteamNetworking {

	SteamGameServerUGC(SteamUGCCallback callback) {
		super(SteamGameServerAPINative.getSteamGameServerUGCPointer(),
				SteamGameServerUGCNative.createCallback(new SteamUGCCallbackAdapter(callback)));
	}

}
