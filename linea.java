public class linea {
    private int LRUcount;
    private String tag;
    private boolean dirty;

    public linea() {
        this.tag = null;
        this.dirty = false;
        this.LRUcount = 0;
    }

    public int getLRUcount() {
        return LRUcount;
    }

    public void setLRUcount(int lRUcount) {
        this.LRUcount = lRUcount;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
