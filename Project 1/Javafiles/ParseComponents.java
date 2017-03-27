import java.util.regex.*;
import java.util.*;
import java.io.*;

class ComponentParameters {
    private String name,source,drain,gate,transType;
    
   public ComponentParameters(String name,String source,String drain,String gate,String type) {//Default Construcor to set the Parameter Values
        this.name = name;
        this.source = source;
        this.drain = drain;
        this.gate = gate;
        this.transType = type;
       }
}
/*
*This Class is used to parse from the required netlist description of the circuit and generate the compinent Description
*/
public class ParseComponents{
    public static List<Component> parseNetlist(String fileName) throws IOException {
        ArrayList<Component> components = new ArrayList<>();
        parseFile(components);
        return components;
    }
    public void parseFile(List<Component> components){
        FileInputStream fileName = null;
        BufferedReader bufferReader = null;
        try {
            fileName = new FileInputStream(fileName);
            bufferReader = new BufferedReader(new InputStreamReader(fileName));
            String line = bufferReader.readLine();
            while(line!=null) {
                if(line.length()==0) {
                    line = bufferReader.readLine();
                    continue;
                }
                Pattern p = Pattern.compile("[a-zA-Z]");
                Matcher m = p.matcher(String.valueOf(line.charAt(0)));
                if(!m.matches()) {
                    line = bufferReader.readLine();
                    continue;
                }
                String[] parts = line.split(" ");
                if(parts.length<5) {
                    line = bufferReader.readLine();
                    continue;
                }
                components.add(new Component(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5]));
                line = bufferReader.readLine();
            }
        }
        finally {
            if(fileName!=null) {
                fileName.close();
            }
            if(br!=null) {
                br.close();
            }
        }
    }
}