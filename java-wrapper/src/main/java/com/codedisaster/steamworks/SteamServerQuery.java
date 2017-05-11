package com.codedisaster.steamworks;

/**
 * Handle type you will receive when querying details on an individual server.
 */
public class SteamServerQuery extends SteamNativeHandle {

	static final long ServerQueryInvalid = 0xffffffff;

	SteamServerQuery(long handle) {
		super(handle);
	}

	public boolean isValid() {
		return handle != ServerQueryInvalid;
	}
}