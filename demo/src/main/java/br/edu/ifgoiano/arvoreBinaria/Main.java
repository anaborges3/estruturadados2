package br.edu.ifgoiano.arvoreBinaria;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        // Criando uma árvore a partir de um array de elementos
        Integer[] elements = { 6, 2, 1, 4, 5, 3, 7, 8 };
        // Integer[] elements = { 6, 2, 1, 4, 3, 8 };
        // Integer[] elements = { 37, 30, 5, 100, 90 };
        Node<Integer> root = tree.createTree(elements);

        // Exibindo a árvore
        System.out.println("Árvore criada:");
        printTree(root, "", true);

        // // Testando inserção
        // System.out.println("\nInserindo 6...");
        // tree.insert(root, 6);
        // printTree(root, "", true);

        // Testando remoção
        System.out.println("\nRemovendo 6...");
        tree.remove(root, 6);
        printTree(root, "", true);

        // // Testando profundidade da árvore
        // System.out.println("\nProfundidade da árvore: " +
        // tree.calculateTreeDepth(root));

        // // Testando nível de um nó
        // System.out.println("Nível do nó 7: " + tree.calculateNodeLevel(root, 7));

        // // Testando busca de um elemento
        // Node<Integer> foundNode = tree.getByElement(root, 12);
        // System.out.println("Nó encontrado (12): " + (foundNode != null ?
        // foundNode.getValue() : "não encontrado"));

        // // Testando busca pelo pai de um nó
        // Node<Integer> fatherNode = tree.getFather(root, 7);
        // System.out.println("Pai do nó 7: " + (fatherNode != null ?
        // fatherNode.getValue() : "não encontrado"));

        // // Testando busca pelo irmão de um nó
        // Node<Integer> brotherNode = tree.getBrother(root, 3);
        // System.out.println("Irmão do nó 3: " + (brotherNode != null ?
        // brotherNode.getValue() : "não encontrado"));

        // // Testando grau de um nó
        // System.out.println("Grau do nó 10: " + tree.degree(root, 10));
    }

    // Método para imprimir a árvore de maneira hierárquica
    public static void printTree(Node<Integer> node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getValue());
            printTree(node.getLeft(), prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.getRight(), prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}
