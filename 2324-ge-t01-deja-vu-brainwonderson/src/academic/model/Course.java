package academic.model;


/**
 * @author 12S22035 Brain Wonderson
 */
public class Course {
    String code;
    String name;
    int credit;
    String grade;
    
    public Course(String code, String name, int credit, String grade) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    @Override
    public String toString() {
       return  code + "|" + name + "|" + credit + "|" + grade ;
    }


}