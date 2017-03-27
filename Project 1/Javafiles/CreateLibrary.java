import java.util.regex.*;
import java.util.*;
import java.io.*;

class Priority {
    int level;
    int pValue;

    Priority(int level,int pVlaue) {
        this.level = level;
        this.pValue = pValue;
    }
}

public class CreateLibrary {

    public Map<Priority,HyperGraph> library;
     CreateLibrary() {
        library = this.createLib(this.library());
    }

    public Map<Priority,HyperGraph> createLibrary(Map<Priority,String> libFileNames) {
        
        Map<Priority,HyperGraph> map = new HashMap<>();

        for(Map.Entry<Priority,String> entry : libFileNames.entrySet()) {
            if(!map.containsKey(entry.getKey())) {
                map.put(entry.getKey(),new HyperGraph(entry.getValue()));
            }
        }   
         return map;
    }

    public void setPriority(String circuitName,int pValue) {
        for(Map.Entry<Priority,String> entry : map.entrySet()) {
            if(entry.getValue().equals(circuitName+".txt")) {
                entry.getKey().priorityValue = priorityValue;
            }
        }
    }
}