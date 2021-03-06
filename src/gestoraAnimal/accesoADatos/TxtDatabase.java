package gestoraAnimal.accesoADatos;

import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.*;

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
    public void createFile(String fileName) throws DataAccessException {
        File archivo = new File(fileName);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
            throw new DataAccessException("Error al crear el archivo, \"" + fileName + "\" no se pudo crear");
        }
    }

    @Override
    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    @Override
    public void write(String fileName, Data data) throws DataWrittingException {
        File file = new File(fileName);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(file, true));
            salida.println(data);
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
            throw new DataWrittingException("No se encontr� el archivo");
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new DataWrittingException("Error al escribir en el archivo");
        }
    }

    @Override
    public int search(String fileName, String search) throws DataReadingException {
        File file = new File(fileName);
        int line = -1;

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            String reading;
            line++;
            while ((reading = entrada.readLine()) != null) {
                if (reading.equalsIgnoreCase(search)) {
                    entrada.close();
                    return line;
                }
                line++;
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DataReadingException("No se encontr� el archivo");
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataReadingException("Error al leer el archivo");
        }
        return line;
    }

    @Override
    public List<Data> listAll(String fileName) throws DataReadingException {
        File file = new File(fileName);
        List<Data> list = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            String reading;
            while ((reading = entrada.readLine()) != null) {
                list.add(new Animal(reading.split(";")));
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            throw new DataReadingException("No se encontró el archivo");
        } catch (IOException e) {
            throw new DataReadingException("Error al leer el archivo");
        }
        return list;
    }

    @Override
    public boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    @Override
    public void remove(String fileName, int index) throws DataAccessException {
        File file = new File(fileName);
        File temp = new File("temp");
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            PrintWriter salida = new PrintWriter(new FileWriter(temp, true));
            String reading;
            int counter = 0;
            while ((reading = entrada.readLine()) != null) {
                if (counter == index) {
                    counter++;
                    continue;
                }
                salida.println(reading);
                counter++;
            }
            entrada.close();
            salida.close();
            file.delete();
            temp.renameTo(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
            throw new DataAccessException("No se encontr� el archivo");
        } catch (IOException e) {
            e.printStackTrace(System.err);
            throw new DataAccessException("Error al escribir en el archivo");
        }
    }
    @Override
    public void modify(String filename, Data original, Data modified) throws DataAccessException{
        File file = new File(filename);
        File temp = new File("temp");
        
        try {
            int index = search(filename,original.toString());
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            PrintWriter salida = new PrintWriter(new FileWriter(temp, true));
            String reading;
            int counter = 0;
            while ((reading = entrada.readLine()) != null) {
                if (counter == index) {
                    salida.println(modified.toString());
                    counter++;
                    continue;
                }
                salida.println(reading);
                counter++;
            }
            entrada.close();
            salida.close();
            file.delete();
            temp.renameTo(file);
        } catch (FileNotFoundException e) {
            throw new DataAccessException("No se encontr� el archivo");
        } catch (IOException e) {
            throw new DataAccessException("Error al escribir en el archivo");
        } catch (DataReadingException ex) {
            throw ex;
        }
    }

}
