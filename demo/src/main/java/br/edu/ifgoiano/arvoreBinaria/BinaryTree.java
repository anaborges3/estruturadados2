package br.edu.ifgoiano.arvoreBinaria;

/*
 * Alunas: 
 * Ana Carolina Silva Borges
 * Danielly Maria dos Santos Oliveira
 * Stéphany Lima Vital
 */

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {
    
    @Override
    public Node<T> createTree(T element) {
        return new Node<>(element);
    }

    @Override
    public Node<T> createTree(T[] elements) {
        if (elements == null || elements.length == 0) return null;
        Node<T> root = new Node<>(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            insert(root, elements[i]);
        }
        return root;
    }

    @Override
    public Integer degree(Node<T> rootNode, T nodeElement) {
        Node<T> node = getByElement(rootNode, nodeElement);
        if (node == null) return null;
        int degree = 0;
        if (node.getLeft() != null) degree++;
        if (node.getRight() != null) degree++;
        return degree;
    }

    @Override
    public void insert(Node<T> rootNode, T element) {
        if (rootNode == null || element == null) return;
        if (element.compareTo(rootNode.getValue()) < 0) {
            if (rootNode.getLeft() == null) {
                rootNode.setLeft(new Node<>(element));
            } else {
                insert(rootNode.getLeft(), element);
            }
        } else if (element.compareTo(rootNode.getValue()) > 0) {
            if (rootNode.getRight() == null) {
                rootNode.setRight(new Node<>(element));
            } else {
                insert(rootNode.getRight(), element);
            }
        }
    }

    @Override
    public boolean remove(Node<T> rootNode, T nodeElement) {
        return rootNode != null && removeNode(rootNode, nodeElement, null);
    }

    private boolean removeNode(Node<T> node, T element, Node<T> parent) {
        if (node == null) return false;
        if (element.compareTo(node.getValue()) < 0) {
            return removeNode(node.getLeft(), element, node);
        } else if (element.compareTo(node.getValue()) > 0) {
            return removeNode(node.getRight(), element, node);
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                if (parent != null) {
                    if (parent.getLeft() == node) parent.setLeft(null);
                    else parent.setRight(null);
                }
            } else if (node.getLeft() != null && node.getRight() == null) {
                if (parent != null) {
                    if (parent.getLeft() == node) parent.setLeft(node.getLeft());
                    else parent.setRight(node.getLeft());
                }
            } else if (node.getRight() != null && node.getLeft() == null) {
                if (parent != null) {
                    if (parent.getLeft() == node) parent.setLeft(node.getRight());
                    else parent.setRight(node.getRight());
                }
            } else {
                Node<T> successor = findMin(node.getRight());
                node.setValue(successor.getValue());
                removeNode(node.getRight(), successor.getValue(), node);
            }
            return true;
        }
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public Node<T> getByElement(Node<T> rootNode, T element) {
        if (rootNode == null || element == null) return null;
        if (element.equals(rootNode.getValue())) return rootNode;
        if (element.compareTo(rootNode.getValue()) < 0) {
            return getByElement(rootNode.getLeft(), element);
        } else {
            return getByElement(rootNode.getRight(), element);
        }
    }

    @Override
    public Integer calculateTreeDepth(Node<T> rootNode) {
        if (rootNode == null) return -1;  // Ajustado para contar corretamente os níveis
        if (rootNode == null) return 0;
        return 1 + Math.max(calculateTreeDepth(rootNode.getLeft()), calculateTreeDepth(rootNode.getRight()));
    }

    @Override
    public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
        return findNodeLevel(rootNode, nodeElement, 0);
    }

    private Integer findNodeLevel(Node<T> node, T element, int level) {
        if (node == null) return null;
        if (node.getValue().equals(element)) return level;
        Integer leftLevel = findNodeLevel(node.getLeft(), element, level + 1);
        return (leftLevel != null) ? leftLevel : findNodeLevel(node.getRight(), element, level + 1);
    }

    @Override
    public Node<T> getFather(Node<T> rootNode, T nodeElement) {
        return findParent(rootNode, nodeElement, null);
    }

    private Node<T> findParent(Node<T> node, T element, Node<T> parent) {
        if (node == null) return null;
        if (node.getValue().equals(element)) return parent;
        if (element.compareTo(node.getValue()) < 0) {
            return findParent(node.getLeft(), element, node);
        } else {
            return findParent(node.getRight(), element, node);
        }
    }

    @Override
    public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
        Node<T> parent = getFather(rootNode, nodeElement);
        if (parent == null) return null;
        if (parent.getLeft() != null && parent.getLeft().getValue().equals(nodeElement)) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }

    @Override
    public String toString(Node<T> rootNode) {
        if (rootNode == null) return "";
        return "root:" + rootNode.getValue() + formatSubtree(rootNode);
    }

    private String formatSubtree(Node<T> node) {
        if (node == null) return "";
        String left = node.getLeft() != null ? " (left:" + node.getLeft().getValue() + formatSubtree(node.getLeft()) + ")" : "";
        String right = node.getRight() != null ? " (right:" + node.getRight().getValue() + formatSubtree(node.getRight()) + ")" : "";
        return left + right;
    }
}
