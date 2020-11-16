import java.io.File;
import java.util.ArrayList;

public class logicSim {
    private int instRefCount;
    private int dataRefCount;
    private int instMissCount;
    private int dataMissCount;
    private int fromRAMWordCount;
    private int toRAMWordCount;

    private int bs;
    private boolean wt;
    private boolean wna;
    private boolean split;

    private int nSets;
    private int setSize;
    private int tagSize;
    private int idSetSize;

    private cache cache1;
    private cache cache2;

    public logicSim(int nSets, int setSize, int tagSize, int idSetSize, int bs, boolean wt, boolean wna, boolean split) {
        this.instRefCount = 0;
        this.dataRefCount = 0;
        this.instMissCount = 0;
        this.dataMissCount = 0;
        this.fromRAMWordCount = 0;
        this.toRAMWordCount = 0;

        if(split == false) {
            
            cache1 = new cache(nSets, setSize, idSetSize);
            cache2 = null;
        } else {
            if(nSets > 1){
                cache1 = new cache(nSets / 2, setSize, idSetSize - 1);
                cache2 = new cache(nSets / 2, setSize, idSetSize - 1);
            }
            else{
                cache1 = new cache(nSets, setSize, idSetSize);
                cache2 = new cache(nSets, setSize, idSetSize);
            }
        }

        this.bs = bs;
        this.wt = wt;
        this.wna = wna;
        this.split = split;
        this.nSets = nSets;
        this.setSize = setSize;
        this.tagSize = tagSize;
        this.idSetSize = idSetSize;
    }

    public void runSim(File traceFile) throws Exception {
        this.instRefCount = 0;
        this.dataRefCount = 0;
        this.instMissCount = 0;
        this.dataMissCount = 0;
        this.fromRAMWordCount = 0;
        this.toRAMWordCount = 0;

        ArrayList<instruccion> instArr = null;
        if(split){
            if(nSets > 1){
                instArr = traceParser.parseFile(traceFile, this.tagSize + 1, this.idSetSize - 1);
            }
            else{
                instArr = traceParser.parseFile(traceFile, this.tagSize + 1, this.idSetSize);
            }
            splittedSim(instArr);
        }
        else{
            instArr = traceParser.parseFile(traceFile, this.tagSize, this.idSetSize);
            splittedSim(instArr);
        }
            
    }

    private void nonsplittedSim(ArrayList<instruccion> instrucciones){
        set currentSet = null;
        linea currentLine = null;
        linea freeLine;
        linea maxLRULine;
        boolean hit;
        for (instruccion inst : instrucciones) {
            //System.out.println(inst.toString());
            currentSet = cache1.getSet(inst.getIdSet());

            if(inst.getTipo().equals("0") || inst.getTipo().equals("1"))
                this.dataRefCount++;
            else if(inst.getTipo().equals("2"))
                this.instRefCount++;

            currentLine = null;
            freeLine = null;
            maxLRULine = null;
            hit = false;
            for (linea l : currentSet.getLineas()) {
                if(inst.getTag().equals(l.getTag())){
                    currentLine = l;
                    currentLine.setLRUcount(0);
                    hit = true;
                }
                if(l.getTag() == null){
                    freeLine = l;
                    break;
                }
                if(maxLRULine == null || maxLRULine.getLRUcount() < l.getLRUcount())
                    maxLRULine = l;
            }
            
            if(hit){ //Hit
                currentLine.setLRUcount(0);
            }
            else{ //Miss
                
                if(inst.getTipo().equals("0") || inst.getTipo().equals("1")){
                    //System.out.println(inst.toString());
                    this.dataMissCount++;
                }
                else if(inst.getTipo().equals("2"))
                    this.instMissCount++;

                if(inst.getTipo().equals("1") && wna){
                    this.toRAMWordCount++;
                    continue;
                }
                else if(freeLine != null){
                    currentLine = freeLine;
                    currentLine.setTag(inst.getTag());
                    this.fromRAMWordCount+=this.bs;
                }
                else{
                    if(maxLRULine.isDirty())
                        this.toRAMWordCount+=this.bs;
                    
                    currentLine = maxLRULine;
                    currentLine.setTag(inst.getTag());
                    currentLine.setDirty(false);
                    currentLine.setLRUcount(0);
                    this.fromRAMWordCount+=this.bs;
                }
            }
            //+1 no referenciados del set
            for (linea l : currentSet.getLineas()) {
                if(!l.equals(currentLine) && l.getTag() != null){
                    l.setLRUcount(l.getLRUcount() + 1);
                }
            }
            //Si es Write
            if(inst.getTipo().equals("1")){
                if(this.wt)
                    this.toRAMWordCount++;
                else
                    currentLine.setDirty(true);
            }
        }
    }

    private void splittedSim(ArrayList<instruccion> instrucciones){
        set currentSet = null;
        linea currentLine = null;
        linea freeLine;
        linea maxLRULine;
        boolean hit;
        for (instruccion inst : instrucciones) {
            //System.out.println(inst.toString());
            if(!split || (inst.getTipo().equals("0") || inst.getTipo().equals("1")))
                currentSet = cache1.getSet(inst.getIdSet());
            else if(inst.getTipo().equals("2"))
                currentSet = cache2.getSet(inst.getIdSet());
            

            if(inst.getTipo().equals("0") || inst.getTipo().equals("1"))
                this.dataRefCount++;
            else if(inst.getTipo().equals("2"))
                this.instRefCount++;

            currentLine = null;
            freeLine = null;
            maxLRULine = null;
            hit = false;
            for (linea l : currentSet.getLineas()) {
                if(inst.getTag().equals(l.getTag())){
                    currentLine = l;
                    currentLine.setLRUcount(0);
                    hit = true;
                }
                if(l.getTag() == null){
                    freeLine = l;
                    break;
                }
                if(maxLRULine == null || maxLRULine.getLRUcount() < l.getLRUcount())
                    maxLRULine = l;
            }
            
            if(hit){ //Hit
                currentLine.setLRUcount(0);
            }
            else{ //Miss
                
                if(inst.getTipo().equals("0") || inst.getTipo().equals("1")){
                    //System.out.println(inst.toString());
                    this.dataMissCount++;
                }
                else if(inst.getTipo().equals("2"))
                    this.instMissCount++;

                if(inst.getTipo().equals("1") && wna){
                    this.toRAMWordCount++;
                    continue;
                }
                else if(freeLine != null){
                    currentLine = freeLine;
                    currentLine.setTag(inst.getTag());
                    this.fromRAMWordCount+=this.bs;
                }
                else{
                    if(maxLRULine.isDirty())
                        this.toRAMWordCount+=this.bs;
                    
                    currentLine = maxLRULine;
                    currentLine.setTag(inst.getTag());
                    currentLine.setDirty(false);
                    currentLine.setLRUcount(0);
                    this.fromRAMWordCount+=this.bs;
                }
            }
            //+1 no referenciados del set
            for (linea l : currentSet.getLineas()) {
                if(!l.equals(currentLine) && l.getTag() != null){
                    l.setLRUcount(l.getLRUcount() + 1);
                }
            }
            //Si es Write
            if(inst.getTipo().equals("1")){
                if(this.wt)
                    this.toRAMWordCount++;
                else
                    currentLine.setDirty(true);
            }
        }
    }

    public int getInstRefCount() {
        return instRefCount;
    }

    public int getDataRefCount() {
        return dataRefCount;
    }

    public int getInstMissCount() {
        return instMissCount;
    }

    public int getDataMissCount() {
        return dataMissCount;
    }

    public int getFromRAMWordCount() {
        return fromRAMWordCount;
    }

    public int getToRAMWordCount() {
        return toRAMWordCount;
    }

}
