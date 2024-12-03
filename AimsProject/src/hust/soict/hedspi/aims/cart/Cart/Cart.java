package hust.soict.hedspi.aims.cart.Cart;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import hust.soict.hedspi.aims.media.Playable;
public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;   //Số lượng tối đa đĩa DVD trong giỏ hàng

    //Mảng lưu các đĩa DVD được thêm vào giỏ hàng
//    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
//    private int qtyOrdered;     //Số lượng đĩa DVD hiện có trong giỏ hàng

//    //Hàm thêm đĩa DVD vào giỏ hàng
//    public int addDigitalVideoDisc(DigitalVideoDisc disc) {
//        if (qtyOrdered == MAX_NUMBERS_ORDERED) {
//            System.out.println("The cart is almost full. Can't add more díc");
//            return 0;
//        } else {
//            itemsOrdered[qtyOrdered] = disc;
//            qtyOrdered ++;
//            System.out.println("The DVD " + '\"' +disc.getTitle() + '\"' + " have been added!");
//            return 1;
//        }
//    }
//    //Ham them dia vào gio hang theo List voi so luong tuy y
//    public int addDigitalVideoDisc(DigitalVideoDisc... dvdArray) {
//        int addCount = 0;
//        for (DigitalVideoDisc disc : dvdArray) {
//            if (qtyOrdered == MAX_NUMBERS_ORDERED) {
//                System.out.println("The cart is almost full. Can't add more discs");
//                break;
//            } else {
//                itemsOrdered[qtyOrdered] = disc;
//                qtyOrdered++;
//                System.out.println("The DVD " + '\"' + disc.getTitle() + '\"' + " has been added!");
//                addCount++;
//            }
//        }
//        return addCount;
//    }
//    //Ham them 2 dia DVD
//    public int addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
//        if (qtyOrdered + 1 >= MAX_NUMBERS_ORDERED) {
//            System.out.println("The cart is almost full. Can't add more discs");
//            return 0;
//        } else {
//            itemsOrdered[qtyOrdered] = dvd1;
//            qtyOrdered++;
//            System.out.println("The DVD " + '\"' + dvd1.getTitle() + '\"' + " has been added!");
//            itemsOrdered[qtyOrdered] = dvd2;
//            qtyOrdered++;
//            System.out.println("The DVD " + '\"' + dvd2.getTitle() + '\"' + " has been added!");
//
//            return 2; //Tra ve so dia DVD da them duoc
//        }
//    }
//
//
//    //Hàm xoá đĩa (Khi cấu trúc dữ liệu của đĩa DVD là 1 mảng cấu trúc)
//    public int removeDigitalVideoDisc(DigitalVideoDisc disc) {
//        if(itemsOrdered[0] ==  null) {
//            System.out.println("Your cart is empty!");
//            return 0;
//        }
//        for(int i =0; i < qtyOrdered;i++) {
//            if(itemsOrdered[i].equals(disc)) {
//                for(int j = i;i<qtyOrdered-1;i++) {
//                    itemsOrdered[i] = itemsOrdered[i+1];
//                }
//                itemsOrdered[qtyOrdered-1] = null;
//                qtyOrdered--;
//                System.out.println("Remove DVD " + '\"'+ disc.getTitle() + '\"' + " successfully!");
//                return 1;
//            }
//        }
//        System.out.println("No DVD match!");
//        return 0;
//    }
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
    // Sắp xếp theo tiêu chí
    public void sortBy(Comparator<Media> comparator) {
        itemsOrdered.sort(comparator);
    }
    public int addMedia(Media media) {
        if(itemsOrdered.size() == MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full. Can't add more media");
            return 0;
        } else {
            itemsOrdered.add(media);
            System.out.println("The media " + '\"' + media.getTitle() + '\"' + " has been added!");
            return 1;
        }
    }
    public int removeMedia(Media media) {
        if(itemsOrdered.size() == 0) {
            System.out.println("Your cart is empty!");
            return 0;
        }
        if(itemsOrdered.remove(media)) {
            System.out.println("Remove media " + '\"' + media.getTitle() + '\"' + " successfully!");
            return 1;
        } else {
            System.out.println("No media match!");
            return 0;
        }
    }
    //Hàm tính tổng giá tiền đĩa
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            total += itemsOrdered.get(i).getCost();
        }
        return total;
    }
    // Đếm số lượng media thuộc loại cụ thể (VD: DVD, CD)
    public long countMediaByType(Class<?> type) {
        return itemsOrdered.stream()
                .filter(type::isInstance) // Lọc các đối tượng thuộc loại `type`
                .count(); // Đếm số lượng
    }



//    public void print() {
//        StringBuilder output = new StringBuilder("*********************CART************************** \nOrdered items: \n");
//        for (int i = 0; i < qtyOrdered;i++) {
//            output.append(i+1 + ".[" + itemsOrdered[i].getTitle() + "] - [" + itemsOrdered[i].getCategory() + "] - ["
//                    + itemsOrdered[i].getDirector() + "] - [" + itemsOrdered[i].getLength() + "]: "
//                    + itemsOrdered[i].getCost() + " $\n");
//        }
//        output.append("total: ").append(totalCost()).append(" $\n");
//        output.append("***************************************************\n");
//        System.out.println(output);
//    }
    // Phương thức làm trống giỏ hàng
    public void emptyCart() {
        itemsOrdered.clear();
    }
    // Kiểm tra xem giỏ hàng có trống không
    public boolean isEmpty() {
        return itemsOrdered.isEmpty();
    }

    public void print() {
        StringBuilder output = new StringBuilder("*********************CART************************** \nOrdered items: \n");
        for (int i = 0; i < itemsOrdered.size();i++) {
            output.append(i+1 + ".[" + itemsOrdered.get(i).getTitle() + "] - [" + itemsOrdered.get(i).getCategory() + "] - "
                    + itemsOrdered.get(i).getCost() + " $\n");
        }
        output.append("total: ").append(totalCost()).append(" $\n");
        output.append("***************************************************\n");
        System.out.println(output);
    }
//    public void searchById(int i) {
//        if(i > qtyOrdered) {
//            System.out.println("No match found !");
//        } else {
//            System.out.println("Result: " +  "[" + itemsOrdered[i-1].getTitle() +
//                    "] - [" + itemsOrdered[i-1].getCategory() + "] - ["
//                    + itemsOrdered[i-1].getDirector() + "] - ["
//                    + itemsOrdered[i-1].getLength() + "]: " +itemsOrdered[i-1].getCost() + " $\n");
//        }
//
//    }
    public void searchById(int i) {
        if(i > itemsOrdered.size()) {
            System.out.println("No match found !");
        } else {
            System.out.println("Result: " +  "[" + itemsOrdered.get(i-1).getTitle() +
                    "] - [" + itemsOrdered.get(i-1).getCategory() + "] - ["
                    + itemsOrdered.get(i-1).getCost() + " $\n");
        }

    }

//    public void searchByTitle(String title) {
//        for(int i = 0;i < qtyOrdered; i++) {
//            if(itemsOrdered[i].getTitle().equals(title)) {
//                System.out.println("Result: " +  "[" + itemsOrdered[i].getTitle() +
//                        "] - [" + itemsOrdered[i].getCategory() + "] - ["
//                        + itemsOrdered[i].getDirector() + "] - ["
//                        + itemsOrdered[i].getLength() + "]: " +itemsOrdered[i].getCost() + " $\n");
//                return;
//            }
//        }
//        System.out.println("No match found !");
//    }
// tìm kiếm media theo tiêu đề
    public Media searchByTitle(String title) {
        for(int i = 0;i < itemsOrdered.size(); i++) {
            if(itemsOrdered.get(i).getTitle().equals(title)) {
                System.out.println("Result: " +  "[" + itemsOrdered.get(i).getTitle() +
                        "] - [" + itemsOrdered.get(i).getCategory() + "] - ["
                        + itemsOrdered.get(i).getCost() + " $\n");
                return itemsOrdered.get(i);
            }
        }
        System.out.println("No match found !");
        return null;
    }
    // Lọc media theo ID
    public List<Media> filterById(int id) {
        return itemsOrdered.stream()
                .filter(media -> media.getId() == id)
                .collect(Collectors.toList());
    }

    // Lọc media theo tiêu đề
    public List<Media> filterByTitle(String title) {
        return itemsOrdered.stream()
                .filter(media -> media.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    // Hiển thị kết quả lọc
    public void displayFilteredMedia(List<Media> filteredItems) {
        if (filteredItems.isEmpty()) {
            System.out.println("No media matches the filter criteria.");
        } else {
            filteredItems.forEach(System.out::println);
        }
    }
}