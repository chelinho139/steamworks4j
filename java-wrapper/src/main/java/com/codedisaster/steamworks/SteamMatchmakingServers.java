package com.codedisaster.steamworks;

/** The filter operation codes that go in the key part of SteamMatchMakingKeyValuePair should be one of these:
<p>
"map"
<blockquote>	- Server passes the filter if the server is playing the specified map.</blockquote>
"gamedataand"
<blockquote>
- Server passes the filter if the server's game data (ISteamGameServer::SetGameData) contains all of the specified strings. The value field is a comma-delimited list of strings to match.
</blockquote>
"gamedataor"
<blockquote>
	- Server passes the filter if the server's game data (ISteamGameServer::SetGameData) contains at least one of the
	specified strings.  The value field is a comma-delimited list of strings to match.
</blockquote>
"gamedatanor"
<blockquote>
	- Server passes the filter if the server's game data (ISteamGameServer::SetGameData) does not contain any
	of the specified strings.  The value field is a comma-delimited list of strings to check.
</blockquote>
"gametagsand"
<blockquote>
	- Server passes the filter if the server's game tags (ISteamGameServer::SetGameTags) contains all
	of the specified strings.  The value field is a comma-delimited list of strings to check.
</blockquote>
"gametagsnor"
<blockquote>
	- Server passes the filter if the server's game tags (ISteamGameServer::SetGameTags) does not contain any
	of the specified strings.  The value field is a comma-delimited list of strings to check.
</blockquote>
"and" (x1 && x2 && ... && xn)
<br>"or" (x1 || x2 || ... || xn)
<br>"nand" !(x1 && x2 && ... && xn)
<br>"nor" !(x1 || x2 || ... || xn)
<blockquote>
	- Performs Boolean operation on the following filters.  The operand to this filter specifies
	the "size" of the Boolean inputs to the operation, in Key/value pairs.  (The keyvalue
	pairs must immediately follow, i.e. this is a prefix logical operator notation.)
	In the simplest case where Boolean expressions are not nested, this is simply
	the number of operands.
<br>

	For example, to match servers on a particular map or with a particular tag, would would
	use these filters.
<blockquote>
		( server.map == "cp_dustbowl" || server.gametags.contains("payload") )
		<br>"or", "2"
		<br>"map", "cp_dustbowl"
		<br>"gametagsand", "payload"
</blockquote>
	If logical inputs are nested, then the operand specifies the size of the entire
	"length" of its operands, not the number of immediate children.
<blockquote>
		( server.map == "cp_dustbowl" || ( server.gametags.contains("payload") && !server.gametags.contains("payloadrace") ) )
		<br>"or", "4"
		<br>"map", "cp_dustbowl"
		<br>"and", "2"
		<br>"gametagsand", "payload"
		<br>"gametagsnor", "payloadrace"
</blockquote>
	Unary NOT can be achieved using either "nand" or "nor" with a single operand.
</blockquote>
"addr"
<blockquote>
	- Server passes the filter if the server's query address matches the specified IP or IP:port.
</blockquote>
"gameaddr"
<blockquote>
	- Server passes the filter if the server's game address matches the specified IP or IP:port.
</blockquote>
</p>
<b>The following filter operations ignore the "value" part of SteamMatchMakingKeyValuePair</b>
<p>
"dedicated"
<blockquote>
	- Server passes the filter if it passed true to SetDedicatedServer.
</blockquote>
"secure"
<blockquote>
	- Server passes the filter if the server is VAC-enabled.
</blockquote>
"notfull"
<blockquote>
	- Server passes the filter if the player count is less than the reported max player count.
</blockquote>
"hasplayers"
<blockquote>
	- Server passes the filter if the player count is greater than zero.
</blockquote>
"noplayers"
<blockquote>
	- Server passes the filter if it doesn't have any players.
</blockquote>
"linux"
<blockquote>
	- Server passes the filter if it's a linux server
</blockquote>
</p>
**/
public class SteamMatchmakingServers extends SteamInterface
{
	public static class GameServerItem
	{
		private int appID;
		private int botPlayers;
		private boolean doNotRefresh;
		private String gameDescription;
		private String gameDir;
		private String gameTags;
		private boolean hadSuccessfulResponse;
		private int ip;
		private String map;
		private int maxPlayers;
		private boolean password;
		private int ping;
		private int players;
		private short port;
		private boolean secure;
		private String serverName;
		private int serverVersion;
		private long steamID;
		private int timeLastPlayed;

		/**
		 * Server is marked as not responding and should no longer be refreshed
		 */
		public boolean doNotRefresh()
		{
			return doNotRefresh;
		}

		/**
		 * Steam AppID of this server
		 */
		public int getAppID()
		{
			return appID;
		}

		/**
		 * Number of bots (i.e simulated players) on this server
		 */
		public int getBotPlayers()
		{
			return botPlayers;
		}

		/**
		 * Game description
		 */
		public String getGameDescription()
		{
			return gameDescription;
		}

		/**
		 * Current game directory
		 */
		public String getGameDir()
		{
			return gameDir;
		}

		/**
		 * The tags this server exposes
		 */
		public String getGameTags()
		{
			return gameTags;
		}

		/**
		 * The IP address of the game server
		 */
		public int getIP()
		{
			return ip;
		}

		/**
		 * The IP and port address of the game server as String
		 */
		public String getIPAddressAsString()
		{
			return (ip >>> 24 & 0xff) + "." + (ip >>> 16 & 0xff) + "." + (ip >>> 8 & 0xff) + "." + (ip & 0xff) + ":" + port;
		}

		/**
		 * Current map
		 */
		public String getMap()
		{
			return map;
		}

		/**
		 * Maximum players that can join this server
		 */
		public int getMaxPlayers()
		{
			return maxPlayers;
		}

		/**
		 * Game server name
		 */
		public String getName()
		{
			// Use the IP address as the name if nothing is set yet.
			if (serverName == null || serverName.isEmpty())
			{
				return getIPAddressAsString();
			}
			else
			{
				return serverName;
			}
		}

		/**
		 * Current ping time in milliseconds
		 */
		public int getPing()
		{
			return ping;
		}

		/**
		 * Total number of players currently on the server. INCLUDES BOTS!!
		 */
		public int getPlayers()
		{
			return players;
		}

		/**
		 * The port of the game server
		 */
		public short getPort()
		{
			return port;
		}

		/**
		 * Server version as reported to Steam
		 */
		public int getServerVersion()
		{
			return serverVersion;
		}

		/**
		 * SteamID of the game server - invalid if it's doesn't have one (old server, or not connected to Steam)
		 */
		public SteamID getSteamID()
		{
			return new SteamID(steamID);
		}

		/**
		 * Time (in unix time) when this server was last played on (for favorite/history servers)
		 */
		public int getTimeLastPlayed()
		{
			return timeLastPlayed;
		}

		/**
		 * Server has responded successfully in the past
		 */
		public boolean hadSuccessfulResponse()
		{
			return hadSuccessfulResponse;
		}

		/**
		 * True if this server needs a password to join
		 */
		public boolean isPassword()
		{
			return password;
		}

		/**
		 * Is this server protected by VAC
		 */
		public boolean isSecure()
		{
			return secure;
		}
	}

	/**
	 * Callback interface for receiving responses after pinging an individual server
	 * <p>
	 * These callbacks all occur in response to querying an individual server via the ISteamMatchmakingServers()->PingServer() call below. If you are destructing an object that implements this
	 * interface then you should call ISteamMatchmakingServers()->CancelServerQuery() passing in the handle to the query which is in progress. Failure to cancel in progress queries when destructing a
	 * callback handler may result in a crash when a callback later occurs.
	 * </p>
	 */
	public interface ISteamMatchmakingPingResponse
	{
		/**
		 * Server has responded successfully and has updated data
		 */
		public void onServerResponded(GameServerItem server);

		/**
		 * Server failed to respond to the ping request
		 */
		public void onServerFailedToRespond();
	}

	/**
	 * Callback interface for receiving responses after requesting details on who is playing on a particular server.
	 * <p>
	 * These callbacks all occur in response to querying an individual server via the ISteamMatchmakingServers()->PlayerDetails() call below. If you are destructing an object that implements this
	 * interface then you should call ISteamMatchmakingServers()->CancelServerQuery() passing in the handle to the query which is in progress. Failure to cancel in progress queries when destructing a
	 * callback handler may result in a crash when a callback later occurs.
	 * </p>
	 */
	public interface ISteamMatchmakingPlayersResponse
	{
		/**
		 * Got data on a new player on the server -- you'll get this callback once per player on the server which you have requested player data on.
		 */
		public void AddPlayerToList( String name, int score, float timePlayed );

		/**
		 * The server failed to respond to the request for player details
		 */
		public void PlayersFailedToRespond();

		/**
		 * The server has finished responding to the player details request (ie, you won't get anymore AddPlayerToList callbacks)
		 */
		public void PlayersRefreshComplete();
	}

	/**
	 * Callback interface for receiving responses after requesting rules details on a particular server.
	 * <p>
	 * These callbacks all occur in response to querying an individual server via the ISteamMatchmakingServers()->ServerRules() call below. If you are destructing an object that implements this
	 * interface then you should call ISteamMatchmakingServers()->CancelServerQuery() passing in the handle to the query which is in progress. Failure to cancel in progress queries when destructing a
	 * callback handler may result in a crash when a callback later occurs.
	 * </p>
	 */
	public interface ISteamMatchmakingRulesResponse
	{
		/**
		 * Got data on a rule on the server -- you'll get one of these per rule defined on the server you are querying
		 */
		public void RulesResponded( String rule, String value );

		/**
		 * The server failed to respond to the request for rule details
		 */
		public void RulesFailedToRespond();

		/**
		 * The server has finished responding to the rule details request (ie, you won't get anymore RulesResponded callbacks)
		 */
		public void RulesRefreshComplete();
	}

	public SteamMatchmakingServers()
	{
		super(SteamAPI.getSteamMatchmakingPointer());
	}

	// Request a new list of servers of a particular type. These calls each correspond to one of the EMatchMakingType values.
	// Each call allocates a new asynchronous request object.
	// Request object must be released by calling ReleaseRequest( SteamServerListRequest )
//	public SteamServerListRequest requestInternetServerList( int iApp, ARRAY_COUNT(nFilters) SteamMatchMakingKeyValuePair **ppchFilters, uint32 nFilters, ISteamMatchmakingServerListResponse *pRequestServersResponse ) {
//	}
//
//	public SteamServerListRequest requestLANServerList( int iApp, ISteamMatchmakingServerListResponse *pRequestServersResponse ) {
//	}
//
//	public SteamServerListRequest requestFriendsServerList( int iApp, ARRAY_COUNT(nFilters) SteamMatchMakingKeyValuePair **ppchFilters, uint32 nFilters, ISteamMatchmakingServerListResponse *pRequestServersResponse ) {
//	}
//
//	public SteamServerListRequest requestFavoritesServerList( int iApp, ARRAY_COUNT(nFilters) SteamMatchMakingKeyValuePair **ppchFilters, uint32 nFilters, ISteamMatchmakingServerListResponse *pRequestServersResponse ) {
//	}
//
//	public SteamServerListRequest requestHistoryServerList( int iApp, ARRAY_COUNT(nFilters) SteamMatchMakingKeyValuePair **ppchFilters, uint32 nFilters, ISteamMatchmakingServerListResponse *pRequestServersResponse ) {
//	}
//
//	public SteamServerListRequest requestSpectatorServerList( int iApp, ARRAY_COUNT(nFilters) SteamMatchMakingKeyValuePair **ppchFilters, uint32 nFilters, ISteamMatchmakingServerListResponse *pRequestServersResponse ) {
//	}

	/**
	 * Releases the asynchronous request object and cancels any pending query on it if there's a pending query in progress. RefreshComplete callback is not posted when request is released.
	 */
	public void releaseRequest(SteamServerListRequest SteamServerListRequest)
	{
	}

	/**
	 * Get details on a given server in the list, you can get the valid range of index values by calling GetServerCount(). You will also receive index values in
	 * ISteamMatchmakingServerListResponse::ServerResponded() callbacks
	 */
	public GameServerItem getServerDetails(SteamServerListRequest request, int serverIndex)
	{
		return null;
	}

	/**
	 * Cancel an request which is operation on the given list type. You should call this to cancel any in-progress requests before destructing a callback object that may have been passed to one of the
	 * above list request calls. Not doing so may result in a crash when a callback occurs on the destructed object. Canceling a query does not release the allocated request handle. The request handle
	 * must be released using ReleaseRequest( hRequest )
	 */
	public void cancelQuery(SteamServerListRequest hRequest)
	{
	}

	/**
	 * Ping every server in your list again but don't update the list of servers Query callback installed when the server list was requested will be used again to post notifications and
	 * RefreshComplete, so the callback must remain valid until another RefreshComplete is called on it or the request is released with ReleaseRequest( hRequest )
	 */
	public void refreshQuery(SteamServerListRequest hRequest)
	{
	}

	/**
	 * Returns true if the list is currently refreshing its server list
	 */
	public boolean isRefreshing(SteamServerListRequest hRequest)
	{
		return false;
	}

	/**
	 * How many servers in the given list, GetServerDetails above takes 0... GetServerCount() - 1
	 */
	public int getServerCount(SteamServerListRequest hRequest)
	{
		return 0;
	}

	/**
	 * Refresh a single server inside of a query (rather than all the servers )
	 */
	public void refreshServer(SteamServerListRequest hRequest, int iServer)
	{
	}

	// -----------------------------------------------------------------------------
	// Queries to individual servers directly via IP/Port
	// -----------------------------------------------------------------------------

	/**
	 * Request updated ping time and other details from a single server
	 */
	public SteamServerQuery pingServer(int ip, short port, ISteamMatchmakingPingResponse requestServersResponse)
	{
		return new SteamServerQuery(pingServer(pointer, ip, port, requestServersResponse));
	}

	/**
	 * Request the list of players currently playing on a server
	 */
	public SteamServerQuery playerDetails(int ip, short port, ISteamMatchmakingPlayersResponse requestServersResponse)
	{
		return null;
	}

	/**
	 * Request the list of rules that the server is running (See ISteamGameServer::SetKeyValue() to set the rules server side)
	 */
	public SteamServerQuery serverRules(int ip, short port, ISteamMatchmakingRulesResponse requestServersResponse)
	{
		return null;
	}

	/**
	 * Cancel an outstanding Ping/Players/Rules query from above. You should call this to cancel any in-progress requests before destructing a callback object that may have been passed to one of the
	 * above calls to avoid crashing when callbacks occur.
	 */
	public void cancelServerQuery(SteamServerQuery steamServerQuery)
	{
	}

	// @off

	/*JNI
		#include "SteamMatchmakingCallback.h"
	*/

	private static native long pingServer(long pointer, int ip, short port, ISteamMatchmakingPingResponse requestServersResponse); /*
	ISteamMatchmakingServers* matchmaking = (ISteamMatchmakingServers*) pointer;

	class Callback : public ISteamMatchmakingPingResponse {
	private:
		JNIEnv* env;
		jobject callback;

		void callVoidMethod(JNIEnv* env, jobject callback, const char* method, const char* signature, ...) const {
			jclass clazz = env->GetObjectClass(callback);
			jclass ex = env->FindClass("com/codedisaster/steamworks/SteamException");
			if (clazz == 0) {
				env->ThrowNew(ex, "Couldn't retrieve class for callback object.");
			}
			else {
				jmethodID methodID = env->GetMethodID(clazz, method, signature);
				if (methodID == 0) {
					env->ThrowNew(ex, "Couldn't retrieve callback method.");
				}
				else {
					va_list args;
					va_start(args, signature);
					env->CallVoidMethodV(callback, methodID, args);
					va_end(args);

					jthrowable ex = env->ExceptionOccurred();
					if (ex != NULL) {
						env->ExceptionDescribe();
						env->ExceptionClear();
						env->DeleteLocalRef(ex);
					}
				}
				env->DeleteLocalRef(clazz);
			}
		}
	public:

		Callback(JNIEnv* env, jobject callback)
		{
			env = env;
			callback = callback;
		}

		void ServerResponded(gameserveritem_t &server)
		{
			// test ob wenigstens eine exception ankommt
			jclass ex = env->FindClass("com/codedisaster/steamworks/SteamException");
			env->ThrowNew(ex, "Couldn't retrieve callback method.");

			callVoidMethod(env, callback, "onServerFailedToRespond", "()V");
		}

		void ServerFailedToRespond()
		{
			// test ob wenigstens eine exception ankommt
			jclass ex = env->FindClass("com/codedisaster/steamworks/SteamException");
			env->ThrowNew(ex, "Couldn't retrieve callback method.");

			callVoidMethod(env, callback, "onServerFailedToRespond", "()V");
		}
	};

	ISteamMatchmakingPingResponse* response = new Callback(env, requestServersResponse);
	HServerQuery query = matchmaking->PingServer(ip, port, response);

	return (int64) query;
	*/
}
