package tc.tlouro_c.swingy.utils;

import javax.swing.ImageIcon;

public class Sprite{

	private final String path;

	public Sprite(boolean isHero, int spriteNumber) {
		this.path = "/sprites/" + (isHero ? "heroes/" : "villains/") + Integer.toString(spriteNumber);
	}

	public ImageIcon getMapSprite() {
		return new ImageIcon(getClass().getResource(path + "_map.png"));
	}

	public ImageIcon getPreviewSprite() {
		return new ImageIcon(getClass().getResource(path + "_preview.gif"));
	}
}
