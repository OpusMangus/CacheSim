import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class simuladorcache {
    public static void main(String[] args) {
        System.out.println("Hello World.");
        // System.out.println(logTwo.log2(1));
        // System.out.println(String.format("%32s", hexToBin.convert("8")).replace(' ',
        // '0'));

        File file = new File("spice10.trace");

        ArrayList<instruccion> instArr = null;
        try {
            instArr = traceParser.parseFile(file, 1, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (instruccion inst : instArr) {
            System.out.println(inst);
        }
    }
}