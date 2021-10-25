package tets;

public class Test {

	public static void main(String[] args) {
		Type tipo = Type.valueOf("Material");
		switch(tipo) {
		case Animal:
			System.out.println("ANIMAAAL");
			break;
		case Material:
			System.out.println("Material");
		}
	}
	public enum Type{
		Animal, Material;
	}

}
