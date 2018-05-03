package br.pucpr.br.pucpr.gui;

import java.util.List;

/**
 * Define as estrategias de layout que o painel utiliza. Cada item da enumeracao e um objeto, filho de LayoutStrategy.
 * Observe a sobrescrita do metodo adjust em cada filho, permitindo polimorfismo.
 */
public enum LayoutStrategy {
    TOP_DOWN {
        @Override
        public void adjust(List<Component> components, Panel panel) {
            int y = panel.getY();

            for (Component c : components) {
                c.setPosition(panel.getX(), y);
                y += c.getH() + 5;
            }
        }
    }, LEFT_RIGHT {
        @Override
        public void adjust(List<Component> components, Panel panel) {
            int x = panel.getX();
            for (Component c : components) {
                c.setPosition(x, panel.getY());
                x += c.getW() + 5;
            }
        }
    }, RIGHT_LEFT {
        @Override
        public void adjust(List<Component> components, Panel panel) {
            int x = panel.getX() + panel.getW();
            for (Component c : components) {
                int cx = x - c.getW();
                c.setPosition(cx, panel.getY());
                x = cx - 5;
            }

        }
    };

    //Definicao do metodo abstrato adjust. Vale para todos os itens da enumeracao
    public abstract void adjust(List<Component> components, Panel panel);

}
