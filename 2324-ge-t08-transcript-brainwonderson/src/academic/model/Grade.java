package academic.model;

/**
 * @author 12S22035 Brain Wonderson
 */

public enum Grade {
    A ("A" ,4.0), AB("AB",3.5),
    B ("B" ,3.0), BC("BC",2.5),
    C ("C" ,2.0), D ("D" ,1.0),
    E ("E" ,0.0), N_A("None", -1);

    private final String letter;
    private final double scale;

    Grade(String letter, double scale) {
        this.letter = letter;
        this.scale = scale;
    }

    public String getLetter() {
        return letter;
    }
    public double getScale() {
        return scale;
    }
    public Grade getGrade(String grade) {
        for (Grade elem: Grade.values()) {
            if (elem.getLetter().equals(grade))
                return elem;
        }
        return null;
    }
}