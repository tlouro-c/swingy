package tc.tlouro_c.swingy;

import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static Frame instance;

	public static Frame getInstance() {
		if (instance == null) {
			instance = new Frame();
		}
		return instance;
	}

	private Frame() {
		super("Swingy");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		this.getContentPane().setBackground((Color.decode("#292b2b")));
		this.setResizable(false);
	}

	
}
