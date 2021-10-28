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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {

    public static void main(String[] args) {
        AppController AC = new AppController();
        ConsoleView CV = new ConsoleView(AC);
        
        //CV.menu();
       
        Patata a = new Patata();
        
        String in = "a";
        Field[] fields = a.getClass().getDeclaredFields();
        
        for(Field field : fields){
            if(field.getType().isEnum()){
                for(Object ob : field.getType().getEnumConstants()){
                    field.getType().cast(ob);
                    System.out.println(field.getType());
                }
                
            }
        }
        
    }
}