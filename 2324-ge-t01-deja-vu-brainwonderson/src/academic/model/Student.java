package academic.model;

/**
 * @author 12S22035 Brain Wonderson
 */
public class Student {
    private String id;
    private String name;
    private int year;
    private String studyProgram;

    // Konstruktor
    public Student() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }
}
