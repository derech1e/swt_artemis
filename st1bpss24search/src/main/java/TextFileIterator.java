import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TextFileIterator implements Iterator<String> {

    private List<String> words;
    private int currentIndex;

    public TextFileIterator(Resource res) {
        if(res == null) throw new NullPointerException();
        String content = getAsString(res);
        words = new ArrayList<>();
        currentIndex = 0;

        // Split the content into words
        String[] splitWords = content.replaceAll("-\n", "").split("\\W+");
        for (String word : splitWords) {
            if (!word.isEmpty()) {
                words.add(word);
            }
        }
    }

    public boolean hasNext() {
        return currentIndex < words.size();
    }

    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return words.get(currentIndex++);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public String getAsString(Resource res) {
        return "We wish you good luck in this exam!\nWe hope you are well pre-\npared.";
    }

}