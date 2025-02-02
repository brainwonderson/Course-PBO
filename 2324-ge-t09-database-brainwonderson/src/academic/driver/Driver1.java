package academic.driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;
import academic.model.*;

public class Driver1 {
    static final String DB_URL = "jdbc:mysql://localhost:3306/db_barang";
    static final String USER = "root";
    static final String PASS = "";

    static final String DELIM = "#";

    private static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
                //tabel dosen
            stmt.execute("CREATE TABLE IF NOT EXISTS lecturers (id VARCHAR(255) PRIMARY KEY, nama TEXT, email TEXT, departement TEXT)");

            // tabel matakuliah
            stmt.execute("CREATE TABLE IF NOT EXISTS courses (id VARCHAR(255) PRIMARY KEY, name TEXT, credits INTEGER)");

            //tabel mahasiswa 
            stmt.execute("CREATE TABLE IF NOT EXISTS student (id VARCHAR(255) PRIMARY KEY, nama TEXT, year INTEGER, departement TEXT)");

            // Membuat tabel course_openings
            stmt.execute("CREATE TABLE IF NOT EXISTS course_openings (id INTEGER AUTO_INCREMENT PRIMARY KEY, course_code VARCHAR(255), academic_year TEXT, semester TEXT, lecturer_codes TEXT, FOREIGN KEY (course_code) REFERENCES courses(id))");
            
             //tabel enrollment
             stmt.execute("CREATE TABLE IF NOT EXISTS enrollments (" +
             "id INT AUTO_INCREMENT PRIMARY KEY, " +
             "course_code VARCHAR(255), " +
             "student_id VARCHAR(255), " +
             "academic_year TEXT, " +
             "semester TEXT, " +
             "FOREIGN KEY (course_code) REFERENCES courses(id), " +
             "FOREIGN KEY (student_id) REFERENCES student(id))");

             //tabel grades
            stmt.execute("CREATE TABLE IF NOT EXISTS grade (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "course_code VARCHAR(255), " +
            "student_id VARCHAR(255), " +
            "academic_year TEXT, " +
            "semester TEXT, " +
            "grade INT, " +
            "FOREIGN KEY (course_code) REFERENCES courses(id), " +
            "FOREIGN KEY (student_id) REFERENCES student(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS student_details (id VARCHAR(255) PRIMARY KEY, nama TEXT, year INTEGER, departement TEXT)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addLecturer(String id, String nama, String email, String departement) {
        String sql = "INSERT IGNORE INTO lecturers (id, nama, email, departement) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, nama);
            pstmt.setString(3, email);
            pstmt.setString(4, departement);
            pstmt.executeUpdate(); // Execute the statement to insert data
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    private static void addCourse(String id, String name, int credits){
        String sql = "INSERT IGNORE INTO courses(id, name, credits) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt  = conn.prepareStatement(sql)) {
                    pstmt.setString(1, id);
                    pstmt.setString(2, name);
                    pstmt.setInt(3, credits);
                    pstmt.executeUpdate();
                    
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addStudent(String id, String nama, int year, String departement){
        String sql = "INSERT IGNORE INTO student(id, nama, year, departement) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, id);
                    pstmt.setString(2, nama);
                    pstmt.setInt(3, year);
                    pstmt.setString(4, departement);
                    pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void openCourse(String code, String academicYear, String semester, String lecturerCodes) {
        String sql= "INSERT IGNORE INTO course_openings(course_code, academic_year, semester, lecturer_codes) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, code);
                    pstmt.setString(2, academicYear);
                    pstmt.setString(3, semester);
                    pstmt.setString(4, lecturerCodes);
                    pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEnrollment(String courseCode, String studentId, String academicYear, String semester) {
        String sql = "INSERT IGNORE INTO enrollments(course_code, student_id, academic_year, semester) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, courseCode);
                    pstmt.setString(2, studentId);
                    pstmt.setString(3, academicYear);
                    pstmt.setString(4, semester);
                    pstmt.executeUpdate();  
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
    }
    }

    private static void addGrade(String courseCode, String studentId, String academicYear, String semester, String grade) {
        // Pertama, periksa apakah kolom 'grade' ada di tabel 'enrollments'. Jika tidak, tambahkan.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM enrollments LIKE 'grade'");
            if (!rs.next()) {
                // Kolom 'grade' tidak ada, tambahkan ke tabel
                stmt.execute("ALTER TABLE enrollments ADD COLUMN grade VARCHAR(10)");
            }
        } catch (SQLException e) {
            System.out.println("Error saat memeriksa atau menambahkan kolom 'grade': " + e.getMessage());
            return;
        }
    
        // Sekarang perbarui 'grade' untuk kursus, mahasiswa, tahun akademik, dan semester yang ditentukan
        String sql = "UPDATE enrollments SET grade = ? WHERE course_code = ? AND student_id = ? AND academic_year = ? AND semester = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, grade);
            pstmt.setString(2, courseCode);
            pstmt.setString(3, studentId);
            pstmt.setString(4, academicYear);
            pstmt.setString(5, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saat memperbarui nilai: " + e.getMessage());
        }
    }

    private static void clearGrade(String courseCode, String studentId, String academicYear, String semester) {
        String sql = "UPDATE enrollments SET grade = NULL WHERE course_code = ? AND student_id = ? AND academic_year = ? AND semester = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseCode);
            pstmt.setString(2, studentId);
            pstmt.setString(3, academicYear);
            pstmt.setString(4, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saat menghapus data pada kolom 'grade': " + e.getMessage());
        }
    }

    private static void clearEnrollment(String courseCode, String studentId, String academicYear, String semester) {
        String sql = "DELETE FROM enrollments WHERE course_code = ? AND student_id = ? AND academic_year = ? AND semester = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseCode);
            pstmt.setString(2, studentId);
            pstmt.setString(3, academicYear);
            pstmt.setString(4, semester);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void clearCourses() {
    String sql = "DELETE FROM courses";
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.executeUpdate();
        System.out.println("Semua data berhasil dihapus dari tabel courses.");
    } catch (SQLException e) {
        System.out.println("Error saat menghapus data dari tabel courses: " + e.getMessage());
    }
}


    private static void getStudentTranscript(String studentId) {
        String studentInfoQuery = "SELECT id, name, year, departement FROM student WHERE id = ?";
        String enrollmentQuery = "SELECT course_code, student_id, academic_year, semester, grade FROM enrollments WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt1 = conn.prepareStatement(studentInfoQuery);
             PreparedStatement pstmt2 = conn.prepareStatement(enrollmentQuery)) {
             
            pstmt1.setString(1, studentId);
            ResultSet studentInfo = pstmt1.executeQuery();
            if (studentInfo.next()) {
                System.out.println(studentInfo.getString("id")+ "|" + studentInfo.getString("name")+"|"+studentInfo.getInt("year") + "|" + studentInfo.getString("departement"));
            }

            pstmt2.setString(1, studentId);
            ResultSet enrollments = pstmt2.executeQuery();
            while (enrollments.next()) {
                System.out.println(enrollments.getString("course_code") + "|" + enrollments.getString("academic_year") + "|" + enrollments.getString("semester") + "|" + enrollments.getString("grade"));
            }   
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void getCourseHistory(String courseCode) {
        String courseInfoQuery = "SELECT id, name, credits FROM courses WHERE id = ?";
        String courseOpeningQuery = "SELECT academic_year, semester, lecturer_codes FROM course_openings WHERE course_code = ?";
        String enrollmentQuery = "SELECT student_id, academic_year, semester, grade FROM enrollments WHERE course_code = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt1 = conn.prepareStatement(courseInfoQuery);
             PreparedStatement pstmt2 = conn.prepareStatement(courseOpeningQuery);
             PreparedStatement pstmt3 = conn.prepareStatement(enrollmentQuery)) {
             
            pstmt1.setString(1, courseCode);
            ResultSet courseInfo = pstmt1.executeQuery();
            if (courseInfo.next()) {
                System.out.println(courseInfo.getString("id")+ "|" + courseInfo.getString("name")+"|"+courseInfo.getInt("credits"));
            }

            pstmt2.setString(1, courseCode);
            ResultSet courseOpening = pstmt2.executeQuery();
            while (courseOpening.next()) {
                System.out.println(courseOpening.getString("academic_year") + "|" + courseOpening.getString("semester") + "|" + courseOpening.getString("lecturer_codes"));
            }

            pstmt3.setString(1, courseCode);
            ResultSet enrollments = pstmt3.executeQuery();
            while (enrollments.next()) {
                System.out.println(enrollments.getString("student_id") + "|" + enrollments.getString("academic_year") + "|" + enrollments.getString("semester") + "|" + enrollments.getString("grade"));
            }   
            
        } catch (Exception e) {    
            System.out.println(e.getMessage());
        }
    }

    private static void addStudentDetail(String id, String nama, int year, String departement) {
    String sql = "INSERT INTO student_details (id, nama, year, departement) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, id);
    pstmt.setString(2, nama);
    pstmt.setInt(3, year);
    pstmt.setString(4, departement);
    pstmt.executeUpdate();
    } catch (SQLException e) {
    System.out.println(e.getMessage());
}
}

// private static void addLecturer(String id, String nama, String email, String departement) {
//     String sql = "INSERT IGNORE INTO lecturers (id, nama, email, departement) VALUES (?, ?, ?, ?)";
//     try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//          PreparedStatement pstmt = conn.prepareStatement(sql)) {
//         pstmt.setString(1, id);
//         pstmt.setString(2, nama);
//         pstmt.setString(3, email);
//         pstmt.setString(4, departement);
//         pstmt.executeUpdate(); // Execute the statement to insert data
        
//     } catch (SQLException e) {
//         System.out.println(e.getMessage());
//     }
// }

    
    public static void main(String[] args) {
        createTable();
        Executor execute = new Executor();
        Scanner inp = new Scanner(System.in);
        String stdin;
        String[] buf;

        while ( true ) {
            stdin = inp.nextLine();
            if (stdin.equals("---")) break;
            buf = stdin.split(DELIM);
            String order = buf[0];
            buf = Arrays.copyOfRange(buf, 1, buf.length);

            switch ( order ) {
                case "course-add":
                    execute.push_back(new Course(buf[0], buf[1], Integer.parseInt(buf[2]), buf[3]));
                    addCourse(buf[0], buf[1], Integer.parseInt(buf[2]));
                    break;

                case "student-add":
                execute.push_back(new Student(buf[0], buf[1], buf[2], buf[3]));
                addStudent(buf[0], buf[1], Integer.parseInt(buf[2]), buf[3]);
                addStudentDetail(buf[0], buf[1], Integer.parseInt(buf[2]), buf[3]);
                    break;

                case "lecturer-add":
                    execute.push_back(new Lecturer(buf[0], buf[1], buf[2], buf[3], buf[4]));
                    addLecturer(buf[0], buf[1], buf[3], buf[4]);
                    break;

                case "enrollment-add":
                    try {
                        execute.enroll(buf[0],buf[1],buf[2],buf[3]);
                       addEnrollment(buf[0], buf[1], buf[2], buf[3]);
                        //clearEnrollment(buf[0], buf[1], buf[2], buf[3]);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }
                    break;

                case "enrollment-grade":
                    execute.enrollment_grade(buf);
                    addGrade(buf[0], buf[1], buf[2], buf[3], buf[4]);
                    //clearGrade(buf[0], buf[1], buf[2], buf[3]);
                    break;

                case "enrollment-remedial":
                    execute.enrollment_remedy(buf);
                    
                    break;

                case "student-details":
                    Student to_be_printed = new Student();
                    to_be_printed = to_be_printed.similar(buf[0], execute.getStudents());
                    System.out.println(to_be_printed.details());
                    
                    break;

                case "course-open":
                    execute.open_course(buf);
                    openCourse(buf[0], buf[1], buf[2], buf[3]);
                    break;

                case "course-history":
                    execute.printHistory(buf[0]);
                    break;

                case "student-transcript":
                    execute.printTranscript(buf[0]);
                    break;

            }

        }

        System.out.print(execute);
        inp.close();
    }
}
