package com.codedisaster.steamworks;

public interface SteamMatchmakingCallback
{
	/**
	 * A server was added/removed from the favorites list, you should refresh now
	 */
	void onFavoritesListChanged(int ip, int queryPort, int connPort, int appID, int flags, boolean add, int accountID);

	/**
	 * Someone has invited you to join a Lobby normally you don't need to do anything with this, since the Steam UI will also display a '<user> has invited you to the lobby, join?' dialog
	 *
	 * if the user outside a game chooses to join, your game will be launched with the parameter "+connect_lobby <64-bit lobby id>", or with the callback onGameLobbyJoinRequested if they're already
	 * in-game
	 */
	void onLobbyInvite(SteamID steamIDUser, SteamID steamIDLobby, long gameID);

	/**
	 * Sent on entering a lobby, or on failing to enter ChatRoomEnterResponse will be set to Success on success, or a higher value on failure
	 *
	 * @see SteamMatchmaking.ChatRoomEnterResponse
	 */
	void onLobbyEnter(SteamID steamIDLobby, int chatPermissions, boolean blocked, SteamMatchmaking.ChatRoomEnterResponse response);

	/**
	 * The lobby metadata has changed if steamIDMember is the steamID of a lobby member, use GetLobbyMemberData() to access per-user details if steamIDMember == steamIDLobby, use GetLobbyData() to
	 * access lobby metadata
	 */
	void onLobbyDataUpdate(SteamID steamIDLobby, SteamID steamIDMember, boolean success);

	/**
	 * The lobby chat room state has changed this is usually sent when a user has joined or left the lobby
	 */
	void onLobbyChatUpdate(SteamID steamIDLobby, SteamID steamIDUserChanged, SteamID steamIDMakingChange, SteamMatchmaking.ChatMemberStateChange stateChange);

	/**
	 * A chat message for this lobby has been sent use GetLobbyChatEntry( chatID ) to retrieve the contents of this message
	 */
	void onLobbyChatMessage(SteamID steamIDLobby, SteamID steamIDUser, SteamMatchmaking.ChatEntryType entryType, int chatID);

	/**
	 * A game created a game for all the members of the lobby to join, as triggered by a SetLobbyGameServer() it's up to the individual clients to take action on this; the usual game behavior is to
	 * leave the lobby and connect to the specified game server
	 */
	void onLobbyGameCreated(SteamID steamIDLobby, SteamID steamIDGameServer, int ip, short port);

	/**
	 * Number of matching lobbies found iterate the returned lobbies with GetLobbyByIndex(), from values 0 to nLobbiesMatching - 1
	 */
	void onLobbyMatchList(int lobbiesMatching);

	/**
	 * Posted if a user is forcefully removed from a lobby can occur if a user loses connection to Steam
	 */
	void onLobbyKicked(SteamID steamIDLobby, SteamID steamIDAdmin, boolean kickedDueToDisconnect);

	/**
	 * Result of our request to create a Lobby result == OK on success at this point, the lobby has been joined and is ready for use a onLobbyEnter callback will also be received (since the local user
	 * is joining their own lobby)
	 */
	void onLobbyCreated(SteamResult result, SteamID steamIDLobby);

	/**
	 * Result of our request to create a Lobby result == OK on success at this point, the lobby has been joined and is ready for use a onLobbyEnter callback will also be received (since the local user
	 * is joining their own lobby)
	 */
	void onFavoritesListAccountsUpdated(SteamResult result);
}