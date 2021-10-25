package gestoraAnimal.exceptions;

public class DataReadingException extends DataAccessException{

	public DataReadingException() {
		super("Hubo un error al leer los datos");
	}

	public DataReadingException(String cause) {
		super(cause);
	}

}
