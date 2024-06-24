package academic.model;


/**
 * @author 12S22035 Brain Wonderson
 */
public class Course {
    private String code;
    private String name;
    private int sks;
    private String grade;

    public Course(String code, String name, int sks, String grade) {
        this.code = code;
        this.name = name;
        this.sks = sks;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return code + "|" + name + "|" + sks + "|" + grade;
    }
}

