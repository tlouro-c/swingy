package tc.tlouro_c.swingy;

import org.junit.Test;

import tc.tlouro_c.swingy.entities.Hero;
import tc.tlouro_c.swingy.entities.MapEntity;

public class MapTest {

	@Test
	public void mapTest() {
		
		Map map = new Map(new Hero());

		for (MapEntity[] row : map.getMapGrid()) {
			for (MapEntity e : row) {
				System.out.print(e + "|");
			}
			System.out.println();
		}


	}
}
