package academic.model;

/**
 * @author 12S22016 Desri Dabukke
 */
 
public class Lecturer extends Humans{ 
    // private String id;
    // private String name;
    private String inisial;
    private String email;
    private String studyProgram;
    
    public Lecturer(String _id, String _name, String inisial, String _email, String _studyProgram) {
        super(_id, _name);
        this.inisial = inisial;
        this.email = _email;
        this.studyProgram = _studyProgram;
    }
 
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    
    public String getInisial() {
        return this.inisial;
    }

    public void setInisial(String _inisial) {
        this.inisial = _inisial;
    }


    public String getEmail() {
        return this.email;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

    public void setStudyProgram(String _studyProgram) {
        this.studyProgram = _studyProgram;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.name + "|" + this.inisial + "|" + this.email + "|" + this.studyProgram;
    }
}