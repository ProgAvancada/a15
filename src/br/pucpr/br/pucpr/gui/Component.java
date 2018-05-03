package br.pucpr.br.pucpr.gui;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Representa um componente grafico.
 */
public interface Component extends MouseListener, MouseMotionListener {

    int getX();
    int getY();
    int getW();
    int getH();

    void paint(Graphics2D g);

    boolean isHovered();

    void setPosition(int x, int y);
}
