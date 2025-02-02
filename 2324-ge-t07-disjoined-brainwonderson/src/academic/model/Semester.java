package academic.model;

/**
 * @author 12S22035 Brain Wonderson
 */

public enum Semester {
    ODD ("odd"), EVEN ("even"), SHORT ("short"), N_A ("N/A");

    private final String word;

    Semester(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public Semester findSemester(String SEM) {
        for (Semester elem: Semester.values()) {
            if (elem.getWord().equals(SEM))
                return elem;
        }
        return null;
    }
}