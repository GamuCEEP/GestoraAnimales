package tets;

public class Patata {

    public String nombre;
    public int cantidad;
    public float precio;
    public Awa tipo;

    public Patata(){
        
    }
    public Patata(String nombre) {
        this.nombre = nombre;
    }

    public enum Awa {
        a, b, c, d, e;
    }
}
