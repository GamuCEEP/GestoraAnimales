package gestoraAnimal.view;

import gestoraAnimal.domain.*;
import gestoraAnimal.negocio.AppController;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ConsoleView {

    private final Scanner input = new Scanner(System.in);
    private final AppController AC;
    private final String exit = "0";
    private final String[] options = {"Añadir datos", "Quitar datos", "Buscar dato", "Listar Datos",
        "Seleccionar dato", "Editar dato", "Ver dato"};

    private Set<Class<?>> numberType = new HashSet<>();

    public ConsoleView(AppController AC) {
        this.AC = AC;
        loadNumberTypes();
    }

    private void loadNumberTypes() {
        numberType.add(byte.class);
        numberType.add(short.class);
        numberType.add(int.class);
        numberType.add(long.class);
        numberType.add(float.class);
        numberType.add(double.class);
        numberType.add(Byte.class);
        numberType.add(Short.class);
        numberType.add(Integer.class);
        numberType.add(Long.class);
        numberType.add(Float.class);
        numberType.add(Double.class);
    }

    private boolean isNumber(Field field) {
        return numberType.contains(field.getType());
    }

    public void showMenu() {
        int i = 1;
        for (String option : options) {
            System.out.println(i++ + " - " + option);
        }
        System.out.println("0 - Salir");
    }

    public int getOption() {
        String option = input.nextLine();
        int opt;
        while (true) {
            try {
                opt = Integer.parseInt(option);
                if (opt >= 0 && opt <= options.length) {
                    return opt;
                }
                System.out.println("Por favor introduce un numero entre 0 y " + options.length);
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un numero valido");
            }
        }
    }

    public void menu() {
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

    private String getText(String msg) {
        System.out.println(msg);
        return input.nextLine();
    }

    /*
    
    private String name;
    private AnimalKind kind;
    private Integer age;
    private Float price;*/
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
    private float getPrice(){
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
        List<Data> entries = AC.listObj(file);
        if(entries.size() == 0){
            System.out.println("Esta vacio");
            return;
        }
        for(int i = 0; i < entries.size(); i++ ){
            System.out.println(i+" - "+entries.get(i));
        }
        
        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                AC.removeObj((Animal)entries.get(option),file);
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
        List<Data> entries = AC.listObj(file);
        
        for(int i = 0; i < entries.size(); i++ ){
            System.out.println(i+" - "+entries.get(i));
        }
    }

    private void selectData() {
        String file = getText("¿De donde quieres seleccionar un animal?");
        List<Data> entries = AC.listObj(file);
        
        for(int i = 0; i < entries.size(); i++ ){
            System.out.println(i+" - "+entries.get(i));
        }
        
        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                AC.selectObj(entries.get(option));
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor introduce un número entre 0 y " + (entries.size() - 1));
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }
    }

    private void editData() {
        
        for(int i = 0; i < Animal.fields.values().length; i++){
            System.out.println(i+" - "+Animal.fields.values()[i]);
            
        }
        /*
    
    name,
        kind,
        age,
        price;
    */  
        while (true) {
            try {
                int option = Integer.parseInt(input.nextLine());
                String edicion = "";
                switch(option){
                    case 0:
                        edicion = getName();
                        break;
                    case 1:
                        edicion = ""+getAnimalKind();
                        break;
                    case 2:
                        edicion = ""+getAge();
                        break;
                    case 3:
                        edicion = ""+getPrice();
                        break;
                }
                
                AC.edit(Animal.fields.values()[option],edicion);
                return;
                
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor introduce un número entre 0 y " + (Animal.fields.values().length - 1));
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un número");
            }
        }
        
        
        
    }

    private void showData() {
        System.out.println(AC.getObjInfo());
    }
}
