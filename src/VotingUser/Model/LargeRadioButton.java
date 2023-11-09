package VotingUser.Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.AbstractButton;
import javax.swing.Icon;

class LargerRadioButtonIcon implements Icon {
    private static final int SIZE = 30;
    private Color color;

    public LargerRadioButtonIcon() {
        this(Color.BLACK); // Default color
    }

    public LargerRadioButtonIcon(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        boolean selected = (c instanceof AbstractButton) && ((AbstractButton) c).isSelected();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, SIZE, SIZE);

        g2d.setColor(selected ? Color.RED : color); // Change the color when selected

        g2d.fill(circle);
    }

    @Override
    public int getIconWidth() {
        return SIZE;
    }

    @Override
    public int getIconHeight() {
        return SIZE;
    }
}