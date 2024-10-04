package tc.tlouro_c.swingy;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tc.tlouro_c.swingy.utils.Sprite;

public class Swingy 
{
    public static void main( String[] args )
    {
        Frame frame = new Frame();
		frame.setLayout(null);


		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 250, 250);

		JLabel label = new JLabel("Mewtoo");
		Sprite sprite = new Sprite(false, 2);
		ImageIcon spriteImg = new ImageIcon(sprite.getPreviewSprite().getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		label.setIcon(spriteImg);

		panel.add(label);
		frame.add(panel);
		frame.setVisible(true);
    }
}
