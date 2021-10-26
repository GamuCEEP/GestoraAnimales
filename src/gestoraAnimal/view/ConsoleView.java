package gestoraAnimal.view;

import gestoraAnimal.negocio.AppController;
import java.util.Scanner;

public class ConsoleView {

    private Scanner input = new Scanner(System.in);
    private AppController AC;
    private String[] options = {
        "Añadir datos",
        "Quitar datos",
        "Buscar dato",
        "Listar Datos",
        "Seleccionar dato",
        "Editar dato",
        "Ver dato"
    };
    
    public ConsoleView(AppController AC){
        this.AC = AC;
    }

    public void showMenu() {
        int i = 1;
        for (String option : options) {
            System.out.println(i + " - " + option);
        }
        System.out.println("0 - Salir");
    }

    public int getOption() {
        String option = input.nextLine();
        int opt;
        while (true) {
            try {
                opt = Integer.parseInt(option);
                if (opt >= 0 && opt <= options.length) return opt;                
                
                System.out.println("Por favor introduce un numero entre 0 y "+options.length);
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un numero valido");
            }
        }
    } /*
    añadir datos 
    quitar datos 
    buscar datos 
    listar datos 
    seleccionar 
    dato editar
    dato seleccionado
     */
    public void menu(){
        showMenu();
        switch(getOption()){
            case 0:
                return;
            case 1: 
                addData();
                break;
            case 2:
                removeData();
                break;
            case 3:
                searchData();
                break;
            case 4:
                listData();
                break;
            case 5:
                selectData();
                break;
            case 6:
                editData();
                break;
            case 7:
                showData();
                break; 
        }
    }

    private void addData() {
        
        System.out.println("");
        
        AC.addObj(obj, fileName);
    }

    private void removeData() {
    }

    private void searchData() {
    }

    private void listData() {
    }

    private void selectData() {
    }

    private void editData() {
    }

    private void showData() {
    }
}
