import java.io.File;

public class simuladorcache {
    public static void main(String[] args) {
        System.out.println("Hello World.");
        // System.out.println(logTwo.log2(1));
        // System.out.println(String.format("%32s", hexToBin.convert("8")).replace(' ',
        // '0'));

        int dirSize = 32;

        int bs = 8;
        int cs = 16;
        boolean wt = false;
        boolean fa = true;
        int sa = 8;
        boolean wna = false;
        boolean split = true;
        File file = new File("cc.trace");

        int nSets = cs;
        if (fa == true)
            nSets = 1;
        else
            nSets = sa;

        int setSize = cs / nSets;

        int offSetSize = logTwo.log2(bs) + 2;
        int idSetSize = logTwo.log2(nSets);
        int tagSize = dirSize - offSetSize - idSetSize;

        logicSim simulador = new logicSim(nSets, setSize, tagSize, idSetSize, bs, wt, wna, split);

        try {
            simulador.runSim(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("1. instref  :" + simulador.getInstRefCount());
        System.out.println("2. dataref  :" + simulador.getDataRefCount());
        System.out.println("3. instmiss :" + simulador.getInstMissCount());
        System.out.println("4. datamiss :" + simulador.getDataMissCount());
        System.out.println("5. fromRam  :" + simulador.getFromRAMWordCount());
        System.out.println("6. toRam    :" + simulador.getToRAMWordCount());
        //cache cache1 = new cache(nSets, setSize, idSetSize);
        
        //for (set cset : cache1.getSets()) {
        //    System.out.println(cset.getIdSet());
        //    for (linea l : cset.getLineas()) {
        //        System.out.println(l);
        //    }
        //}
    }
}