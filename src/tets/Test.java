package tets;

import gestoraAnimal.negocio.AppController;
import gestoraAnimal.view.ConsoleView;




public class Test {

    public static void main(String[] args) {
        AppController AC = new AppController();
        ConsoleView CV = new ConsoleView(AC);
        while(true){
                    CV.menu();

        }
       
       
        
    }
}