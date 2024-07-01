import java.util.HashSet;
import java.util.Set;

public abstract class AbstractUnit extends AbstractEnterpriseUnit {

    private Set<AbstractEnterpriseUnit> childNodes;

    public AbstractUnit(String name) {
        super(name);
        childNodes = new HashSet<>();
    }

    public boolean add(AbstractEnterpriseUnit childNode) {
        if(childNode == null) throw new NullPointerException();
        if (this instanceof Holding) {
            if (!(childNode instanceof Company)) throw new IllegalArgumentException();
            return this.childNodes.add(childNode);
        } else if (this instanceof Company) {
            if (!(childNode instanceof Division)) throw new IllegalArgumentException();
            return this.childNodes.add(childNode);
        } else if (this instanceof Division) {
            if (!(childNode instanceof Team)) throw new IllegalArgumentException();
            return this.childNodes.add(childNode);
        }
        return this.childNodes.add(childNode);
    }

    public boolean remove(AbstractEnterpriseUnit childNode) {
        if(childNode == null) throw new NullPointerException();

        if(!this.childNodes.contains(childNode)) return false;

        if (this instanceof Holding) {
            if (!(childNode instanceof Company)) throw new IllegalArgumentException();
            this.childNodes.remove(childNode);
            return true;
        }
        if (this instanceof Company) {
            if (!(childNode instanceof Division)) throw new IllegalArgumentException();
            this.childNodes.remove(childNode);
            return true;
        }
        if (this instanceof Division) {
            if (!(childNode instanceof Team)) throw new IllegalArgumentException();
            this.childNodes.remove(childNode);
            return true;
        }
        return this.childNodes.remove(childNode);
    }
    public Set<AbstractEnterpriseUnit> getChildNodes() {
        return this.childNodes;
    }
}