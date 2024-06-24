package academic.model;


/**
 * @author 12S22035 Brain Wonderson
 */
public class Student {
    private String id;
    private String name;
    private int year;
    private String studyProgram;


    public Student(String id, String name, int year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }
}

