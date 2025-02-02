package academic.model;
/**
 * @author 12S22016 Desri Dabukke
 */
public class CourseOpen {
    private String courseCode;
    private String academicyear;
    private String  semester;
    private String lecturelist;

    public CourseOpen(String courseCode, String academicyear, String semester, String lecturelist) {
        this.courseCode = courseCode;
        this.academicyear = academicyear;
        this.semester = semester;
        this.lecturelist = lecturelist;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getAcademicyear() {
        return academicyear;
    }

    public String getSemester() {
        return semester;
    }

    public String getLecturelist() {
        return lecturelist;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setAcademicyear(String academicyear) {
        this.academicyear = academicyear;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setLecturelist(String lecturelist) {
        this.lecturelist = lecturelist;
    }

    @Override
    public String toString() {
        return this.courseCode + "|" + this.academicyear + "|" + this.semester + "|" + this.lecturelist;
    }
    
}
