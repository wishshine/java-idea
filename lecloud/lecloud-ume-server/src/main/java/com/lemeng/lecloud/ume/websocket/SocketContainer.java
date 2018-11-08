package com.lemeng.lecloud.ume.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;

public class SocketContainer {

	/**
	 * 聊天socket
	 */
	private static String SOCKET_SESSION_KEY_CHAT = "SOCKET_CHAT";
	
	/**
	 * 系统通知socket
	 */
	private static String SOCKET_SESSION_KEY_SYS = "SOCKET_SYS";

	private static Map<String, WebSocketSession> USER_CHAT_SESSION_MAP = new ConcurrentHashMap<String, WebSocketSession>();

	/**
	 * 新增用户聊天session
	 * 
	 * @param userKey
	 * @param session
	 * @return
	 */
	public static boolean addUserChatSession(String userId, WebSocketSession session) {
		return addSession(SOCKET_SESSION_KEY_CHAT + userId, session);
	}

	/**
	 * 移除用户聊天 session
	 * 
	 * @param userId
	 */
	public static void removeUserChatSession(String userId) {
		removeSession(SOCKET_SESSION_KEY_CHAT + userId);
	}
	
	/**
	 * 获取聊天session
	 * @param userKey
	 * @return
	 */
	public static WebSocketSession getUserChatSession(String userId) {
		return getUserSession(SOCKET_SESSION_KEY_CHAT + userId);
	}

	/**
	 * 新增系统通知session
	 * 
	 * @param userKey
	 * @param session
	 * @return
	 */
	public static boolean addUserSySession(String userId, WebSocketSession session) {
		return addSession(SOCKET_SESSION_KEY_SYS + userId, session);
	}

	/**
	 * 移除系统通知 session
	 * 
	 * @param userId
	 */
	public static void removeUserSysSession(String userId) {
		removeSession(SOCKET_SESSION_KEY_SYS + userId);
	}
	
	/**
	 * 获取系统通知session
	 * @param userKey
	 * @return
	 */
	public static WebSocketSession getUserSysSession(String userId) {
		return getUserSession(SOCKET_SESSION_KEY_SYS + userId);
	}

	
	private static boolean addSession(String userKey, WebSocketSession session) {
		if (!StringUtils.isEmpty(userKey) && session != null) {
			USER_CHAT_SESSION_MAP.put(userKey, session);
			return true;
		}
		return false;
	}

	private static void removeSession(String userKey) {
		if (!StringUtils.isEmpty(userKey)) {
			USER_CHAT_SESSION_MAP.remove(userKey);
		}
	}

	private static WebSocketSession getUserSession(String userKey) {
		if (!StringUtils.isEmpty(userKey)) {
			return USER_CHAT_SESSION_MAP.get(userKey);
		}
		return null;
	}

	public static Map<String, WebSocketSession> getSocketContainer() {
		return USER_CHAT_SESSION_MAP;
	}

}
