package academic.model;

public class Enrollment implements Comparable<Enrollment> {
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

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return this.courseCode + "|" + this.studentId + "|" + this.academicYear + "|" + this.semester + "|" + this.grade;
    }

    @Override
    public int compareTo(Enrollment otherEnrollment) {
        // Comparing by courseCode
        int courseCodeComparison = this.courseCode.compareTo(otherEnrollment.getCourseCode());
        if (courseCodeComparison != 0) {
            return courseCodeComparison;
        }
        // If courseCode is the same, compare by studentId
        return this.studentId.compareTo(otherEnrollment.getStudentId());
    }
}