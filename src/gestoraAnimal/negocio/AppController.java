package gestoraAnimal.negocio;

import gestoraAnimal.accesoADatos.*;
import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.DataAccessException;
import gestoraAnimal.exceptions.DataReadingException;
import gestoraAnimal.exceptions.DataWrittingException;

public class AppController {

	private final IDatabase DB = new TxtDatabase();

	/*
	 * añadir datos quitar datos buscar datos listar datos seleccionar dato editar
	 * dato seleccionado
	 */
	public void createFile(String fileName) {
		if (DB.exists(fileName))
			return;

		try {
			DB.createFile(fileName);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public void addObj(Data obj, String fileName) {
		try {
			DB.write(fileName, obj);
		} catch (DataWrittingException e) {
			e.printStackTrace();
		}
	}

	public void removeObj(Data obj, String fileName) {
		int index;
		try {
			index = DB.search(fileName, obj.toString());
			DB.remove(fileName, index);
		} catch (DataReadingException e1) {
			e1.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public String searchObj(String busqueda, Field field) {
		
	}

	public String listObj() {

	}

	public void selectObj(Data obj) {

	}

	public void editObj() {

	}

	public String getDataInfo(Data obj) {

	}
}
