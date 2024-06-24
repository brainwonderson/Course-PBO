package academic.driver;
import academic.model.Enrollment;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 12S22035 Brain Wonderson
 * 
 */

public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Enrollment> enrollments = new ArrayList<>();

        String input;
        while (!(input = scanner.nextLine()).equals("---")) {
            String[] parts = input.split("#");
            enrollments.add(new Enrollment(parts[0], parts[1], parts[2], parts[3]));
        }

        scanner.close(); // Close the scanner

        // Display enrollments
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
}