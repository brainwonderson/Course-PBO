package academic.model;

import java.util.Collection;

/**
 * @author 12S22035 Brain Wonderson
 */

public class Enrollment {
    private final Course course;
    private final Student student;
    private final String academicYear;
    private Semester semester = Semester.N_A;
    private Grade grade;
    private String exGrade;

    public Enrollment() {
        this.course = new Course();
        this.student = new Student();
        this.academicYear = "";
        this.grade = Grade.N_A;
        this.exGrade = "None";
    }

    public Enrollment(Course course, Student student, String academicYear, String semester) {
        this.course = course;
        this.student = student;
        this.academicYear = academicYear;
        this.semester = this.semester.findSemester(semester);
        this.grade = Grade.N_A;
        this.exGrade = "None";
    }

    public Course getCourse() {
        return course;
    }
    public Student getStudent() {
        return student;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public String getSemester() {
        return semester.getWord();
    }

    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public String getExGrade() {
        return exGrade;
    }
    public void setExGrade(String exGrade) {
        this.exGrade = exGrade;
    }

    public Enrollment similar
            (String sought_code, String sought_id, String sought_year,
             String sought_semester, Collection<Enrollment> list) {
        for (Enrollment elem: list) {
            if (
                    elem.getCourse().getCode().equals(sought_code) &&
                    elem.getStudent().getCode().equals(sought_id) &&
                    elem.getAcademicYear().equals(sought_year) &&
                    elem.getSemester().equals(sought_semester)
            ) return elem;
        }
        return null;
    }

    public boolean remedialable(String[] args) {
        return this.getCourse().getCode()
                       .equals(args[0]) &&
               this.getStudent().getCode()
                       .equals(args[1]) &&
               this.getAcademicYear()
                       .equals(args[2]) &&
               this.getSemester()
                       .equals(args[3]) &&
               this.getExGrade()
                       .equals("None");
    }

    public String toString() {
        if (exGrade.equals("None"))
            return String.format("%s|%s|%s|%s|%s", course.getCode(),
                                                       student.getCode(),
                                                       academicYear,
                                                       semester.getWord(),
                                                       grade.getLetter());
        return String.format("%s|%s|%s|%s|%s(%s)", course.getCode(),
                                                   student.getCode(),
                                                   academicYear,
                                                   semester.getWord(),
                                                   grade.getLetter(),
                                                   exGrade);
    }
}