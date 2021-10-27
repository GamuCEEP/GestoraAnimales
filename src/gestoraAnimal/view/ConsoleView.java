package gestoraAnimal.view;

import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.DataTypeNotFound;
import gestoraAnimal.negocio.AppController;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ConsoleView {

	private final Scanner input = new Scanner(System.in);
	private final AppController AC;
	private final String exit = "0";
	private final String[] options = { "Añadir datos", "Quitar datos", "Buscar dato", "Listar Datos",
			"Seleccionar dato", "Editar dato", "Ver dato" };

	public ConsoleView(AppController AC) {
		this.AC = AC;
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

	private Class<?> getDataType() {
		Class<?> dataType;
		System.out.println("¿Que tipo de dato quieres añadir?");
		while (true) {
			String inputText = input.nextLine();
			if (inputText.equalsIgnoreCase(exit)) {
				return null;
			}
			try {
				dataType = Data.Type.valueOf(inputText).clazz;
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("No se encontró el tipo introducido");
			}
		}
		return dataType;
	}
	public String getParams(Class<?> dataType) {
        Field[] fields = dataType.getDeclaredFields();
        StringBuilder text = new StringBuilder();
        
        for (int i = 0; i < fields.length; i++) {
            System.out.println("Introduce " + fields[i].getName() + ": ");
            String inp = input.nextLine();
            
            text.append(inp).append(";");
            
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
		System.out.println("¿Quiere continuar? (Si / No");
		String inp = input.nextLine();
		return inp.equalsIgnoreCase("si");
	}

	private void addData() {
		Class<?> dataType = getDataType();
		String fileName = getFileName();
		String params = getParams(dataType);

		if (confirmInputs(dataType.getName(), fileName, params.toString())) {
			try {
				AC.addObj(Data.createData(params), fileName);
			} catch (SecurityException | IllegalArgumentException | DataTypeNotFound ex) {
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
