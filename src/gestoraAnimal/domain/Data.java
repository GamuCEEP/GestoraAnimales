
package gestoraAnimal.domain;

import gestoraAnimal.exceptions.DataTypeNotFound;

public interface Data {
	
	public static Data createData(String data) throws DataTypeNotFound{
		String[] temp = data.split(";",1);
		Data.Type type = Data.Type.valueOf(temp[0]);
		String objData = temp[1];
		switch(type) {
		case Animal:
			return new Animal(objData);
		default:
			throw new DataTypeNotFound(type+" no es un tipo de objeto reconocido");
		}
	}
    
	
	public enum Type{
		Animal
	}
}
