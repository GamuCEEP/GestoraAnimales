package gestoraAnimal.exceptions;

public class DataTypeNotFound extends Exception{
	public DataTypeNotFound(String cause) {
		super(cause);
	}
	public DataTypeNotFound() {
		super("No se encontr� el tipo de objeto para crear");
	}
}
