package academic.model;

public class Lecturer extends Person {
    private String email;
    private String initial;

    public Lecturer() {
        super();
        email = "";
        initial = "";
    }

    public Lecturer(String id, String name, String initial, String email, String program) {
        super(id, name, program);
        this.email = email;
        this.initial = initial;
    }

    // Accessors and mutators for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Accessors and mutators for initial
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s", id, name, initial, email, program);
    }
}
