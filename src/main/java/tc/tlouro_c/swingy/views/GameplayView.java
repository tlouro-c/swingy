package tc.tlouro_c.swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import tc.tlouro_c.swingy.controllers.GameplayController;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.Map;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.utils.SuperJPanel;

public class GameplayView {
	
	private GameplayController controller;
	private SuperJPanel panel;
	private SuperJPanel dashboard;
	private SuperJPanel preview;
	private SuperJPanel leftColumn;
	private SuperJPanel rightColumn;
	private SuperJPanel mainScreen;
	private SuperJPanel interactivePanel;
	private CharacterView characterView;
	private MapView mapView;
	private Hero hero;
	private Map map;

	public GameplayView(GameplayController controller) {
		this.controller = controller;
		characterView = new CharacterView();
		mapView = new MapView();
		interactivePanel = new SuperJPanel(340, 220, new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel = new SuperJPanel(1020, 738,  new FlowLayout(FlowLayout.LEFT, 3, 3));
		leftColumn = new SuperJPanel(660, 730, panel.getLayout());
		rightColumn = new SuperJPanel(350, 730, panel.getLayout());
		mainScreen = new SuperJPanel(650, 500, new FlowLayout(FlowLayout.CENTER, 0, 14));

		Border border = BorderFactory.createLineBorder(Color.WHITE, 6);
		mainScreen.setBorder(border);

		leftColumn.add(mainScreen);
		panel.add(leftColumn);
		panel.add(rightColumn);
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	} 

	public void setMap(Map map) {
		this.map = map;
	}

	public SuperJPanel loadScreen() {
		dashboard = characterView.dashboard(hero, 650, 220);
		preview = characterView.preview(hero, 340, 500);
		leftColumn.add(dashboard);
		rightColumn.add(preview);

		mapView.drawMap(mainScreen, map);

		return this.panel;
	}

	public void updateDashboard() {
		characterView.updateDashboard(hero);
	}

	public void updateMapView() {
		interactivePanel.removeAll();
		interactivePanel.revalidate();
		interactivePanel.repaint();
		mapView.drawMap(mainScreen, map);
	}

	public void updatePreview() {
		preview.removeAll();
		var previewSprite = SuperJPanel.icon(hero.getPreviewSprite(500, 340));
		preview.add(previewSprite, BorderLayout.CENTER);
		previewSprite.revalidate();
		preview.repaint();
	}

	public void handleConflict(Villain villain) {
		controller.disableControls();
		preview = characterView.preview(villain, 340, 500);
		var label = interactivePanel.titleLabel(String.format("A %s Lv.%d has appeared!", villain.getName(), villain.getLevel()), null, 1);
		label.setHorizontalAlignment(JLabel.CENTER);
		var runBtn = SuperJPanel.button("Run", 150, 50);
		runBtn.addActionListener(e -> controller.run(villain));

		var fightBtn = SuperJPanel.button("Fight", 150, 50);
		fightBtn.addActionListener(e -> controller.fight(villain, "A fight will take place!"));

		interactivePanel.add(label);
		interactivePanel.add(runBtn);
		interactivePanel.add(fightBtn);
		rightColumn.removeAll();
		rightColumn.add(preview);
		rightColumn.add(interactivePanel);
		rightColumn.revalidate();
		rightColumn.repaint();
	}

	public void freeHero(String message) {
		if (message != null) {
			interactivePanel.removeAll();
			interactivePanel.revalidate();
			interactivePanel.repaint();
			var label = interactivePanel.titleLabel(message, null, 1);
			label.setHorizontalAlignment(JLabel.CENTER);
			interactivePanel.add(label);
		}
		controller.enableControls();
	}

	public void startFightPrompt(Villain villain, String message) {
		interactivePanel.removeAll();
		interactivePanel.revalidate();
		interactivePanel.repaint();
		var label = interactivePanel.titleLabel(message, null, 1);
		label.setHorizontalAlignment(JLabel.CENTER);
		var simulateBtn = SuperJPanel.button("Start simulation", 150, 50);
		simulateBtn.addActionListener(e -> controller.simulateFight(villain));

		interactivePanel.add(label);
		interactivePanel.add(simulateBtn);
	}

	public void fightWon(Villain villain) {

		interactivePanel.removeAll();
		interactivePanel.revalidate();
		interactivePanel.repaint();
		var label = interactivePanel.titleLabel("You won the fight!", null, 1);
		var labelXp = interactivePanel.textLabel("+ " + villain.getLevel() * 500 + " experience!", null, 1);
		label.setHorizontalAlignment(JLabel.CENTER);
		labelXp.setHorizontalAlignment(JLabel.CENTER);


		interactivePanel.add(label);
		interactivePanel.add(labelXp);

		var artifact = villain.getArtifact();
		if (artifact != null) {
			var artifactLabel = interactivePanel.textLabel(villain.getName() + " dropped a " + artifact, null, 1);
			artifactLabel.setHorizontalAlignment(JLabel.CENTER);
			var pickBtn = SuperJPanel.button("Pick it up", 150, 50);
			var dropBtn = SuperJPanel.button("Drop it", 150, 50);

			pickBtn.addActionListener(e -> {controller.pickUpArtifact(artifact);});
			dropBtn.addActionListener(e -> freeHero(""));

			interactivePanel.add(artifactLabel);
			interactivePanel.add(pickBtn);
			interactivePanel.add(dropBtn);
		} else {
			freeHero(null);
		}
	}

	public void endScreen(String message, ActionListener backToMainMenuListener) {
		panel.removeAll();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

		var label = SuperJPanel.titleLabel(message, null);
		label.setPreferredSize(new Dimension(panel.getWidth(), 300));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.BOTTOM);
		var btn = SuperJPanel.button("Back to main menu", 150, 50);
		btn.addActionListener(backToMainMenuListener);
		panel.add(label);
		panel.add(btn);
		panel.revalidate();
		panel.repaint();
	}

}
