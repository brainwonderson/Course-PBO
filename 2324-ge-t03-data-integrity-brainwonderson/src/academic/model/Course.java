package academic.model;

public class Course implements Comparable<Course> {
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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getSks() {
        return sks;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return code + "|" + name + "|" + sks + "|" + grade;
    }

    @Override
    public int compareTo(Course otherCourse) {
        return this.code.compareTo(otherCourse.getCode());
    }
}