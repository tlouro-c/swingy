package tc.tlouro_c.swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.utils.Sprite;
import tc.tlouro_c.swingy.utils.SuperJPanel;

public class GameplayView {
	
	private SuperJPanel panel;
	private SuperJPanel dashboard;
	private SuperJPanel preview;
	private SuperJPanel leftColumn;
	private SuperJPanel rightColumn;
	private SuperJPanel mainScreen;
	private SuperJPanel controlPanel;
	private SuperJPanel dynamicPanel;
	private CharacterView characterView;
	private Hero hero;

	public GameplayView() {
		characterView = new CharacterView();
		panel = new SuperJPanel(1020, 738,  new FlowLayout(FlowLayout.LEFT, 3, 3));
		leftColumn = new SuperJPanel(660, 730, panel.getLayout());
		rightColumn = new SuperJPanel(350, 730, panel.getLayout());
		mainScreen = new SuperJPanel(650, 500, panel.getLayout());
		

		Border border = BorderFactory.createLineBorder(Color.WHITE, 6);
		mainScreen.setBorder(border);

		leftColumn.add(mainScreen);
		panel.add(leftColumn);
		panel.add(rightColumn);
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	} 

	public SuperJPanel loadScreen() {
		dashboard = characterView.dashboard(hero, 650, 220);
		preview = characterView.preview(hero, 340, 500);
		leftColumn.add(dashboard);
		rightColumn.add(preview);

		loadControlPanel();
		return this.panel;
	}

	public void updateDashboard() {
		characterView.updateDashboard(hero);
	}

	public void loadControlPanel() {

		controlPanel = new SuperJPanel(340, 220, new FlowLayout(FlowLayout.CENTER, 0, 0));
		dynamicPanel = new SuperJPanel(340, 60, new FlowLayout(FlowLayout.CENTER, 0, 0));

		var buttonsContainer = new SuperJPanel(250, 150, controlPanel.getLayout());

		var c1 = new SuperJPanel(buttonsContainer.getWidth(), 50, buttonsContainer.getLayout());
		var c2 = new SuperJPanel(buttonsContainer.getWidth(), 50, new FlowLayout(FlowLayout.CENTER, 50, 0));
		var c3 = new SuperJPanel(buttonsContainer.getWidth(), 50, buttonsContainer.getLayout());

		var moveUpBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/up.png", 40, 40), 50, 50);
		var moveDownBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/down.png", 40, 40), 50, 50);
		var moveLeftBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/left.png", 40, 40), 50, 50);
		var moveRightBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/right.png", 40, 40), 50, 50);

		c1.add(moveUpBtn);
		c2.add(moveLeftBtn);
		c2.add(moveRightBtn);
		c3.add(moveDownBtn);

		buttonsContainer.add(c1);
		buttonsContainer.add(c2);
		buttonsContainer.add(c3);

		controlPanel.add(dynamicPanel);
		controlPanel.add(buttonsContainer);

		rightColumn.add(controlPanel);
	}




}
