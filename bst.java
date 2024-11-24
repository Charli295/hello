import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class bst {
    Node root;

    // Insert a node in the BST
    public Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    // Delete a node from the BST
    public Node delete(Node root, int data) {

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
           //Voila case -> root.data == val

            //case1 - leaf node
            if(root.right ==null && root.left == null ){
                return null;
            }

            //case2 - single child
            if(root.right == null){
                return root.left;
            }else if(root.left==null){
                return root.right;
            }

            //case3 - both children
            Node IS=FindInorderSuccesor(root.right);
            root.data=IS.data;
            root.right=delete(root.right,IS.data);
        }
        return root;
    }

    //FindInorderSuccesor Func

    public static Node FindInorderSuccesor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root; 
    }
    

    // In-order traversal to display the tree  ====    Left - Root - Right
    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(root.data + " ");
            display(root.right);
        }
    }

    // Search for a value in the BST
    public boolean search(Node root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        if (data < root.data) {
            return search(root.left, data);
        } else {
            return search(root.right, data);
        }
    }

    // Breadth-First Search (BFS) - Level-wise print
    public void bfs(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node currNode = q.remove();
            
            if(currNode == null){
                System.out.println();

                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                System.out.print(currNode.data+" ");
                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        bst bst = new bst();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Display the tree (In-order)");
            System.out.println("4. Search for a node");
            System.out.println("5. BFS (Level-wise print)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the node value to insert: ");
                    int value = scanner.nextInt();
                    bst.root = bst.insert(bst.root, value);
                    System.out.println("Node inserted.");
                    break;
                case 2:
                    System.out.print("Enter the node value to delete: ");
                    int delValue = scanner.nextInt();
                    bst.root = bst.delete(bst.root, delValue);
                    System.out.println("Node deleted.");
                    break;
                case 3:
                    System.out.print("Tree (In-order Traversal): ");
                    bst.display(bst.root);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter the node value to search: ");
                    int searchValue = scanner.nextInt();
                    boolean found = bst.search(bst.root, searchValue);
                    if (found) {
                        System.out.println("Node found in the tree.");
                    } else {
                        System.out.println("Node not found in the tree.");
                    }
                    break;
                case 5:
                    System.out.print("BFS (Level-wise print): ");
                    bst.bfs(bst.root);
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
