package br.pucpr.br.pucpr.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Adiciona uma layer colorida ao componente decorado. Observe que o decorator repassa para o componente interno dele
 * a maior parte das funcionalidades. A única diferença fica no método paint, onde a pintura da camada de debug é feita
 */
public class DebugDecorator implements Component {
    private Component decorated; //Componente que será decorato
    private Color color;

    public DebugDecorator(Component decorated, Color color) {
        this.decorated = decorated;

        //Copia a cor passada com alfa em 50% para transparencia
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 127);
    }

    @Override
    public int getX() {
        return decorated.getX();
    }

    @Override
    public int getY() {
        return decorated.getY();
    }

    @Override
    public int getW() {
        return decorated.getW();
    }

    @Override
    public int getH() {
        return decorated.getH();
    }

    @Override
    public void paint(Graphics2D g) {
        decorated.paint(g);

        //Pinta a camada de debug
        g.setColor(color);
        g.fillRect(getX(), getY(), getW(), getH());
    }

    @Override
    public boolean isHovered() {
        return decorated.isHovered();
    }

    @Override
    public void setPosition(int x, int y) {
        decorated.setPosition(x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        decorated.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        decorated.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        decorated.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        decorated.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        decorated.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        decorated.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        decorated.mouseMoved(e);
    }
}
