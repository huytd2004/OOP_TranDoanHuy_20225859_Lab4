package hust.soict.hedspi.aims.Aims;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.store.Store.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.CompactDisc;

public class Aims {
    private static Store store = new Store();  // Kho chứa media
    private static Cart cart = new Cart();    // Giỏ hàng
    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }
    public static void main(String[] args) {
        // Khởi tạo dữ liệu mẫu
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", 24.95f));
        store.addMedia(new Book("Doraemon", "Cartoon", 20.05f));
        store.addMedia(new Book("One Piece", "Cartoon", 15.05f));
        store.addMedia(new CompactDisc("Hello", "Pop", 10.05f));
        store.addMedia(new CompactDisc("Goodbye", "Pop", 10.05f));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng thừa
            switch (choice) {
                case 1:
                    handleViewStore(scanner);
                    break;
                case 2:
                    handleUpdateStore(scanner);
                    break;
                case 3:
                    handleViewCart(scanner);
                    break;
                case 0:
                    System.out.println("Exiting AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (choice != 0);

        scanner.close();
    }
    public static void handleViewStore(Scanner scanner) {
        int choice;
        do {
            System.out.println("Store Items:");
            System.out.println(store.toString());

            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleSeeMediaDetails(scanner);
                    break;
                case 2:
                    handleAddToCart(scanner);
                    break;
                case 3:
                    handlePlayMedia(scanner);
                    break;
                case 4:
                    handleViewCart(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (choice != 0);
    }
    public static void handleAddToCart(Scanner scanner) {
        System.out.println("Enter the title of the media you want to add to the cart:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);

        if (media != null) { // Kiểm tra tiêu đề hợp lệ
            cart.addMedia(media); // Thêm vào giỏ hàng
            System.out.println("Added \"" + media.getTitle() + "\" to the cart.");

            // Nếu media là DVD, đếm số lượng DVD trong giỏ
            if (media instanceof DigitalVideoDisc) {
                long dvdCount = cart.countMediaByType(DigitalVideoDisc.class);
                System.out.println("There are now " + dvdCount + " DVDs in the current cart.");
            }
        } else {
            System.out.println("Media with title \"" + title + "\" not found in the store.");
        }
    }
    public static void handlePlayMedia(Scanner scanner) {
        System.out.println("Enter the title of the media you want to play:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);

        if (media != null) {
            if (media instanceof Playable) { // Kiểm tra xem media có thể phát không bằng cách kiểm tra xem media có implement Playable không
                ((Playable) media).play();
            } else {
                System.out.println("This media cannot be played!");
            }
        } else {
            System.out.println("Media not found!");
        }
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }
    public static void handleSeeMediaDetails(Scanner scanner) {
        System.out.println("Enter the title of the media:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);

        if (media != null) {
            // Hiển thị thông tin media
            System.out.println(media);
            int choice;
            do {
                mediaDetailsMenu();
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        cart.addMedia(media);
                        System.out.println("Added to cart.");
                        break;
                    case 2:
                        if (media instanceof Playable) { // Kiểm tra xem media có thể phát không bằng cách kiểm tra xem media có implement Playable không
                            ((Playable) media).play();
                        } else {
                            System.out.println("This media cannot be played!");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again!");
                }
            } while (choice != 0);
        } else {
            System.out.println("Media not found!");
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }
    public static void handleUpdateStore(Scanner scanner){
        System.out.println("------Update Store------");
        System.out.println("1.Add media to store");
        System.out.println("2.Remove media from store");
        System.out.println("Please choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                System.out.println("Enter information of media");
                System.out.println("Enter media type: 1.Book     2.DVD     3.CD");
                int mediaType = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter title: ");
                String title = scanner.nextLine();
                System.out.println("Enter cost: ");
                float cost = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("Enter the category:");
                String category = scanner.nextLine();

                switch (mediaType){
                    case 1:
                        System.out.println("Enter number of authors: ");
                        int numAuthors = scanner.nextInt();
                        scanner.nextLine();
                        Book book = new Book(title, category, cost);
                        for(int i = 0;i < numAuthors;i++){
                            System.out.println("Enter author " + (i + 1) + ": ");
                            String author = scanner.nextLine();
                            book.addAuthor(author);
                        }
                        store.addMedia(book);
                        break;

                    case 2:
                        DigitalVideoDisc digitalVideoDisc = new DigitalVideoDisc(title, category, cost);
                        store.addMedia(digitalVideoDisc);
                        break;

                    case 3:
                        CompactDisc compactDisc = new CompactDisc(title,category,cost);
                        store.addMedia(compactDisc);
                        break;
                }
                break;

            case 2:
                System.out.println("Enter title of the media: ");
                String removeTitle = scanner.nextLine();
                Media media = store.searchByTitle(removeTitle);
                if(media != null){
                    store.removeMedia(media);
                }
                else {
                    System.out.println("Media not found");
                }
                break;
        }
    }
    public static void handleViewCart(Scanner scanner) {
        int choice;
        do {
            System.out.println("Current Cart:");
            cart.print(); // Hiển thị giỏ hàng

            cartMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleFilterMediaInCart(scanner);
                    break;
                case 2:
                    handleSortMediaInCart(scanner);
                    break;
                case 3:
                    // xóa media từ cart
                    System.out.println("Enter the title of the media you want to remove from the cart:");
                    String title = scanner.nextLine();
                    Media media = cart.searchByTitle(title);
                    if (media != null) {
                        cart.removeMedia(media);
                        System.out.println("Removed \"" + media.getTitle() + "\" from the cart.");
                    } else {
                        System.out.println("Media not found in the cart.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the title of the media you want to play:");
                    title = scanner.nextLine();
                    media = cart.searchByTitle(title);

                    if (media != null) {
                        if (media instanceof Playable) { // Kiểm tra xem media có thể phát không bằng cách kiểm tra xem media có implement Playable không
                            ((Playable) media).play();
                        } else {
                            System.out.println("This media cannot be played!");
                        }
                    } else {
                        System.out.println("Media not found!");
                    }
                    break;
                case 5:
                    handlePlaceOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }
    public static void handleFilterMediaInCart(Scanner scanner) {
        System.out.println("Choose filter option:");
        System.out.println("1. By ID");
        System.out.println("2. By Title");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        switch (choice) {
            case 1: // Lọc theo ID
                System.out.println("Enter ID to filter:");
                int id = scanner.nextInt();
                List<Media> filteredById = cart.filterById(id);
                cart.displayFilteredMedia(filteredById);
                break;

            case 2: // Lọc theo Title
                System.out.println("Enter Title to filter:");
                String title = scanner.nextLine();
                List<Media> filteredByTitle = cart.filterByTitle(title);
                cart.displayFilteredMedia(filteredByTitle);
                break;

            default:
                System.out.println("Invalid choice. Returning to cart menu.");
        }
    }
    public static void handleSortMediaInCart(Scanner scanner) {
        System.out.println("Choose sort option:");
        System.out.println("1. By Title");
        System.out.println("2. By Cost");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        switch (choice) {
            case 1: // Sắp xếp theo Title
                cart.sortBy(Media.COMPARATOR_BY_TITLE_COST);
                System.out.println("Cart sorted by title:");
                cart.print();
                break;

            case 2: // Sắp xếp theo Cost
                cart.sortBy(Media.COMPARATOR_BY_COST_TITLE);
                System.out.println("Cart sorted by cost:");
                cart.print();
                break;

            default:
                System.out.println("Invalid choice. Returning to cart menu.");
        }
    }
    public static void handlePlaceOrder() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Cannot place an order.");
        } else {
            System.out.println("Order placed successfully! Your cart is now empty.");
            cart.emptyCart(); // Làm trống giỏ hàng
        }
    }


}