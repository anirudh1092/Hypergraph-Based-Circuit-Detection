import java.io.*;
import java.util.*;

public class MatchModule {

	HyperGraph givenCkt;
	HyperGraph libCkt;
	//Constructor to match the 
	public MatchModule(requiredFile,lib){
		this.givenCkt=requiredFile;
		this.libCkt=lib;
	}
  public boolean isMatch(HyperGraph givenCkt,HyperGraph libCkt) {
        Map<String,CompCount> givenCkt_netList = new HashMap<>();
        Map<String,CompCount> libCkt_netList = new HashMap<>();

        for(Map.Entry<String,List<String> entry : givenCkt.netListMap.entrySet()) {
            givenCkt_netList.put(entry.getKey(),new CompCount());
            for(String str : entry.getValue()) {
                if(str.contains("source")) {
                    givenCkt_netList.get(entry.getKey()).source++;
                }
                if(str.contains("drain")) {
                    givenCkt_netList.get(entry.getKey()).drain++;
                }
                if(str.contains("gate")) {
                    givenCkt_netList.get(entry.getKey()).gate++;
                }
                if(str.contains("body")) {
                    givenCkt_netList.get(entry.getKey()).body++;
                }
            }
        }

        for(Map.Entry<String,List<String> entry : libCkt.netListMap.entrySet()) {
            libCkt_netList.put(entry.getKey(),new CompCount());
            for(String str : entry.getValue()) {
                if(str.contains("source")) {
                    libCkt_netList.get(entry.getKey()).source++;
                }
                if(str.contains("drain")) {
                    libCkt_netList.get(entry.getKey()).drain++;
                }
                if(str.contains("gate")) {
                    libCkt_netList.get(entry.getKey()).gate++;
                }
                if(str.contains("body")) {
                    libCkt_netList.get(entry.getKey()).body++;
                }
            }
        }

        boolean flag = false;
		for(Map.Entry<String,List<String> entry : givenCkt.netListMap.entrySet()) {
            if(libCkt_netList.containsKey(entry.getKey())) {
                if(entry.getValue().size()==libCkt_netList.get(entry.getKey()).size()) {
                    flag = true;
                }
                else {
                    flag = false;
                    return flag;
                }
            }
        }    

        return flag;    
    }

    public Map<Priority,Boolean> matching(HyperGraph givenCkt,Map<Priority,HyperGraph> library) {
        Map<Priority,Boolean> map = new HashMap<>();
        for(Map.Entry<Priority,HyperGraph> entry : library.entrySet()) {
            map.put(entry.getKey(),isMatch(givenCkt,entry.getValue()));
        }
        return map;
    }

    public boolean isMatchLib(HyperGraph givenCkt,HyperGraph libCkt) {
        Map<String,List<Boolean>> givenCktBool = new HashMap<>();
        Map<String,List<Boolean>> libCktBool = new HashMap<>();

        for(Component comp : givenCkt.components) {
            givenCktBool.put(comp.name,new ArrayList<Boolean>());
            givenCktBool.get(comp.name).add(false);
            givenCktBool.get(comp.name).add(false);
            givenCktBool.get(comp.name).add(false);
            givenCktBool.get(comp.name).add(false);
        }

        for(Component comp : libCkt.components) {
            libCktBool.put(comp.name,new ArrayList<Boolean>());
            libCktBool.get(comp.name).add(false);
            libCktBool.get(comp.name).add(false);
            libCktBool.get(comp.name).add(false);
            libCktBool.get(comp.name).add(false);
        }

        int buff = libCkt.components.size();
        int i=0;
        while(i<givenCkt.components.size()) {
            if(i+buff>libCkt.components.size()) {
                break;
            }
            int count = buff;
            for(int j=i;j+buff<givenCkt.components.size();j++) {
                if(count==0) {
                    break;
                }
                String net = givenCkt.components.get(j).source;
                List<String> netCons = givenCkt.netListMap.get(net);
                if(netCons.get(0).contains(".source")) {
                    givenCktBool.get(givenCkt.components.get(j).name).get(0) = true;
                    libCktBool.get(libCkt.components.get(j).name).get(0) = true;
                }
                
				if(netCons.get(0).contains(".drain")) {
                    givenCktBool.get(givenCkt.components.get(j).name).get(0) = true;
                    libCktBool.get(libCkt.components.get(j).name).get(0) = true;
                }
                
				if(netCons.get(0).contains(".body")) {	
                    givenCktBool.get(givenCkt.components.get(j).name).get(0) = true;
                    libCktBool.get(libCkt.components.get(j).name).get(0) = true;
                }
                
		               count--;
            }
            i++;
        }
    }

    public Map<Priority,Boolean> matchingLib(HyperGraph givenCkt,Map<Priority,HyperGraph> library) {
        Map<Priority,Boolean> map = new HashMap<>();
        for(Map.Entry<Priority,HyperGraph> entry : library.entrySet()) {
            map.put(entry.getKey(),isMatchLib(givenCkt,entry.getValue()));
        }
        return map;
    }
}

class CompCount {
    int source;
    int drain;
    int gate;
    int body;

    CompCount() {
        source=0;
        drain=0;
        gate=0;
        body=0;
    }
}