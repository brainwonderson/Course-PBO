package academic.model;

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
        // Definisikan local class di dalam metode toString()
        class LocalEntity {
            // Metode untuk menghasilkan representasi String dari Entity
            public String entityToString() {
                return String.format("%s|%s", code, name);
            }
        }

        // Membuat objek dari local class
        LocalEntity localEntity = new LocalEntity();

        // Memanggil metode entityToString() dari local class
        return localEntity.entityToString();
    }
}
