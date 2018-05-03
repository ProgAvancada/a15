package br.pucpr.br.pucpr.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe do painel. Utiliza o padrao Strategy, ao implementar a interface Component. Como todo painel e um componente
 * ele pode ser incluido dentro de outros paineis.
 *
 * A politica de dimensionamento dos componentes dentro do painel pode ser escolhida utilizando um objeto do tipo
 * LayoutStrategy
 */
public class Panel implements Component{
    private List<Component> components = new ArrayList<>();
    private int x, y, w, h;
    private LayoutStrategy layout = LayoutStrategy.LEFT_RIGHT;

    public Panel(int x, int y, int w, int h) {
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

    /**
     * Adiciona um componente ao painel. A posicao x e y do componente será alterada no momento do desenho, de acordo
     * com o layout definido para o painel.
     * @param c O componente
     * @return O proprio painel. Essa e uma tecnica conhecida como Method Chaining. Isso permite que o add seja
     * chamado na forma painel.add(c1).add(c2). O metodo setLayout tambem a utiliza.
     */
    public Panel add(Component c) {
        components.add(c);
        return this;
    }

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

    @Override
    public void paint(Graphics2D g) {
        layout.adjust(components, this);
        for (Component c : components) {
            Graphics2D g2d = (Graphics2D) g.create();
            c.paint(g2d);
            g2d.dispose();
        }
    }

    @Override
    public boolean isHovered() {
        for (Component c : components) {
            if (c.isHovered()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Component c : components) {
            c.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Component c : components) {
            c.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Component c : components) {
            c.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (Component c : components) {
            c.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (Component c : components) {
            c.mouseExited(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (Component c : components) {
            c.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Component c : components) {
            c.mouseMoved(e);
        }
    }

    public Panel setLayout(LayoutStrategy layout) {
        this.layout = layout;
        return this;
    }

    public LayoutStrategy getLayout() {
        return layout;
    }
}
