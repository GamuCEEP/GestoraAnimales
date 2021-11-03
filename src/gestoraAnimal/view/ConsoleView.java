package gestoraAnimal.view;

import gestoraAnimal.domain.*;
import gestoraAnimal.negocio.AppController;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner input = new Scanner(System.in);
    private final AppController AC;
    private final String exit = "0";
    private String selectedFile = "";
    private final String[] options = {"Añadir datos", "Quitar datos", "Buscar dato", "Listar Datos",
        "Seleccionar dato", "Editar dato seleccionado", "Ver dato seleccionado"};


    public ConsoleView(AppController AC) {
        this.AC = AC;
    }

    public void showMenu() {
        int i = 1;
        System.out.println("\n__________________________");
        for (String option : options) {
            System.out.println(i++ + " - " + option);
        }
        System.out.println("0 - Salir");
        System.out.println("______Dato seleccionado: "+AC.getSelectedObjName());
    }

    public int getOption() {
        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                if (option >= 0 && option <= options.length) {
                    return option;
                }
                System.out.println("Por favor introduce un numero entre 0 y " + options.length);
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un numero valido");
            }
        }
    }

    public void menu() {
        while(true){
        showMenu();
        switch (getOption()) {
            case 0:
                System.out.println("Chau");
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
    }
    private List<Data> listData(String filename){
        List<Data> entries = AC.listObj(filename);
        if (entries.isEmpty()) {
            System.out.println("Esta vacio");
            return entries;
        }
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(i+1 + " : " + entries.get(i).getName());
        }
        return entries;
    }

    private String getText(String msg) {
        System.out.println(msg);
        return input.nextLine();
    }

    private AnimalKind getAnimalKind() {
        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                return AnimalKind.values()[option];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor introduce un número entre 0 y " + (AnimalKind.values().length - 1));
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }
    }

    private String getName(AnimalKind kind) {
        System.out.println("¿Cual es el nombre del " + kind + "?");
        return input.nextLine();
    }

    private String getName() {
        System.out.println("¿Cual es el nombre?");
        return input.nextLine();
    }

    private int getAge() {
        while (true) {
            try {
                System.out.println("¿Cual es la edad de tu animal?");
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }
    }

    private float getPrice() {
        while (true) {
            try {
                System.out.println("¿Cual es el precio de tu animal?");
                return Float.parseFloat(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }
    }

    private void addData() {

        AnimalKind kind;
        String name;
        int age;
        float price;

        System.out.println("¿Que animal quieres guardar?");
        for (int i = 0; i < AnimalKind.values().length; i++) {
            System.out.println(i + " - " + AnimalKind.values()[i]);
        }

        kind = getAnimalKind();
        name = getName(kind);
        age = getAge();
        price = getPrice();

        AC.addObj(new Animal(name, kind, age, price), getText("¿Donde quieres guardarlo?"));

    }

    private void removeData() {
        String file = getText("¿De donde quieres quitar un animal?");
        List<Data> entries = listData(file);

        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                AC.removeObj((Animal) entries.get(option-1), file);
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor introduce un número entre 0 y " + (entries.size()));
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }

    }

    private void searchData() {
        String file = getText("¿Donde quieres buscar un animal?");
        String busqueda = getText("¿Que quieres buscar?");
        System.out.println(AC.searchObj(file, busqueda));
    }

    private void listData() {
        String file = getText("¿De donde quieres listar los animales?");
        listData(file);
    }
    

    private void selectData() {
        String file = getText("¿De donde quieres seleccionar un animal?");
        List<Data> entries = listData(file);
        selectedFile = file;

        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                AC.selectObj(entries.get(option-1));
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor introduce un número entre 1 y " + (entries.size()));
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }
    }

    private void editData() {
        if(!AC.selectedObjExists()) selectData();
        System.out.println("¿Que campo quieres modificar?");
        for (int i = 0; i < Animal.fields.values().length; i++) {
            System.out.println(i+1 + " - " + Animal.fields.values()[i]);
        }
        int option;
        while (true) {
            try {
                option = Integer.parseInt(input.nextLine());
                String edicion = "";
                switch (option) {
                    case 1:
                        edicion = getName();
                        break;
                    case 2:
                        edicion = "" + getAnimalKind();
                        break;
                    case 3:
                        edicion = "" + getAge();
                        break;
                    case 4:
                        edicion = "" + getPrice();
                        break;
                }
                System.out.println(Animal.fields.values()[option-1]);
                AC.edit(Animal.fields.values()[option-1], edicion, selectedFile);
                return;

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor introduce un número entre 1 y " + (Animal.fields.values().length));
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }

    }

    private void showData() {

        if(!AC.selectedObjExists()) selectData();
        System.out.println(AC.getObjInfo());

        
    }
}
