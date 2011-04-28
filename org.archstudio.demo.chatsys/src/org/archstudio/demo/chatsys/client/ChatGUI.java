package org.archstudio.demo.chatsys.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.archstudio.demo.chatsys.IChat;

class ChatGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -4010923966612042237L;

	JTextArea transcript;
	JTextField entryField;
	JButton sendButton;
	StringBuffer transcriptBuf;

	IChat chatServer = null;

	public ChatGUI(String identifier, IChat chatServer) {
		super(identifier);
		this.chatServer = chatServer;

		transcriptBuf = new StringBuffer();
		transcript = new JTextArea();
		entryField = new JTextField(20);
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add("Center", transcript);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(entryField);
		bottomPanel.add(sendButton);
		this.getContentPane().add("South", bottomPanel);

		this.setSize(500, 400);
		this.setVisible(true);
		validate();
		repaint();
	}

	public void addMessageToTranscript(String text) {
		transcriptBuf.append(text);
		transcriptBuf.append(System.getProperty("line.separator"));
		transcript.setText(transcriptBuf.toString());
	}

	public void actionPerformed(ActionEvent evt) {
		String text = entryField.getText();
		if (!text.equals("")) {
			chatServer.sendMessage(getTitle(), text);
		}
	}

}
