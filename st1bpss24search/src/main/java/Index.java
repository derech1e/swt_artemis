import java.util.*;

public class Index {

    private Map<String, List<Resource>> index = new HashMap<>();

    public Index() {}

    public void add(Resource res) {
        if(res == null) throw new NullPointerException();
        Set<String> keywords = res.getType().getCollector().getKeywords(res);
        keywords.forEach(item -> index.computeIfAbsent(item, k -> new ArrayList<>()).add(res));
    }


    public List<Resource> getResources(String keyword) {
        if(keyword == null) throw new NullPointerException();
        return this.index.getOrDefault(keyword, Collections.emptyList());
    }

}