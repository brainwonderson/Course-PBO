package academic.model;
/**
 * @author 12S22016 Desri Dabukke
 */
public class Humans {
    protected String id;
    protected String name;

    public Humans(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
