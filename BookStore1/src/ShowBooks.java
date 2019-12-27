import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ShowBooks extends Container {
   // private Shop shop = null;
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    public ShowBooks(Socket socket){
       // this.shop = shop;
        this.socket = socket;
        String[][] str1 = null;
        String[] str = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.reset();
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            oos.writeObject(new Request("VIEW"));

            Reply rep = (Reply) ois.readObject();
            ArrayList<Book> b = rep.getBooks();
            if(b.size()!=0){
                str1 = new String[b.size()][7];
                str = new String[7];
                str[0] = "id";
                str[1] = "name";
                str[2] = "author";
                str[3] = "year";
                str[4] = "rating";
                str[5] = "price";
                str[6] = "count";
                for(int i =0;i<b.size();i++){
                    str1[i][0] = String.valueOf(b.get(i).getId());
                    str1[i][1] = b.get(i).getName();
                    str1[i][2] = b.get(i).getAuthor();
                    str1[i][3] = String.valueOf(b.get(i).getYear());
                    str1[i][4] = String.valueOf(b.get(i).getRating());
                    str1[i][5] = String.valueOf(b.get(i).getPrice());
                    str1[i][6] = String.valueOf(b.get(i).getCount());
                }
            }

           // JTable jt = new JTable(objects);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        JFrame book = new JFrame("BOOKS");
        book.setSize(500,500);
        book.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        book.setResizable(false);
        book.getContentPane().setLayout(new FlowLayout());
        book.setLocationRelativeTo(null);

        JTable table = new JTable(str1,str);

        JScrollPane pane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));
        book.getContentPane().add(pane);

        JButton back = new JButton("BACK");
        back.setBounds(300,350,150,50);
        book.add(back);

        JButton exit = new JButton("EXIT");
        exit.setBounds(300,400,150,50);
        book.add(exit);

        book.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get role
                //if role ADMIN
                //admin setvisible true
                //else if role USER
                //user setvisible true
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    oos.writeObject(new Request("EXIT"));
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
