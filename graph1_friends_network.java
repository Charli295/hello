import java.util.*;

class User {
    String name;
    String dob; //(format: DD-MM-YYYY)
    int comments;

    public User(String name, String dob, int comments) {
        this.name = name;
        this.dob = dob;
        this.comments = comments;
    }
}

public class graph1_friends_network {
   
    private Map<String, User> userData = new HashMap<>();
    private Map<String, List<String>> friendsNetwork = new HashMap<>(); 

    // Add a new user
    public void addUser(String name, String dob, int comments) {
        User newUser = new User(name, dob, comments);
        userData.put(name, newUser);
        friendsNetwork.put(name, new ArrayList<>()); 
    }

   // Add a friendship (bidirectional)
    public void addFriendship(String user1, String user2) {
    if (!friendsNetwork.containsKey(user1) || !friendsNetwork.containsKey(user2)) {
        System.out.println("Error: One or both users do not exist. Please add the users first.");
        return;
    }
    
    friendsNetwork.get(user1).add(user2);
    friendsNetwork.get(user2).add(user1);
}


    // 1. Find the user with maximum friends
    public void findMaxFriends() {
        String maxUser = null;
        int maxFriends = 0;
        for (String user : friendsNetwork.keySet()) {
            int friendCount = friendsNetwork.get(user).size();
            if (friendCount > maxFriends) {
                maxFriends = friendCount;
                maxUser = user;
            }
        }
        if (maxUser != null) {
            System.out.println("User with maximum friends: " + maxUser + " (Friends: " + maxFriends + ")");
        }
    }

    // 2. Find user with maximum and minimum comments
    public void findMaxAndMinComments() {
        if (userData.isEmpty()) return;

        String maxCommentUser = null;
        String minCommentUser = null;
        int maxComments = Integer.MIN_VALUE;
        int minComments = Integer.MAX_VALUE;

        for (User user : userData.values()) {
            if (user.comments > maxComments) {
                maxComments = user.comments;
                maxCommentUser = user.name;
            }
            if (user.comments < minComments) {
                minComments = user.comments;
                minCommentUser = user.name;
            }
        }

        System.out.println("User with maximum comments: " + maxCommentUser + " (Comments: " + maxComments + ")");
        System.out.println("User with minimum comments: " + minCommentUser + " (Comments: " + minComments + ")");
    }

    // 3. Find users having birthday in the current month
    public void findBirthdayUsers() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Current month (1-based index)

        System.out.println("Users having birthday this month:");
        for (User user : userData.values()) {
            String[] dobParts = user.dob.split("-");
            int birthMonth = Integer.parseInt(dobParts[1]);

            if (birthMonth == currentMonth) {
                System.out.println(user.name + " (Birthday: " + user.dob + ")");
            }
        }
    }

    // Perform DFS traversal
    public void DFS(String startUser) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(startUser);

        System.out.print("DFS Traversal: ");
        while (!stack.isEmpty()) {
            String currentUser = stack.pop();

            if (!visited.contains(currentUser)) {
                System.out.print(currentUser + " ");
                visited.add(currentUser);

                // Add friends to the stack
                for (String friend : friendsNetwork.get(currentUser)) {
                    if (!visited.contains(friend)) {
                        stack.push(friend);
                    }
                }
            }
        }
        System.out.println();
    }

    // Perform BFS traversal
    public void BFS(String startUser) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startUser);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            String currentUser = queue.poll();

            if (!visited.contains(currentUser)) {
                System.out.print(currentUser + " ");
                visited.add(currentUser);

                // Add friends to the queue
                for (String friend : friendsNetwork.get(currentUser)) {
                    if (!visited.contains(friend)) {
                        queue.offer(friend);
                    }
                }
            }
        }
        System.out.println();
    }

    // Menu-driven program
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Social Network Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. Find User with Maximum Friends");
            System.out.println("4. Find User with Maximum and Minimum Comments");
            System.out.println("5. Find Users Having Birthday in This Month");
            System.out.println("6. DFS Traversal");
            System.out.println("7. BFS Traversal");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter date of birth (DD-MM-YYYY): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter number of comments: ");
                    int comments = scanner.nextInt();
                    addUser(name, dob, comments);
                    break;

                case 2:
                    System.out.print("Enter first user name: ");
                    String user1 = scanner.nextLine();
                    System.out.print("Enter second user name: ");
                    String user2 = scanner.nextLine();
                    addFriendship(user1, user2);
                    break;

                case 3:
                    findMaxFriends();
                    break;

                case 4:
                    findMaxAndMinComments();
                    break;

                case 5:
                    findBirthdayUsers();
                    break;

                case 6:
                    System.out.print("Enter starting user for DFS: ");
                    String dfsUser = scanner.nextLine();
                    DFS(dfsUser);
                    break;

                case 7:
                    System.out.print("Enter starting user for BFS: ");
                    String bfsUser = scanner.nextLine();
                    BFS(bfsUser);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

        } while (choice != 8);

        scanner.close();
    }

    public static void main(String[] args) {
        graph1_friends_network socialNetwork = new graph1_friends_network();

        // Display the menu
        socialNetwork.displayMenu();
    }
}
