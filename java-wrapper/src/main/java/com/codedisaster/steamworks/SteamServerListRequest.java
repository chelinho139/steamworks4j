package com.codedisaster.steamworks;

/**
 * Handle type you will receive when requesting server list.
 */
public class SteamServerListRequest extends SteamNativeHandle {

	SteamServerListRequest(long handle) {
		super(handle);
	}

	public boolean isValid() {
		return handle != 0;
	}
}