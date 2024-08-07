import java.util.ArrayList;
import java.util.List;

public class JIdea {

    private List<JAttachment> attachments = new ArrayList<>();
    private JState state;

    public void discuss(String text) {

    }

    public void evaluate(JValuation valuation) {

    }

    public void hold() {

    }

    public void release() {

    }

    public void decline() {

    }

    public boolean isDeclined() {
        return true;
    }

    public boolean isReleased() {
        return true;
    }

    public String getCurrentDiscussion() {
        return null;
    }

    public JValuation getValuation() {
        return null;
    }

    public void addAttachment(JAttachment attachment) {

    }

    public List<JAttachment> getAttachment(JAttachment attachment) {

    }

    public boolean removeAttachment(JAttachment attachment) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    abstract class JState {

        private String currentDiscussion;



    }

    class Draft extends JState {

    }

    class OpenDraft extends JState {

    }
}