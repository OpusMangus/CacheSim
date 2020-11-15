import java.util.ArrayList;

public class set {
    private String idSet;
    private ArrayList<linea> lineas;

    public set(String id, int setSize) {
        this.setIdSet(id);
        lineas = new ArrayList<linea>();
        for (int i = 0; i < setSize; i++) {
            lineas.add(new linea());
        }
    }
    
    public String getIdSet() {
        return idSet;
    }

    public void setIdSet(String idSet) {
        this.idSet = idSet;
    }

    public ArrayList<linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<linea> lineas) {
        this.lineas = lineas;
    }
}
