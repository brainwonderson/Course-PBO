package academic.driver;

import java.util.Arrays;
import java.util.Scanner;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import academic.model.Lecturer;

/**
 * @author 12S22035 Brain Wonderson
 */

public class Driver1 {
    
    static final String delim  = "#";
    static final String delim2 = ",";
    static Course[]      matkulz = new     Course[0];
    static Student[]     siswaz  = new    Student[0];
    static Enrollment[]  enrolz  = new Enrollment[0];
    static Lecturer[]    dosenz  = new   Lecturer[0];

   public static void main ( String[] _args )
    {
        Scanner inp = new Scanner( System.in );
        String input = null;
        String foo   =   "";
        String[] buf;               // penampung hasil split
        String order;               // perintah untuk case
        String[]   init    = null;  // untuk inisial
        Lecturer   tempLec = null;  // temporary lecturer
        Lecturer   newLec  = null;  // for new lecturer
        Course     tempCou = null;  // temporary course
        Course     newCou  = null;  // for new course
        Student    tempStu = null;  // temporary student
        Student    newStu  = null;  // for new student
        Enrollment newEnr  = null;  // for new enrollment
        

        while ( 2 > 1 )
        {
            input = inp.nextLine( );
            if ( input.equals("---") ) break;
            buf = null;
            buf = input.split(delim);
            order = buf[0];
            buf = Arrays.copyOfRange( buf, 1, buf.length );

            switch ( order )
            {
                case "lecturer-add":
                    tempLec = null;
                    tempLec = isDuplicateLec( buf[0], buf[2], buf[3] );
                    if ( tempLec != null ) break;
                    else {
                        newLec = null;
                        newLec = arrayToLecturer( buf );
                        addLecturer( newLec );
                        break;
                    }
                
                case "student-add":
                    tempStu = null;
                    tempStu = isDuplicateStu( buf[0] );
                    if ( tempStu != null ) break;
                    else {
                        newStu = null;
                        newStu = arrayToStudent( buf );
                        addStudent( newStu );
                        break;
                    }

                case "course-add":
                    tempCou = null;
                    tempCou = isDuplicateCou( buf[0] );
                    if ( tempCou != null ) break;
                    else {
                        init = buf[4].split( delim2 );
                        for ( int i=0; i<init.length; i++ ) {
                            if ( i != init.length-1 ) {
                                for ( Lecturer temp: dosenz ) {
                                    if ( temp.getInitial( ).equals( init[i] ) ) {
                                        foo = foo.concat( init[i] + " (" + temp.getEmail( ) + ");" );
                                    }
                                }
                                
                            }
                            else {
                                for ( Lecturer temp: dosenz ) {
                                    if ( temp.getInitial( ).equals( init[i] ) ) {
                                        foo = foo.concat( init[i] + " (" + temp.getEmail( ) + ")" );
                                    }
                                }
                            }
                        }
                        
                        buf[4] = foo;
                        newCou = null;
                        newCou = arrayToCourse( buf );
                        addCourse( newCou );
                        foo = "";
                        break;
                    }
                
                case "enrollment-add":
                    newEnr = null;
                    newEnr = arrayToEnrollment( buf );
                    addEnrollment( newEnr );
                    break;
            }
        }

        printAll( );
        inp.close( );

    }

    private static void printAll ( )
    {
        printAllLecturers( );
        printAllCourses( );
        printAllStudents( );
        printAllEnrollments( );
        
    }

    // Lecturers
    public static Lecturer isDuplicateLec ( String CODE,String INITIAL, String EMAIL )
    {
        for ( Lecturer temp : dosenz ) {
            if ( temp.getId( ).equals( CODE ) &&
                 temp.getInitial( ).equals( INITIAL ) &&
                 temp.getEmail( ).equals( EMAIL )) { return temp; }
        }
        return null;
    }
    
    private static void printAllLecturers() { for (Lecturer temp : dosenz) { System.out.println( temp ); } }

    private static void addLecturer ( Lecturer temp )
    {
        dosenz = Arrays.copyOf(dosenz, dosenz.length + 1);
        dosenz[dosenz.length - 1] = temp;
    }

    private static Lecturer arrayToLecturer ( String[] temp)
    {
        Lecturer dosen = new Lecturer( temp[0], temp[1], temp[2], temp[3], temp[4] );
        return dosen;
    }

    // Courses
    public static Course isDuplicateCou ( String CODE )
    {
        for ( Course temp : matkulz ) {
            if ( temp.getCode( ).equals( CODE ) ) { return temp; }
        }
        return null;
    }

    private static void printAllCourses ( ) { for (Course temp : matkulz) { System.out.println( temp ); } }

    private static void addCourse ( Course temp )
    {
        matkulz = Arrays.copyOf(matkulz, matkulz.length + 1);
        matkulz[matkulz.length - 1] = temp;
    }

    private static Course arrayToCourse ( String[] temp ) {
        Course matkul = new Course( temp[0], temp[1], Integer.parseInt( temp[2] ), temp[3], temp[4] );
        return matkul;
    }

    // Students
    public static Student isDuplicateStu ( String CODE )
    {
        for ( Student temp : siswaz ) {
            if ( temp.getId( ).equals( CODE ) ) { return temp; }
        }
        return null;
    }
    
    private static void printAllStudents ( )
    {
        for ( Student temp : siswaz) { System.out.println( temp ); }
    }

    private static void addStudent ( Student temp )
    {
        siswaz = Arrays.copyOf( siswaz, siswaz.length + 1 );
        siswaz[siswaz.length - 1] = temp;
    }

    private static Student arrayToStudent ( String[] temp )
    {
        Student siswa = new Student( temp[0], temp[1], temp[2], temp[3] );
        return siswa;
    }

    

    private static void printAllEnrollments ( )
    {
        for ( Enrollment temp : enrolz ) { System.out.println( temp ); }
    }

    private static void addEnrollment ( Enrollment temp )
    {
        enrolz = Arrays.copyOf( enrolz, enrolz.length + 1 );
        enrolz[enrolz.length - 1] = temp;
    }

    private static Enrollment arrayToEnrollment ( String[] temp )
    {
        Enrollment enrol = new Enrollment( temp[0], temp[1], temp[2], temp[3] );
        return enrol;
    }

}