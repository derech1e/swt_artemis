public class ResourceType {

    private String description;
    private KeywordCollector collector;

    public ResourceType(String desc, KeywordCollector collector) {
        if(desc == null || collector == null) throw new NullPointerException();
        if(desc.isEmpty()) throw new IllegalArgumentException();
        this.description = desc;
        this.collector = collector;
    }

    public String getDescription() {
        return this.description;
    }

    public KeywordCollector getCollector() {
        return this.collector;
    }

}