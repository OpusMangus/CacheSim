import java.util.ArrayList;

public class cache {
    private ArrayList<set> sets;

    public cache(int nSets, int setSize, int idSetSize){
        this.sets = new ArrayList<set>();

        String ids;
        if(idSetSize == 0){
            this.sets.add(new set(null, setSize));
        }
        else{
            for (int i = 0; i < nSets; i++) {
                ids = Integer.toBinaryString(i);
                ids = String.format("%" + idSetSize + "s", ids).replace(' ', '0');
                this.sets.add(new set(ids, setSize));
            }
        }
    }

    public ArrayList<set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<set> sets) {
        this.sets = sets;
    }

    public set getSet(String id){
        if(!id.equals(""))
            return sets.get(Integer.parseInt(id, 2));
        else
            return sets.get(0);
    }
}
