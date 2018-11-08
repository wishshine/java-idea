package com.lemeng.lecloud.ume.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * @author WL-PC
 *
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {

//	@Autowired
//	private UserRecordService userRecordService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		try {
//			UserChatRecord record=SocketUtils.sendOnlyMsg(message);
//			// 存储发送信息
//			userRecordService.storeUserRecord(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("建立连接");
		String uri = session.getUri().toString();
		String userId = SocketUtils.getUrlParams(uri).get("userId");
		System.out.println(userId);
		SocketContainer.addUserChatSession(userId, session);
		super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("断开连接");
		String uri = session.getUri().toString();
		String userId = SocketUtils.getUrlParams(uri).get("userId");
		System.out.println(userId);
		SocketContainer.removeUserChatSession(userId);
		super.afterConnectionClosed(session, status);
	}

}
