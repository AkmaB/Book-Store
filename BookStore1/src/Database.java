import java.sql.*;
import java.util.ArrayList;


public class Database {
    private Connection conn=null;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void addBook(Book book){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO books (id, name, author, year, rating, price, count, genre) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3,    book.getYear());
            ps.setDouble(4, book.getRating());
            ps.setDouble(5, book.getPrice());
            ps.setInt(6,  book.getCount());
            ps.setString(7, book.getGenre());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from books");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String author=rs.getString("author");
                int year = rs.getInt("year");
                double rating=rs.getDouble("rating");
                double price=rs.getDouble("price");
                int count=rs.getInt("count");
                String genre = rs.getString("genre");

                list.add(new Book(id,name,author, year,rating, price,count,genre));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void removeBook(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE * FROM books where id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateBook(Long id, int count){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE books set count = ? where id = ?");
            ps.setInt(1, count);
            ps.setLong(2, id);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Book> sortBook(String s){
        ArrayList<Book> list = new ArrayList<>();
        try {
            if(s.equals("rating")){
            PreparedStatement ps = conn.prepareStatement("SELECT * from books ORDER BY rating");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String author=rs.getString("author");
                int year = rs.getInt("year");
                double rating=rs.getDouble("rating");
                double price=rs.getDouble("price");
                int count=rs.getInt("count");
                String genre = rs.getString("genre");

                list.add(new Book(id,name,author, year,rating, price,count,genre));
            }
            ps.close();
            }
            else if(s.equals("price")){
                PreparedStatement ps = conn.prepareStatement("SELECT * from books ORDER BY price");
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String author=rs.getString("author");
                    int year = rs.getInt("year");
                    double rating=rs.getDouble("rating");
                    double price=rs.getDouble("price");
                    int count=rs.getInt("count");
                    String genre = rs.getString("genre");

                    list.add(new Book(id,name,author, year,rating, price,count,genre));
                }
                ps.close();
            }
            else if(s.equals("count")){
                PreparedStatement ps = conn.prepareStatement("SELECT * from books ORDER BY count");
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String author=rs.getString("author");
                    int year = rs.getInt("year");
                    double rating=rs.getDouble("rating");
                    double price=rs.getDouble("price");
                    int count=rs.getInt("count");
                    String genre = rs.getString("genre");

                    list.add(new Book(id,name,author, year,rating, price,count,genre));
                }
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public void addUser(User user){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (id_user, name,password, role) VALUES (NULL, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUserBook(Long id_u,Long idB, int n){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_books (id_user, id_book, countB) VALUES (?, ?, ?)");
            ps.setLong(1, id_u );
            ps.setLong(2, idB);
            ps.setInt(3,n);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGenre(String g){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO genre (id_genre, genre) VALUES (NULL, ?)");
            ps.setString(1,g);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String password=rs.getString("password");
                String role=rs.getString("role");

                list.add(new User(id,name,password,role));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public ArrayList<Genre> getAllGenres(){
        ArrayList<Genre> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from genre");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id_genre");
                String name = rs.getString("genre");

                list.add(new Genre(id,name));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public void addExcBook(Long idU, Long idB,String s){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usersbook (id_user, id_book, name) VALUES (?, NULL, ?)");
            ps.setLong(1, idU);
            ps.setString(2, s);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Book excGetB(){
        Book b = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from usersbook ");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long idU = rs.getLong("id_user");
                Long idB = rs.getLong("id_book");
                String name = rs.getString("name");

                return new Book(idB,name);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  User check(String login,String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where 1");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String n = rs.getString("name");
            String p = rs.getString("password");
            String r = rs.getString("role");
            Long l = rs.getLong("id_user");
            if (login.equals(n) && password.equals(p)) {
                User u = new User(l,n,p,r);
                return u;
            }
        }
        ps.close();
        return null;
}
}
