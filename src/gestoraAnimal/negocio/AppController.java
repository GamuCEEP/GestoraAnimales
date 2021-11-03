package gestoraAnimal.negocio;

import gestoraAnimal.accesoADatos.*;
import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.DataAccessException;
import gestoraAnimal.exceptions.DataReadingException;
import gestoraAnimal.exceptions.DataWrittingException;
import gestoraAnimal.exceptions.NoSelectedObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            return new ArrayList<>();
        }
    }

    public void selectObj(Data obj) {
        selectedObj = (Animal)obj;
    }

    public void edit (Animal.fields campo, String edition, String filename){
        
        Data original = new Animal(selectedObj.toString().split(";"));
        switch(campo) {
            case nombre:
                selectedObj.setName(edition);
                break;
            case tipo:
                selectedObj.setKind(AnimalKind.valueOf(edition));
                break;
            case edad:
                selectedObj.setAge(Integer.parseInt(edition));
                break;
            case precio:
                selectedObj.setPrecio(Float.parseFloat(edition));
                break;
        }
        try {
            DB.modify(filename, original, selectedObj);
        } catch (DataWrittingException ex) {
        } catch (DataAccessException ex) {
        }
    }

    public String getObjInfo(){
        return selectedObj.toPrint();
    }
    public String getSelectedObjName(){
        if (selectedObjExists()) return selectedObj.getName();
        return "Ninguno";
    }
    public boolean selectedObjExists(){
        return selectedObj != null;
    }
}
