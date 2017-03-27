
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Sizing {
 

    public static void writeSizing(int level) {
    	String sizingRule="";
    	String circuitName="";
    	switch(level){
    	case 0: 
    		sb.append("voltage-controlled current source\n")
            sb.append("sigid^2/iid=A/WL+sigW^2/W^2+sigW^2/L^2+4/(Vgs-Vth)+Ak/W.L\n");
            sb.append("FE1 : vds-(vgs-Vth)>=VSATmin\n");
            sb.append("FE2 : vds>=0\n");
            sb.append("FE3 : vgs-Vth >= 0\n");
            sb.append("RG1 : w·l >= AminSAT\n");
            sb.append("RG2 : w >= WminSAT\n");
            sb.append("RG3 : l>=LminSAT.\n");
    		writeFile(circuitName,String.valueOf(level),sizingRule);
    		break;
    	case 1:
    		sb.append("Voltage-Controlled Resistor\n");
            sb.append("FE1 : (vgs-Vth)-vds >= Vlinmin\n");
            sb.append("FE2 : vds>=0\n");
            sb.append("FE3 : vgs-Vth >= 0\n");break;
    	   writeFile(circuitName,String.valueOf(level),sizingRule);
        	
    	case 2:
    		 sb.append("Voltage-Controlled Resistor\n");
            sb.append("FG : l1=l2\n");
            sb.append("FE : |vds2− vds1| <= delta Vdsmax\n");
            sb.append("RE : vgs1,2-Vth 1,2 >= Vgsmin\n");
            writeFile(circuitName,String.valueOf(level),sizingRule);
    		break;
    	case 3:
            sb.append("Cascode Current Mirror\n");
            sb.append("FG1 : w1ls=w1cm\n");
            sb.append("FG2 : w2ls=w2cm\n");
                writeFile(circuitName,String.valueOf(level),sizingRule);
        
            break;
        case 4:
                sb.append("Level Shifter\n");
            sb.append("FG : l1=l2\n");
            sb.append("RE : Vgs(1,2)-Vth(1,2)>=Vgsmin\n");
            writeFile(circuitName,String.valueOf(level),sizingRule);
        
            break;
    	case 5:
              sb.append("4 Transistor Current Mirror\n");
            sb.append("FG1 : wvrl=wvrl\n");
            sb.append("FG2 : wcm=wcml\n");
            sb.append("FE : |Vds_vrl(1,2)-Vds_dscml|<=Vdsmax\n");
            writeFile(circuitName,String.valueOf(level),sizingRule);
            break;     
        case 6:
            sb.append("Differential Pair\n");
            sb.append("FG1: l1=l2\n");
            sb.append("FG2: w1=w2\n");
            sb.append("FE: |Vds2-Vdsmin|<delVdsmax\n");
            sb.append("RE: |Vgs2-Vgs1|<delVgsmax\n");
            writeFile(circuitName,String.valueOf(level),sizingRule);
             break;

        }
    	
    }
    	public static void writeFile(StringBuilder sb ){
    			 try{
    			  File file = new File ("Sizing.txt");
    			     PrintWriter writer = new PrintWriter(file);
    	           writer.write(sb.toString());
    	           writer.close();
    		 } catch (IOException e) {
    	              e.printStackTrace();
    		 }
    	    	   
    	}
  }    	
    	