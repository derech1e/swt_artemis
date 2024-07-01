public class Library {

    private Book[] books = new Book[10];
    private int bookCounter = 0;

    public Library() {
        System.out.println("Hello, I am a library, which can store up to 10 books!");
    }

    public void add(Book book) {
        if(this.bookCounter >= 10) {
            System.out.println("The library is full!");
            return;
        }

        System.out.println(String.format("I added the book %s!", book));
        this.books[this.bookCounter] = book;
        this.bookCounter++;
    }

    public Book search(String title) {
        for(Book item : this.books) {
            if(item != null && title == item.toString()) {
                System.out.println(String.format("The book with the title %s exists in the library!", title));
                return item;
            }
        }
        System.out.println(String.format("The book with the title %s does not exist in the library!", title));
        return null;
    }
}
