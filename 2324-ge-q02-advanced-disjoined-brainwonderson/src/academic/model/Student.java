package academic.model;

/**
 * @author 12S22016 Desri Dabukke
 */

public class Student extends Humans{
    // private String id;
    // private String name;
    private String academicyear;
    private String studyprogram;
    private double gpa;
    private int totalCredits;

    public Student(String _id, String _name, String _academicyear, String studyprogram, String department) {
        super(_id, _name);
        this.academicyear = _academicyear;
        this.studyprogram = studyprogram;
        this.gpa = 0;
        this.totalCredits = 0;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getacademicyear() {
        return this.academicyear;
    }

    public String getStudyProgram() {
        return this.studyprogram;
    }

    public String getYear() {
        return null;
    }

    public double getGpa() {
        return this.gpa;
    }

    public int getTotalCredits(){
        if (this.gpa == 0.0) {
            return 0;
        } else{
        return totalCredits;
        }
    }

    public void setTotalCredits(int _totalCredits){
        this.totalCredits = _totalCredits; 
    }

    // buat public gradepoint a, ab, b, bc, c, d, e = 4, 3.5, 3, 2.5, 2, 1, 0
    public static double gradepoint(String grade){
        if (grade.equals("A")) {
            return 4.0;
        } else if (grade.equals("AB")) {
            return 3.5;
        } else if (grade.equals("B")) {
            return 3.0;
        } else if (grade.equals("BC")) {
            return 2.5;
        } else if (grade.equals("C")) {
            return 2.0;
        } else if (grade.equals("D")) {
            return 1.0;
        } else if (grade.equals("E")) {
            return 0.0;
        } else {
            return 0.0;
        }
    }



    @Override
    public String toString() {
        return this.id + "|" + this.name + "|" + this.academicyear + "|" + this.studyprogram;
    }

    public static String gradeFromGPA(double gpa2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gradeFromGPA'");
    }

    public void setGpa(double gpa2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGpa'");
    }
}