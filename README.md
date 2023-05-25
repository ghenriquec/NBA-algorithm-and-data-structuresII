## Trabalho prático sobre Métodos de Ordenação

[Português](README.md) | [English](README_EN.md)

Este projeto tem como objetivo implementar e analisar o desempenho de seis algoritmos de ordenação para a disciplina de Algoritimo e Estrutura de Dados 2

Cada algoritmo tem suas próprias características e eficiências, variando de acordo com o tamanho e a natureza dos dados a serem ordenados.

Os algoritmos implementados neste projeto são aplicados para ordenar uma lista de jogadores da NBA, considerando diferentes critérios de ordenação. O desempenho de cada método é avaliado com base no tempo de execução, número de comparações e número de movimentações realizadas durante a ordenação.

Os algoritmos de ordenação implementados são descritos brevemente a seguir:

## Bubble Sort:
Um algoritmo simples de ordenação que compara pares adjacentes de elementos e os troca se estiverem na ordem errada. Este processo é repetido até que não seja mais necessário trocar elementos.

## Heap Sort:
Um algoritmo de ordenação baseado em uma estrutura de dados chamada heap (árvore binária parcialmente ordenada). O algoritmo constrói um heap máximo e, em seguida, extrai o elemento máximo, reconstrói o heap e repete esse processo até que todos os elementos estejam ordenados.

## Insertion Sort:
Um algoritmo de ordenação que insere iterativamente cada elemento da lista na posição correta, comparando-o com os elementos já ordenados à sua esquerda.

## Quick Sort:
Um algoritmo de ordenação baseado em divisão e conquista que seleciona um 'pivô' e particiona a lista em torno do pivô, colocando todos os elementos menores que o pivô à esquerda e todos os elementos maiores à direita. Este processo é aplicado recursivamente nas sublistas resultantes.

## Selection Sort:
Um algoritmo de ordenação que seleciona iterativamente o menor (ou maior) elemento da lista e o troca com o primeiro elemento ainda não ordenado.

## Merge Sort:
Um algoritmo de ordenação baseado em divisão e conquista que divide a lista em duas metades, ordena cada metade recursivamente e, em seguida, combina (merge) as metades ordenadas em uma única lista ordenada.

## Fila:
Uma fila é uma estrutura de dados que segue a abordagem "FIFO" (First-In-First-Out), o que significa que o primeiro elemento inserido na fila é o primeiro a ser removido. Semelhante a uma fila de pessoas em um supermercado, onde a pessoa que chega primeiro é a primeira a ser atendida. As operações básicas em uma fila são a inserção de elementos no final (enqueue) e a remoção de elementos do início (dequeue).

## Pilha:
Uma pilha é uma estrutura de dados que segue a abordagem "LIFO" (Last-In-First-Out), o que significa que o último elemento inserido na pilha é o primeiro a ser removido. É semelhante a uma pilha de pratos, onde o último prato colocado é o primeiro a ser retirado. As operações básicas em uma pilha são a inserção de elementos no topo (push) e a remoção de elementos do topo (pop).

## Lista:
Uma lista é uma estrutura de dados que armazena uma coleção de elementos em uma sequência ordenada. A lista pode ser implementada de várias maneiras, como uma matriz (array-based list) ou uma lista encadeada (linked list). A lista permite a inserção e remoção de elementos em qualquer posição e também suporta a recuperação de elementos por meio de sua posição. Além disso, a lista pode ter tamanho variável.

## Lista Encadeada:
Uma lista encadeada é uma estrutura de dados composta por nós que são interconectados por meio de referências. Cada nó contém um valor e um ponteiro para o próximo nó na sequência. A lista encadeada pode ser simplesmente encadeada (cada nó aponta apenas para o próximo) ou duplamente encadeada (cada nó aponta para o próximo e para o anterior). A lista encadeada é eficiente para inserção e remoção de elementos, especialmente em posições intermediárias, mas a recuperação de elementos requer percorrer a lista a partir do início.

## Lista duplamente encadeada: 
Uma lista duplamente encadeada é uma extensão da lista encadeada, em que cada nó possui um ponteiro para o nó anterior e para o próximo nó na sequência. Isso permite percorrer a lista em ambas as direções, facilitando a inserção e remoção de elementos em qualquer posição. No entanto, a lista duplamente encadeada requer mais espaço de armazenamento devido ao ponteiro adicional em cada nó.

