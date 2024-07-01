public abstract class AbstractEnterpriseUnit implements EnterpriseNode {

    private String name;

    public AbstractEnterpriseUnit(String name) {
        if(name == null) throw new NullPointerException();
        if(name.isEmpty()) throw new IllegalArgumentException();
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}