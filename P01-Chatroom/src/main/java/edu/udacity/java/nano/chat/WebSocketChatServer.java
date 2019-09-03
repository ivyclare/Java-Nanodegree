package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static final Logger LOG = Logger.getLogger(WebSocketChatServer.class.getSimpleName());

    private static void sendMessageToAll(String content) {
        onlineSessions.forEach((s, session) -> {
            try {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(content);
                } else {
                    onlineSessions.remove(s);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
    	 Message message = new Message();
      	onlineSessions.put(session.getId(), session);
          message.setMessageType(Message.MessageType.JOIN);
          message.setOnlineUsers(onlineSessions.size());
          sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);

        System.out.println("@OnMessage: Message from " + message.getUsername() + " @ session no. " + session.getId() + ": " + message.getContent() + ".");
        message.setMessageType(Message.MessageType.CHAT);
        message.setOnlineUsers(onlineSessions.size());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        session.close();
        onlineSessions.remove(session.getId());

        Message message = new Message();
        System.out.println("@OnClose: " + "..." + "has closed his session @ " + session.getId() + ".");
        message.setMessageType(Message.MessageType.LEAVE);
        message.setOnlineUsers(onlineSessions.size());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOG.info("Error: " + error.getMessage());
    }

}
