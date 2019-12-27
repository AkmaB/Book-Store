import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String password;
    private String name;
    private String role;
    private Database db;

    User(){

    }

    public User(Long id, String name,String password, String role) {
        this.id = id;
        this.name = name;
        this.password=password;
        this.role = role;
    }

    public User(String name,String password,String role){
        this.name = name;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
