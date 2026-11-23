import java.io.*;
import java.util.Arrays;
public class HobbyCollection {
    private static final String FILE_NAME = "hobbies.txt";
    private Set[] items = new Set[0];
    private int count = 0;
    public int getCount() { return count; }
    // array grows by one after every adding
    public void add(Set h) {
        items = Arrays.copyOf(items, count + 1);
        items[count] = h;
        count++;
    }
    // creates a new array without the removed item
    public boolean remove(int i) {
        if (!isValid(i)) return false;
        Set[] newItems = new Set[count - 1];
        int k = 0;
        for (int j = 0; j < count; j++) {
            if (j != i) newItems[k++] = items[j];
        }
        items = newItems;
        count--;
        return true;
    }
    public void printOne(int i) {
        if (!isValid(i)) {
            System.out.println("No hobby with index " + i);
            return;
        }
        System.out.println(items[i].getDescription());
    }
    public void printAll() {
        if (count == 0) {
            System.out.println("The list is empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("--- " + i + " ---");
            System.out.println(items[i].getDescription());
        }
    }
    public void printList() {
        if (count == 0) {
            System.out.println("The list is empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(i + ". " + items[i].getTitle());
        }
    }
    // biggest pleasure first
    public void sortByPleasure() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (items[j].getPleasure() < items[j + 1].getPleasure()) swap(j, j + 1);
            }
        }
    }
    // biggest amount of classes first
    public void sortByClasses() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (items[j].getClasses() < items[j + 1].getClasses()) swap(j, j + 1);
            }
        }
    }
    public void search(String phrase) {
        String p = phrase.toLowerCase();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (items[i].getHobby().toLowerCase().contains(p)
                    || items[i].getCategory().toLowerCase().contains(p)) {
                System.out.println(i + ". " + items[i].getTitle());
                found = true;
            }
        }
        if (!found) System.out.println("Nothing found.");
    }
    public void searchByClasses(int classes) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (items[i].getClasses() == classes) {
                System.out.println(i + ". " + items[i].getTitle());
                found = true;
            }
        }
        if (!found) System.out.println("Nothing found.");
    }
    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < count; i++) {
                out.println(items[i].toFileLine());
            }
            System.out.println("Saved " + count + " hobbies to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Cannot save file: " + e.getMessage());
        }
    }
    public void readFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            System.out.println("File " + FILE_NAME + " not found.");
            return;
        }
        items = new Set[0];
        count = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = in.readLine()) != null) {
                Set h = Set.fromFileLine(line);
                if (h != null) add(h);
            }
            System.out.println("Loaded " + count + " hobbies from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
    }
    private boolean isValid(int i) {
        return i >= 0 && i < count;
    }
    private void swap(int a, int b) {
        Set t = items[a];
        items[a] = items[b];
        items[b] = t;
    }
}
