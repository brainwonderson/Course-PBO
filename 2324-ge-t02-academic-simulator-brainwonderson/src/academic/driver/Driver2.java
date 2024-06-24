package academic.driver;
import java.util.*;
import academic.model.*;

/**
 * @author 12S22035 Brain Wonderson
 * 
 */
public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[100]; // assuming maximum 100 students
        int index = 0;

       
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) {
                break;
            }

            String[] segments = input.split("#");
            if (segments.length == 4) {
                String id = segments[0];
                String name = segments[1];
                int year = Integer.parseInt(segments[2]);
                String studyProgram = segments[3];

                Student student = new Student(id, name, year, studyProgram);
                students [index++] = student;
            } else {
                System.out.println("Format input tidak valid. Harap masukkan data sesuai format.");
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println(students[i].toString());
        }
    }
}