package academic.model;
/**
 * @author 12S22035 Brain Wonderson
 */
public class Enrollment {
    private String code;
    private String id;
    private String year;
    private String semester;
    private String grade = "None";

    // Constructor
    public Enrollment(String code, String id, String year, String semester) {
        this.code = code;
        this.id = id;
        this.year = year;
        this.semester = semester;
    }

    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // toString method
    @Override
    public String toString() {
        return code + "|" + id + "|" + year + "|" + semester + "|" + grade;
    }
}
