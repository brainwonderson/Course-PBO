package academic.driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/*@ author 12S22035 Brain Wonderson
 * 
 */
class Student {
    String id;
    String name;
    double gpa;
    int credit;

    public Student(String id, String name, double gpa, int credit) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.credit = credit;
    }

    public String toString() {
        return id + "|" + name + "|" + gpa + "|" + credit;
    }
}

public class Driver2 {
    private List<Student> students;

    public Driver2() {
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

    public void showBestStudents(int n) {
        // Sort students by GPA and then by ID if GPA is same
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student t1, Student t2) {
                if (t1.gpa != t2.gpa) {
                    return Double.compare(t2.gpa, t1.gpa); // Sort by GPA descending
                } else {
                    return t1.id.compareTo(t2.id); // Sort by ID ascending if GPA is same
                }
            }
        });

        // Print top n students
        int count = 0;
        for (Student student : students) {
            if (count >= n) {
                break;
            }
            System.out.println(student);
            count++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Driver2 manager = new Driver2(); // Membuat objek Driver2 untuk mengelola data mahasiswa

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
            } else if (command.startsWith("student-best")) {
                String[] parts = command.split("#");
                if (parts.length == 2) {
                    int n = Integer.parseInt(parts[1]);
                    manager.showBestStudents(n);
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
