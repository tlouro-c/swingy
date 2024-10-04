package tc.tlouro_c.swingy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.concurrent.Flow;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

	public Frame() {
		super("Swingy");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(500, 500));
	}

	
}
