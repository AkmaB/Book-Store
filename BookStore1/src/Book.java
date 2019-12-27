import java.io.Serializable;
import java.util.ArrayList;

public class Book extends Genre implements Serializable {
    private Long id; //long id in dbs
    private String name;
    private String genre; //button in new Window list of genres
    private String author;
    private int year;
    private double rating;
    private double price;
    private int count;
    private Long id_g;

    public Book(){

    }

    public Book(Long id, String name, String author, int year, double rating, double price, int count, String genre) {
        super(genre);
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.price = price;
        this.count = count;
        this.genre = genre;
    }

    public Book(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId_g() {
        return id_g;
    }

    public void setId_g(Long id_g) {
        this.id_g = id_g;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
