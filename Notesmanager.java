package Task4;
import java.io.*;
import java.util.Scanner;
public class Notesmanager {

    private static final String FILE_NAME = "notes.txt";
    static Scanner Ob = new Scanner(System.in);
    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            System.out.println("\n==== Notes Manager ====");
            System.out.println("1. Write Note (Overwrite)");
            System.out.println("2. Append Note");
            System.out.println("3. Read Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Ob.nextInt();
            Ob.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    writeNote( false);
                    break;
                case 2:
                    writeNote( true);
                    break;
                case 3:
                    readNotes();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting... Bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
       Ob.close();
    }

    private static void writeNote(boolean append) {
        System.out.print("Enter your note: ");
        String note = Ob.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, append)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes found.");
            return;
        }

        System.out.println("\n=== Your Notes ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}