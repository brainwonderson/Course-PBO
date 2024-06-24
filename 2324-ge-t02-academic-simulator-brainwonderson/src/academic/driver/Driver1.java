package academic.driver;
import java.util.*;
import academic.model.*;

/**
 * @author 12S22035 Brain Wonderson
 * 
 */
public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Course[] courses = new Course[100]; // assuming maximum 100 courses
        int index = 0;

       
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) {
                break;
            }

            String[] segments = input.split("#");
            if (segments.length == 4) {
                String code = segments[0];
                String name = segments[1];
                int sks = Integer.parseInt(segments[2]);
                String grade = segments[3];

                Course course = new Course(code, name, sks, grade);
                courses[index++] = course;
            } else {
                System.out.println("Format input tidak valid. Harap masukkan data sesuai format.");
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(courses[i].toString());
        }
    }
}