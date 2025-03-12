package br.edu.ifgoiano.arvoreBinaria;

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
        int count = 0;
        if (node.getLeft() != null) count++;
        if (node.getRight() != null) count++;
        return count;
    }

    @Override
    public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
        return findLevel(rootNode, nodeElement, 0);
    }
    
    private Integer findLevel(Node<T> node, T value, int level) {
        if (node == null) return null;
        if (node.getValue().equals(value)) return level;
        Integer left = findLevel(node.getLeft(), value, level + 1);
        if (left != null) return left;
        return findLevel(node.getRight(), value, level + 1);
    }

    @Override
    public Integer calculateTreeDepth(Node<T> rootNode) {
        return calculateDepth(rootNode);
    }
    
    private int calculateDepth(Node<T> node) {
        if (node == null) return -1;
        return Math.max(calculateDepth(node.getLeft()), calculateDepth(node.getRight())) + 1;
    }
    
    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }
    
    @Override
    public String toString(Node<T> rootNode) {
        if (rootNode == null) return "";
        StringBuilder sb = new StringBuilder();
        buildTreeString(sb, rootNode, "root");
        return sb.toString().replace(" )", ")").replace("( ", "(").replace("right:", " right:").replace("left:", " left:").replace("  ", " ").trim();
    }
    
    private void buildTreeString(StringBuilder sb, Node<T> node, String label) {
        if (node == null) return;
        sb.append(label).append(":").append(node.getValue());
        if (node.getLeft() != null || node.getRight() != null) {
            sb.append(" (");
            buildTreeString(sb, node.getLeft(), "left");
            if (node.getLeft() != null && node.getRight() != null) sb.append(" ");
            buildTreeString(sb, node.getRight(), "right");
            sb.append(")");
        }
    }

    @Override
    public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
        Node<T> parent = getFather(rootNode, nodeElement);
        if (parent == null) return null;
        if (parent.getLeft() != null && parent.getLeft().getValue().equals(nodeElement)) return parent.getRight();
        if (parent.getRight() != null && parent.getRight().getValue().equals(nodeElement)) return parent.getLeft();
        return null;
    }

    @Override
    public Node<T> getByElement(Node<T> rootNode, T element) {
        if (rootNode == null || element == null) return null;
        Node<T> current = rootNode;
        while (current != null) {
            int cmp = element.compareTo(current.getValue());
            if (cmp == 0) return current;
            else if (cmp < 0) current = current.getLeft();
            else current = current.getRight();
        }
        return null;
    }

    @Override
    public void insert(Node<T> rootNode, T element) {
        if (rootNode == null || element == null) return;
        Node<T> current = rootNode;
        while (true) {
            if (element.compareTo(current.getValue()) < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node<>(element));
                    return;
                }
                current = current.getLeft();
            } else if (element.compareTo(current.getValue()) > 0) {
                if (current.getRight() == null) {
                    current.setRight(new Node<>(element));
                    return;
                }
                current = current.getRight();
            } else {
                return;
            }
        }
    }

    @Override
    public Node<T> getFather(Node<T> rootNode, T nodeElement) {
        return findParent(rootNode, nodeElement, null);
    }
    
    private Node<T> findParent(Node<T> node, T value, Node<T> parent) {
        if (node == null) return null;
        if (node.getValue().equals(value)) return parent;
        if (value.compareTo(node.getValue()) < 0) return findParent(node.getLeft(), value, node);
        else return findParent(node.getRight(), value, node);
    }
    
    @Override
    public boolean remove(Node<T> rootNode, T nodeElement) {
        return removeNode(rootNode, nodeElement, null);
    }
    
    private boolean removeNode(Node<T> node, T value, Node<T> parent) {
        if (node == null) return false;
        if (value.compareTo(node.getValue()) < 0) {
            return removeNode(node.getLeft(), value, node);
        } else if (value.compareTo(node.getValue()) > 0) {
            return removeNode(node.getRight(), value, node);
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                if (parent != null) {
                    if (parent.getLeft() == node) parent.setLeft(null);
                    else parent.setRight(null);
                }
            } else if (node.getLeft() != null && node.getRight() != null) {
                Node<T> successor = findMin(node.getRight());
                node.setValue(successor.getValue());
                removeNode(node.getRight(), successor.getValue(), node);
            } else {
                Node<T> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if (parent != null) {
                    if (parent.getLeft() == node) parent.setLeft(child);
                    else parent.setRight(child);
                } else {
                    node.setValue(child.getValue());
                    node.setLeft(child.getLeft());
                    node.setRight(child.getRight());
                }
            }
            return true;
        }
    }
}
