package academic.driver;

/**
 * @author 12S22035 Brain Wonderson
 */

import academic.model.Enrollment;

import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membaca input dari pengguna
        String courseCode = scanner.nextLine();

        String studentId = scanner.nextLine();

        String academicYear = scanner.nextLine();

        String semester = scanner.nextLine();

        // Menginstansiasi objek Enrollment menggunakan input dari pengguna
        Enrollment enrollment = new Enrollment(courseCode, studentId, academicYear, semester);

        // Menampilkan informasi objek Enrollment
        System.out.println(enrollment);

        scanner.close();
    }
}


