package academic.model;

public class Student implements Comparable<Student> {
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + studyProgram;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.id.compareTo(otherStudent.getId());
    }
}