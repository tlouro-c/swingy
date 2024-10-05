package tc.tlouro_c.swingy.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tc.tlouro_c.swingy.models.Character;
import tc.tlouro_c.swingy.utils.Sprite;
import tc.tlouro_c.swingy.utils.SuperJPanel;

public class HeroSelectView extends SuperJPanel {
	
	private CharacterView characterView;
	private SuperJPanel dashboard;
	private SuperJPanel preview;
	private SuperJPanel leftColumn;
	private SuperJPanel rightColumn;
	private SuperJPanel mainScreen;
	private JButton createHeroBtn;
	private JButton selectExistingHeroBtn;
	private JTextField heroName;
	private JLabel attackPointsLabel;
	private JLabel defensePointsJLabel;
	private JLabel hitPointsLabel;
	private JLabel heroClassLabel;
	private JLabel remainingPointsLabel;

	public HeroSelectView() {
		super(1020, 738, new FlowLayout(FlowLayout.LEFT, 3, 3));
		characterView = new CharacterView();
		leftColumn = new SuperJPanel(660, 730, getLayout());
		rightColumn = new SuperJPanel(350, 730, getLayout());
		mainScreen = new SuperJPanel(650, 500, new FlowLayout(FlowLayout.CENTER, 0, 250));
		loadInitialButtons();

		leftColumn.add(mainScreen);
		this.add(leftColumn);
		this.add(rightColumn);
	}

	private void loadInitialButtons() {
		createHeroBtn = SuperJPanel.button("Create a new hero", 200, 50);
		createHeroBtn.addActionListener(e -> loadCreateHeroForm());


		selectExistingHeroBtn = SuperJPanel.button("Select an existing hero", 200, 50);
		mainScreen.add(createHeroBtn);
		mainScreen.add(selectExistingHeroBtn);
	}

	private void hideInitialButtons() {
		createHeroBtn.setVisible(false);
		selectExistingHeroBtn.setVisible(false);
	}

	private void unhideInitialButtons() {
		createHeroBtn.setVisible(true);
		selectExistingHeroBtn.setVisible(true);
	}

	private void loadCreateHeroForm() {

		mainScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		hideInitialButtons();

		
		remainingPointsLabel = mainScreen.titleLabel("30", null, 1);

		loadHeader();
		loadHeroClassChoice();
		loadNameInput();
		loadStatsInputs();
		
		
	}

	private void loadHeader() {
		var header = new SuperJPanel(mainScreen.getWidth(), 120, new FlowLayout(FlowLayout.CENTER, 0, 5));

		var createNewHeroLabel = mainScreen.titleLabel("Create A New Hero", null, 1);
		var dimension = new Dimension(mainScreen.getWidth(), 50);
		createNewHeroLabel.setPreferredSize(dimension);
		createNewHeroLabel.setSize(dimension);
		createNewHeroLabel.setHorizontalAlignment(JLabel.CENTER);
		createNewHeroLabel.setVerticalAlignment(JLabel.CENTER);

		var descriptionLabel1 = header.textLabel("Each hero begins with all base stats set to 60.", null, 1);
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

		var label = container.textLabel("Bruiser", null, 0.1);
		label.setHorizontalAlignment(JLabel.CENTER);
		var choiceLeftBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/decrease.png", 20, 20), 30, 30);
		var choiceRightBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/increase.png", 20, 20), 30, 30);

		AtomicInteger index = new AtomicInteger(0);
		choiceLeftBtn.addActionListener(e -> choiceLeftBtn(label, index));
		choiceRightBtn.addActionListener(e -> choiceRightBtn(label, index));

		container.add(choiceLeftBtn);
		container.add(label);
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

		var helperLabel = mainScreen.textLabel("Remaining Points:", null, -1);
		helperLabel.setHorizontalAlignment(JLabel.CENTER);
		remainingPointsLabel = mainScreen.textLabel("30", null, 0.1);
		remainingPointsLabel.setHorizontalAlignment(JLabel.CENTER);
		attackPointsLabel = mainScreen.titleLabel("0", null, 0.1);
		defensePointsJLabel = mainScreen.titleLabel("0", null, 0.1);
		hitPointsLabel = mainScreen.titleLabel("0", null, 0.1);

		var labels = new JLabel[] {attackPointsLabel, defensePointsJLabel, hitPointsLabel};

		for (JLabel label : labels) {
			label.setHorizontalAlignment(JLabel.CENTER);

			var container = new SuperJPanel(mainScreen.getWidth(), 30, new FlowLayout(FlowLayout.CENTER, 20, 0));
			var decreaseBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/decrease.png", 20, 20), 30, 30);
			decreaseBtn.setEnabled(false);
			var increaseBtn = SuperJPanel.iconButton(Sprite.scaledImage("/icons/increase.png", 20, 20), 30, 30);

			decreaseBtn.addActionListener(e -> decreaseLabelValue(label, increaseBtn, decreaseBtn));
			increaseBtn.addActionListener(e -> increaseLabelValue(label, increaseBtn, decreaseBtn));

			container.add(decreaseBtn);
			container.add(label);
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

}
