import java.util.*;

class Node {
    String data;
    Node left, right;

    public Node(String data) {
        this.data = data;
        left = right = null;
    }
}

public class inorder{
    
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

        public static void main(String[] args) {
        inorder tree = new inorder();

        Scanner sc=new Scanner(System.in);
        
        System.out.print("Enter postfix expression : ");
        String postfix = sc.nextLine(); // Example postfix expression - > ab+cde+**

        Node root = tree.constructTree(postfix);

        System.out.print("In-order Traversal of the Expression Tree: ");
        tree.inOrderTraversal(root);
    }
}
