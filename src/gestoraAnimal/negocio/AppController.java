package gestoraAnimal.negocio;

import gestoraAnimal.accesoADatos.*;
import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.DataAccessException;
import gestoraAnimal.exceptions.DataReadingException;
import gestoraAnimal.exceptions.DataWrittingException;

public class AppController {

    private Data selectedObj;

    private final IDatabase DB = new TxtDatabase();

    /*
	 * a�adir datos quitar datos buscar datos listar datos seleccionar dato editar
	 * dato seleccionado
     */
    public boolean createFile(String fileName) {
        if (DB.exists(fileName)) {
            return true;
        }

        try {
            DB.createFile(fileName);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    public boolean addObj(Data obj, String fileName) {
        try {
            DB.write(fileName, obj);
        } catch (DataWrittingException e) {
            return false;
        }
        return true;
    }

    public boolean removeObj(Data obj, String fileName) {
        int index;
        try {
            index = DB.search(fileName, obj.toString());
            DB.remove(fileName, index);
            return true;
        } catch (DataReadingException e1) {
            return false;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public String searchObj(String fileName, String busqueda, Field field) {
        try {
            return DB.searchField(fileName, busqueda, field);
        } catch (DataReadingException e) {
            return "Error al buscar";
        }
    }

    public String listObj(String fileName) {
        try {
            String result = "";
            for (Data obj : DB.listAll(fileName)) {
                result += obj.toPrint() + "\n";
            }

            return result + "\n";

        } catch (DataReadingException e) {
            return "";
        }
    }

    public void selectObj(Data obj) {
        selectedObj = obj;
    }

    public void editObj(Field field, String edition) {
        if (selectedObj instanceof Animal) {
            ((Animal)selectedObj).changeField(field, edition);
        }
    }

    public String getObjInfo(Data obj) {
        return selectedObj.toPrint();
    }
}
