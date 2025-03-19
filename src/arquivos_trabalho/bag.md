<img src="imgs/UNIFOR_logo1b.png" width="400"> 

# T290 Resolução de Probelmas com Grafos (T290)
**Orientador**: Prof. Me Ricardo Carubbi

# Explicação da Classe `Bag`

## 1. Propósito da Classe
A classe `Bag<Item>` representa uma **estrutura de dados não ordenados** de itens genéricos, permitindo adição de elementos e iterações, contudo sem remoção.

- Utiliza uma **lista encadeada** para armazenar os itens.
- Suporta **iteração** sobre os itens na ordem de inserção reversa, pois o último itens adicionado será o primeiro na `bag`.
- Implementa a interface **Iterable<Item>**, permitindo o uso de loops baseados em iteração.

## 2. Construtor

### **Construtor: `public Bag()`**
```java
public Bag() {
    first = null;
    n = 0;
}
```
**Objetivo:** Inicializa uma nova instância da classe `Bag`, criando uma lista vazia.

**Análise Assintótica:** $\Theta(1)$ - Inicializa a lista encadeada definindo `first` como `null` e `n` como `0`.

**Equivalente em Python:**
```python
class Bag:
    def __init__(self):
        self.first = None
        self.n = 0
```

## 3. Principais Métodos

### **Verificar se está vazia: `public boolean isEmpty()`**
```java
public boolean isEmpty() {
    return first == null;
}
```
**Objetivo:** Retorna `true` se a `bag` estiver vazia, caso contrário, retorna `false`.

**Análise Assintótica:** $\Theta(1)$ - Verifica diretamente se o ponteiro `first` é `null`.

**Equivalente em Python:**
```python
def is_empty(self):
    return self.first is None
```

### **Retornar tamanho: `public int size()`**
```java
public int size() {
    return n;
}
```
**Objetivo:** Retorna o número total de elementos armazenados na `Bag`.

**Análise Assintótica:** $\Theta(1)$ - Retorna o valor armazenado de `n`.

**Equivalente em Python:**
```python
def size(self):
    return self.n
```

### **Adicionar elemento: `public void add(Item item)`**
```java
public void add(Item item) {
    Node<Item> oldfirst = first;
    first = new Node<Item>();
    first.item = item;
    first.next = oldfirst;
    n++;
}
```
**Objetivo:** Adiciona um novo elemento no início da lista encadeada.

**Análise Assintótica:** $\Theta(1)$ - Insere um novo item no início da lista encadeada, alterando apenas ponteiros.

**Equivalente em Python:**
```python
def add(self, item):
    old_first = self.first
    self.first = Node(item)
    self.first.next = old_first
    self.n += 1
```

### **Iterador: `public Iterator<Item> iterator()`**
```java
public Iterator<Item> iterator() {
    return new LinkedIterator(first);
}
```
**Objetivo:** Retorna um iterador para percorrer os elementos armazenados na `Bag`.

**Análise Assintótica:** $\Theta(1)$ - Retorna um novo iterador a partir da posição `first`.

**Equivalente em Python:**
```python
def __iter__(self):
    return BagIterator(self.first)
```

### **Classe Interna `Node<Item>`**
```java
private static class Node<Item> {
    private Item item;
    private Node<Item> next;
}
```
**Objetivo:** Define um nó da lista encadeada, contendo um item e um ponteiro para o próximo elemento.

**Análise Assintótica:** $\Theta(1)$ - Cria um novo nó contendo um elemento e referência para o próximo.

**Equivalente em Python:**
```python
class Node:
    def __init__(self, item):
        self.item = item
        self.next = None
```

### **Classe Interna `LinkedIterator`**
```java
private class LinkedIterator implements Iterator<Item> {
    private Node<Item> current;

    public LinkedIterator(Node<Item> first) {
        current = first;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
    }
}
```
**Objetivo:** Implementa um iterador para percorrer os elementos da `Bag`.

**Análise Assintótica:**
- **`hasNext()`**: $\Theta(1)$ - Verifica se `current` é `null`.
- **`next()`**: $\Theta(1)$ - Retorna o item atual e avança o ponteiro para o próximo nó.

**Equivalente em Python:**
```python
class BagIterator:
    def __init__(self, first):
        self.current = first

    def __iter__(self):
        return self

    def __next__(self):
        if self.current is None:
            raise StopIteration
        item = self.current.item
        self.current = self.current.next
        return item
```