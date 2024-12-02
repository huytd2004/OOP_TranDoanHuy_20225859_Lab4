package hust.soict.hedspi.aims.media;
import java.util.ArrayList;
import java.util.List;
public class Book extends Media{
    private static int nbBook = 0;
    private List<String> authors = new ArrayList<String>();
    public Book(int id, String title, float cost) {
        super(id, title, cost);
        this.setId(++nbBook);
    }
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
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
}
