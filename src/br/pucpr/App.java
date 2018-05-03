package br.pucpr;

import br.pucpr.br.pucpr.gui.Button;
import br.pucpr.br.pucpr.gui.Checkbox;
import br.pucpr.br.pucpr.gui.Component;
import br.pucpr.br.pucpr.gui.*;
import br.pucpr.br.pucpr.gui.Panel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Proxy;

public class App extends JComponent {

    private Panel main = new Panel(10, 10, 400, 400);
    private Panel rl = new Panel(0,0, 300, 150);
    private Panel lr = new Panel(0,0, 300, 150);

    public Component log(Component decorated) {
        return (Component) Proxy.newProxyInstance(getClass().getClassLoader(),
            new Class[] { Component.class },
            (obj, method, args) -> {
                long before = System.nanoTime(); //Pega o tempo do sistema
                Object ret = method.invoke(decorated, args); //Chama o método no objeto decorado
                long time = System.nanoTime() - before; //Calcula o tempo que levou
                //Gera a mensagem de log
                System.out.println("O método " + method.getName() + " levou " + time + " para executar.");
                return ret;
            });
    }

    public App() {
        addMouseMotionListener(main);
        addMouseListener(main);

        //Chamadas ao JPanel utilizando a tecnica de invocation chaining.
        main.setLayout(LayoutStrategy.TOP_DOWN)
                .add(new DebugDecorator(rl, Color.YELLOW))
                .add(log(lr)); //Logaremos os tempos do painel lr.

        //Painel da direita para esquerda com 3 checkboxes
        rl.setLayout(LayoutStrategy.RIGHT_LEFT)
                .add(new Checkbox(10, 10, 10, 10))
                .add(new Checkbox(10, 10, 40, 40))
                .add(new DebugDecorator(new Checkbox(10, 10, 50, 50), Color.RED));

        //Painel da esquerda para a direita com 3 botoes
        lr.setLayout(LayoutStrategy.LEFT_RIGHT)
                .add(new Button(10, 10, 20, 20))
                .add(new Button(10, 10, 30, 30))
                .add(new Button(10, 10, 60, 60));
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Observe que so o painel principal é desenhado. Os demais serao comandados atraves do composite.
        Graphics2D g2d = (Graphics2D) g.create();
        main.paint(g2d);
        g2d.dispose();

        repaint();
    }
}
