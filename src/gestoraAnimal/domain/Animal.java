package gestoraAnimal.domain;


public class Animal implements Data {

    private String name;
    private AnimalKind kind;
    private Integer age;
    private Float price;

    public enum fields implements DataField {
        name,
        kind,
        age,
        price;

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
        this.name = data[fields.name.ordinal()];
        this.kind = AnimalKind.valueOf(data[fields.kind.ordinal()]);
        this.age = (int) Double.parseDouble(data[fields.age.ordinal()]);
        this.price = Float.parseFloat(data[fields.price.ordinal()]);
    }

    public Animal(Object[] data) {

        for (int i = 0; i < this.getClass().getDeclaredFields().length; i++) {
            try {
                this.getClass().getDeclaredFields()[i].set(this, data[i]);
            } catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeField(DataField field, String edition) {
        switch ((fields) field) {
            case name:
                this.name = edition;
                break;
            case kind:
                this.kind = AnimalKind.valueOf(edition);
                break;
            case age:
                this.age = Integer.parseInt(edition);
                break;
            case price:
                this.price = Float.parseFloat(edition);
                break;
            default:
        }
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

    public String toPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(this.name);
        sb.append("Tipo: ").append(this.kind);
        sb.append("Edad: ").append(this.age);
        sb.append("Precio: ").append(this.price);
        return sb.toString();
    }

}
