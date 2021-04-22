public class LinkedEntity {
    private static int idCounter = 0;
    final int id = ++idCounter;
    LinkedEntity child;

    LinkedEntity() {
        child = null;
    }

    public void add(LinkedEntity entity) {
        if (child == null)
            child = entity;
        else
            child.add(entity);
    }

    public boolean delete(int id) {
        if (this.id == id)
            return false;
        else if (this.child == null)
            return false;
        else {
            if (child.id == id) {
                child = child.child;
                return true;
            } else
                return child.delete(id);
        }
    }

    public boolean contains(int id) {
        if (this.id == id)
            return true;
        else if (this.child == null)
            return false;
        else
            return this.child.contains(id);
    }

    public int size() {
        /*if (this.child == null)
            return 1;
        else
            return 1 + this.child.size();*/
        int c = 1;
        for (LinkedEntity n = this; n.child != null; n = n.child) c++;
        return c;
    }
}
