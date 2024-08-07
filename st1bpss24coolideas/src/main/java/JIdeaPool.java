import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JIdeaPool {

    private Map<JTopic, Set<JIdea>> topicJIdeaMap = new HashMap<>();

    public JIdeaPool() {

    }

    public void add(JTopic topic) {
        if (topic == null) throw new NullPointerException();
        topicJIdeaMap.put(topic, new HashSet<>());
    }

    public void add(JIdea idea, JTopic topic) {
        if(topic == null || idea == null) throw new NullPointerException();
        topicJIdeaMap.get(topic).add(idea);
    }

    public boolean remove(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        return topicJIdeaMap.remove(topic) != null;
    }

    public boolean remove(JIdea idea) {
        if(idea == null) throw new NullPointerException();
        return topicJIdeaMap.remove(idea) != null;
    }

    public int numberOfTopics() {
        return topicJIdeaMap.size();
    }

    public int numberOfIdeas() {
        return topicJIdeaMap.size();
    }

    public void removeDeclined() {

    }

    public void removeReleased() {

    }

}