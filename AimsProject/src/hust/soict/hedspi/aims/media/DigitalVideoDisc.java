package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    //Khai báo thuộc tính
    private static int nbDigitalVideoDiscs = 0; // Class attribute can khai bao


    //Phương thức khởi dựng các thông tin của đĩa DVD
    //Các phương thức khởi dựng trên nạp chồng
    //Constructor by title
    public DigitalVideoDisc(String title) {
        super(title);
        setId(++nbDigitalVideoDiscs); // Update class variable and assign id
    }
    //Constructor by category, title and cost
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
        setId(++nbDigitalVideoDiscs); // Update class variable and assign id
    }
    //Constructor by title, category , director, cost
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, cost);
        this.director = director;
        setId(++nbDigitalVideoDiscs); // Update class variable and assign id
    }
    // Constructor by all attributes
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
        setId(++nbDigitalVideoDiscs); // Update class variable and assign id
    }
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
    @Override
    public String toString() {
        return String.format("DVD - %s - %s - %s - %dm. : %.2f $", title, category, director, length, cost)
                .replaceAll(" null | 0 ", " Unknown ");
    }


}