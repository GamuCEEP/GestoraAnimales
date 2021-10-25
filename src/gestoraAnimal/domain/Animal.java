
package gestoraAnimal.domain;

public class Animal implements Data{
    private String name;
    private AnimalKind kind;
    private int age;
    private float precio;
    
    public enum fields implements Field{
    	type, 
    	name,
    	kind,
    	age,
    	precio;
    	
    	public int getIndex() {
    		return this.ordinal();
    	}
    }
    
    public Animal(String name, AnimalKind kind, int age, float precio){
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.precio = precio;
    }
    public Animal (String ... data){
        this.name = data[fields.name.ordinal()];
        this.kind = AnimalKind.valueOf(data[fields.kind.ordinal()]);
        this.age = Integer.parseInt(data[fields.age.ordinal()]);
        this.precio = Float.parseFloat(data[fields.precio.ordinal()]);
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
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
    public String toString() {
        return this.name + ";" + this.kind+";"+this.age;
    }
    
    
    
    
}
