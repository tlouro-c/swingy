package tc.tlouro_c.swingy;

import tc.tlouro_c.swingy.entities.MapEntity;
import tc.tlouro_c.swingy.entities.villains.VillainFactory;
import tc.tlouro_c.swingy.entities.Hero;

public class Map {

	private MapEntity[][] mapGrid;
	private int sideSize;
	private int spawnXY;

	public Map(Hero hero) {
		this.sideSize = (hero.getLevel() - 1) * 5 + 10 - hero.getLevel(); 
		this.spawnXY = sideSize / 2;
		this.mapGrid = new MapEntity[sideSize][sideSize];
		this.populate(hero);
	}

	private void populate(Hero hero) {

		mapGrid[spawnXY][spawnXY] = hero;
		for (int i = 0; i < sideSize * 2; i++) {
			var randomY = (int)(sideSize * Math.random());
			var randomX = (int)(sideSize * Math.random());
			if (mapGrid[randomY][randomX] == null) {
				mapGrid[randomY][randomX] = VillainFactory.getInstance().newRandomVillain();
			} else {
				i--;
			}
		}
	}

	public MapEntity[][] getMapGrid() {
		return this.mapGrid;
	}

	public void putMapGrid(int x, int y, MapEntity entity) {
		this.mapGrid[y][x] = entity;
	}

}
