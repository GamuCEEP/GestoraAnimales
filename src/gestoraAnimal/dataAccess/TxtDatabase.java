package gestoraAnimal.dataAccess;

import gestoraAnimal.domain.Animal;
import gestoraAnimal.domain.Data;
import gestoraAnimal.domain.Field;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TxtDatabase implements IDatabase {

    @Override
    public void createFile(String fileName) {
        File archivo = new File(fileName);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
            //throw new AccesoADatosEx("Error al crear el archivo, \"" + nombreArchivo + "\" no se pudo crear");
        }
    }

    @Override
    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    @Override
    public void write(String fileName, Data data) {
        File file = new File(fileName);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(file, true));
            salida.println(data);
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
            //throw new EscrituraDatosEx();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            //throw new EscrituraDatosEx();
        }
    }

    @Override
    public String searchField(String fileName, String search, Field field) {
        File file = new File(fileName);
        int line = -1;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            String reading;
            while ((reading = entrada.readLine()) != null) {
                if(reading.split(";")[field.pos].equalsIgnoreCase(search))
                    return "Found on line "+line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "Not found";
    }

    @Override
    public List<Data> listAll(String fileName) {
        File file = new File(fileName);
        List<Data> list = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            String reading;
            while ((reading = entrada.readLine()) != null) {
                list.add(new Animal(reading.split(";")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

}
