package com.lemeng.lecloud.ume.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * @author WL-PC
 *
 */
public class SysWebSocketHandler extends TextWebSocketHandler {

	private static final Log LOGGER = LogFactory.getLog(SysWebSocketHandler.class);

//	@Autowired
//	private UserRecordService userRecordService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		try {
			LOGGER.info("系统通知");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String uri = session.getUri().toString();
		LOGGER.info("socket建立连接，url:" + uri);
		String userId = SocketUtils.getUrlParams(uri).get("userId");
		SocketContainer.addUserSySession(userId, session);// 加入系统通知session
		super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String uri = session.getUri().toString();
		LOGGER.info("socket断开连接，url:" + uri);
		String userId = SocketUtils.getUrlParams(uri).get("userId");
		System.out.println(userId);
		SocketContainer.removeUserSysSession(userId);//移除系统通知session
		super.afterConnectionClosed(session, status);
	}

}
