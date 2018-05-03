package br.pucpr.br.pucpr.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends AbstractComponent {
    private boolean isDown = false;
    private ButtonListener listener;

    public Button(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    private void fireButtonClicked() {
        if (listener != null) {
            listener.onClick(this);
        }
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(isDown ? Color.BLACK : Color.WHITE);
        g.drawLine(x, y, x+w, y);
        g.drawLine(x, y, x, y+h);

        g.setColor(isDown ? Color.WHITE : Color.BLACK);
        g.drawLine(x, y+h, x+w, y+h);
        g.drawLine(x+w, y, x+w, y+h);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, w, h);

    }

    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDown = isHovered();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDown && isHovered()) {
            fireButtonClicked();
        }
        isDown = false;
    }
}
