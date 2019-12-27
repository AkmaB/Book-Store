import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable{
    private String code;
    private Book book;
    private Genre genre;
    private Long id;
    private Long id_b;
    private Long id_u;
    private Long id_g;
    private int countB;
    private String login;
    private String password;
    private String role;
    private String g;
    private String booK;
    private User u;
    private Buyer b;
    private double r;

    public Request() {
    }

    public Request(String code) {
        this.code = code;
    }

    public Request(String code, Long id) {
        this.code = code;
        this.id = id;
    }

    public Request(String code, Long id, Long id_u) {
        this.code = code;
        this.id = id;
        this.id_u = id_u;
    }

    public Request(String code,Long id,int countB){
        this.code = code;
        this.id = id;
        this.countB = countB;
    }

    public Request(String code,double r){
        this.code = code;
        this.r = r;
    }

    public Request(String code,Long id_u, Long id_b,String booK){
        this.code = code;
        this.id_u = id_u;
        this.id_b = id_b;
        this.booK = booK;
    }


    public Request(String code, String login, String password) {
        this.code = code;
        this.login = login;
        this.password = password;
    }

    public Buyer getB() {
        return b;
    }

    public void setB(Buyer b) {
        this.b = b;
    }

    public Request(String code, Genre genre){
        this.code = code;
        this.genre = genre;
    }

    public Request(String code, String g){
        this.code = code;
        this.g = g;
    }

    public Request(String code, User u) {
        this.code = code;
        this.u = u;
    }

    public Request(String code, User u,Long id_b,int countB){
        this.code = code;
        this.u = u;
        this.id_b = id_b;
        this.countB = countB;
    }

    public String getBooK() {
        return booK;
    }

    public void setBooK(String booK) {
        this.booK = booK;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public Long getId_u() {
        return id_u;
    }

    public void setId_u(Long id_u) {
        this.id_u = id_u;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getCountB() {
        return countB;
    }

    public void setCountB(int countB) {
        this.countB = countB;
    }

    public Long getId_b() {
        return id_b;
    }

    public void setId_b(Long id_b) {
        this.id_b = id_b;
    }

    public Long getId_g() {
        return id_g;
    }

    public void setId_g(Long id_g) {
        this.id_g = id_g;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request(String code, Book book) {
        this.code = code;
        this.book = book;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Request{" +
                "code='" + code + '\'' +
                ", book=" + book +
                '}';
    }
}
