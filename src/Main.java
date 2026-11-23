import java.util.Scanner;
public class Main {
    // scanner is static so that helper methods can use it too
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Part 2.1 Create collection and load hobbies from file
        HobbyCollection hobby = new HobbyCollection();
        readHobby(hobby);
        // Part 2.2 Menu loop
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println(" Hobby MENU ");
            System.out.println("1. Show all hobby");
            System.out.println("2. Add a new hobby");
            System.out.println("3. Remove hobby");
            System.out.println("4. Sort hobby by grate pleasure");
            System.out.println("5. Sort hobby by amount classes");
            System.out.println("6. Show all info about hobby");
            System.out.println("7. Show info about one hobby");
            System.out.println("8. Read hobby from file");
            System.out.println("9. Save hobby to the file");
            System.out.println("10. Search by the phrase");
            System.out.println("11. Search by amount classes");
            System.out.println("0. Save and exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showHobby(hobby);
                    break;
                case "2":
                    addHobby(hobby);
                    break;
                case "3":
                    removeHobby(hobby);
                    break;
                case "4":
                    sortPlueasure(hobby);
                    break;
                case "5":
                    sortAmounClass(hobby);
                    break;
                case "6":
                    showInfo(hobby);
                    break;
                case "7":
                    showInfoAbtHobby(hobby);
                    break;
                case "8":
                    readHobby(hobby);
                    break;
                case "9":
                    saveinFileHobby(hobby);
                    break;
                case "10":
                    searchHobbyByName(hobby);
                    break;
                case "11":
                    saerhcHobbyByAmountClasses(hobby);
                    break;
                case "0":
                    saveHobby(hobby);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose one of all variants.");
            }
        }
        scanner.close();
    }
    static void showHobby(HobbyCollection hobby) {
        hobby.printList();
    }
    static void addHobby(HobbyCollection hobby) {
        System.out.print("Hobby name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        int pleasure = readInt("Pleasure (1-10): ");
        int classes = readInt("Classes per month: ");
        hobby.add(new Set(name, classes, category, pleasure));
        System.out.println("Hobby added.");
    }
    static void removeHobby(HobbyCollection hobby) {
        hobby.printList();
        int i = readInt("Enter index of the hobby to remove: ");
        if (hobby.remove(i)) {
            System.out.println("Hobby removed.");
        } else {
            System.out.println("No hobby with index " + i);
        }
    }
    static void sortPlueasure(HobbyCollection hobby) {
        hobby.sortByPleasure();
        System.out.println("Sorted by pleasure.");
    }
    static void sortAmounClass(HobbyCollection hobby) {
        hobby.sortByClasses();
        System.out.println("Sorted by amount of classes.");
    }

    static void showInfo(HobbyCollection hobby) {
        hobby.printAll();
    }

    static void showInfoAbtHobby(HobbyCollection hobby) {
        hobby.printOne(readInt("Enter hobby index: "));
    }

    static void searchHobbyByName(HobbyCollection hobby) {
        System.out.print("Enter search phrase: ");
        hobby.search(scanner.nextLine().trim());
    }
    static void saerhcHobbyByAmountClasses(HobbyCollection hobby) {
        hobby.searchByClasses(readInt("Enter amount of classes: "));
    }
    static void readHobby(HobbyCollection hobby) {
        hobby.readFromFile();
    }
    static void saveinFileHobby(HobbyCollection hobby) {
        hobby.saveToFile();
    }
    static void saveHobby(HobbyCollection hobby) {
        hobby.saveToFile();
    }
    // asks until the user enters a whole number
    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
}