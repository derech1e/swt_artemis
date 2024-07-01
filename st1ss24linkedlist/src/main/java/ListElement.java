public class ListElement {
    private String content;
    private ListElement next = null;

    public ListElement(String content) {
        if (this.isEmpty(content))
            throw new IllegalArgumentException("content must not be null or empty");
        this.content = content;
    }

    public void setContent(String content) {
        if (this.isEmpty(content)) throw new IllegalArgumentException("content must not be null or empty");
        this.content = content;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    public String getContent() {
        return content;
    }

    public ListElement getNext() {
        return next;
    }

    private boolean isEmpty(String content) {
        return content == null || content.isEmpty();
    }
}
