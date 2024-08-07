import java.util.ArrayList;
import java.util.List;

public class JIdea extends JContent {

    private List<JAttachment> attachments = new ArrayList<>();
    private JState state = new Draft();

    public JIdea(String title, String description) {
        super(title, description);
    }

    public void discuss(String text) {
        if (text == null) throw new NullPointerException();
        if (text.isEmpty()) throw new IllegalArgumentException();
        this.state.discuss(text);
    }

    public void evaluate(JValuation valuation) {
        if (valuation == null) throw new NullPointerException();
        this.state.evaluate(valuation);
    }

    public void hold() {
        this.state.hold();
    }

    public void release() {
        this.state.release();
    }

    public void decline() {
        this.state.decline();
    }

    public boolean isDeclined() {
        return this.state instanceof DeclinedIdea;
    }

    public boolean isReleased() {
        return this.state instanceof ReleaseIdea;
    }

    public String getCurrentDiscussion() {
        return this.state.getCurrentDiscussion();
    }

    public JValuation getValuation() {
        return this.state.getValuation();
    }

    public void addAttachment(JAttachment attachment) {
        if (attachment == null) throw new NullPointerException();
        this.attachments.add(attachment);
    }

    public List<JAttachment> getAttachments() {
        return this.attachments;
    }

    public boolean removeAttachment(JAttachment attachment) {
        return this.attachments.remove(attachment);
    }

    @Override
    public String toString() {
        return String.format("Idea: %s\n%s", this.getTitle(), this.getDescription());
    }

    abstract class JState {

        private String currentDiscussion;
        private JValuation valuation;

        public void discuss(String text) {
            throw new IllegalStateException();
        }

        public void evaluate(JValuation valuation) {
            throw new IllegalStateException();
        }

        public void hold() {
            throw new IllegalStateException();
        }


        public void release() {
            throw new IllegalStateException();
        }


        public void decline() {
            throw new IllegalStateException();
        }


        public String getCurrentDiscussion() {
            return this.currentDiscussion;
        }

        public void setCurrentDiscussion(String currentDiscussion) {
            if (currentDiscussion == null) throw new NullPointerException();
            if (currentDiscussion.isEmpty()) throw new IllegalArgumentException();
            this.currentDiscussion = currentDiscussion;
        }

        public JValuation getValuation() {
            return this.valuation;
        }

        public void setValuation(JValuation valuation) {
            if (valuation == null) throw new NullPointerException();
            this.valuation = valuation;
        }


    }

    private class Draft extends JState {
        @Override
        public void hold() {
            state = new OpenDraft();
        }

        @Override
        public void decline() {
            state = new DeclinedIdea();
        }
    }

    private class OpenDraft extends JState {
        @Override
        public void discuss(String text) {
            this.setCurrentDiscussion(getCurrentDiscussion() + "\n" + text);
            super.discuss(text);
        }

        @Override
        public void evaluate(JValuation valuation) {
            this.setValuation(valuation);
        }

        @Override
        public void hold() {
            state = new Draft();
        }

        @Override
        public void decline() {
            state = new DeclinedIdea();
        }
    }

    private class ApprovedIdea extends JState {
        @Override
        public void release() {
            state = new ApprovedIdea();
            super.release();
        }
    }

    private class ReleaseIdea extends JState {

    }

    private class DeclinedIdea extends JState {

    }
}