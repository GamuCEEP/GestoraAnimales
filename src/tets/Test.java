package tets;

import gestoraAnimal.domain.Animal;
import gestoraAnimal.domain.AnimalKind;
import gestoraAnimal.domain.Data;
import gestoraAnimal.exceptions.DataTypeNotFound;
import gestoraAnimal.negocio.AppController;
import gestoraAnimal.view.ConsoleView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Wrapper;


public class Test {

    public static void main(String[] args) {
        AppController AC = new AppController();
        ConsoleView CV = new ConsoleView(AC);
        
        String params = CV.getParams(Animal.class);
        
        
        System.out.println(params);
        Data a = null;
        try {
			a = Data.createData("Animal;"+params);
		} catch (DataTypeNotFound e) {
			e.printStackTrace();
		}
        a.toPrint();
        
        //System.out.println(intclass.getSuperclass().getName().equals(Number.class.getName()));
        //System.out.println(intclass.getSuperclass().getName()+" / "+Number.class.getName());
        
    }
}