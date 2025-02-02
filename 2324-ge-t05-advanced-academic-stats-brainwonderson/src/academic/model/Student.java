package academic.model;

public class Student extends Person {
    private String educationYear;

    public Student() {
        super();
        educationYear = "";
    }

    public Student(String id, String name, String educationYear, String program) {
        super(id, name, program);
        this.educationYear = educationYear;
    }

    // Accessors and mutators for educationYear
    public String getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(String educationYear) {
        this.educationYear = educationYear;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", id, name, educationYear, program);
    }
}
