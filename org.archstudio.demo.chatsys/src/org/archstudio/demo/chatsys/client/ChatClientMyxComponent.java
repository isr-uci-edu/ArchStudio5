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

/**
 * Myx brick: "Client Impl"
 * 
 * @see org.archstudio.demo.chatsys.client.ChatClientMyxComponentStub
 */
public class ChatClientMyxComponent extends org.archstudio.demo.chatsys.client.ChatClientMyxComponentStub implements
		ActionListener {

	JFrame frame;
	JTextArea transcript;
	JTextField entryField;
	JButton sendButton;
	StringBuffer transcriptBuf;

	public void begin() {
		super.begin();

		frame = new JFrame(this.getMyxBrickItems().getBrickName().toString());
		transcriptBuf = new StringBuffer();
		transcript = new JTextArea();
		entryField = new JTextField(20);
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add("Center", transcript);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(entryField);
		bottomPanel.add(sendButton);
		frame.getContentPane().add("South", bottomPanel);

		frame.setSize(500, 400);
		frame.setVisible(true);
		frame.validate();
		frame.repaint();

	}

	public void actionPerformed(ActionEvent evt) {
		String text = entryField.getText();
		if (!text.equals("")) {
			chat.sendMessage(this.getMyxBrickItems().getBrickName().getName(), text);
		}
	}

	public void messageSent(String sender, String message) {
		transcriptBuf.append(sender + ": " + message);
		transcriptBuf.append(System.getProperty("line.separator"));
		transcript.setText(transcriptBuf.toString());
	}

}