import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JIdeaPool {

    private Map<JTopic, Set<JIdea>> topicJIdeaMap = new HashMap<>();

    public JIdeaPool() {

    }

    public JIdea getIdea(String title) {
        for (Map.Entry<JTopic, Set<JIdea>> entry : topicJIdeaMap.entrySet())
            for (JIdea item : entry.getValue())
                if (item.getTitle().equals(title))
                    return item;
        return null;
    }

    public void add(JTopic topic) {
        if (topic == null) throw new NullPointerException();
        topicJIdeaMap.put(topic, new HashSet<>());
    }

    public void add(JIdea idea, JTopic topic) {
        if (topic == null || idea == null) throw new NullPointerException();
        topicJIdeaMap.get(topic).add(idea);
    }

    public boolean remove(JTopic topic) {
        if (topic == null) throw new NullPointerException();
        return topicJIdeaMap.remove(topic) != null;
    }

    public boolean remove(JIdea idea) {
        if (idea == null) throw new NullPointerException();
        boolean result = false;
        for (Map.Entry<JTopic, Set<JIdea>> item : topicJIdeaMap.entrySet()) {
            for (JIdea j : item.getValue()) {
                if (j.equals(idea)) {
                    item.getValue().remove(idea);
                    result = true;
                }
            }
        }
        return result;
    }

    public int numberOfTopics() {
        return topicJIdeaMap.size();
    }

    public int numberOfIdeas() {
        HashSet<JIdea> ideas = new HashSet<>();
        for (JTopic topic : topicJIdeaMap.keySet()) {
            ideas.addAll(topicJIdeaMap.get(topic));
        }
        return ideas.size();
    }

    public void removeDeclined() {
        for (Map.Entry<JTopic, Set<JIdea>> item : topicJIdeaMap.entrySet()) {
            item.getValue().removeIf(JIdea::isDeclined);
            // Alternative implementation:
//            for (JIdea idea : item.getValue()) {
//                if (idea.isDeclined())
//                    item.getValue().remove(idea);
//            }
        }
    }

    public void removeReleased() {
        for (Map.Entry<JTopic, Set<JIdea>> item : topicJIdeaMap.entrySet()) {
            item.getValue().removeIf(JIdea::isReleased);
        }
    }

}