package academic.model;

public class GradeList {
    private String code;
    private String id;
    private int credit;
    private String grade;

    public GradeList(String code, String id, int credit, String grade) {
        this.code = code;
        this.id = id;
        this.credit = credit;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
