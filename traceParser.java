public class traceParser {
    private static instruccion parseLine(String s, int tagSize, int idSetSize){
        String[] splitted = s.split(" ");
        instruccion inst = new instruccion();
        
        //Tipo
        inst.setTipo(splitted[0]);
        
        String direccion = hexToBin32.convert(splitted[1]);
        //Tag
        inst.setTag(direccion.substring(0, tagSize - 1));

        //idSet
        inst.setIdSet(direccion.substring(tagSize, tagSize + idSetSize));

        return inst;
    }
}
