package org.archstudio.demo.chatsys.server;

/**
 * Myx brick: "Server Impl"
 * 
 * @see org.archstudio.demo.chatsys.server.ChatServerMyxComponentStub
 * @generated
 */
public class ChatServerMyxComponent extends org.archstudio.demo.chatsys.server.ChatServerMyxComponentStub {

	@Override
	public void sendMessage(String sender, String text) {
		chatEvents.messageSent(sender, text);
	}

}