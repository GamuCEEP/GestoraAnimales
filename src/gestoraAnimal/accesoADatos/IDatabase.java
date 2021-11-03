
package gestoraAnimal.accesoADatos;

import gestoraAnimal.domain.*;
import gestoraAnimal.exceptions.*;

import java.util.List;

public interface IDatabase {
	/**
	 * Crea un archivo con el nombre dado
	 * 
	 * @param fileName el nombre del archivo que se crear�
	 * @throws DataAccessException
	 */
	public void createFile(String fileName) throws DataAccessException;

	/**
	 * Borra el archivo dado por el nombre
	 * 
	 * @param fileName el nombre del archivo para borrar
	 */
	public void deleteFile(String fileName);

	/**
	 * Escribe el objeto Data en el archivo pasado con el formato Data.toString()
	 * 
	 * @param fileName el nombre del archivo en el que escribir
	 * @param data
	 * @throws DataWrittingException
	 */
	public void write(String fileName, Data data) throws DataWrittingException;

	/**
	 * Borra el objeto Data del archivo pasado
	 * 
	 * @param fileName
	 * @param index
	 * @throws DataAccessException
	 */
	public void remove(String fileName, int index) throws DataAccessException;
        
        public void modify(String filename, Data original, Data modified) throws DataAccessException;

	/**
	 * Busca en el archivo el texto dado
	 * 
	 * @param fileName
	 * @param search
	 * @return
	 * @throws DataReadingException
	 */
	public int search(String fileName, String search) throws DataReadingException;

	/**
	 * Recoge toda la informaci�n del archivo
	 * 
	 * @param fileName el archivo de donde coger los datos
	 * @return una lista con toda la informaci�n del archivo convertida en objetos
	 *         Data
	 * @throws DataReadingException
	 */
	public List<Data> listAll(String fileName) throws DataReadingException;

	/**
	 * Mira si un archivo existe o no
	 * 
	 * @param fileName el archivo que comprobar
	 * @return truee si el archivo existe
	 */
	public boolean exists(String fileName);
}
