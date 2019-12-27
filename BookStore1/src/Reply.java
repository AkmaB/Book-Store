import java.io.Serializable;
import java.lang.management.LockInfo;
import java.util.ArrayList;

public class Reply implements Serializable {
    private String code;
    private ArrayList<Book> books = null;
    private ArrayList<Genre> genres = null;
    private Book b;

    Long idU;
    Long idB;
    String nameB;
    String nameU;

    public void addBook(Book b){
        books.add(b);
    }

    public void addGenre(Genre g){
        genres.add(g);
    }

    public Reply() {
       this.books = new ArrayList<>();
       this.genres =  new ArrayList<>();
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public Reply(Long idU, Long idB, String nameB, String nameU) {
        this.idU = idU;
        this.idB = b.getId();
        this.nameB = b.getName();
        this.nameU = nameU;
    }

    public Long getIdU() {
        return idU;
    }

    public void setIdU(Long idU) {
        this.idU = idU;
    }

    public Long getIdB() {
        return idB;
    }

    public void setIdB(Long idB) {
        this.idB = idB;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    public Book getB() {
        return b;
    }

    public void setB(Book b) {
        this.b = b;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Genre> getGenres(){
        return genres;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "code='" + code + '\'' +
                ", books=" + books +
                '}';
    }
}
