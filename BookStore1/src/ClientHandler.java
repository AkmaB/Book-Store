import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

    }

    public void run(){
        Database db = new Database();

        while(true) {
            try{
                Request req = (Request) ois.readObject();

                if (req.getCode().equals("ADD_BOOK")) {
                    db.addBook(req.getBook());

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }

                else if (req.getCode().equals("VIEW_BOOKS")) {
                    Reply rep = new Reply();
                    for (Book b : db.getAllBooks()) {
                        rep.addBook(b);
                    }
                    oos.writeObject(rep);

                }

                else if(req.getCode().equals("MINUS_COUNT")){
                    db.updateBook(req.getId_b(),req.getCountB());
                }

                else if (req.getCode().equals("REMOVE")){
                    db.removeBook(req.getId());

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("UPDATE_BOOK")){
                    db.updateBook(req.getId(),req.getCountB());

                    Reply rep = new Reply();
                    rep.setCode("UPDATE");
                    oos.writeObject(rep);

                }

                else if(req.getCode().equals("SORT_BOOK_R")){
                    Reply rep = new Reply();
                    for (Book b : db.sortBook("rating")) {
                        rep.addBook(b);
                    }
                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("SORT_BOOK_P")){
                    Reply rep = new Reply();
                    for (Book b : db.sortBook("price")) {
                        rep.addBook(b);
                    }
                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("SORT_BOOK_C")){
                    Reply rep = new Reply();
                    for (Book b : db.sortBook("count")) {
                        rep.addBook(b);
                    }
                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("BUY_BOOK")){
                    db.addUserBook(req.getU().getId(),req.getId_b(),req.getCountB());

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("addBu")){
                    db.addExcBook(req.getId_u(),req.getId_b(),req.getBooK());

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("getBu")){
                    Reply rep = new Reply();
                    rep.addBook(db.excGetB());
                    oos.writeObject(rep);

                }

                else if(req.getCode().equals("ADD_GENRE")){
                    db.addGenre(req.getG());

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }

                else if (req.getCode().equals("GETGENRE")){
                    Reply rep = new Reply();
                    for (Genre g : db.getAllGenres())
                        rep.addGenre(g);

                    oos.writeObject(rep);
                }

                else if(req.getCode().equals("CHECK")){
                    User otvet = db.check(req.getLogin(),req.getPassword());
                    if(otvet.getRole().equals("ADMIN")){
                        oos.writeObject(new Request("ADMIN",otvet));
                    }
                    else if(otvet.getRole().equals("USER")){
                        oos.writeObject(new Request("USER",otvet));
                    }
                    else{
                        oos.writeObject(new Request("INCORRECT"));
                    }
                }

                else if(req.getCode().equals("SAVE")){
                    db.addUser(new User(req.getLogin(),req.getPassword(),"USER"));

                    Reply rep = new Reply();
                    rep.setCode("SUCCESS");
                    oos.writeObject(rep);
                }


                else if(req.getCode().equals("SHOW_GENRES")){
                    db.getAllGenres();
                }

                else if (req.getCode().equals("EXIT")) {
                    oos.close();
                    ois.close();
                    break;
                }
            }
            catch(IOException | ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
        }
    }

}
