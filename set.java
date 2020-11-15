import java.util.ArrayList;

public class set {
    private String idSet;
    private ArrayList<linea> lineas;

    public set(){
        lineas = new ArrayList<linea>();
    }

    public set(String id, int setSize){
        this.idSet = id;
        lineas = new ArrayList<linea>();
        for (int i = 0; i < setSize; i++) {
            lineas.add(new linea());
        }
    }

    public ArrayList<linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<linea> lineas) {
        this.lineas = lineas;
    }
}
