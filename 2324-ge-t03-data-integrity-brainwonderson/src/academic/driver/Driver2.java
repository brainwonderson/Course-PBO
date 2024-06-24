package academic.driver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import academic.model.*;

public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Course[] courses = new Course[100]; // assuming maximum 100 courses
        Student[] students = new Student[100]; // assuming maximum 100 students
        Enrollment[] enrollments = new Enrollment[100]; // assuming maximum 100 enrollments
        int courseIndex = 0;
        int studentIndex = 0;
        int enrollmentIndex = 0;

        // Set to store error messages
        Set<String> errorMessages = new HashSet<>();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) {
                break;
            }

            String[] segments = input.split("#", 2);
            if (segments.length == 2) {
                String command = segments[0];
                String data = segments[1];

                switch (command) {
                    case "course-add":
                        String[] courseData = data.split("#");
                        if (courseData.length == 4) {
                            String code = courseData[0];
                            String name = courseData[1];
                            int sks = Integer.parseInt(courseData[2]);
                            String grade = courseData[3];
                            Course course = new Course(code, name, sks, grade);
                            courses[courseIndex++] = course;
                        } else {
                            errorMessages.add("Format data course tidak valid.");
                        }
                        break;

                    case "student-add":
                        String[] studentData = data.split("#");
                        if (studentData.length == 4) {
                            String id = studentData[0];
                            String name = studentData[1];
                            int year = Integer.parseInt(studentData[2]);
                            String department = studentData[3];
                            Student student = new Student(id, name, year, department);
                            students[studentIndex++] = student;
                        } else {
                            errorMessages.add("Format data student tidak valid.");
                        }
                        break;

                    case "enrollment-add":
                        String[] enrollmentData = data.split("#");
                        if (enrollmentData.length == 4) {
                            String courseCode = enrollmentData[0];
                            String studentId = enrollmentData[1];
                            String academicYear = enrollmentData[2];
                            String semester = enrollmentData[3];
                            if (!isValidEnrollment(courseCode, studentId, courses, students, courseIndex, studentIndex)) {
                                if (!isValidCourse(courseCode, courses, courseIndex)) {
                                    errorMessages.add("invalid course|" + courseCode);
                                }
                                if (!isValidStudent(studentId, students, studentIndex)) {
                                    errorMessages.add("invalid student|" + studentId);
                                }
                            } else {
                                Enrollment enrollment = new Enrollment(courseCode, studentId, academicYear, semester);
                                enrollments[enrollmentIndex++] = enrollment;
                            }
                        } else {
                            errorMessages.add("Format data enrollment tidak valid.");
                        }
                        break;

                    default:
                        errorMessages.add("Perintah tidak dikenali.");
                        break;
                }
            } else {
                errorMessages.add("Format input tidak valid.");
            }
        }

        // Print error messages
        for (String errorMessage : errorMessages) {
            System.out.println(errorMessage);
        }
        Arrays.sort(courses, 0, courseIndex);
        // Print courses
        for (int i = 0; i < courseIndex; i++) {
            System.out.println(courses[i].toString());
        }

        // Print students
        for (int i = 0; i < studentIndex; i++) {
            System.out.println(students[i].toString());
        }

        // Print enrollments
        for (int i = 0; i < enrollmentIndex; i++) {
            System.out.println(enrollments[i].toString());
        }
    }

    // Method to check if enrollment is valid
    private static boolean isValidEnrollment(String courseCode, String studentId, Course[] courses, Student[] students, int courseIndex, int studentIndex) {
        return isValidCourse(courseCode, courses, courseIndex) && isValidStudent(studentId, students, studentIndex);
    }

    // Method to check if course is valid
    private static boolean isValidCourse(String courseCode, Course[] courses, int courseIndex) {
        for (int i = 0; i < courseIndex; i++) {
            if (courseCode.equals(courses[i].getCode())) {
                return true;
            }
        }
        return false;
    }

    // Method to check if student is valid
    private static boolean isValidStudent(String studentId, Student[] students, int studentIndex) {
        for (int i = 0; i < studentIndex; i++) {
            if (studentId.equals(students[i].getId())) {
                return true;
            }
        }
        return false;
    }
}