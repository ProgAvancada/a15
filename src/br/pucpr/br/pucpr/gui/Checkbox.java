package br.pucpr.br.pucpr.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

public class Checkbox extends AbstractComponent {
    private boolean checked = false;

    public Checkbox(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);

        if (checked) {
            g.setColor(Color.GREEN.darker());
            g.fillRect(x, y, w, h);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2));
            Path2D check = new Path2D.Float();
            check.moveTo(x + 0.2*w, y + 0.6*h);
            check.lineTo(x + 0.3*w, y + 0.8*h);
            check.lineTo(x + 0.8*w, y + 0.2*h);
            g.draw(check);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isHovered) checked = !checked;
    }

    public boolean isChecked() {
        return checked;
    }
}
