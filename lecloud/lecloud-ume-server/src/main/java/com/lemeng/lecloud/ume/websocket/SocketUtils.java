package com.lemeng.lecloud.ume.websocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class SocketUtils {

	public static Map<String, String> getUrlParams(String urlParamStr) {
		Map<String, String> paramMap = new HashMap<>();
		String paramsStr = urlParamStr.substring(urlParamStr.lastIndexOf("?") + 1);
		String[] params = paramsStr.split("&");
		for (String kVstr : params) {
			String[] kvList = kVstr.split("=");
			if (!StringUtils.isEmpty(kvList[0])) {
				String value = kvList.length > 1 ? kvList[1] : null;
				paramMap.put(kvList[0], value);
			}
		}
		return paramMap;
	}

	public static void sendAllMsg(TextMessage message) throws Exception {
		Map<String, WebSocketSession> map = SocketContainer.getSocketContainer();
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			WebSocketSession session = map.get(key);
			if (session.isOpen()) {
				session.sendMessage(message);
			}
		}
	}

//	public static UserChatRecord sendOnlyMsg(TextMessage message) throws Exception {
//		ObjectMapper maper = new ObjectMapper();
//		String content = message.getPayload();
//		UserChatRecord record = maper.readValue(content, UserChatRecord.class);
//		Long userId = record.getUserId();// 发送给谁
//		// 发给朋友
//		WebSocketSession session = SocketContainer.getUserChatSession(userId.toString());
//		if (session != null && session.isOpen()) {
//			session.sendMessage(message);
//			record.setIsRead("1");// 消息已读
//		} else {
//			record.setIsRead("0");
//		}
//		// 发给自己
//		Long sendId = record.getSendId();
//		WebSocketSession sendSession = SocketContainer.getUserChatSession(sendId.toString());
//		if (sendSession != null && sendSession.isOpen()) {
//			sendSession.sendMessage(message);
//		}
//		return record;
//	}

}
