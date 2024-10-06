package tc.tlouro_c.swingy.utils;

import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame {

	public Frame(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		this.getContentPane().setBackground((Color.decode("#292b2b")));
		this.setResizable(false);
	}

	
}
