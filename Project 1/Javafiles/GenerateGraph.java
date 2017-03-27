import java.util.regex.*;
import java.util.*;
import java.io.*;

public class HyperGraph {
    List<Component> components;
    List<String> netList;
    Map<String,List<String>> netListMap;
    Map<String,List<String>> componentMap;

   public  HyperGraph(String fileName) {
        components = ParseComponents.parseNetlist(fileName);
        netList = createNetList(components);
        Collections.sort(components);
        netListMap = generateNetListMap(components,netList);
        componentMap = generateComponentMap(components,netList);
    }

    public List<String> createNetList(List<Component> components) {
        Set<String> nets = new HashSet<>();
        List<String> netList = new ArrayList<>();
        for(Component comp : components) {
            if(isNet(comp.source) && !nets.contains(comp.source)) {
                nets.add(comp.source);
            }
            if(isNet(comp.drain) && !nets.contains(comp.drain)) {
                nets.add(comp.drain);
            }
            if(isNet(comp.gate) && !nets.contains(comp.gate)) {
                nets.add(comp.source);
            }
        }
        netList.addAll(nets);
        return netList;
    }
    public Map<String,List<String>> genNetListMap(List<Component> components,List<String> netList) {
    
            Map<String,List<String>> map = new HashMap<>();
            for(String net : netList) {
            if(!map.containsKey(net)) {
                map.put(net,new ArrayList<String>());
            }
            for(Component component : components) {
                if(component.source.equals(net)) {
                    map.get(net).add(component.name+".source");//Append component.source
                }
                if(component.drain.equals(net)) {
                    map.get(net).add(component.name+".drain");
                }
                if(component.gate.equals(net)) {
                    map.get(net).add(component.name+".gate");
                }
                if(component.body.equals(net)) {
                    map.get(net).add(component.name+".body");
                }
            }
        }
         return map;
     }
    public Map<String,List<String>> genCompMap(List<Component> components,List<String> netList) {
        Map<String,List<String>> map = new HashMap<>();
       for(Component comp : components) {
            if(!map.containsKey(comp.name)) {
                map.put(comp.name,new ArrayList<String>());
            }
            Set<String> set = new HashSet<>();
            set.add(comp.source);
            set.add(comp.drain);
            set.add(comp.gate);
            set.add(comp.body);
            map.get(comp.name).addAll(set);
        }
        return map;
    }
    public static boolean isNet(String str) {
        Pattern p = Pattern.compile("[n]");
        Matcher m = p.matcher(String.valueOf(str.charAt(0)));
        return m.matches();
    }
    public static void main(String[] args) throws IOException {

        //**********Parsing the library circuit file to Preprocess for generation of the library using circuit netlists
             String fileName = "Circuitlist.txt";
            List<Integer> PriorityFl=new ArrayList<>();
            bufferReader = new BufferedReader(new InputStreamReader(fileName));
            
            String line = bufferReader.readLine();
            while(line!=null) {
                if(line.length()==0) {
                    line = bufferReader.readLine();
                    continue;
                }
               List<String> CircuitFile=new ArrayList<String>();
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
                while(line.hasNext()!=null){
                CircuitFile.add(part[0]);
                 PriorityFl.add(part[1]);
                }
        //Creation of the library
        CreateLibrary lib =new CreateLibrary();
        for(String filename:CircuitFile){
            lib.createLibrary(filename);
            lib.setPriority(filename);
        }
        //      Parse the desired Circuit file for comparison
        List<Component> components = ParseComponents.parseNetlist("C:\Users\anirudh\Downloads\Cad\Libraries\Level1\CascodePair.ckt");
        List<String> netList = createNetList(components);
        Map<String,List<String>> netListMap = generateNetListMap(components,netList);
        Map<String,List<String>> componentMap = generateComponentMap(components,netList);
         //************Print the netList***********************************
        System.out.println(netList);
        System.out.println(netListMap);
        System.out.println(componentMap);
        //****************************************************************
    
    }
}