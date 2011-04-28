package org.archstudio.demo.chatsys.server;

import java.util.Vector;

import org.archstudio.demo.chatsys.IChat;
import org.archstudio.demo.chatsys.IChatListener;

public class ChatImpl implements IChat {

	public ChatImpl() {
	}

	public void sendMessage(String sender, String message) {
		fireMessageSent(sender, message);
	}

	protected Vector<IChatListener> chatListenerList = new Vector<IChatListener>();

	public void addChatListener(IChatListener l) {
		chatListenerList.add(l);
	}

	public void removeChatListener(IChatListener l) {
		chatListenerList.remove(l);
	}

	protected void fireMessageSent(String sender, String message) {
		IChatListener[] chatListeners = chatListenerList.toArray(new IChatListener[chatListenerList.size()]);
		for (IChatListener chatListener : chatListeners) {
			chatListener.messageSent(sender, message);
		}
	}
}
