package academic.model;

/**
 * @author 12S22035 Brain Wonderson
 */


public abstract class Entity {
    protected String code;
    protected String name;

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("%s|%s", code, name);
    }
}