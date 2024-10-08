package tc.tlouro_c.swingy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import tc.tlouro_c.swingy.models.Character;
import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.utils.DBManager;
import tc.tlouro_c.swingy.utils.Sprite;
import tc.tlouro_c.swingy.utils.SuperJPanel;

public class HeroSelectView {
	
	private CharacterView characterView;
	private SuperJPanel panel;
	private SuperJPanel dashboard;
	private SuperJPanel preview;
	private SuperJPanel leftColumn;
	private SuperJPanel rightColumn;
	private SuperJPanel mainScreen;
	private JButton createHeroBtn;
	private JButton selectExistingHeroBtn;
	private JButton startGameBtn;
	private JTextField heroName;
	private JLabel attackPointsLabel;
	private JLabel defensePointsJLabel;
	private JLabel hitPointsLabel;
	private JLabel heroClassLabel;
	private JLabel remainingPointsLabel;
	private int selectedSprite;
	private ActionListener listenerForCreateHeroBtn;
	private ActionListener listenerForGameStart;
	private Hero selectedHero;


	public HeroSelectView(ActionListener listenerForCreateHeroBtn, ActionListener listenerForGameStart) {
		panel = new SuperJPanel(1020, 738,  new FlowLayout(FlowLayout.LEFT, 3, 3));
		characterView = new CharacterView();
		leftColumn = new SuperJPanel(660, 730, panel.getLayout());
		rightColumn = new SuperJPanel(350, 730, panel.getLayout());
		mainScreen = new SuperJPanel(650, 500, panel.getLayout());
		Border border = BorderFactory.createLineBorder(Color.WHITE, 6);
		mainScreen.setBorder(border);
		selectedSprite = 1;
		this.listenerForCreateHeroBtn = listenerForCreateHeroBtn;
		this.listenerForGameStart = listenerForGameStart;
		
		leftColumn.add(mainScreen);
		panel.add(leftColumn);
		panel.add(rightColumn);
	}

	public SuperJPanel startScreen() {
		loadInitialButtons();
		return panel;
	}

	public void loadExistingHeroes() {

		clearScreen();
		mainScreen.setLayout(new BoxLayout(mainScreen, BoxLayout.Y_AXIS));
		var container = new SuperJPanel(mainScreen.getWidth(), mainScreen.getHeight() - 70, new FlowLayout(FlowLayout.CENTER, 10, 10));
		var titleLabel = SuperJPanel.titleLabel("Select A Hero", null);
		SuperJPanel.addMargin(titleLabel, 60, 0, 60, 0);
		container.add(titleLabel);

		var heroesList = DBManager.getInstance().fetchHeroes();

		for (Character hero : heroesList) {
			var subContainer = new SuperJPanel(container.getWidth(), 40, new FlowLayout(FlowLayout.CENTER));

			var nameLabel = subContainer.textLabel(hero.getName(), null, 0.5);
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			var selectHeroBtn = SuperJPanel.button("Select", 75, 30);
			selectHeroBtn.addActionListener(e -> selectHero((Hero)hero));
			var deleteHeroBtn = SuperJPanel.button("Delete", 75, 30);
			deleteHeroBtn.addActionListener(e -> deleteHero(hero));

			subContainer.add(selectHeroBtn);
			subContainer.add(nameLabel);
			subContainer.add(deleteHeroBtn);
			container.add(subContainer);
		}

		mainScreen.add(container);

		var finalBtnContainer = new SuperJPanel(mainScreen.getWidth(), 50, new FlowLayout(FlowLayout.CENTER, 20, 0));

		var goBackBtn = SuperJPanel.button("Go Back", 100, 50);
		goBackBtn.addActionListener(e -> loadInitialButtons());
		startGameBtn = SuperJPanel.button("Start Game", 100, 50);
		startGameBtn.setEnabled(false);
		startGameBtn.addActionListener(listenerForGameStart);

		finalBtnContainer.add(goBackBtn);
		finalBtnContainer.add(startGameBtn);
		mainScreen.add(finalBtnContainer);

	}

	private void selectHero(Hero hero) {
		updateHeroView(hero);
		this.selectedHero = hero;
		this.startGameBtn.setEnabled(true);
	}

	public Hero getSelectedHero() {
		return selectedHero;
	}

	private void deleteHero(Character hero) {

		int confirmationResult = JOptionPane.showConfirmDialog(mainScreen,
								"Are you sure you want to delete this hero?",
								"Delete hero" , JOptionPane.YES_NO_OPTION);

		if (confirmationResult == 0) {
			var db = DBManager.getInstance();
			db.deleteHero(hero);
			if (db.fetchHeroesCount() > 0) {
				loadExistingHeroes();
			} else {
				loadInitialButtons();
			}
		}
	}


	private void clearScreen() {
		mainScreen.removeAll();
		mainScreen.revalidate();
        mainScreen.repaint();
		rightColumn.removeAll();
		rightColumn.revalidate();
        rightColumn.repaint();
		if (dashboard != null) {
			dashboard.removeAll();
			dashboard.revalidate();
			dashboard.repaint();
		}
		this.selectedHero = null;
	}

	public void loadInitialButtons() {
		clearScreen();
		mainScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 210));

		var databaseEntries = DBManager.getInstance().fetchHeroesCount();

		createHeroBtn = SuperJPanel.button("Create a new hero", 200, 50);
		if (databaseEntries >= 5) {
			createHeroBtn.setEnabled(false);
		} else {
			createHeroBtn.addActionListener(e -> loadCreateHeroForm());
		}
		selectExistingHeroBtn = SuperJPanel.button("Select an existing hero", 200, 50);
		if (databaseEntries <= 0) {
			selectExistingHeroBtn.setEnabled(false);
		} else {
			selectExistingHeroBtn.addActionListener(e -> loadExistingHeroes());
		}
		mainScreen.add(createHeroBtn);
		mainScreen.add(selectExistingHeroBtn);
	}

	private void loadCreateHeroForm() {
		clearScreen();
		mainScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

		remainingPointsLabel = mainScreen.titleLabel("30", null, 1);

		loadHeader();
		loadHeroClassChoice();
		loadSpriteChoice();
		loadNameInput();
		loadStatsInputs();

		var finalBtnContainer = new SuperJPanel(mainScreen.getWidth(), 50, new FlowLayout(FlowLayout.CENTER, 20, 0));

		var goBackBtn = SuperJPanel.button("Go Back", 100, 50);
		goBackBtn.addActionListener(e -> loadInitialButtons());
		var createHeroBtn = SuperJPanel.button("Create Hero", 100, 50);
		createHeroBtn.addActionListener(listenerForCreateHeroBtn);

		finalBtnContainer.add(goBackBtn);
		finalBtnContainer.add(createHeroBtn);
		mainScreen.add(finalBtnContainer);
	}

	private void loadSpriteChoice() {
		var preview = new SuperJPanel(340, 500, new BorderLayout());
		var previewSprite = SuperJPanel.icon(Sprite.scaledImage("/sprites/heroes/1_preview.gif", 440, 340));
		var buttonsContainer = new SuperJPanel(rightColumn.getWidth(), 60, new FlowLayout(FlowLayout.CENTER, 20, 8));
		Border border = BorderFactory.createLineBorder(Color.WHITE, 6);
		preview.setBorder(border);

		var choiceLeftBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/decrease.png", 20, 20), 30, 30);
		var choiceRightBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/increase.png", 20, 20), 30, 30);

		choiceLeftBtn.addActionListener(e -> choiceLeftBtnSprite(previewSprite));
		choiceRightBtn.addActionListener(e -> choiceRightBtnSprite(previewSprite));

		preview.add(previewSprite, BorderLayout.NORTH);
		buttonsContainer.add(choiceLeftBtn);
		buttonsContainer.add(choiceRightBtn);
		preview.add(buttonsContainer);
		rightColumn.add(preview);
	}

	private void choiceLeftBtnSprite(JLabel sprite) {
		selectedSprite--;
		if (selectedSprite < 1) {
			selectedSprite = 3;
		}
		sprite.setIcon(Sprite.scaledImage("/sprites/heroes/" + selectedSprite + "_preview.gif", 440, 340));
	}

	private void choiceRightBtnSprite(JLabel sprite) {
		selectedSprite++;
		if (selectedSprite > 3) {
			selectedSprite = 1;
		}
		sprite.setIcon(Sprite.scaledImage("/sprites/heroes/" + selectedSprite + "_preview.gif", 440, 340));
	}


	private void loadHeader() {
		var header = new SuperJPanel(mainScreen.getWidth(), 140, new FlowLayout(FlowLayout.CENTER, 0, 5));

		var createNewHeroLabel = mainScreen.titleLabel("Create A New Hero", null, 1);
		var dimension = new Dimension(mainScreen.getWidth(), 50);
		createNewHeroLabel.setPreferredSize(dimension);
		createNewHeroLabel.setSize(dimension);
		createNewHeroLabel.setHorizontalAlignment(JLabel.CENTER);
		createNewHeroLabel.setVerticalAlignment(JLabel.CENTER);

		var descriptionLabel1 = header.textLabel("Each hero begins the following base stats: 50 Attack, 50 Defense, 200 Hit Points", null, 1);
		descriptionLabel1.setHorizontalAlignment(JLabel.CENTER);
		var descriptionLabel2 = header.textLabel("You have an additional 30 points to allocate as you choose.", null, 1);
		descriptionLabel2.setHorizontalAlignment(JLabel.CENTER);
		var descriptionLabel3 = header.textLabel("Your class selection will also influence the overall stats.", null, 1);
		descriptionLabel3.setHorizontalAlignment(JLabel.CENTER);

		header.add(createNewHeroLabel);
		header.add(descriptionLabel1);
		header.add(descriptionLabel2);
		header.add(descriptionLabel3);
		mainScreen.add(header);
	}

	private void loadNameInput() {

		var container = new SuperJPanel(mainScreen.getWidth(), 30, new FlowLayout(FlowLayout.CENTER, 20, 0));
		var nameLabel = container.textLabel("Name", null, -1);
		heroName = container.input("", 1);

		container.add(nameLabel);
		container.add(heroName);
		mainScreen.add(container);
	}

	private void loadHeroClassChoice() {
		
		var container = new SuperJPanel(mainScreen.getWidth(), 30, new FlowLayout(FlowLayout.CENTER, 20, 0));

		heroClassLabel = container.textLabel("Bruiser", null, 0.1);
		heroClassLabel.setHorizontalAlignment(JLabel.CENTER);
		var choiceLeftBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/decrease.png", 20, 20), 30, 30);
		var choiceRightBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/increase.png", 20, 20), 30, 30);

		AtomicInteger index = new AtomicInteger(0);
		choiceLeftBtn.addActionListener(e -> choiceLeftBtn(heroClassLabel, index));
		choiceRightBtn.addActionListener(e -> choiceRightBtn(heroClassLabel, index));

		container.add(choiceLeftBtn);
		container.add(heroClassLabel);
		container.add(choiceRightBtn);
		mainScreen.add(container);
	}

	private void choiceLeftBtn(JLabel label, AtomicInteger index) {
		String[] classes = {"Bruiser", "Assassin", "Tank"};

		var tmpIndex = index.decrementAndGet();
		if (tmpIndex < 0) {
			index.set(2);
		}


		label.setText(classes[index.get()]);
	}

	private void choiceRightBtn(JLabel label, AtomicInteger index) {
		String[] classes = {"Bruiser", "Assassin", "Tank"};

		var tmpIndex = index.incrementAndGet();
		if (tmpIndex > 2) {
			index.set(0);
		}

		label.setText(classes[index.get()]);
	}

	private void loadStatsInputs() {

		String[] types = {"Attack", "Defense", "Hit Points"};

		var helperLabel = mainScreen.textLabel("Remaining Points:", null, -1);
		helperLabel.setHorizontalAlignment(JLabel.CENTER);
		remainingPointsLabel = mainScreen.textLabel("30", null, 0.1);
		remainingPointsLabel.setHorizontalAlignment(JLabel.CENTER);
		attackPointsLabel = mainScreen.titleLabel("0", null, 0.1);
		defensePointsJLabel = mainScreen.titleLabel("0", null, 0.1);
		hitPointsLabel = mainScreen.titleLabel("0", null, 0.1);

		var labels = new JLabel[] {attackPointsLabel, defensePointsJLabel, hitPointsLabel};

		for (int i = 0; i < 3; i++) {
			final int index = i;
			labels[index].setHorizontalAlignment(JLabel.CENTER);
			var container = new SuperJPanel(mainScreen.getWidth(), 30, new FlowLayout(FlowLayout.CENTER, 20, 0));
			var type = container.textLabel(types[index], null, 0.1);
			var decreaseBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/decrease.png", 20, 20), 30, 30);
			decreaseBtn.setEnabled(false);
			var increaseBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/increase.png", 20, 20), 30, 30);

			decreaseBtn.addActionListener(e -> decreaseLabelValue(labels[index], increaseBtn, decreaseBtn));
			increaseBtn.addActionListener(e -> increaseLabelValue(labels[index], increaseBtn, decreaseBtn));

			container.add(type);
			container.add(decreaseBtn);
			container.add(labels[index]);
			container.add(increaseBtn);
			mainScreen.add(container);
		}
		mainScreen.add(helperLabel);
		mainScreen.add(remainingPointsLabel);
	}

	private void increaseLabelValue(JLabel label, JButton increaseBtn, JButton decreaseBtn) {
		int remainingPoints = Integer.parseInt(remainingPointsLabel.getText());
		remainingPoints--;
		int newLabelValue = Integer.parseInt(label.getText());
		newLabelValue++;
		
		if (newLabelValue > 0) {
			decreaseBtn.setEnabled(true);
		}
		if (remainingPoints == 0) {
			increaseBtn.setEnabled(false);
		}

		remainingPointsLabel.setText(Integer.toString(remainingPoints));
		label.setText(Integer.toString(newLabelValue));
	}

	private void decreaseLabelValue(JLabel label, JButton increaseBtn, JButton decreaseBtn) {
		int remainingPoints = Integer.parseInt(remainingPointsLabel.getText());
		remainingPoints++;
		var labelValue = Integer.parseInt(label.getText());
		labelValue--;

		if (remainingPoints > 0) {
			increaseBtn.setEnabled(true);
		}
		if (labelValue == 0) {
			decreaseBtn.setEnabled(false);
		}

		remainingPointsLabel.setText(Integer.toString(remainingPoints));
		label.setText(Integer.toString(labelValue));
	}

	private void updateHeroPanelView(Character hero) {

		if (this.dashboard != null) {
			leftColumn.remove(this.dashboard);
		}
		dashboard = characterView.dashboard(hero, 650, 220);
		leftColumn.add(dashboard);
		leftColumn.revalidate();
		leftColumn.repaint();
	}

	private void updateHeroPreview(Character hero) {
		
		if (this.preview != null) {
			rightColumn.remove(this.preview);
		}
		preview = characterView.preview(hero, 340, 500);
		rightColumn.add(preview);
		rightColumn.revalidate();
		rightColumn.repaint();
	}

	public void updateHeroView(Character hero) {
		updateHeroPanelView(hero);
		updateHeroPreview(hero);
	}

	public String getHeroName() {
		return heroName.getText();
	}

	public int getAttackPoints() {
		return Integer.parseInt(attackPointsLabel.getText());
	}

	public int getDefensePoints() {
		return Integer.parseInt(defensePointsJLabel.getText());
	}

	public int getHitPoints() {
		return Integer.parseInt(hitPointsLabel.getText());
	}

	public int getSelectedSprite() {
		return selectedSprite;
	}

	public CharacterClass getHeroClass() {
		var labelText = heroClassLabel.getText();

		if (labelText == "Assassin") {
			return CharacterClass.ASSASSIN;
		} else if (labelText == "Bruiser") {
			return CharacterClass.BRUISER;
		} else {
			return CharacterClass.TANK;
		}
	}

	public void	displayErrorPopUp(String text, String title) {
		JOptionPane.showMessageDialog(mainScreen, text, title, JOptionPane.ERROR_MESSAGE);
	}

}
