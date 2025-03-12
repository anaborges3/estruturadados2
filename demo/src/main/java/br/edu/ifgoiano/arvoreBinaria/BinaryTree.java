package br.edu.ifgoiano.arvoreBinaria;

/*
 * Alunas: 
 * Ana Carolina Silva Borges
 * Danielly Maria dos Santos Oliveira
 * Stéphany Lima Vital
 */

// Implementação de uma Árvore Binária de Busca (BST) genérica
public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

    // Método para criar uma árvore com um único elemento
    @Override
    public Node<T> createTree(T element) {
        return new Node<>(element);
    }

    // Método para criar uma árvore a partir de um array de elementos
    @Override
    public Node<T> createTree(T[] elements) {
        if (elements == null || elements.length == 0) {
            return null; // Retorna null se o array estiver vazio
        }

        Node<T> root = new Node<>(elements[0]); // Define o primeiro elemento como raiz
        for (int i = 1; i < elements.length; i++) {
            insert(root, elements[i]); // Insere os elementos subsequentes
        }
        return root;
    }

    // Método para inserir um elemento na árvore binária de busca
    @Override
    public void insert(Node<T> root, T element) {
        if (element.compareTo(root.getValue()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft(new Node<>(element)); // Insere na esquerda se estiver vazia
            } else {
                insert(root.getLeft(), element); // Recursão para inserção na subárvore esquerda
            }
        } else {
            if (root.getRight() == null) {
                root.setRight(new Node<>(element)); // Insere na direita se estiver vazia
            } else {
                insert(root.getRight(), element); // Recursão para inserção na subárvore direita
            }
        }
    }

    // Método para remover um elemento da árvore garantindo a reorganização correta
    @Override
    public boolean remove(Node<T> rootNode, T element) {
        return removeNode(rootNode, element) != null;
    }

    private Node<T> removeNode(Node<T> rootNode, T element) {
        if (rootNode == null) {
            return null;
        }
        if (element.compareTo(rootNode.getValue()) < 0) {
            rootNode.setLeft(removeNode(rootNode.getLeft(), element));
        } else if (element.compareTo(rootNode.getValue()) > 0) {
            rootNode.setRight(removeNode(rootNode.getRight(), element));
        } else {
            if (rootNode.getLeft() == null) {
                return rootNode.getRight();
            } else if (rootNode.getRight() == null) {
                return rootNode.getLeft();
            }
            Node<T> minNode = findMin(rootNode.getRight());
            rootNode.setValue(minNode.getValue());
            rootNode.setRight(removeNode(rootNode.getRight(), minNode.getValue()));
        }
        return rootNode;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public Node<T> getByElement(Node<T> rootNode, T element) {
        if (rootNode == null || rootNode.getValue().equals(element)) {
            return rootNode;
        }
        if (element.compareTo(rootNode.getValue()) < 0) {
            return getByElement(rootNode.getLeft(), element);
        } else {
            return getByElement(rootNode.getRight(), element);
        }
    }

    @Override
    public Node<T> getFather(Node<T> rootNode, T element) {
        if (rootNode == null || rootNode.getValue().equals(element)) {
            return null;
        }
        if ((rootNode.getLeft() != null && rootNode.getLeft().getValue().equals(element)) ||
                (rootNode.getRight() != null && rootNode.getRight().getValue().equals(element))) {
            return rootNode;
        }
        if (element.compareTo(rootNode.getValue()) < 0) {
            return getFather(rootNode.getLeft(), element);
        } else {
            return getFather(rootNode.getRight(), element);
        }
    }

    @Override
    public Node<T> getBrother(Node<T> rootNode, T element) {
        Node<T> father = getFather(rootNode, element);
        if (father == null) {
            return null;
        }
        if (father.getLeft() != null && father.getLeft().getValue().equals(element)) {
            return father.getRight();
        } else {
            return father.getLeft();
        }
    }

    @Override
    public Integer degree(Node<T> rootNode, T element) {
        Node<T> node = getByElement(rootNode, element);
        if (node == null) {
            return 0;
        }
        int degree = 0;
        if (node.getLeft() != null)
            degree++;
        if (node.getRight() != null)
            degree++;
        return degree;
    }

    // Método para calcular a profundidade da árvore
    @Override
    public Integer calculateTreeDepth(Node<T> rootNode) {
        if (rootNode == null) {
            return 0;
        }
        return 1 + Math.max(calculateTreeDepth(rootNode.getLeft()), calculateTreeDepth(rootNode.getRight()));
    }

    // Método para calcular o nível de um nó específico
    @Override
    public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
        return getNodeLevel(rootNode, nodeElement, 0);
    }

    private Integer getNodeLevel(Node<T> node, T element, int level) {
        if (node == null) {
            return -1;
        }
        if (node.getValue().equals(element)) {
            return level;
        }

        int leftLevel = getNodeLevel(node.getLeft(), element, level + 1);
        if (leftLevel != -1) {
            return leftLevel;
        }
        return getNodeLevel(node.getRight(), element, level + 1);
    }

    // Método para gerar a representação em string da árvore
    @Override
    public String toString(Node<T> rootNode) {
        if (rootNode == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("root:").append(rootNode.getValue());
        if (rootNode.getLeft() != null || rootNode.getRight() != null) {
            sb.append(" (left:").append(toString(rootNode.getLeft()));
            sb.append(" right:").append(toString(rootNode.getRight())).append(")");
        }
        return sb.toString();
    }
}