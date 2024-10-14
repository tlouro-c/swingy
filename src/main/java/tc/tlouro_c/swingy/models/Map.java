package tc.tlouro_c.swingy.models;

import tc.tlouro_c.swingy.models.villains.VillainFactory;

public class Map {

	private MapEntity[][] mapGrid;
	private int mapSize;
	private int spawnXY;

	public Map(Hero hero) {
		this.mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2); 
		this.spawnXY = mapSize / 2;
		this.mapGrid = new MapEntity[mapSize][mapSize];
		this.populate(hero);
	}

	private void populate(Hero hero) {

		mapGrid[spawnXY][spawnXY] = hero;
		hero.setX(spawnXY);
		hero.setY(spawnXY);
		for (int i = 0; i < mapSize * 2; i++) {
			var randomY = (int)((mapSize - 1 + 0.99) * Math.random());
			var randomX = (int)((mapSize - 1 + 0.99)  * Math.random());
			if (mapGrid[randomY][randomX] == null) {
				var newVillain = VillainFactory.getInstance().newRandomVillain();
				newVillain.setX(randomX);
				newVillain.setY(randomY);
				mapGrid[randomY][randomX] = newVillain;
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
	
	public int getMapSize() {
		return mapSize;
	}

}
