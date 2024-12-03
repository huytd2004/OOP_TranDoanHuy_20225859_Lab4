package hust.soict.hedspi.aims.media;
import java.util.Comparator;
public class MediaComparatorByTitleCost implements Comparator<Media> {

    @Override
    public int compare(Media o1, Media o2) {
        if(o1.getTitle().compareTo(o2.getTitle()) == 0) {
            return Float.floatToIntBits(o2.getCost()) - Float.floatToIntBits(o1.getCost());
        }
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
