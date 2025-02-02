package academic.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author 12S22035 Brain Wonderson
 */


public class Executor {
    private Collection <Course> course_list;
    private Collection <Student> student_list;
    private Collection <Enrollment> enrollment_list;
    private Collection <Lecturer> lecturer_list;
    private Collection <CourseOpening> opened_list;

    private LinkedList <Course> course_ll;
    private LinkedList <Student> student_ll;
    private LinkedList <Enrollment> enrollment_ll;
    private LinkedList <Lecturer> lecturer_ll;
    private LinkedList <CourseOpening> opened_ll;

    public Executor() {
        course_list = new HashSet<>();
        student_list = new HashSet<>();
        enrollment_list = new HashSet<>();
        lecturer_list = new HashSet<>();
        opened_list = new HashSet<>();

        course_ll = new LinkedList<>();
        student_ll = new LinkedList<>();
        enrollment_ll = new LinkedList<>();
        lecturer_ll = new LinkedList<>();
        opened_ll = new LinkedList<>();
    }

    public Collection <Student> getStudents() {
        return student_list;
    }
    public Collection <Course> getCourses() {
        return course_list;
    }

    public void printHistory(String code) {
        for (CourseOpening elem: opened_ll) {
            if (elem.getCode().equals(code))
                System.out.println(elem);
            for (Enrollment obj: enrollment_ll) {
                if (
                        obj.getCourse().getCode().equals(code) &&
                        obj.getAcademicYear().equals(elem.getYear()) &&
                        obj.getSemester().equals(elem.getSemester())
                ) {
                    System.out.println(obj);
                }
            }
        }
    }

    public CourseOpening open_course(String[] args) {

        Course temp = new Course();
        temp = temp.similar(args[0], getCourses());

        if (temp == null) { return null; }

        CourseOpening newObj = new CourseOpening();
        Semester newSem = Semester.N_A;
        newSem = newSem.findSemester(args[2]);
        String[] draft = args[3].split(",");
        args[3] = newObj.make_draft(draft, getLecturers());
        newObj = new CourseOpening(temp, args[1], newSem, args[3]);

        for (Course elem: course_list) {
            if (elem.getCode().equals(newObj.getCode()) && opened_list.add(newObj)) {
                opened_ll.add(newObj);
                Collections.sort(opened_ll);
                return newObj;
            }
        }

        return null;
    }

    // COURSE
    public Course push_back(Course newObj) {
        if (course_list.add(newObj)) {
            course_ll.add(newObj);
            return newObj;
        }
        return null;
    }


    //STUDENT
    public Student push_back(Student newObj) {
        if (student_list.add(newObj)) {
            student_ll.add(newObj);
            return newObj;
        }
        return null;
    }


    //LECTURER
    public Lecturer push_back(Lecturer newObj) {
        if (lecturer_list.add(newObj)) {
            lecturer_ll.add(newObj);
            return newObj;
        }
        return null;
    }
    /* untuk concat dosen */
    public Collection<Lecturer> getLecturers() {
        return lecturer_list;
    }


    //ENROLLMENT
    private Enrollment push_back(Course course, Student student, String year, String semester)
    /* throws Exception */{
        Enrollment newEnrollment = new Enrollment(course, student, year, semester);

        Enrollment temp = new Enrollment();
        temp = temp.similar(course.getCode(), student.getCode(), year, semester, enrollment_list);
        if (temp != null)
            return null;

        if (enrollment_list.add(newEnrollment)) {
            student.put_grade(new GradeList(course, student, "None"));
            enrollment_ll.add(newEnrollment);
            return newEnrollment;
        }
        return null;
    }

    public Enrollment enroll(String code, String id, String year, String semester)
    throws Exception {
        CourseOpening tempOpen = new CourseOpening();
        tempOpen = tempOpen.isOpened(code, year, semester, opened_list);
        Course tempCourse = new Course();
        tempCourse = tempCourse.similar(code, course_list);
        if (tempOpen == null)
            throw new Exception("invalid course|".concat(code));

        Student tempStudent = new Student();
        tempStudent = tempStudent.similar(id, student_list);
        if (tempStudent == null)
            throw new Exception("invalid student|".concat(id));

        return push_back(tempCourse, tempStudent, year, semester);
    }

    public void enrollment_grade(String[] args) {
        Grade grade = Grade.N_A;
        grade = grade.getGrade(args[4]);

        for (Enrollment elem: enrollment_list) {
            if (
                    elem.getCourse().getCode().equals(args[0]) &&
                    elem.getStudent().getCode().equals(args[1]) &&
                    elem.getAcademicYear().equals(args[2]) &&
                    elem.getSemester().equals(args[3])
            ) { elem.setGrade(grade); }
        }

        for (Student student: student_list) {
            if (student.getCode().equals(args[1])) {
                for (GradeList gl: student.getGrade_list()) {
                    if (gl.getCode().equals(args[0])) gl.setGrade(grade);
                }
            }

        }
    }

    public void enrollment_remed(String[] args) {
        Grade grade = Grade.N_A;
        grade = grade.getGrade(args[4]);

        for (Student student: student_list) {
            if (student.getCode().equals(args[1])) {
                for (GradeList gl: student.grade_list) {
                    for (Enrollment enrol: enrollment_list) {
                        if (enrol.getCourse().getCode().equals(args[0]) &&
                            enrol.getStudent().getCode().equals(args[1]) &&
                            enrol.getAcademicYear().equals(args[2]) &&
                            enrol.getSemester().equals(args[3]) &&
                            gl.getCode().equals(args[0]) &&
                            enrol.getExGrade().equals("None"))
                        {gl.setGrade(grade);}
                    }
                }
            }
        }

        for (Enrollment elem: enrollment_list) {
            if (
                    elem.getCourse().getCode().equals(args[0]) &&
                    elem.getStudent().getCode().equals(args[1]) &&
                    elem.getAcademicYear().equals(args[2]) &&
                    elem.getSemester().equals(args[3]) &&
                    elem.getExGrade().equals("None")
            ) { elem.setExGrade(elem.getGrade().getLetter()); elem.setGrade(grade); }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Lecturer elem: lecturer_ll)
            sb.append(elem.toString().concat("\n"));
        for (Course elem: course_ll)
            sb.append(elem.toString().concat("\n"));
        for (Student elem: student_ll)
            sb.append(elem.toString().concat("\n"));
        for (Enrollment elem: enrollment_ll)
            sb.append(elem.toString().concat("\n"));

        return sb.toString();
    }
}