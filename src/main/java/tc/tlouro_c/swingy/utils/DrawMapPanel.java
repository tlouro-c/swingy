package tc.tlouro_c.swingy.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.Map;
import tc.tlouro_c.swingy.models.MapEntity;
import tc.tlouro_c.swingy.models.Villain;

public class DrawMapPanel extends JPanel {

	private Image tileSpriteImg;
	private Image playerSpriteImg;
	private int mapSize;
	private int tileSize;
	private MapEntity[][] mapGrid;

	public DrawMapPanel(Map map, int tileSize) {
		this.tileSpriteImg = Sprite.scaledImage("/sprites/tiles/grass.png", tileSize, tileSize).getImage();
		this.playerSpriteImg = Sprite.scaledImage("/sprites/trainer/south.png", tileSize, tileSize).getImage();
		this.tileSize = tileSize;
		this.mapSize = map.getMapSize();
		this.setPreferredSize(new Dimension(mapSize * tileSize, mapSize * tileSize));	
		this.mapGrid = map.getMapGrid();
	}

	@Override
    public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(new Color(255, 255, 0, 127));

        if (tileSpriteImg != null) {
			int x = 0;
			int y = 0;

			for (int i = 0; i < mapSize; i++) {
				for (int j = 0; j < mapSize; j++) {
					g2d.drawImage(tileSpriteImg, x, y, this);
					if (mapGrid[i][j] instanceof Hero) {
						g2d.fillRect(x, y, tileSize, tileSize);
						g2d.drawImage(playerSpriteImg, x, y, this);
					} else if (mapGrid[i][j] instanceof Villain) {
						var villain = (Villain) mapGrid[i][j];
						g2d.drawImage(villain.getMapSprite(tileSize, tileSize).getImage(), x, y, this);
					}
					x += tileSize;
				}
				x = 0;
				y += tileSize;
			}
        }
    }
	
}
