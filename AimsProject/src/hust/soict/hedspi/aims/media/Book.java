package hust.soict.hedspi.aims.media;
import java.util.ArrayList;
import java.util.List;
public class Book extends Media{
    private static int nbBook = 0;
    private List<String> authors = new ArrayList<String>();
    public Book(String title, float cost) {
        super(title, cost);
        this.setId(++nbBook);
    }
    public Book(String title, String category, float cost) {
        super(title, category, cost);
        this.setId(++nbBook);
    }

    public void addAuthor(String authorName){
        for(String author: authors) {
            if(author.equalsIgnoreCase(authorName))
                System.err.println("Exist author name");
            return;
        }
        authors.add(authorName);
    }

    public void removeAuthor(String authorName) {
        for(String author: authors) {
            if(author.equalsIgnoreCase(authorName))
                authors.remove(author);
            return;
        }
        System.err.println("Don't exist author name");
    }

    @Override
    public String toString() {
        return String
                .format("Book - %s - %s - %s : %.2f $", title, category,
                        authors.isEmpty() ? "Unknown" : String.join(", ", authors), cost)
                .replaceAll(" null | 0 ", " Unknown ");
    }
}
