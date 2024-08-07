import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JMember implements ContentObserver {

    private Set<JTopic> topics = new HashSet<>();

    public void subscribe(JTopic topic) {
        topics.add(topic);
    }

    public void unsubscribe(JTopic topic) {
        topics.remove(topic);
    }

    @Override
    public void update(JContent content) {
        System.out.println(content.toString());
    }

    public Set<JTopic> getSubscribedTopics() {
        return topics;
    }
}