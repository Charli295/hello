import java.util.*;

class HashTableSeparateChaining {
    LinkedList<Entry>[] table;
    int size;

    public HashTableSeparateChaining(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void put(String key, String value) {
        int index = Math.abs(key.hashCode()) % size;
        for (Entry entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry(key, value));
    }

    public int get(String key) {
        int index = Math.abs(key.hashCode()) % size;
        int comparisons = 0;
        for (Entry entry : table[index]) {
            comparisons++;
            if (entry.key.equals(key)) {
                return comparisons;
            }
        }
        return comparisons;  // Return comparisons even if not found
    }

    static class Entry {
        String key, value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}

class HashTableLinearProbing {
    String[] keys;
    String[] values;
    int size;

    public HashTableLinearProbing(int size) {
        this.size = size;
        keys = new String[size];
        values = new String[size];
    }

    public void put(String key, String value) {
        int index = Math.abs(key.hashCode()) % size;
        for (int i = 0; i < size; i++) {
            if (keys[index] == null || keys[index].equals(key)) {
                keys[index] = key;
                values[index] = value;
                return;
            }
            index = (index + 1) % size;
        }
    }

    public int get(String key) {
        int index = Math.abs(key.hashCode()) % size;
        int comparisons = 0;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (keys[index] != null && keys[index].equals(key)) {
                return comparisons;
            }
            index = (index + 1) % size;
        }
        return comparisons;  // Return comparisons even if not found
    }
}

public class collisionwala {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the size of the hash table
        System.out.print("Enter the size of the hash table: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HashTableSeparateChaining separateChainingTable = new HashTableSeparateChaining(size);
        HashTableLinearProbing linearProbingTable = new HashTableLinearProbing(size);

        // Ask the user how many clients to add
        System.out.print("Enter the number of clients: ");
        int numberOfClients = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input the client names and phone numbers
        for (int i = 0; i < numberOfClients; i++) {
            System.out.print("Enter the name of client " + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.print("Enter the phone number of " + name + ": ");
            String phone = scanner.nextLine();

            separateChainingTable.put(name, phone);
            linearProbingTable.put(name, phone);
        }

        // Ask the user for names to search and compare
        System.out.print("Enter the name to search for comparisons: ");
        String searchName = scanner.nextLine();

        int comparisonsSeparateChaining = separateChainingTable.get(searchName);
        int comparisonsLinearProbing = linearProbingTable.get(searchName);

        System.out.println(searchName + " - Separate Chaining comparisons: " + comparisonsSeparateChaining);
        System.out.println(searchName + " - Linear Probing comparisons: " + comparisonsLinearProbing);

        scanner.close();
    }
}


//C:\Users\shivr\OneDrive\Desktop\Java 2024\AssigmentsDSA_SY_2024\.cph