package hust.soict.hedspi.aims.media;
import java.util.Comparator;
public class MediaComparatorByCostTitle implements Comparator<Media> {

        @Override
        public int compare(Media o1, Media o2) {
            if(Float.floatToIntBits(o1.getCost()) == Float.floatToIntBits(o2.getCost())) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            return Float.floatToIntBits(o2.getCost()) - Float.floatToIntBits(o1.getCost());
        }
}
