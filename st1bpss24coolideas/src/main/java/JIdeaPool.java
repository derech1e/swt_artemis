import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JIdeaPool {

    private Map<JTopic, Set<JIdea>> pool = new HashMap<>();

    public JIdeaPool() {

    }

    public JIdea getIdea(String title) {
        for (Map.Entry<JTopic, Set<JIdea>> entry : pool.entrySet())
            for (JIdea item : entry.getValue())
                if (item.getTitle().equals(title))
                    return item;
        return null;
    }

    public void add(JTopic topic) {
        if (topic == null) throw new NullPointerException();
        pool.put(topic, new HashSet<>());
    }

    public void add(JIdea idea, JTopic topic) {
        if (topic == null || idea == null) throw new NullPointerException();
        pool.getOrDefault(topic, new HashSet<>()).add(idea);
    }

    public boolean remove(JTopic topic) {
        if (topic == null) throw new NullPointerException();
        return pool.remove(topic) != null;
    }

    public boolean remove(JIdea idea) {
        if (idea == null) throw new NullPointerException();
        boolean result = false;
        for (Map.Entry<JTopic, Set<JIdea>> item : pool.entrySet()) {
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
        return pool.size();
    }

    public int numberOfIdeas() {
        HashSet<JIdea> ideas = new HashSet<>();
        for (JTopic topic : pool.keySet()) {
            ideas.addAll(pool.get(topic));
        }
        return ideas.size();
    }

    public void removeDeclined() {
        for (Map.Entry<JTopic, Set<JIdea>> item : pool.entrySet()) {
            item.getValue().removeIf(JIdea::isDeclined);
            // Alternative implementation:
//            for (JIdea idea : item.getValue()) {
//                if (idea.isDeclined())
//                    item.getValue().remove(idea);
//            }
        }
    }

    public void removeReleased() {
        for (Map.Entry<JTopic, Set<JIdea>> item : pool.entrySet()) {
            item.getValue().removeIf(JIdea::isReleased);
        }
    }

}