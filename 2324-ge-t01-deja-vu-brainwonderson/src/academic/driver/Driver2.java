package academic.driver;

import academic.model.Student;

/**
 * DO NOT EDIT THIS CLASS
 * 
 * @author MSS
 */
public class Driver2 {

    public static void main(String[] _args) {
        Student student = new Student();
        student.setId(_args[0]);
        student.setName(_args[1]);
        int year = Integer.parseInt(_args[2]);
        student.setYear(year);
        student.setStudyProgram(_args[3]);

        System.out.println(student);
    }

}