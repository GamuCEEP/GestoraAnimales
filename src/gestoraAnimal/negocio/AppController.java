package gestoraAnimal.negocio;

import gestoraAnimal.accesoADatos.*;
import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.DataAccessException;
import gestoraAnimal.exceptions.DataReadingException;
import gestoraAnimal.exceptions.DataWrittingException;
import java.util.List;

public class AppController {

    private Animal selectedObj;

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

    public boolean removeObj(Animal obj, String fileName) {
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

    public String searchObj(String fileName, String busqueda) {
        try {
            return ""+DB.search(fileName, busqueda);
        } catch (DataReadingException e) {
            return "Error al buscar";
        }
    }

    public List<Data> listObj(String fileName) {
        try {
            return DB.listAll(fileName);
        } catch (DataReadingException e) {
            return null;
        }
    }

    public void selectObj(Data obj) {
        selectedObj = (Animal)obj;
    }

    public void edit (Animal.fields campo, String edition) {
        switch(campo) {
            case name:
                selectedObj.setName(edition);
                break;
            case kind:
                selectedObj.setKind(AnimalKind.valueOf(edition));
                break;
            case age:
                selectedObj.setAge(Integer.parseInt(edition));
                break;
            case price:
                selectedObj.setPrecio(Float.parseFloat(edition));
                break;
            default:
                
        }
    }

    public String getObjInfo() {
        return selectedObj.toPrint();
    }
}
