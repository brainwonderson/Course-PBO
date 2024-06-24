package academic.driver;

import academic.model.Course;

/**
 * DO NOT EDIT THIS CLASS
 * 
 * @author MSS *
 */
public class Driver1 {

    public static void main(String[] _args) {
        String code = _args[0];
        String name = _args[1];
        int credit = Integer.parseInt(_args[2]);
        String grade = _args[3];
        
        Course course = new Course(code, name, credit, grade);
         System.out.println(course);
    }

}