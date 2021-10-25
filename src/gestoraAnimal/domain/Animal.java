/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoraAnimal.domain;

/**
 *
 * @author Alumno Mañana
 */
public class Animal implements Data{
    private String name;
    private AnimalKind kind;
    private int age;
    
    public enum field{
        
    }
    
    public Animal(String name, AnimalKind kind, int age){
        this.name = name;
        this.kind = kind;
        this.age = age;
    }
    public Animal (String ... data){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalKind getKind() {
        return kind;
    }

    public void setKind(AnimalKind kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + ";" + this.kind+";"+this.age;
    }
    
    
    
    
}
