import java.util.*;
class Node {
    int data;
    Node left, right;
    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class bt {
    Node root;

    // Insert a node into the binary tree
    public Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
            return node;
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    // Display the tree (In-order traversal)
    public void display(Node node) {
        if (node != null) {
            display(node.left);
            System.out.print(node.data + " ");
            display(node.right);
        }
    }

    // Find the depth of the tree
    public int findDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = findDepth(node.left);
            int rightDepth = findDepth(node.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    // Display the leaf nodes
    public void displayLeafNodes(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            System.out.print(node.data + " ");
        }
        displayLeafNodes(node.left);
        displayLeafNodes(node.right);
    }

    // Create a copy of the tree
    public Node createCopy(Node node) {
        if (node == null) {
            return null;
        }
        Node newNode = new Node(node.data);
        newNode.left = createCopy(node.left);
        newNode.right = createCopy(node.right);
        return newNode;
    }

    public static void main(String[] args) {
        bt tree = new bt();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert a node");
            System.out.println("2. Display the tree (In-order)");
            System.out.println("3. Find depth of the tree");
            System.out.println("4. Display leaf nodes");
            System.out.println("5. Create a copy of the tree and display");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the node value to insert: ");
                    int value = scanner.nextInt();
                    tree.root = tree.insert(tree.root, value);
                    System.out.println("Node inserted.");
                    break;

                case 2:
                    System.out.print("Tree (In-order Traversal): ");
                    tree.display(tree.root);
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Depth of tree: " + tree.findDepth(tree.root));
                    break;

                case 4:
                    System.out.print("Leaf nodes: ");
                    tree.displayLeafNodes(tree.root);
                    System.out.println();
                    break;

                case 5:
                    Node copy = tree.createCopy(tree.root);
                    System.out.print("Copied Tree (In-order Traversal): ");
                    tree.display(copy);
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
