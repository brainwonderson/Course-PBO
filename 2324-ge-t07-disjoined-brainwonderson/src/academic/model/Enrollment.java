package academic.model;
/**
 * @author 12S22035 Brain Wonderson
 */

import java.util.Collection;

public class Enrollment<T extends Course, S extends Student> {
    private final T course;
    private final S student;
    private final String academicYear;
    private Semester semester = Semester.N_A;
    private Grade grade;
    private String exGrade;

    public Enrollment() {
        this.course = null;
        this.student = null;
        this.academicYear = "";
        this.grade = Grade.N_A;
        this.exGrade = "None";
    }

    public Enrollment(T course, S student, String academicYear, String semester) {
        this.course = course;
        this.student = student;
        this.academicYear = academicYear;
        this.semester = this.semester.findSemester(semester);
        this.grade = Grade.N_A;
        this.exGrade = "None";
    }

    public T getCourse() {
        return course;
    }

    public S getStudent() {
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

    public Enrollment<T, S> similar(String sought_code, String sought_id, String sought_year, String sought_semester, Collection<Enrollment<T, S>> list) {
        for (Enrollment<T, S> elem : list) {
            if (
                    elem.getCourse().getCode().equals(sought_code) &&
                            elem.getStudent().getCode().equals(sought_id) &&
                            elem.getAcademicYear().equals(sought_year) &&
                            elem.getSemester().equals(sought_semester)
            ) return elem;
        }
        return null;
    }

    @Override
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
