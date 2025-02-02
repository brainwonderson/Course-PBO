package academic.driver;
import java.util.Arrays;
import java.util.Scanner;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import academic.model.Lecturer;
import academic.model.GradeList;

/**
 * @author Brain Wonderson
 */
public class Driver2 {
    
    static final String  DELIM   = "#";
    static final String  DELIM2  = ",";
    static Course[]      matkulz;
    static Student[]     siswaz;
    static Enrollment[]  enrolz;
    static Lecturer[]    dosenz;
    static GradeList[]   gradez;

    public static void main ( String[] _args ) {
        Scanner inp = new Scanner(System.in);
        String input = null;
        String foo = "";
        String[] buf;                   // penampung hasil split
        String order;                   // perintah untuk case
        String[] init;                  // untuk inisial    
        Lecturer tempLec;               // temporary lecturer
        Lecturer newLec;                // for new lecturer
        Course tempCou;                 // temporary course
        Course newCou;                  // for new course
        Student tempStu;                // temporary student
        Student newStu;                 // for new student
        Enrollment tempEnr;             // temporary enrollment
        Enrollment newEnr;              // for new enrollment
        GradeList newGrade;             // for new grade
        GradeList tempGrade;             // temporary grade

        matkulz = new Course[0];
        siswaz = new Student[0];
        enrolz = new Enrollment[0];
        dosenz = new Lecturer[0];
        gradez = new GradeList[0];

        while (true) {
            input = inp.nextLine();
            if (input.equals("---")) break;
            buf = input.split(DELIM);
            order = buf[0];
            buf = Arrays.copyOfRange(buf, 1, buf.length);

            switch (order) {
                case "lecturer-add":
                    tempLec = isDuplicateLec(buf[0], buf[2], buf[3]);
                    if (tempLec != null) break;
                    newLec = arrayToLecturer(buf);
                    addLecturer(newLec);
                    break;

                case "student-add":
                    tempStu = isDuplicateStu(buf[0]);
                    if (tempStu != null) break;
                    newStu = arrayToStudent(buf);
                    addStudent(newStu);
                    break;

                case "course-add":
                    tempCou = isDuplicateCou(buf[0]);
                    if (tempCou != null) break;
                    init = buf[4].split(DELIM2);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < init.length; i++) {
                        for (Lecturer temp : dosenz) {
                            if (temp.getInitial().equals(init[i])) {
                                stringBuilder.append(init[i]).append(" (").append(temp.getEmail()).append((i == init.length - 1) ? ")" : ";");
                            }
                        }
                    }
                    buf[4] = stringBuilder.toString();
                    newCou = arrayToCourse(buf);
                    addCourse(newCou);
                    break;

                case "enrollment-add":
                    tempEnr = isDuplicateEnr(buf[0], buf[1], buf[2]);
                    if (tempEnr != null) break;
                    newEnr = arrayToEnrollment(buf);
                    addEnrollment(newEnr);
                    tempGrade = isDuplicateGrade(buf[0], buf[1]);
                    if (tempGrade == null) {
                        tempCou = isDuplicateCou(buf[0]);
                        newGrade = new GradeList(buf[0], buf[1], tempCou.getCredit(), "");
                       
                        addGrade(newGrade);
                    }
                    break;

                case "enrollment-grade":
                    for (Enrollment x : enrolz) {
                        if (x.getCode().equals(buf[0]) &&
                                x.getId().equals(buf[1]) &&
                                x.getYear().equals(buf[2])) {
                            x.setGrade(buf[4]);
                            break;
                        }
                    }

                    for (GradeList x : gradez) {
                        if (x.getCode().equals(buf[0]) &&
                                x.getId().equals(buf[1])) {
                            x.setGrade(buf[4]);
                        }
                    }
                    break;

                case "student-details":
                    int totalCredit = 0;
                    float tempGPA = 0;
                    float sum = 0;

                    for (GradeList x : gradez) {
                        if (x.getId().equals(buf[0])) {
                            totalCredit += x.getCredit();
                            switch (x.getGrade()) {
                                case "A" -> sum += (4 * x.getCredit());
                                case "AB" -> sum += (3.5 * x.getCredit());
                                case "B" -> sum += (3 * x.getCredit());
                                case "BC" -> sum += (2.5 * x.getCredit());
                                case "C" -> sum += (2 * x.getCredit());
                                case "D" -> sum += (1 * x.getCredit());
                                case "E" -> sum += (0 * x.getCredit());
                            }
                        }
                    }
                    tempStu = isDuplicateStu(buf[0]);
                    tempGPA = (totalCredit == 0) ? 0 : sum / totalCredit;
                    totalCredit = (tempGPA == 0) ? 0 : totalCredit;
                    System.out.printf("%s|%s|%s|%s|%.2f|%d\n", buf[0],
                            tempStu.getName(),
                            tempStu.getEducationYear(),
                            tempStu.getProgram(),
                            tempGPA,
                            totalCredit);
                    break;
            }
        }
        printAll();
        inp.close();
    }

    private static void printAll() {
        printAllLecturers();
        printAllCourses();
        printAllStudents();
        printAllEnrollments();
    }

    public static GradeList isDuplicateGrade(String CODE, String ID) {
        for (GradeList temp : gradez) {
            if (temp.getCode().equals(CODE) &&
                    temp.getId().equals(ID)) {
                return temp;
            }
        }
        return null;
    }

    public static void addGrade(GradeList temp) {
        gradez = Arrays.copyOf(gradez, gradez.length + 1);
        gradez[gradez.length - 1] = temp;
    }

    public static Lecturer isDuplicateLec(String CODE, String INITIAL, String EMAIL) {
        for (Lecturer temp : dosenz) {
            if (temp.getId().equals(CODE) &&
                    temp.getInitial().equals(INITIAL) &&
                    temp.getEmail().equals(EMAIL)) {
                return temp;
            }
        }
        return null;
    }

    private static void printAllLecturers() {
        for (Lecturer temp : dosenz) {
            System.out.println(temp);
        }
    }

    private static void addLecturer(Lecturer temp) {
        dosenz = Arrays.copyOf(dosenz, dosenz.length + 1);
        dosenz[dosenz.length - 1] = temp;
    }

    private static Lecturer arrayToLecturer(String[] temp) {
        return new Lecturer(temp[0], temp[1], temp[2], temp[3], temp[4]);
    }

    public static Course isDuplicateCou(String CODE) {
        for (Course temp : matkulz) {
            if (temp.getCode().equals(CODE)) {
                return temp;
            }
        }
        return null;
    }

    private static void printAllCourses() {
        for (Course temp : matkulz) {
            System.out.println(temp);
        }
    }

    private static void addCourse(Course temp) {
        matkulz = Arrays.copyOf(matkulz, matkulz.length + 1);
        matkulz[matkulz.length - 1] = temp;
    }

    private static Course arrayToCourse(String[] temp) {
        return new Course(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3], temp[4]);
    }

    public static Student isDuplicateStu(String CODE) {
        for (Student temp : siswaz) {
            if (temp.getId().equals(CODE)) {
                return temp;
            }
        }
        return null;
    }

    private static void printAllStudents() {
        for (Student temp : siswaz) {
            System.out.println(temp);
        }
    }

    private static void addStudent(Student temp) {
        siswaz = Arrays.copyOf(siswaz, siswaz.length + 1);
        siswaz[siswaz.length - 1] = temp;
    }

    private static Student arrayToStudent(String[] temp) {
        return new Student(temp[0], temp[1], temp[2], temp[3]);
    }

    public static Enrollment isDuplicateEnr(String CODE, String ID, String YEAR) {
        for (Enrollment temp : enrolz) {
            if (temp.getCode().equals(CODE) &&
                    temp.getId().equals(ID) &&
                    temp.getYear().equals(YEAR)) {
                return temp;
            }
        }
        return null;
    }

    private static void printAllEnrollments() {
        for (Enrollment temp : enrolz) {
            System.out.println(temp);
        }
    }

    private static void addEnrollment(Enrollment temp) {
        enrolz = Arrays.copyOf(enrolz, enrolz.length + 1);
        enrolz[enrolz.length - 1] = temp;
    }

    private static Enrollment arrayToEnrollment(String[] temp) {
        return new Enrollment(temp[0], temp[1], temp[2], temp[3]);
    }

    
}
