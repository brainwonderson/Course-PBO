package academic.model;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author 12S22035 Brain Wonderson
 */


public class Student extends Entity {
    private final String year;
    private final String studyProgram;
    protected Collection <GradeList> grade_list;

    public Student() {
        code = "";
        name = "";
        year = "";
        studyProgram = "";
        grade_list = new HashSet<>();
    }

    public Student(String code, String name, String year, String studyProgram) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
        grade_list = new HashSet<>();
    }

    public String getYear() {
        return year;
    }
    public String getStudyProgram() {
        return studyProgram;
    }

    public Student similar(String sought, Collection<Student> list) {
        for (Student elem: list) {
            if (elem.getCode().equals(sought))
                return elem;
        }
        return null;
    }

    public GradeList put_grade(GradeList newObj) {
        boolean noSimilar = true;
        for (GradeList elem: grade_list) {
            if (elem.getCode().equals(newObj.getCode()))
                noSimilar = false; break;
        }
        if (noSimilar && grade_list.add(newObj)) {
//            System.out.printf(""); /* jika tidak print sesuatu, total credit jadi 11 bukan 7 */
            return newObj;
            }
        return null;
    }

    public Collection <GradeList> getGrade_list() {
        return grade_list;
    }

    public String details() {
        double gpa = 0;
        int totalCredit = 0;
        for (GradeList elem: grade_list) {
            totalCredit += elem.getCredit();
            gpa += ( elem.getGrade()*elem.getCredit() );
        }
        gpa = (gpa==0 && totalCredit==0)? 0.00 : gpa/totalCredit;
        totalCredit = (gpa==0)? 0 : totalCredit;
        return String.format("%s|%s|%s|%s|%.2f|%d", code,
                                                    name,
                                                    year,
                                                    studyProgram,
                                                    gpa,
                                                    totalCredit);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", super.toString(),
                                                year,
                                                studyProgram);
    }
}