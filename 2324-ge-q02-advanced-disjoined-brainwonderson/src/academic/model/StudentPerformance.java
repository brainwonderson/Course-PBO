package academic.model;
import java.util.HashMap;
import java.util.Map;

public class StudentPerformance {
    private String id;
    private Map<String, Double> performanceMap; // Map untuk menyimpan performa mahasiswa per semester

    public StudentPerformance(String id) {
        this.id = id;
        this.performanceMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void addPerformance(String semester, double performance) {
        performanceMap.put(semester, performance);
    }

    public double getPerformance(String semester) {
        return performanceMap.getOrDefault(semester, 0.0); // Mengembalikan nilai default 0.0 jika tidak ada nilai untuk semester tersebut
    }

    public static String grade(double gpa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'grade'");
    }

    public Map<String, StudentPerformance> getPerformanceMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerformanceMap'");
    }
}
