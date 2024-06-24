package academic.model;

public class Enrollment {
    private String courseCode;
    private String studentId;
    private String academicYear;
    private String semester;
    private String grade;

    public Enrollment(String courseCode, String studentId, String academicYear, String semester) {
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = "None";
    }

    @Override
    public String toString() {
        return this.courseCode + "|" + this.studentId + "|" + this.academicYear + "|" + this.semester + "|" + this.grade;
    }
}
