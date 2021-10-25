
package gestoraAnimal.dataAccess;

import gestoraAnimal.domain.*;
import java.util.List;

public interface IDatabase {
    public void createFile(String fileName);
    public void deleteFile(String fileName);
    public void write(String fileName, Data data);
    public String searchField(String fileName, String search, Field  field);
    public List<Data> listAll (String fileName);
    public boolean exists(String fileName);
} 
