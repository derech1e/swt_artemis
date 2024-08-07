import java.util.HashSet;
import java.util.Set;

public class JMember implements ContentObserver {

    private Set<JTopic> topics = new HashSet<>();

    public void subscribe(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        topics.add(topic);
        topic.addObserver(this);
    }

    public void unsubscribe(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        topics.remove(topic);
        topic.removeObserver(this);
    }

    @Override
    public void update(JContent content) {
        for (JTopic topic : topics)
            if (topic == content)
                System.out.printf("The topic %s has been updated!%n", topic.getId());
    }

    public Set<JTopic> getSubscribedTopics() {
        return topics;
    }
}