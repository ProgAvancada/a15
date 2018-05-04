# Decorator e Proxy Dinâmico


O exercício da aula passada propunha a criação de um mecanismo que permitisse ao programador  colorir qualquer 
componente de tela. Mas pedia para fazer isso sem alterar a interface Component ou qualquer classe já criada.

Em aula, resolvemos o exercício através do padrão de projeto Decorator. 

## Decorator

Implementamos o objeto 
[DebugDecorator](https://github.com/ProgAvancada/a15/blob/master/src/br/pucpr/br/pucpr/gui/DebugDecorator.java), que 
implementa a Interface component. Ao invés de conter uma implementação completa de um componente, esse objeto encapsula 
um componente qualquer em seu interior, repassa todas as funções para ele, mas realiza a pintura com a camada colorida 
sobreposta.

Assim, o decorator é usado da seguinte forma:

```java
//Componente que vamos decorar
Button btn = new Button(10,10,100,50);
//Camada vermelha
DebugDecorator layer = new DebugDecorator(btn, Color.RED);
painel.add(layer);
```

Observe que quando o painel chamar o método paint a invocação será:

painel.paint -> layer.paint -> btn.paint

### Decorator no Java

No Java, há um uso bastante interessante do padrão Decorator, através dos streams. Isso permite manter a criação 
de novos streams simples, enquanto a API fornece recursos avançados como buffers, acesso a dados primitivos, 
criptografia ou compressão.

O java define as interfaces [InputStream](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html) e 
[OutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html) que definem um conjunto básico de 
métodos para qualquer stream "bruto", que lidam simplesmente com arrays de bytes.

A API também define uma série de decoradores, com funções avançadas. Por exemplo, a classe 
[BufferedInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html) associa 
um buffer ao stream que ela decora, enquanto a classe 
[DataInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/DataInputStream.html) permite fazer inserções com 
tipos de dados primitivos (float, double, int, etc). Há até a classe 
[CypherInputStream](https://docs.oracle.com/javase/8/docs/api/javax/crypto/CipherInputStream.html), que permite 
adicionar criptografia aos dados.

Todos esses streams podem ser adicionados a qualquer implementação "real" de InputStream, como a 
[FileInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html) (para arquivos) ou a um 
InputStream de um [Socket](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html), para comunicação na internet.

Veja um exemplo:
```java
//Criamos um fileInputStream para ler de um arquivo. 
FileInputStream fis = new FileInputStream(new File("dados.dat"));
//Sem buffer, seria ineficiente, por isso o decoramos com um BufferedInputStream:
BufferedInputStream bis = new BufferedInputStream(fis);
//Para ler com mais facilidade, decoramos o buffer com um DataInputStream:
DataInputStream dis = new DataInputStream(bis);

//Agora fazermos a leitura:
float valor = dis.readFloat(); 
```

Observe que essa estratégia permite associar facilmente outros decoradores, em praticamente qualquer ordem.

## Proxy dinâmico

Muitas vezes precisamos adicionar a mesma funcionalidade em todos os métodos do decorator. Por exemplo, suponhamos que 
precisemos registrar o tempo que cada método da interface 
[Component](https://github.com/ProgAvancada/a15/blob/master/src/br/pucpr/br/pucpr/gui/Component.java) executou. 
Faríamos algo parecido com:

```java

public void paint(Graphics g) {
    long before = System.nanoTime() //Pega o tempo do sistema
    decorated.paint(g); //Chama o método sendo medido
    long time = System.nanoTime() - before; //Calcula o tempo que o método levou para executar
    System.out.println("O método paint levou " + time + " para executar.");
}
```

Um decorator teria que realizar essa implementação em todos os métodos. Os proxies dinamicos facilitam esse processo.
Ao utilizar a classe [Proxy](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html) para gerar um proxy
 dinâmico, toda chamada a um método da interface "proxiada" será delegado ao método invoke da interface 
[InvocationHandler](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html), 
que recebe como parâmetros:

1. Uma instância do objeto que fez a chamada
1. Um objeto do tipo [Method](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html), que 
descreve qual método foi chamado e permite chamá-lo
1. Uma lista de Objects, com os parâmetros desse método.

Através do comando Proxy.newProxyInstance, cria-se um proxy desses. Confira a implementação no método log da classe 
[App](https://github.com/ProgAvancada/a15/blob/master/src/br/pucpr/App.java).