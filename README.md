# Decorator e Proxy Dinâmico


O exercício da aula passada propunha a criação de um mecanismo que permitisse ao programador  colorir qualquer 
componente de tela. Mas pedia para fazer isso sem alterar a interface Component ou qualquer classe já criada.

Em aula, resolvemos o exercício através do padrão de projeto Decorator. 

## Decorator

Implementamos o objeto DebugDecorator, que implementa a Interface component. Ao invés de conter uma implementação 
completa de um componente, esse objeto encapsula um componente qualquer em seu interior, repassa todas as funções para 
ele, mas realiza a pintura com a camada colorida sobreposta.

Assim, o decorator é usado da seguinte forma:

```java
//Componente que vamos decorar
Button btn = new Button(10,10,100,50);
//Camada vermelha
DebugDecorator layer = new DebugDecorator(btn, new Color(255,0,0,127));
painel.add(layer);
```

Observe que quando o painel chamar o método paint a invocação será:
painel.paint -> layer.paint -> btn.paint

## Proxy dinamico

Muitas vezes precisamos adicionar a mesma funcionalidade em todos os métodos do decorator. Por exemplo, suponhamos que 
precisemos registrar o tempo que cada método da interface Component executou. Faríamos algo parecido com:

```java

public void paint(Graphics g) {
    long before = System.nanoTime() //Pega o tempo do sistema
    decorated.paint(g); //Chama o método sendo medido
    long time = System.nanoTime() - before; //Calcula o tempo que o método levou para executar
    System.out.println("O método paint levou " + time + " para executar.");
}
```

Um decorator teria que realizar essa implementação em todos os métodos. Os proxies dinamicos facilitam esse processo.
Ao gerar um proxy dinamico, toda chamada a um método de uma determinada interface chamará um método chamado invoke, 
que contem:
1. Uma instancia do objeto que fez a chamada
1. Um objeto do tipo Method, que descreve qual método foi chamado
1. Uma lista de Objects, com os parametros desse método.

Através do comando Proxy.newProxyInstance, cria-se um proxy desses. Confira a implementação na classe App.