package academic.model;

import java.util.Collection;

/**
 * @author 12S22035 Brain Wonderson
 */

public class CourseOpening implements Comparable<CourseOpening>{  //menggunakan interface
    private Course course;
    private String year;
    private Semester semester;
    private String initDraft;

    public CourseOpening() {
        this.course = new Course();
        this.year = "";
        this.semester = Semester.N_A;
        this.initDraft = "";
    }

    public CourseOpening(Course course, String year, Semester semester, String initDraft) {
        this.course = course;
        this.year = year;
        this.semester = semester;
        this.initDraft = initDraft;
    }

    @Override
    public int compareTo(CourseOpening comparator) {
        if (semester.ordinal() > comparator.semester.ordinal()) {
            return 1;
        } else if (semester.ordinal() == comparator.semester.ordinal()) {
            return 0;
        } else {
            return -1;
        }
    }

    public CourseOpening isOpened(String code, String year, String semester, Collection<CourseOpening> list) {
        for (CourseOpening elem: list) {
            if (
                    elem.getCode().equals(code) &&
                    elem.semester.getWord().equals(semester) &&
                    elem.year.equals(year)
            )
                return elem;
        }
        return null;
    }


    public String make_draft(String[] init, Collection<Lecturer> list) {
        String draft = "";
        for (short i=0; i<init.length; i++) {
            for (Lecturer elem: list) {
                if (elem.getInitial().equals(init[i]))
                    draft = draft.concat(init[i] +
                            " (" +
                            elem.getEmail() +
                            ( i==init.length-1? ")" : ");" ));
            }
        }

        return draft;
    }

    public String getCode() {
        return course.code;
    }
    public String getYear() {
        return year;
    }
    public String getSemester() {
        return semester.getWord();
    }

    public String toString() {
        return String.format("%s|%s|%d|%s|%s|%s|%s", course.getCode(),
                                                     course.getName(),
                                                     course.getCredit(),
                                                     course.getPassingGrade(),
                                                     year,
                                                     semester.getWord(),
                                                     initDraft);
    }
}