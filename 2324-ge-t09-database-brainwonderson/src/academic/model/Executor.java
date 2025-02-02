package academic.model;

import java.util.*;

/**
 * @author 12S22035 Brain Wonderson
 */

public class Executor {
    private Collection <Course> course_set;
    private Collection <Student> student_set;
    private Collection <Enrollment> enrollment_set;
    private Collection <Lecturer> lecturer_set;
    private Collection <CourseOpening> opened_set;

    private LinkedList <Course> course_list;
    private LinkedList <Student> student_list;
    private LinkedList <Enrollment> enrollment_list;
    private LinkedList <Lecturer> lecturer_list;
    private LinkedList <CourseOpening> opened_list;

    public Executor() {
        course_set = new HashSet<>();
        student_set = new HashSet<>();
        enrollment_set = new HashSet<>();
        lecturer_set = new HashSet<>();
        opened_set = new HashSet<>();

        course_list = new LinkedList<>();
        student_list = new LinkedList<>();
        enrollment_list = new LinkedList<>();
        lecturer_list = new LinkedList<>();
        opened_list = new LinkedList<>();
    }

    public Collection <Student> getStudents() {
        return student_set;
    }

    public Collection <Course> getCourses() {
        return course_set;
    }

    public void printHistory(String code) {
        for (CourseOpening elem: opened_list) {
            if (elem.getCode().equals(code)) {
                System.out.println(elem);

                for (Enrollment obj : enrollment_list) {
                    if ( obj.getCourse().getCode().equals(code) &&
                         obj.getAcademicYear().equals(elem.getYear()) &&
                         obj.getSemester().equals(elem.getSemester())
                        ) {
                        System.out.println(obj);
                    }
                }
            }
        }
    }

    public void printTranscript(String code) {
        Student person = new Student();
        person = person.similar(code, student_set);
        System.out.println(person.details());

        Student finalPerson = person;
        class Transcript {      // nested class
            public void allCourses() {
                Collection <String> history = new HashSet<>();
                for (GradeList elem: finalPerson.grade_list) {
                    history.add(elem.getCode());
                }

                LinkedList <Enrollment> transcript_list = new LinkedList<>();
                LinkedList <Enrollment> enrollment_copy;
                enrollment_copy = (LinkedList) enrollment_list.clone();
                Comparator<Enrollment> comparator =(o1, o2) -> {
                    String[] data1 = o1.getAcademicYear().split("/");
                    int year1 = Integer.parseInt(data1[0]);

                    String[] data2 = o2.getAcademicYear().split("/");
                    int year2 = Integer.parseInt(data2[0]);

                    int value;

                    value = Integer.compare(year1, year2);

                    return value;
                };
                enrollment_copy.sort(comparator);

                for (String elem: history) {
                    for (int i=enrollment_copy.size()-1; i>=0; i--) {
                        if (enrollment_copy.get(i).getCourse().getCode().equals(elem) &&
                            enrollment_copy.get(i).getStudent().getCode().equals(code))
                        {
                            boolean hasCourse = false;
                            for (Enrollment obj: transcript_list) {
                                if (obj.getCourse().getCode().equals(elem)) {
                                    hasCourse = true;
                                    break;
                                }
                            }
                            if (!hasCourse)
                                transcript_list.add(enrollment_copy.get(i));
                        }
                    }
                }
                transcript_list.sort(comparator);

                for (Enrollment elem: transcript_list) {
                    System.out.println(elem);
                }

            }

        }

        Transcript transcript = new Transcript();
        transcript.allCourses();

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

        for (Course elem: course_set) {
            if (elem.getCode().equals(newObj.getCode()) && opened_set.add(newObj)) {
                opened_list.add(newObj);
                Collections.sort(opened_list);
                return newObj;
            }
        }

        return null;
    }

    // COURSE
    public Course push_back(Course newObj) {
        if (course_set.add(newObj)) {
            course_list.add(newObj);
            return newObj;
        }
        return null;
    }


    //STUDENT
    public Student push_back(Student newObj) {
        if (student_set.add(newObj)) {
            student_list.add(newObj);
            return newObj;
        }
        return null;
    }


    //LECTURER
    public Lecturer push_back(Lecturer newObj) {
        if (lecturer_set.add(newObj)) {
            lecturer_list.add(newObj);
            return newObj;
        }
        return null;
    }

    public Collection<Lecturer> getLecturers() {
        return lecturer_set;
    }


    //ENROLLMENT
    private Enrollment push_back(Course course, Student student, String year, String semester)
    /* throws Exception */{
        Enrollment newEnrollment = new Enrollment(course, student, year, semester);

        Enrollment temp = new Enrollment();
        temp = temp.similar(course.getCode(), student.getCode(), year, semester, enrollment_set);
        if (temp != null)
            return null;

        if (enrollment_set.add(newEnrollment)) {
            student.put_grade(new GradeList(course, student, "None"));
            enrollment_list.add(newEnrollment);
            return newEnrollment;
        }
        return null;
    }

    public Enrollment enroll(String code, String id, String year, String semester)
    throws Exception {
        CourseOpening tempOpen = new CourseOpening();
        tempOpen = tempOpen.isOpened(code, year, semester, opened_set);
        Course tempCourse = new Course();
        tempCourse = tempCourse.similar(code, course_set);
        if (tempOpen == null)
            throw new Exception("invalid course|".concat(code));

        Student tempStudent = new Student();
        tempStudent = tempStudent.similar(id, student_set);
        if (tempStudent == null)
            throw new Exception("invalid student|".concat(id));

        return push_back(tempCourse, tempStudent, year, semester);
    }

    public void enrollment_grade(String[] args) {
        Grade grade = Grade.N_A;
        grade = grade.getGrade(args[4]);

        for (Enrollment elem: enrollment_set) {
            if (
                    elem.getCourse().getCode().equals(args[0]) &&
                    elem.getStudent().getCode().equals(args[1]) &&
                    elem.getAcademicYear().equals(args[2]) &&
                    elem.getSemester().equals(args[3])
            ) { elem.setGrade(grade); }
        }

        boolean yearBigger = true;
        String[] data1 = args[2].split("/");
        int year1 = Integer.parseInt(data1[0]);
        for (Enrollment obj: enrollment_set) {
            String[] data2 = obj.getAcademicYear().split("/");
            int year2 = Integer.parseInt(data2[0]);
            if (year1 < year2) {
                yearBigger = false; break;
            }
        }

        if (yearBigger) {
            for (Student student : student_set) {
                if (student.getCode().equals(args[1])) {
                    for (GradeList gl : student.getGrade_list()) {
                        if (gl.getCode().equals(args[0])) gl.setGrade(grade);
                    }
                }

            }
        }
    }

    public void enrollment_remedy(String[] args) {
        Grade grade = Grade.N_A;
        grade = grade.getGrade(args[4]);

        for (Student student: student_set) {
            if (student.getCode().equals(args[1])) {
                for (GradeList gl: student.grade_list) {
                    for (Enrollment enrol: enrollment_set) {
                        if (gl.getCode().equals(args[0]) && enrol.remedialable(args)) {
                            gl.setGrade(grade);
                            break;
                        }
                    }
                }
            }
        }

        for (Enrollment elem: enrollment_set) {
            if (elem.remedialable(args)) {
                elem.setExGrade(elem.getGrade().getLetter());
                elem.setGrade(grade);
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Lecturer elem: lecturer_list)
            sb.append(elem.toString().concat("\n"));
        for (Course elem: course_list)
            sb.append(elem.toString().concat("\n"));
        for (Student elem: student_list)
            sb.append(elem.toString().concat("\n"));
        for (Enrollment elem: enrollment_list)
            sb.append(elem.toString().concat("\n"));

        return sb.toString();
    }
}