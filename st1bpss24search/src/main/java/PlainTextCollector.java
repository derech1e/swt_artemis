import java.util.HashSet;
import java.util.Set;

public class PlainTextCollector implements KeywordCollector {


    @Override
    public Set<String> getKeywords(Resource res) {
        TextFileIterator textFileIterator = new TextFileIterator(res);
        Set<String> keyWords = new HashSet<>();
        while (textFileIterator.hasNext()) {
            keyWords.add(textFileIterator.next());
        }
        return keyWords;
    }
}