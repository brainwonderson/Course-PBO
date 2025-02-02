package academic.model;

import java.util.Collection;

/**
 * @author 12S22035 Brain Wonderson
 */

public class Course extends Entity {
    private final int credit;
    private final String passingGrade;

    public Course() {
        code = "";
        name = "";
        credit = 0;
        passingGrade = "";
    }

    public Course(String code, String name, int credit, String passingGrade) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
    }

    public int getCredit() {
        return credit;
    }
    public String getPassingGrade() {
        return passingGrade;
    }

    public Course similar(String sought, Collection<Course> list) {
        for (Course elem: list) {
            if (elem.getCode().equals(sought))
                return elem;
        }
        return null;
    }


    @Override
    public String toString() {
        return String.format("%s|%d|%s", super.toString(),
                                         credit,
                                         passingGrade);
    }
}