package academic.driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*@ author 12S22035 Brain Wonderson
 * 
 */
public class Driver1 {
    private List<Student> students;

    public Driver1() {
        students = new ArrayList<>();
    }

    public void addStudent(String id, String name, double gpa, int credit) {
        Student student = new Student(id, name, gpa, credit);
        students.add(student);
    }

    public void showAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Driver1 manager = new Driver1(); // Membuat objek Driver1 untuk mengelola data mahasiswa

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("---")) {
                break;
            } else if (command.equals("student-show-all")) {
                manager.showAllStudents();
            } else if (command.startsWith("student-add")) {
                String[] parts = command.split("#");
                if (parts.length == 5) {
                    String id = parts[1];
                    String name = parts[2];
                    double gpa = Double.parseDouble(parts[3]);
                    int credit = Integer.parseInt(parts[4]);
                    manager.addStudent(id, name, gpa, credit);
                } else {
                    System.out.println("Format perintah tidak valid.");
                }
            } else {
                System.out.println("Perintah tidak dikenali.");
            }
        }

        scanner.close();
    }
}
