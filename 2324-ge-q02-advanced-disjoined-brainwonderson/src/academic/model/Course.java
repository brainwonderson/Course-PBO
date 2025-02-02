package academic.model;

/**
 * @author 12S22016 Desri Dabukke
 */

public class Course {
    private String kode;
    private String studyProgram;
    private int credits;
    private String grade;
    private String lecturer_initial;

    public Course(String kode, String studyProgram, int credits) {
        this.kode = kode;
        this.studyProgram = studyProgram;
        this.credits = credits;
        this.grade = "";
        this.lecturer_initial = "";
    }

    public String getkode() {
        return this.kode;
    }

    public void setkode(String kode) {
        this.kode = kode;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

    public int getCredits() {
        return this.credits;
    }

    public String getGrade() {
        return this.grade;
    }


    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLecturer() {
        return this.lecturer_initial;
    }

    public void setLecturer(String lecturer) {
        this.lecturer_initial = lecturer;
    }

    
    public String getEmail() {
        return null;
    }  

    @Override
    public String toString() {
        return this.kode + "|" + this.studyProgram + "|" + this.credits + "|" + this.grade /*+ "|" + this.lecturer_initial*/;
    }
}
