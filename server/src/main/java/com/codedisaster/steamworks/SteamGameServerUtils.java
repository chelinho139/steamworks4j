package com.codedisaster.steamworks;

public class SteamGameServerUtils extends SteamNetworking {

	SteamGameServerUtils(SteamNetworkingCallback callback) {
		super(SteamGameServerAPINative.getSteamGameServerNetworkingPointer(),
				SteamGameServerNetworkingNative.createCallback(new SteamNetworkingCallbackAdapter(callback)));
	}

}
