package tets;

import gestoraAnimal.negocio.AppController;
import gestoraAnimal.view.ConsoleView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class Test {

    public static void main(String[] args) {
        AppController AC = new AppController();
        ConsoleView CV = new ConsoleView(AC);
        
        CV.menu();
        
    }
}