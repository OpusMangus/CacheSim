public class instruccion {
    private String tipo;
    private String tag;
    private String idSet;

    public String getTipo() {
        return tipo;
    }

    public String getTag() {
        return tag;
    }

    public String getIdSet() {
        return idSet;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setIdSet(String idSet) {
        this.idSet = idSet;
    }

    @Override
    public String toString(){
        return tipo + " " + tag + " " + idSet;
    }
    
}
