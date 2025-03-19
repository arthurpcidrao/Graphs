<img src="imgs/UNIFOR_logo1b.png" width="400"> 

# Resolução de Probelmas com Grafos (T290)
**Orientador**: Prof. Me Ricardo Carubbi

# Explicação da Classe `Graph`

## 1. Propósito da Classe
A classe `Graph` representa um **grafo não direcionado** com vértices numerados de `0` a `V - 1`. Essa implementação utiliza **listas de adjacência** para armazenar as conexões entre os vértices.

- Suporte para **arestas paralelas** e **laços**.
- Eficiência na **iteração sobre vértices adjacentes**.
- Representação eficiente em termos de espaço, ocupando $\Theta(V + E)$.

## 2. Construtores

### **Construtor: `public Graph(int V)`**
```java
public Graph(int V) {
    if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
    this.V = V;
    this.E = 0;
    adj = (Bag<Integer>[]) new Bag[V];
    for (int v = 0; v < V; v++) {
        adj[v] = new Bag<Integer>();
    }
}
```
**Objetivo:** Inicializa um grafo vazio com `V` vértices e nenhuma aresta.

**Análise Assintótica:** $\Theta(V)$ - Inicializa um array de listas de adjacência, percorrendo todos os vértices.

### **Construtor: `public Graph(In in)`**
```java
public Graph(In in) {
    this.V = in.readInt();
    this.E = in.readInt();
    adj = (Bag<Integer>[]) new Bag[V];
    for (int v = 0; v < V; v++) {
        adj[v] = new Bag<Integer>();
    }
    for (int i = 0; i < E; i++) {
        int v = in.readInt();
        int w = in.readInt();
        addEdge(v, w);
    }
}
```
**Objetivo:** Constrói um grafo a partir de um fluxo de entrada contendo informações sobre vértices e arestas.

**Análise Assintótica:** $\Theta(V + E)$ - Lê os vértices e arestas da entrada e os insere no grafo.

### **Construtor: `public Graph(Graph G)`**
```java
public Graph(Graph G) {
    this.V = G.V();
    this.E = G.E();
    adj = (Bag<Integer>[]) new Bag[V];
    for (int v = 0; v < V; v++) {
        adj[v] = new Bag<Integer>();
    }
    for (int v = 0; v < G.V(); v++) {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int w : G.adj[v]) {
            reverse.push(w);
        }
        for (int w : reverse) {
            adj[v].add(w);
        }
    }
}
```
**Objetivo:** Cria uma cópia profunda de um grafo existente.

**Análise Assintótica:** $\Theta(V + E)$ - Duplica a estrutura do grafo original, preservando a ordem de adjacência.

## 3. Principais Métodos

### **Adicionar Aresta: `public void addEdge(int v, int w)`**
```java
public void addEdge(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    E++;
    adj[v].add(w);
    adj[w].add(v);
}
```
**Objetivo:** Adiciona uma aresta não direcionada entre os vértices `v` e `w`.

**Análise Assintótica:** $\Theta(1)$ - Cada adição em uma lista de adjacência ocorre em tempo constante.

### **Lista de Adjacência: `public Iterable<Integer> adj(int v)`**
```java
public Iterable<Integer> adj(int v) {
    validateVertex(v);
    return adj[v];
}
```
**Objetivo:** Retorna os vértices adjacentes ao vértice `v`.

**Análise Assintótica:** $\Theta(1)$ - Retorna a lista de adjacência armazenada.

### **Grau de um Vértice: `public int degree(int v)`**
```java
public int degree(int v) {
    validateVertex(v);
    return adj[v].size();
}
```
**Objetivo:** Retorna o grau do vértice `v`, ou seja, o número de arestas conectadas a ele.

**Análise Assintótica:** $\Theta(1)$ - Retorna o tamanho da lista de adjacência.

### **Representação em DOT: `public String toDot()`**
```java
public String toDot() {
    StringBuilder s = new StringBuilder();
    s.append("graph {\n");
    s.append("node[shape=circle, style=filled, fixedsize=true, width=0.3, fontsize=\"10pt\"]\n");
    int selfLoops = 0;
    for (int v = 0; v < V; v++) {
        for (int w : adj[v]) {
            if (v < w) {
                s.append(v + " -- " + w + "\n");
            }
            else if (v == w) {
                if (selfLoops % 2 == 0) {
                    s.append(v + " -- " + w + "\n");
                }
                selfLoops++;
            }
        }
    }
    s.append("}\n");
    return s.toString();
}
```
**Objetivo:** Retorna uma representação do grafo no formato DOT para visualização com Graphviz.

**Análise Assintótica:** $\Theta(V + E)$ - Percorre todos os vértices e suas listas de adjacência para construir a representação em DOT.

