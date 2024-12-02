package hust.soict.hedspi.aims.media;
import java.util.ArrayList;
public class CompactDisc  extends Disc{

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public String getArtist() {
        return artist;
    }
    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category,cost);
        this.artist = artist;
    }
    public void addTrack(Track track) {
        for(Track t: tracks) {
            if(t.equals(track)) {
                System.err.println("Track is exist");
                return;
            }
        }
        tracks.add(track);
    }
    public void removeTrack(Track track) {
        for(Track t: tracks) {
            if(t.equals(track)) {
                tracks.remove(t);
                return;
            }
        }
        System.err.println("Don't exist track");
    }
    public int getLength() {
        int length = 0;
        for(Track t: tracks) {
            length += t.getLength();
        }
        return length;
    }
    public void play() {
        // Print information about the CompactDisc
        System.out.println("Playing CompactDisc: " + getTitle());
        System.out.println("Artist: " + artist);
        System.out.println("Category: " + getCategory());
        System.out.println("Cost: " + getCost() + " USD");
        System.out.println("Total Length: " + getLength() + " seconds");

        // Play each track
        for (Track track : tracks) {
            track.play();
        }
    }
}
