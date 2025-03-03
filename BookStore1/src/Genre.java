import java.io.Serializable;

public class Genre implements Serializable {
    private Long id;
    private String genre;

    public Genre(){

    }

    public Genre(Long id){
        this.id = id;
    }

    public Genre(Long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre(String genre){
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }
}
