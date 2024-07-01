public class List {
    private ListElement head = null;

    public List() {
    }

    public void append(String content) {
        if (content == null || content.isEmpty()) throw new IllegalArgumentException("content must not be null or empty");
        ListElement newItem = new ListElement(content);

        if (head == null) {
            head = newItem;
            return;
        }

        ListElement item = head;
        while (item.getNext() != null) {
            item = item.getNext();
        }

        item.setNext(newItem);
    }

    public String remove(String content) {
        if (content == null || content.isEmpty()) throw new IllegalArgumentException("content must not be null or empty");

        if(head == null)
            return null;

        if (head.getContent().equals(content)) {
            head = head.getNext();
            return content;
        }

        ListElement item = head;

        while (item.getNext() != null) {
            if(item.getNext().getContent().equals(content)) {
                item.setNext(item.getNext().getNext());
                return content;
            }
            item = item.getNext();
        }
        return null;
    }
}
