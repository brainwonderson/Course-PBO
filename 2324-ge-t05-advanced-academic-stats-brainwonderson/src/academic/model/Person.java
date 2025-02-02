package academic.model;

public class Person {
    protected String id;
    protected String name;
    protected String program;

    public Person() {
        id = "";
        name = "";
        program = "";
    }

    public Person(String id, String name, String program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }

    // Accessors and mutators for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Accessors and mutators for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Accessors and mutators for program
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", id, name, program);
    }
}
