package academic.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * @author 12S22016 Desri Dabukke
 * 12S22035- Brain Wonderson
 */
import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import academic.model.StudentPerformance;
import academic.model.Lecturer;
import academic.model.CourseOpen;

public class Driver1 {

    private static Course[] courses;
    public static void main(String[] args) {
        courses = new Course[100];
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> coursesList = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();
        ArrayList<CourseOpen> courseOpens = new ArrayList<>();
        ArrayList<StudentPerformance> studentPerformance = new ArrayList<>();
        ArrayList<String[]> bestStudentRequests = new ArrayList<>(); // Store "find-the-best-student" requests
    
        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.equals("---")) {
                break;
            }
    
            String[] part = input.split("#");
    
            switch (part[0]) {
                case "student-add": 
                    StudentAdd(part[1], part[2], part[3], part[4], students);
                    break;
                case "enrollment-add": 
                    EnrollmentAdd(part[1], part[2], part[3], part[4], enrollments, studentEnrollments);
                    break;
                case "enrollment-remedial": 
                    EnrollmentRemedial(part[1], part[2], part[3], part[4], part[5], enrollments);
                    break;
                case "enrollment-grade": 
                    EnrollmentGrade(part[1], part[2], part[3], part[4], part.length > 5 ? part[5] : "", enrollments);
                    break;
                case "lecturer-add": 
                    LecturerAdd(part[1], part[2], part[3], part[4], part[5], lecturers);
                    break;
                case "course-add": 
                    CourseAdd(part[1], part[2], Integer.parseInt(part[3]), part[4], coursesList);
                    break;
                case "student-details": 
                    StudentDetails(part[1], coursesList, enrollments, students);
                    break;
                case "course-open": 
                    CourseOpen(part[1], part[2], part[3], part[4], coursesList, lecturers, courseOpens);
                    break;
                case "course-history": 
                    CourseHistory(coursesList, enrollments, courseOpens);
                    break;
                case "find-the-best-student":
                    // Store "find-the-best-student" request for later processing
                    bestStudentRequests.add(new String[]{part[1], part[2]});
                    break;
            }
        }
    
        // Output the data
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.toString());
        }
    
        for (Course course : coursesList) {
            System.out.println(course.toString());
        }
    
        for (Student student : students) {
            System.out.println(student.toString());
        }
    
        for (Enrollment enrollment : studentEnrollments) {
            if (!enrollment.getreturngrade().equals("")) {
                System.out.println(enrollment.toremedial());
            } else {
                System.out.println(enrollment.toString());
            }
        }
    
        // Process best student requests after main loop ends
        for (String[] request : bestStudentRequests) {
            bestStudent(students, enrollments, coursesList, request[0], request[1]);
        }
    
        scan.close();
    }
    

    

    public static void StudentAdd(String code, String name, String academicyear, String program, ArrayList<Student> students) {
        // Method untuk menambahkan data mahasiswa
        boolean hasStudent = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(code)) {
                hasStudent = true;
            }
        }
        if (!hasStudent) {
            Student student = new Student(code, name, academicyear, program, "");
            students.add(student);
        }
    }

    public static void EnrollmentAdd(String courseCode, String idStudent, String academicyear, String semester, ArrayList<Enrollment> enrollments, ArrayList<Enrollment> studentEnrollments) {
         // Method untuk menambahkan data pendaftaran kursus
        boolean enroll = false;
        for (Enrollment e : enrollments) {
            if (e.getenrollcode().equals(courseCode) && e.getId().equals(idStudent)
                    && e.getyear().equals(academicyear) && e.getEven().equals(semester)) {
                enroll = true;
            }
        }
        if (!enroll) {
            Enrollment enrollment = new Enrollment(courseCode, idStudent, academicyear, semester);
            enrollments.add(enrollment);
            studentEnrollments.add(enrollment);
        }
    }

    public static void EnrollmentRemedial(String courseCode, String idStudent, String academicyear, String semester, String grade, ArrayList<Enrollment> enrollments) {
        // Method untuk menangani remedial pendaftaran kursus
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getenrollcode().equals(courseCode) && enrollment.getId().equals(idStudent)
                    && enrollment.getyear().equals(academicyear) && enrollment.getEven().equals(semester)) {
                if (enrollment.getgrade().equals("None")) {
                    break;
                } else {
                    if (enrollment.getTotalRemedi() == 0) {
                        enrollment.setreturngrade(grade);
                        enrollment.exchangegrade();
                        enrollment.setTotalRemedi();
                    } else {
                        String previousGrade = enrollment.getreturngrade();
                        enrollment.setRemedial(previousGrade + "(" + grade + ")");
                    }
                    break;
                }
            }
        }
    }

    public static void EnrollmentGrade(String courseCode, String idStudent, String academicyear, String semester, String grade, ArrayList<Enrollment> enrollments) {
         // Method untuk memberikan nilai pendaftaran kursus
        if (grade != "") {
            for (Enrollment enroll : enrollments) {
                if (enroll.getenrollcode().equals(courseCode) && enroll.getId().equals(idStudent)
                        && enroll.getyear().equals(academicyear) && enroll.getEven().equals(semester)) {
                    enroll.setgrade(grade);
                }
            }
        }
    }

    public static void LecturerAdd(String id, String name, String initial, String email, String studyProgram, ArrayList<Lecturer> lecturers) {
        Lecturer lecturer = new Lecturer(id, name, initial, email, studyProgram);
        lecturers.add(lecturer);
    }

    public static void CourseAdd(String code, String name, int credits, String grade, ArrayList<Course> courses) {
        Course course = new Course(code, name, credits);
        course.setGrade(grade);
        courses.add(course);
    }

    public static void StudentDetails(String idStudent, ArrayList<Course> courses, ArrayList<Enrollment> enrollments, ArrayList<Student> students) {
        // Method untuk mendapatkan detail mahasiswa
        String[] stringcourse = new String[5];
        String[] value = new String[5];
        double amountgradepoint = 0;
        int amountcreditpoint = 0;
        int d = 0;
    
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(idStudent)) {
                stringcourse[d] = enrollment.getenrollcode();
                value[d] = enrollment.getgrade();
                d++;
            }
        }
    
        for (int j = 0; j < d; j++) {
            for (int k = j + 1; k < d; k++) {
                if (stringcourse[j].equals(stringcourse[k])) {
                    value[j] = value[k];
                    stringcourse[k] = null;
                    value[k] = null;
                }
            }
        }
    
        for (int j = 0; j < d; j++) {
            if (stringcourse[j] != null && value[j] != null) {
                for (Course course : courses) {
                    if (course.getkode().equals(stringcourse[j])) {
                        amountgradepoint += Student.gradepoint(value[j]) * course.getCredits();
                        amountcreditpoint += course.getCredits();
                    }
                }
            }
        }
    
        double gpa = amountgradepoint / amountcreditpoint;
    
        if (amountgradepoint == 0.0) {
            amountcreditpoint = 0;
        }
    
        for (Student student : students) {
            if (student.getId().equals(idStudent)) {
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getacademicyear() + "|"
                                + student.getStudyProgram() + "|" + String.format("%.2f", gpa) + "|"
                                + amountcreditpoint);
            }
        }
    }
    
    public static void CourseOpen(String courseCode, String academicyear, String semester, String lecturerList, ArrayList<Course> courses, ArrayList<Lecturer> lecturers, ArrayList<CourseOpen> courseOpens) {
         // Method untuk membuka kursus
        String[] lecturersArray = lecturerList.split(",");
        String lecturerListFormatted = "";
        for (int i = 0; i < lecturersArray.length; i++) {
            String lecturerInitial = lecturersArray[i];
            for (Lecturer lecturer : lecturers) {
                if (lecturer.getInisial().equals(lecturerInitial)) {
                    if (i != 0) {
                        lecturerListFormatted += ";";
                    }
                    lecturerListFormatted += lecturer.getInisial() + " (" + lecturer.getEmail() + ")";
                    break;
                }
            }
        }
        boolean getlec = false;
        for (Course course : courses) {
            if (course.getkode().equals(courseCode)) {
                getlec = true;
            }
        }
        for (Course course : courses) {
            if (course.getkode().equals(courseCode) ){
                getlec = true;
            }
            if (getlec) {
                CourseOpen courseOpen = new CourseOpen(courseCode, academicyear, semester, lecturerListFormatted);
                courseOpens.add(courseOpen);
    
            }
        }
    }
    
    public static void CourseHistory(ArrayList<Course> courses, ArrayList<Enrollment> enrollments, ArrayList<CourseOpen> courseOpens) {
        // Method untuk melihat riwayat kursus
        courseOpens.sort((c1, c2) -> c2.getSemester().compareTo(c1.getSemester()));
        for(CourseOpen courseOpen : courseOpens){
            String subject = "";
            String credit = "";
            String grade = "";
    
            String academicyear = courseOpen.getAcademicyear();
            String semester = courseOpen.getSemester();
            for (Course course : courses) {
                if (course.getkode().equals(courseOpen.getCourseCode())) {
                    subject = course.getStudyProgram();
                    credit = Integer.toString(course.getCredits());
                    grade = course.getGrade();
                }
                System.out.println(courseOpen.getCourseCode() + "|" + subject + "|" + credit + "|" + grade + "|" + academicyear + "|" + semester + "|" + courseOpen.getLecturelist());
            }
            for (Enrollment enrollment : enrollments) {
                if(enrollment.getenrollcode().equals(courseOpen.getCourseCode())&& enrollment.getyear().equals(courseOpen.getAcademicyear()) && enrollment.getEven().equals(courseOpen.getSemester())){
                    if(!enrollment.getreturngrade().equals("")){
                        System.out.println(enrollment.toremedial());
                    } else {
                        System.out.println(enrollment.toString());
                    }
                }
            }
        }
    }
    public static boolean isEven(String id) {
        // Extract the numerical part of the student ID and check if it's even
        int numericalPart = Integer.parseInt(id.substring(3));
        return numericalPart % 2 == 0;
    }
    
    //create a best student function using even numbers
    public static void bestStudent(ArrayList<Student> students, ArrayList<Enrollment> enrollments, ArrayList<Course> courses, String academicyear, String semester) {
        String bestStudent = "";
        String bestGradeEven = "";
        String bestGradeOdd = "";
        for (Student student : students) {
            // Check if the student ID is even
            if (isEven(student.getId())) {
                double totalGpaEven = 0;
                double totalGpaOdd = 0;
                int totalCreditsEven = 0;
                int totalCreditsOdd = 0;
                String studentBestGradeEven = "";
                String studentBestGradeOdd = "";
                for (Enrollment enrollment : enrollments) {
                    // Filter enrollments by academic year, semester, and student ID
                    if (enrollment.getId().equals(student.getId()) && enrollment.getyear().equals(academicyear)) {
                        for (Course course : courses) {
                            if (course.getkode().equals(enrollment.getenrollcode())) {
                                double gradePoint = Student.gradepoint(enrollment.getgrade()) * course.getCredits();
                                if (enrollment.getEven().equals("even")) {
                                    totalGpaEven += gradePoint;
                                    totalCreditsEven += course.getCredits();
                                    if (gradePoint > 0 && gradePoint >= Student.gradepoint(studentBestGradeEven)) {
                                        studentBestGradeEven = enrollment.getgrade();
                                    }
                                } else {
                                    totalGpaOdd += gradePoint;
                                    totalCreditsOdd += course.getCredits();
                                    if (gradePoint > 0 && gradePoint >= Student.gradepoint(studentBestGradeOdd)) {
                                        studentBestGradeOdd = enrollment.getgrade();
                                    }
                                }
                            }
                        }
                    }
                }
                // Compare the best grades for this student with the overall best grades
                if (Student.gradepoint(studentBestGradeEven) > Student.gradepoint(bestGradeEven)) {
                    bestGradeEven = studentBestGradeEven;
                    bestStudent = student.getId(); // Update bestStudent for even semester
                }
                if (Student.gradepoint(studentBestGradeOdd) > Student.gradepoint(bestGradeOdd)) {
                    bestGradeOdd = studentBestGradeOdd;
                    bestStudent = student.getId(); // Update bestStudent for odd semester
                }
            }
        }
        // Output the best student and their best grades for odd and even semesters
        if (!bestStudent.isEmpty() && !bestGradeEven.isEmpty() && !bestGradeOdd.isEmpty()) {
            System.out.println(bestStudent + "|" + bestGradeOdd + "/" + bestGradeEven);
        }
    }
    
    
    
    

}




    

