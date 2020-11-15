import java.io.File;
import java.util.ArrayList;

public class simuladorcache {
    public static void main(String[] args) {
        System.out.println("Hello World.");
        // System.out.println(logTwo.log2(1));
        // System.out.println(String.format("%32s", hexToBin.convert("8")).replace(' ',
        // '0'));

        int dirSize = 32;
        
        int bs = 8;
        int cs = 64;
        boolean wt = false;
        boolean fa = false;
        int sa = 64;
        boolean wna  = false;
        boolean split = false;
        File file = new File("spice10.trace");

        int nSets = cs;
        if (fa == true)
            nSets = 1;
        else
            nSets = sa;
        

        int offSetSize = logTwo.log2(bs) + 2;
        int idSetSize = logTwo.log2(nSets);
        int tagSize = dirSize - offSetSize - idSetSize;

        ArrayList<instruccion> instArr = null;
        try {
            instArr = traceParser.parseFile(file, tagSize, idSetSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (instruccion inst : instArr) {
            System.out.println(inst);
        }
    }
}