/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoraAnimal.exceptions;

/**
 *
 * @author Alumno Mañana
 */
public class NoSelectedObjectException extends Exception{
    public NoSelectedObjectException(){
        super("No se ha seleccionado ningún objeto");
    }
}
