package academic.model;

/**
 * @author 12S22016 Desri Dabukke
 */

public class Enrollment {
    private String enrollcode;
    private String id;
    private String year;
    private String semester;
    private String grade;
    private String returngrade;
    private String remedial;
    private int totalremedial;

    public Enrollment(String enroll_code, String _nim, String _year, String _even) {
        this.enrollcode = enroll_code;
        this.id = _nim;
        this.year = _year;
        this.semester = _even ;
        this.grade = "None";
        this.returngrade = "";
        this.remedial = null;
        this.totalremedial = 0;
 
    }
    
    public String getenrollcode() {
        return this.enrollcode;
    }

    public String getId() {
        return this.id;
    }

    public String getyear() {
        return this.year;
    }

    public String getEven() {
        return this.semester;
    }

    public String getgrade() {
        return this.grade;
    }

    public String getreturngrade() {
        return this.returngrade;
    }

    public String getRemedial() {
        return this.remedial;
    }

    public int getTotalRemedi() {
        return this.totalremedial;
    }

    public void setTotalRemedi() {
        this.totalremedial += 1;
    }
    
    public void setgrade(String _grade) {
        this.grade = _grade;
    }

    public void setreturngrade(String _returngrade) {
        this.returngrade = _returngrade;
    }
    public void setRemedial(String _remedial) {
        this.remedial = _remedial;
    }

    public void exchangegrade() {
        String temp = "";
        temp = this.grade;
        this.grade = this.returngrade;
        this.returngrade = temp;
        
    }
    

    @Override
    public String toString() {
        return this.enrollcode + "|" + this.id + "|" + this.year + "|" + this.semester + "|" + this.grade;
    }

    public String toremedial() {
            return this.enrollcode + "|" + this.id + "|" + this.year + "|" + this.semester + "|" + this.grade + "(" +this.returngrade+")";
        }

    public Object getEven2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEven2'");
    }
}