import java.util.Set;
import java.util.HashSet;

public class DefaultCollector implements KeywordCollector {

    @Override
    public Set<String> getKeywords(Resource res) {
        Set<String> keywords = new HashSet<>();
        keywords.add(res.getName());
        return keywords;
    }
}