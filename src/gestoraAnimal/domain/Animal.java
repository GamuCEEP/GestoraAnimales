package gestoraAnimal.domain;


public class Animal implements Data {

    private String name;
    private AnimalKind kind;
    private Integer age;
    private Float price;

    public enum fields implements DataField {
        nombre,
        tipo,
        edad,
        precio;

        @Override
        public int getIndex() {
            return this.ordinal();
        }
    }

    public Animal(String name, AnimalKind kind, int age, float price) {
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.price = price;
    }

    public Animal(String[] data) {
        this.name = data[fields.nombre.ordinal()];
        this.kind = AnimalKind.valueOf(data[fields.tipo.ordinal()]);
        this.age = (int) Double.parseDouble(data[fields.edad.ordinal()]);
        this.price = Float.parseFloat(data[fields.precio.ordinal()]);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalKind getKind() {
        return kind;
    }

    public void setKind(AnimalKind kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getPrecio() {
        return price;
    }

    public void setPrecio(float precio) {
        this.price = precio;
    }

    @Override
    public String toString() {
        return this.name + ";" + this.kind + ";" + this.age + ";" + this.price;
    }

    @Override
    public String toPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(this.name);
        sb.append("\nTipo: ").append(this.kind);
        sb.append("\nEdad: ").append(this.age);
        sb.append("\nPrecio: ").append(this.price);
        return sb.toString();
    }

}
