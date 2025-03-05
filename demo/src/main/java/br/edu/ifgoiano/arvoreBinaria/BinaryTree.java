package br.edu.ifgoiano.arvoreBinaria;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

    @Override
    public Node<T> createTree(T element) {
        return new Node<>(element);
    }

    @Override
    public Node<T> createTree(T[] elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }
        
        Node<T> root = new Node<>(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            insertNode(root, elements[i]);
        }
        return root;
    }

    private void insertNode(Node<T> root, T element) {
        if (element.compareTo(root.getValue()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft(new Node<>(element));
            } else {
                insertNode(root.getLeft(), element);
            }
        } else {
            if (root.getRight() == null) {
                root.setRight(new Node<>(element));
            } else {
                insertNode(root.getRight(), element);
            }
        }
    }

    @Override
    public int calculateTreeDepth(Node<T> rootNode) {
        if (rootNode == null) {
            return 0;
        }
        return 1 + Math.max(calculateTreeDepth(rootNode.getLeft()), calculateTreeDepth(rootNode.getRight()));
    }

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

