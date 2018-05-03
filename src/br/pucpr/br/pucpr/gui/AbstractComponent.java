package br.pucpr.br.pucpr.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Implementacao padrao do componente grafico. Ja inclui as coordenadas de tela e teste se o componente esta "hovered".
 */
public abstract class AbstractComponent implements Component {
    protected int x, y, w, h;
    protected boolean isHovered;

    public AbstractComponent(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public abstract void paint(Graphics2D g);

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    private int getX2() {
        return x + w;
    }

    private int getY2() {
        return y + h;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        isHovered = e.getX() > x && e.getX() < getX2()
                && e.getY() > y && e.getY() < getY2();
    }

    @Override
    public boolean isHovered() {
        return isHovered;
    }
}
