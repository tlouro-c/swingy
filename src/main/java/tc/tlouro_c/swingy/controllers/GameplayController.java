package tc.tlouro_c.swingy.controllers;

import tc.tlouro_c.swingy.utils.Frame;

public class GameplayController {

	private Frame frame;


	public GameplayController() {
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void start() {
		//var startScreen = view.startScreen();
		//frame.add(startScreen);
		frame.setVisible(true);
	}
	
}
