package academic.model;

public class student2 {
    class Student {
        String id;
        String name;
        double gpa;
        int credit;
    
        public Student(String id, String name, double gpa, int credit) {
            this.id = id;
            this.name = name;
            this.gpa = gpa;
            this.credit = credit;
        }
    
        public String toString() {
            return id + "|" + name + "|" + gpa + "|" + credit;
        }
    }
    
}
