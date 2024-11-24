import java.util.*;

class Node {
    String data;
    Node left, right;

    public Node(String data) {
        this.data = data;
        left = right = null;
    }
}


public class all_traversal {

   
    public Node constructTree(String postfix) {
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            // If the character is an operand, create a new node and push to stack
            if (Character.isLetterOrDigit(ch)) {
                stack.push(new Node(Character.toString(ch)));
            }
            // If the character is an operator, pop two nodes and create a new node for the operator
            else {
                Node operatorNode = new Node(Character.toString(ch));
                operatorNode.right = stack.pop();  
                operatorNode.left = stack.pop();   
                stack.push(operatorNode);          
            }
        }

        return stack.peek();
    }

    // in-order traversal -> Left - Root - Right
    public void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
     
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");

            current = current.right;
        }
    }

    // Non-recursive pre-order traversal  Root - Left - Right
    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    // Non-recursive post-order traversal ->   Left Right Root
    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    public void displayMenu(Node root) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Expression Tree Traversal Menu ---");
            System.out.println("1. In-order Traversal");
            System.out.println("2. Pre-order Traversal");
            System.out.println("3. Post-order Traversal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nIn-order Traversal: ");
                    inOrderTraversal(root);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("\nPre-order Traversal: ");
                    preOrderTraversal(root);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("\nPost-order Traversal: ");
                    postOrderTraversal(root);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    public static void main(String[] args) {
        all_traversal tree = new all_traversal();

        System.out.print("Enter postfix Expression : ");
        Scanner sc =new Scanner(System.in);
        String postfix =sc.nextLine(); // Example postfix expression   ->   ab+cde+**

        Node root = tree.constructTree(postfix);

        tree.displayMenu(root);
    }
}
