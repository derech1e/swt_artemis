public class Resource {

    private String name;
    private String path;
    private ResourceType rt;

    public Resource(String name, String path, ResourceType rt) {
        if(name == null || path == null || rt == null) throw new NullPointerException();
        if(name.isEmpty() || path.isEmpty()) throw new IllegalArgumentException();

        this.name = name;
        this.path = path;
        this.rt = rt;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public ResourceType getType() {
        return this.rt;
    }

}