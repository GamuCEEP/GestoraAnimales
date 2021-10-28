package gestoraAnimal.view;

import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.DataTypeNotFound;
import gestoraAnimal.negocio.AppController;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private Class<? extends Data> getDataType() {
        Class<? extends Data> dataType;
        System.out.println("¿Que tipo de dato quieres añadir?");
        while (true) {
            String inputText = input.nextLine();
            if (inputText.equalsIgnoreCase(exit)) {
                return null;
            }
            try {
                dataType = (Class<? extends Data>) Data.Type.valueOf(inputText).clazz;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("No se encontró el tipo introducido");
            }
        }
        return dataType;
    }

    public String getParams(Class<?> dataType) {
        Field[] fields = dataType.getDeclaredFields();
        StringBuilder text = new StringBuilder(dataType.getSimpleName() + ";");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (isNumber(field)) {
                System.out.println("Introduce " + field.getName() + " (número) : ");
                double inp = 0;
                try {
                    inp = input.nextDouble();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println("Por favor introduce un número");
                    i--;
                    continue;
                }
                text.append(inp).append(";");
            } else if (field.getType().isEnum()) {
                //TODO - en el caso de que sea un Enum, comprobar que este enum tiene el dat introducidos
            } else {
                System.out.println("Introduce " + field.getName() + ": ");
                String inp = input.nextLine();
                text.append(inp).append(";");
            }
        }
        return text.toString();
    }

    private String getFileName() {
        System.out.println("¿Donde lo quieres guardar?");
        return input.nextLine();
    }

    private boolean confirmInputs(String... inputs) {
        System.out.println("Los datos introducidos son: ");
        for (String input : inputs) {
            System.out.println(input);
        }
        System.out.println("¿Quiere continuar? (Si / No)");
        String inp = input.nextLine();
        return inp.equalsIgnoreCase("si");
    }

    private void addData() {
        Class<?> dataType = getDataType();
        String fileName = getFileName();
        String params = getParams(dataType);

        if (confirmInputs(dataType.getName(), fileName, params)) {
            try {
                AC.addObj(Data.createData(params), fileName);
            } catch (SecurityException | IllegalArgumentException | DataTypeNotFound ex) {
                ex.printStackTrace();
            }
        }
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
