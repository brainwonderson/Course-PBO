package academic.driver;

import academic.model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

/**
 * @author 12S22035 Brain Wonderson
 */

public class Driver1 {

    static final String DELIM = "#";

    public static void main(String[] args) {
        Executor execute = new Executor();
        Scanner inp = new Scanner(System.in);
        String stdin;
        String[] buf;

        while ( true ) {
            stdin = inp.nextLine();
            if (stdin.equals("---")) break;
            buf = stdin.split(DELIM);
            String order = buf[0];
            buf = Arrays.copyOfRange(buf, 1, buf.length);

            switch ( order ) {
                case "course-add":
                    execute.push_back(new Course(buf[0], buf[1], Integer.parseInt(buf[2]), buf[3]));
                    break;

                case "student-add":
                    execute.push_back(new Student(buf[0], buf[1], buf[2], buf[3]));
                    break;

                case "lecturer-add":
                    execute.push_back(new Lecturer(buf[0], buf[1], buf[2], buf[3], buf[4]));
                    break;

                case "enrollment-add":
                    try {
                        execute.enroll(buf[0],buf[1],buf[2],buf[3]);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }
                    break;

                case "enrollment-grade":
                    execute.enrollment_grade(buf);
                    break;

                case "enrollment-remedial":
                    execute.enrollment_remed(buf);
                    break;

                case "student-details":
                    Student to_be_printed = new Student();
                    to_be_printed = to_be_printed.similar(buf[0], execute.getStudents());
                    System.out.println(to_be_printed.details());
                    break;

                case "course-open":
                    execute.open_course(buf);
                    break;

                case "course-history":
                    execute.printHistory(buf[0]);
                    break;

            }

        }

        System.out.println(execute);
        inp.close();
    }
}