package academic.model;

/**
 * @author 12S22035 Brain Wonderson
 */


public class GradeList {
    private final Course course;
    private final Student student;
    private Grade grade = Grade.N_A;

    public GradeList(Course _course, Student _student, String _grade ) {
        course = _course;
        student = _student;
        grade = grade.getGrade(_grade);
    }

//    public void setGrade(String newGrade) {
//        for (Grade elem: Grade.values()) {
//            if (elem.getLetter().equals(newGrade))
//                grade = elem;
//        }
//    }
    public void setGrade(Grade newObj) {
        grade = newObj;
    }

    public String getCode() {
        return course.getCode();
    }
    public String getId() {
        return student.getCode();
    }
    public int getCredit() {
        return course.getCredit();
    }
    public double getGrade() {
        return grade.getScale();
    }
}