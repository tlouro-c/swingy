package tc.tlouro_c.swingy.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SuperJPanel extends JPanel {

	private int height;
	private int width;

	public SuperJPanel(int width, int height, LayoutManager layout) {
		this.width = width;
		this.height = height;
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(width, height);
		this.setBackground(new Color(255,255,255, 0));
        this.setOpaque(false);  // Set to false to allow transparency

		// ! Development only
		//  this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}

	public static void setFont(JComponent element, Font font, Color color) {
		element.setFont(font);
		element.setForeground(color);
	}

	public static JLabel icon(ImageIcon icon) {
		var iconLabel = new JLabel(icon);

		// ! Development only
		// iconLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		return iconLabel;
	}

	public JLabel titleLabel(String text, ImageIcon icon, double widthRatio) {
		var titleLabel = new JLabel(text);
		titleLabel.setFont(new Font("", Font.BOLD, 20));
		titleLabel.setForeground(Color.white);
		if (widthRatio != -1) {
			var dimension = new Dimension((int)(width * widthRatio), titleLabel.getPreferredSize().height);
			titleLabel.setPreferredSize(dimension);
			titleLabel.setSize(dimension);
		}
		if (icon != null) {
			titleLabel.setIcon(icon);
		}

		// ! Development only
		// titleLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		return titleLabel;
	}

	public static JLabel titleLabel(String text, ImageIcon icon) {
		var titleLabel = new JLabel(text);
		titleLabel.setFont(new Font("", Font.BOLD, 20));
		titleLabel.setForeground(Color.white);

		if (icon != null) {
			titleLabel.setIcon(icon);
		}

		// ! Development only
		// titleLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		return titleLabel;
	}

	public JLabel textLabel(String text, ImageIcon icon, double widthRatio) {
		var textLabel = new JLabel(text);
		textLabel.setFont(new Font("", Font.PLAIN, 12));
		textLabel.setForeground(Color.white);
		if (widthRatio != -1) {
			var dimension = new Dimension((int)(width * widthRatio), textLabel.getPreferredSize().height);
			textLabel.setPreferredSize(dimension);
			textLabel.setSize(dimension);
		}
		if (icon != null) {
			textLabel.setIcon(icon);
		}

		// ! Development only
		// textLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		return textLabel;
	}

	public static JLabel textLabel(String text, ImageIcon icon) {
		var textLabel = new JLabel(text);
		textLabel.setFont(new Font("", Font.PLAIN, 12));
		textLabel.setForeground(Color.white);

		if (icon != null) {
			textLabel.setIcon(icon);
		}

		// ! Development only
		// textLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		return textLabel;
	}

	public static CustomProgressBar progressBar(int value, int max, Color fgColor, Color bgColor) {
		CustomProgressBar progressBar = new CustomProgressBar(0, max, fgColor, bgColor);

		progressBar.setValue(value);
		var dimension = new Dimension(120, 8);
		progressBar.setPreferredSize(dimension);
		progressBar.setSize(dimension);

		return progressBar;
	}

	public static void addMargin(JComponent element, int top, int left, int bottom, int right) {
		element.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}

	public static JButton button(String text, int width, int height) {

		var newBtn = new JButton(text);
		var dimensions = new Dimension(width, height);
		newBtn.setPreferredSize(dimensions);
		newBtn.setSize(dimensions);
		return	newBtn;
	}

	public static JButton iconButton(ImageIcon icon, int width, int height) {

		var newBtn = new JButton(icon);
		var dimensions = new Dimension(width, height);
		newBtn.setPreferredSize(dimensions);
		newBtn.setSize(dimensions);
		return	newBtn;
	}

	public JTextField input(String placeholder, double widthRatio) {

		var newInput = new JTextField(10);
		newInput.setText(placeholder);
		if (widthRatio != -1) {
			var dimension = new Dimension((int)(width * widthRatio), newInput.getPreferredSize().height);
			newInput.setPreferredSize(dimension);
			newInput.setSize(dimension);
		}
		return	newInput;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
