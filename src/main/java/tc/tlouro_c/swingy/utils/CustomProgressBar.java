package tc.tlouro_c.swingy.utils;

import java.awt.*;
import javax.swing.*;

public class CustomProgressBar extends JProgressBar {

    private Color fgColor;
    private Color bgColor;

    public CustomProgressBar(int min, int max, Color fgColor, Color bgColor) {
        super(min, max);
        this.fgColor = fgColor;
        this.bgColor = bgColor;
		this.setBorder(BorderFactory.createLineBorder(bgColor, 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Call the superclass method
        super.paintComponent(g);
        
        // Get the current value and the maximum value
        int value = getValue();
        int width = getWidth();
        int height = getHeight();
        
        // Draw the background
        g.setColor(bgColor);
        g.fillRoundRect(0, 0, width, height, 25, 25);
        
        // Draw the progress
        int progressWidth = (int) ((value / (double) getMaximum()) * width);
        g.setColor(fgColor);
        g.fillRoundRect(0, 0, progressWidth, height, 25, 25);
  
        // Draw the string if enabled
        if (isStringPainted()) {
            String text = getString();
            g.setColor(Color.BLACK); // Change this to customize text color
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getAscent();
            g.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 2);
        }
    }
}
