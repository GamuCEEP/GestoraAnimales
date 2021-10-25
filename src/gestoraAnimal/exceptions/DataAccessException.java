package gestoraAnimal.exceptions;

public class DataAccessException extends Exception {

	public DataAccessException() {
		super("Hubo un error al acceder a los datos");
	}

	public DataAccessException(String cause) {
		super(cause);
	}

}
