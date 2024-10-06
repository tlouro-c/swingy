package tc.tlouro_c.swingy.utils;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Sprite {

	private final String path;
	private int spriteNumber;

	public Sprite(boolean isHero, int spriteNumber) {
		this.path = "/sprites/" + (isHero ? "heroes/" : "villains/") + Integer.toString(spriteNumber);
		this.spriteNumber = spriteNumber;
	}

	public Sprite(String path) {
		this.path = path;
	}

	public static ImageIcon scaledImage(String path, int height, int width) {
		return new ImageIcon(new ImageIcon(Sprite.class.getResource(path))
			.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	public ImageIcon getMapSprite(int height, int width) {
		return new ImageIcon(new ImageIcon(getClass().getResource(path + "_map.png"))
					.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	public ImageIcon getPreviewSprite(int height, int width) {
		return new ImageIcon(new ImageIcon(getClass().getResource(path + "_preview.gif"))
					.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	public String getPath() {
		return path;
	}

	public int getSpriteNumber() {
		return spriteNumber;
	}
}
