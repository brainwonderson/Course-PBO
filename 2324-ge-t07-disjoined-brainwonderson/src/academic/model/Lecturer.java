package academic.model;

/**
 * @author 12S22035 Brain Wonderson
 */

public class Lecturer extends Entity {
    private final String email;
    private final String initial;
    private final String studyProgram;

    public Lecturer() {
        code = "";
        name = "";
        email = "";
        initial = "";
        studyProgram = "";
    }

    public Lecturer(String code, String name, String initial, String email, String studyProgram) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.initial = initial;
        this.studyProgram = studyProgram;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
    public String getEmail() {
        return email;
    }
    public String getInitial() {
        return initial;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", super.toString(),
                                            initial,
                                            email,
                                            studyProgram);
    }
}