import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class traceParser {
    public static instruccion parseLine(String s, int tagSize, int idSetSize) {
        String[] splitted = s.split(" ");
        instruccion inst = new instruccion();

        // Tipo
        inst.setTipo(splitted[0]);

        String direccion = hexToBin32.convert(splitted[1]);
        // Tag
        inst.setTag(direccion.substring(0, tagSize));

        // idSet
        inst.setIdSet(direccion.substring(tagSize, tagSize + idSetSize));

        return inst;
    }

    public static ArrayList<instruccion> parseFile(File file, int tagSize, int idSetSize) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<instruccion> instArr = new ArrayList<instruccion>();
        String st;
        while ((st = br.readLine()) != null){
            instArr.add(traceParser.parseLine(st, tagSize, idSetSize));
        }
        br.close();

        return instArr;
    }
}
