package tc.tlouro_c.swingy.views;


import tc.tlouro_c.swingy.models.Map;
import tc.tlouro_c.swingy.utils.DrawMapPanel;
import tc.tlouro_c.swingy.utils.SuperJPanel;

public class MapView {



	public void drawMap(SuperJPanel targetPanel, Map map) {
		int tileSize = 464 / map.getMapSize();
		targetPanel.removeAll();
		targetPanel.add(new DrawMapPanel(map, tileSize));
		targetPanel.revalidate();
		targetPanel.repaint();
	}


	
}
