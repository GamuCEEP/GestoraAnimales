package gestoraAnimal.exceptions;

public class DataWrittingException extends DataAccessException{

	public DataWrittingException() {
		super("Hubo un error al guardar los datos");
	}

	public DataWrittingException(String cause) {
		super(cause);
	}

}
