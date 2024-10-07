package tc.tlouro_c.swingy.views;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

import tc.tlouro_c.swingy.models.Artifact;
import tc.tlouro_c.swingy.models.Character;
import tc.tlouro_c.swingy.utils.Sprite;
import tc.tlouro_c.swingy.utils.SuperJPanel;

public class CharacterView {

	private JLabel nameLabel;
	private JLabel avatarLabel;
	private JLabel heroClassLabel;
	private JLabel attackLabel;
	private JLabel bonusAttackLabel;
	private JLabel defenseLabel;
	private JLabel bonusDefenseLabel;
	private JProgressBar healthBar;
	private JLabel healthLabel;
	private JLabel bonusHealthLabel;
	private JProgressBar levelBar;
	private JLabel levelLabel;
	private JLabel artifactIcon;
	private JLabel previewSprite;

	public CharacterView() {
	}

	public SuperJPanel dashboard(Character c, int width, int height) {

		var d = new SuperJPanel(width, height, new FlowLayout(FlowLayout.LEFT, 0, 10));

		var header = new SuperJPanel(width - 100, 90, new FlowLayout(FlowLayout.LEFT, 0, 0));
		var lvlAndHpBar = new SuperJPanel(width - 100, 48, new FlowLayout(FlowLayout.LEFT, 0, 10));
		var attackAndDefense = new SuperJPanel(width / 2 - 10, 100, new FlowLayout(FlowLayout.LEFT, 0, 15));

		createLevelBar(c.getLevel(), c.getCurrentXP() , c.getLvlUpXP());
		createHpBar(c.getCurrentHP(), c.getMaxHP());
		createAvatar(c.getMapSprite(90, 90));
		nameLabel = header.titleLabel(c.getName(), null, 1);
		heroClassLabel = header.textLabel(c.getCharacterClass().toString(), null, 1);
		attackLabel = d.titleLabel(Integer.toString(c.getAttack()), getStatsIcon("attack"), 0.5);
		SuperJPanel.addMargin(attackLabel, 0, 15, 0, 0);
		defenseLabel = d.titleLabel(Integer.toString(c.getDefense()), getStatsIcon("defense"), 0.5);
		SuperJPanel.addMargin(defenseLabel, 0, 15, 0, 0);

		lvlAndHpBar.add(levelLabel);
		lvlAndHpBar.add(levelBar);
		lvlAndHpBar.add(healthLabel);
		lvlAndHpBar.add(healthBar);
		header.add(nameLabel);
		header.add(heroClassLabel);
		header.add(lvlAndHpBar);
		attackAndDefense.add(attackLabel);
		attackAndDefense.add(defenseLabel);
		d.add(avatarLabel);
		d.add(header);
		d.add(attackAndDefense);
		d.add(newInventory(c.getArtifact(), c.getMaxHP(), c.getAttack(), c.getDefense(), d.getWidth()));

		return d;
	}

	public SuperJPanel preview(Character c, int width, int height) {
		var preview = new SuperJPanel(width, height, new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.WHITE, 8);
		preview.setBorder(border);
		
		previewSprite = SuperJPanel.icon(c.getPreviewSprite(height, width));
		preview.add(previewSprite, BorderLayout.CENTER);

		return preview;
	}

	private ImageIcon getStatsIcon(String stat) {
		return Sprite.scaledImage("/icons/" + stat + ".png", 20, 20);
	}

	private SuperJPanel newInventory(Artifact artifact, int hitPoints, int attack, int defense, int panelWidth) {

		var inventory = new SuperJPanel(panelWidth / 2, 100, new FlowLayout(FlowLayout.LEFT, 12, 12));
		var inventorySlot = new SuperJPanel(70, 70, new FlowLayout(FlowLayout.CENTER, 0, 5));
		var inventoryLabels = new SuperJPanel(150, 70, new FlowLayout(FlowLayout.LEFT, 0, 4));

		inventorySlot.setBackground(Color.decode("#E0E0E0"));
		inventorySlot.setOpaque(true);
		inventorySlot.setBorder(new LineBorder(new Color(0x696969), 5));
		inventory.add(inventorySlot);

		createInventoryLabels(artifact, hitPoints, attack, defense);

		if (artifact != null) {
			inventorySlot.add(artifactIcon);
		}

		inventoryLabels.add(bonusAttackLabel);
		inventoryLabels.add(bonusDefenseLabel);
		inventoryLabels.add(bonusHealthLabel);
		inventory.add(inventoryLabels);
		return inventory;
	}

	private void createLevelBar(int level, int currentXP, int levelUpXP) {
		levelBar = SuperJPanel.progressBar(currentXP, levelUpXP, Color.decode("#007FFF"), Color.decode("#6CB4EE"));
		levelLabel = SuperJPanel.textLabel("Lv. " + Integer.toString(level), null);
		SuperJPanel.addMargin(levelLabel, 0, 0, 0, 5);
	}

	private void updateLevelBar(int level, int currentXP, int levelUpXP) {
		levelBar.setValue(currentXP);
		levelBar.setMaximum(levelUpXP);
		levelLabel.setText("Lv. " + Integer.toString(level));
	}

	private void createHpBar(int currentHP, int maxHP) {
		healthBar = SuperJPanel.progressBar(currentHP, maxHP, Color.decode("#00AB66"), Color.decode("#ACE1AF"));
		healthLabel = SuperJPanel.textLabel("HP: " + Integer.toString(currentHP) + "/" + Integer.toString(maxHP), null);
		SuperJPanel.addMargin(healthLabel, 0, 20, 0, 5);
	}

	private void updateHpBar(int currentHP, int maxHP) {
		healthBar.setValue(currentHP);
		healthBar.setMaximum(maxHP);
		healthLabel.setText("HP: " + Integer.toString(currentHP) + "/" + Integer.toString(maxHP));
	}

	private void updateStats(int attack, int defense, int currentHP, int maxHP) {
		attackLabel.setText(Integer.toString(attack));
		defenseLabel.setText(Integer.toString(defense));
		updateHpBar(currentHP, maxHP);
	}

	private void createAvatar(ImageIcon avatar) {
		avatarLabel = SuperJPanel.icon(avatar);
		SuperJPanel.addMargin(avatarLabel, -30, 0, 0, 0);
	}

	public void updateDashboard(Character c) {
		var attack = c.getAttack();
		var defense = c.getDefense();
		var currentHP = c.getCurrentHP();
		var maxHP = c.getMaxHP();

		updateStats(attack, defense, currentHP, maxHP);
		updateLevelBar(c.getLevel(), c.getCurrentXP(), c.getLvlUpXP());
		updateInventory(c.getArtifact(), maxHP, attack, defense);
	}

	public void createInventoryLabels(Artifact artifact, int hitPoints, int attack, int defense) {

		int bonusAttack = 0;
		int bonusDefense = 0;
		int bonusMaxHP = 0;

		if (artifact != null) {
			artifactIcon = SuperJPanel.icon(Sprite.scaledImage(artifact.getSprite().getPath(), 50, 50));
			bonusAttack = (int)(attack * artifact.getAttackMultiplier()) - attack;
			bonusDefense = (int)(defense * artifact.getDefenseMultiplier()) - defense;
			bonusMaxHP = (int)(hitPoints * artifact.getMaxHPMultiplier()) - hitPoints;
		}

		bonusAttackLabel = SuperJPanel.textLabel("+" + Integer.toString(bonusAttack) + " Bonus Attack", null);
		bonusDefenseLabel = SuperJPanel.textLabel("+" + Integer.toString(bonusDefense) + " Bonus Defense", null);
		bonusHealthLabel = SuperJPanel.textLabel("+" + Integer.toString(bonusMaxHP) + " Bonus HP", null);
	}

	public void updateInventory(Artifact artifact, int hitPoints, int attack, int defense) {

		int bonusAttack = 0;
		int bonusDefense = 0;
		int bonusMaxHP = 0;

		if (artifact != null) {
			artifactIcon.setIcon(Sprite.scaledImage(artifact.getSprite().getPath(), 50, 50));
			bonusAttack = (int)(attack * artifact.getAttackMultiplier()) - attack;
			bonusDefense = (int)(defense * artifact.getDefenseMultiplier()) - defense;
			bonusMaxHP = (int)(hitPoints * artifact.getMaxHPMultiplier()) - hitPoints;
		}
		bonusAttackLabel.setText("+" + Integer.toString(bonusAttack) + " Bonus Attack");
		bonusDefenseLabel.setText("+" + Integer.toString(bonusDefense) + " Bonus Defense");
		bonusHealthLabel.setText("+" + Integer.toString(bonusMaxHP) + " Bonus HP");
	}
}
