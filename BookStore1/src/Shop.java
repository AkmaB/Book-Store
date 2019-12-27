import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.Image;
import java.util.Collection;
import java.util.Collections;


public class Shop extends JFrame {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private JPanel main,am,suc,jp,shb,rem,um,sortTable,addGg,incS,buyB,addBu,excBu;
    private JPanel bookP;
    private JButton b1 = null;
    private JButton b2 = null;
    private JButton b0 = null;
    private ArrayList<Genre> allGenre = null;
    private ArrayList<Book> books = null;
    private ArrayList<Book> sortedBooks = null;
    private Reply rep;
    private Long id_u = null;
    private Database db;
    private User u;
    private ArrayList<Buyer> buyers = null;
    private Buyer b;



    public Shop(Socket socket) throws IOException, ClassNotFoundException {
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois  = new ObjectInputStream(socket.getInputStream());

        buyers = new ArrayList<>();

        setTitle("BOOK STORE");

        main = new JPanel(null);
        main.setBounds(0,0,600,600);
        main.setBackground(new java.awt.Color(199, 229,255));
        main.setVisible(true);
        add(main);

        b1=new JButton("Sign in");
        b1.setBounds(150,140,100,45);
        b1.setBackground(Color.white);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                signIn();
            }
        });
        main.add(b1);

        b2=new JButton("Sign up");
        b2.setBounds(150,190,100,45);
        b2.setBackground(Color.white);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
                signUp();
            }
        });
        main.add(b2);

        //Exit
        b0 = new JButton("Exit");
        b0.setBounds(150,240,100,45);
        b0.setBackground(Color.white);
        main.add(b0);

        b0.addActionListener(new ActionListener() {
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

        setSize(600,600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void userMenu(){
        um = new JPanel(null);
        um.setSize(600,600);
        um.setBackground(new java.awt.Color(199, 229,255));
        um.setVisible(true);
        add(um);

        JLabel img = new JLabel(new ImageIcon("bkstr.jpg"));
        img.setSize(400,150);
        um.add(img);

        JLabel img2 = new JLabel(new ImageIcon("bks.png"));
        img2.setBounds(350,250,200,200);
        um.add(img2);

        JLabel sortU = new JLabel("Shop by genre");
        sortU.setBounds(0,155,100,25);
        sortU.setBackground(Color.white);
        um.add(sortU);

        String[]  sort1= {
                "rating",
                "price",
                "count"
        };
        JComboBox jcbSort = new JComboBox(sort1);
        jcbSort.setBounds(5,206,100,25);
        jcbSort.setBackground(Color.white);
        um.add(jcbSort);

        JButton ok1 = new JButton("sort");
        ok1.setBounds(106,206,100,50);
        ok1.setBackground(Color.white);
        ok1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) jcbSort.getSelectedItem();
                sortedB(s);
                um.setVisible(false);
            }
        });
        um.add(ok1);

        JButton buy = new JButton("buy book");
        buy.setBounds(106,257,100,50);
        buy.setBackground(Color.white);
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                um.setVisible(false);
                buyBuu();
            }
        });
        um.add(buy);

        JButton addBook = new JButton("add book");
        addBook.setBackground(Color.white);
        addBook.setBounds(106,308,100,50);
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                um.setVisible(false);
                addBooku();
            }
        });
        um.add(addBook);

        JButton exch = new JButton("exchange of books");
        exch.setBackground(Color.white);
        exch.setBounds(106,359,150,50);
        exch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                um.setVisible(false);
                excB();
            }
        });
        um.add(exch);

        JButton shw = new JButton("show books");
        shw.setBackground(Color.white);
        shw.setBounds(106,410,150,50);
        shw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                um.setVisible(false);
                shwBu();
            }
        });
        um.add(shw);

        JButton back = new JButton("back to main");
        back.setBounds(399,530,120,20);
        back.setBackground(Color.white);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                um.setVisible(false);
                main.setVisible(true);
            }
        });
        um.add(back);

        JButton exit = new JButton("exit");
        exit.setBackground(Color.white);
        exit.setBounds(520,530,60,20);
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
        um.add(exit);




    }

    public void excB(){
        excBu = new JPanel(null);
        excBu.setSize(600,600);
        excBu.setBackground(new java.awt.Color(199, 229,255));
        excBu.setVisible(true);
        add(excBu);

        JLabel img = new JLabel(new ImageIcon("1c6.jpg"));
        img.setSize(150,50);
        excBu.add(img);


        JButton backPA = new JButton("back");
        backPA.setBounds(450,510,60,20);
        backPA.setBackground(Color.white);
        backPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excBu.setVisible(false);
                um.setVisible(true);
            }
        });
        excBu.add(backPA);

    }

    public void buyBuu(){
        buyB = new JPanel(null);
        buyB.setSize(600,600);
        buyB.setBackground(new java.awt.Color(199, 229,255));
        buyB.setVisible(true);
        add(buyB);

        try {
            oos.writeObject(new Request("VIEW_BOOKS"));
            Reply rep = (Reply) ois.readObject();
            books = rep.getBooks();
            String[][] strBook = null;
            String[] columnB = null;
            if(books.size()!=0){
                columnB = new String[8];
                columnB[0] =  "id";
                columnB[1] = "name";
                columnB[2] = "author";
                columnB[3] = "year";
                columnB[4] = "rating";
                columnB[5] = "price";
                columnB[6] = "count";
                columnB[7] = "genre";
                strBook = new String[books.size()][8];
                for(int i = 0; i<books.size(); i++){
                    strBook[i][0] = String.valueOf(books.get(i).getId());
                    strBook[i][1] = books.get(i).getName();
                    strBook[i][2] = books.get(i).getAuthor();
                    strBook[i][3] = String.valueOf(books.get(i).getYear());
                    strBook[i][4] = String.valueOf(books.get(i).getRating());
                    strBook[i][5] = String.valueOf(books.get(i).getPrice());
                    strBook[i][6] = String.valueOf(books.get(i).getCount());
                    strBook[i][7] = books.get(i).getGenre();
                }

                JTable tableB = new JTable(strBook,columnB);
                JScrollPane pane = new JScrollPane(tableB);
                getContentPane().add(pane,BorderLayout.CENTER);
                tableB.setSize(600,300);
                tableB.setBackground(new java.awt.Color(199, 229,255));
                buyB.add(tableB);
            }
            else {
                JOptionPane.showMessageDialog(shb,"THERE IS NO BOOKS");
            }

        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        JLabel idW = new JLabel("write id");
        idW.setBounds(80,310,100,45);
        idW.setBackground(Color.white);
        buyB.add(idW);

        JTextField id = new JTextField();
        id.setBounds(125,310,100,45);
        id.setBackground(Color.white);
        buyB.add(id);

        String[] n ={
                "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"
        };
        JComboBox jcb = new JComboBox(n);
        jcb.setBackground(Color.white);
        jcb.setBounds(226,310,60,25);
        buyB.add(jcb);

        JButton buy = new JButton("buy");
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long idB = Long.valueOf(id.getText());
                int n1 = jcb.getSelectedIndex();
                int c = 0;
                for (int i =0; i<books.size();i++){
                    if(idB -1 == books.get(i).getId()){
                        if(n1 <= books.get(i).getCount()){
                            n1 = books.get(i).getCount();
                            b = new Buyer(u.getName(),books.get(i).getName(),n1,books.get(i).getPrice());
                            buyers.add(b);
                            c = books.get(i).getCount() - n1;
                        }
                        else if(n1 > books.get(i).getCount()){
                            JOptionPane.showMessageDialog(buyB,"NOT ENOUGH BOOKS");
                        }
                        else{
                            JOptionPane.showMessageDialog(buyB,"INCORRECT");
                        }
                    }
                }

                try {
                    oos.writeObject(new Request("BUY_BOOK",u,idB, n1));

                    Reply rep = (Reply) ois.readObject();
                    if(rep.getCode().equals("SUCCESS")){
                        JOptionPane.showMessageDialog(buyB,"SUCCESSFULLY BOUGHT");
                    }
                    else
                        JOptionPane.showMessageDialog(buyB,"INCORRECT");
                    // oos.writeObject(new Request("MINUS_COUNT",u.getId(),c));

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                id.setText(null);
            }
        });
        buy.setBackground(Color.white);
        buy.setBounds(125,355,100,45);
        buyB.add(buy);

        JButton back = new JButton("back");
        back.setBackground(Color.white);
        back.setBounds(450,520,60,20);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyB.setVisible(false);
                um.setVisible(true);
            }
        });
        buyB.add(back);


    }

    public void addBooku(){
        addBu = new JPanel(null);
        addBu.setSize(600,600);
        addBu.setBackground(new java.awt.Color(199, 229,255));
        addBu.setVisible(true);
        add(addBu);

        JLabel name =new JLabel("Name: ");
        name.setBounds(50,50,100,50);
        name.setBackground(Color.white);
        addBu.add(name);

        JTextField nameWrite = new JTextField();
        nameWrite.setBounds(150,50,250,50);
        addBu.add(nameWrite);

        JButton addBk = new JButton("add for exchange");
        addBk.setBounds(200,100,150,50);
        addBk.setBackground(Color.white);
        addBk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = nameWrite.getText();
                try {
                    oos.writeObject(new Request("addBu",u.getId(),null,s));

//                    oos.writeObject(new Request("getBu"));

                    Reply rep = (Reply)ois.readObject();
//
//                    n = String.valueOf(u.getId());
//                    n1 = String.valueOf(rep.getB().getId());
//                    sss.add(n);
//                    sss.add(u.getName());
//                    sss.add(n1);
//                    sss.add(rep.getB().getName());


                    if(rep.getCode().equals("SUCCESS")){
                        JOptionPane.showMessageDialog(addBu,"SUCCESSFULLY ADDED");
                    }
                    else {
                        JOptionPane.showMessageDialog(addBu,"INCORRECT");
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                nameWrite.setText(null);
            }
        });
        addBu.add(addBk);

        JButton backPA = new JButton("back");
        backPA.setBounds(450,510,60,20);
        backPA.setBackground(Color.white);
        backPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBu.setVisible(false);
                um.setVisible(true);
            }
        });
        addBu.add(backPA);



    }

    public void shwBu(){
        shb = new JPanel(null);
        shb.setBounds(0,0,600,600);
        shb.setBackground(new java.awt.Color(199, 229,255));
        shb.setVisible(true);
        add(shb);
        try {
            oos.writeObject(new Request("VIEW_BOOKS"));
            Reply rep = (Reply) ois.readObject();
            books = rep.getBooks();
            String[][] strBook = null;
            String[] columnB = null;
            if(books.size()!=0){
                columnB = new String[8];
                columnB[0] =  "id";
                columnB[1] = "name";
                columnB[2] = "author";
                columnB[3] = "year";
                columnB[4] = "rating";
                columnB[5] = "price";
                columnB[6] = "count";
                columnB[7] = "genre";
                strBook = new String[books.size()][8];
                for(int i = 0; i<books.size(); i++){
                    strBook[i][0] = String.valueOf(books.get(i).getId());
                    strBook[i][1] = books.get(i).getName();
                    strBook[i][2] = books.get(i).getAuthor();
                    strBook[i][3] = String.valueOf(books.get(i).getYear());
                    strBook[i][4] = String.valueOf(books.get(i).getRating());
                    strBook[i][5] = String.valueOf(books.get(i).getPrice());
                    strBook[i][6] = String.valueOf(books.get(i).getCount());
                    strBook[i][7] = books.get(i).getGenre();
                }

                JTable tableB = new JTable(strBook,columnB);
                JScrollPane pane = new JScrollPane(tableB);
                getContentPane().add(pane,BorderLayout.CENTER);
                tableB.setSize(600,300);
                tableB.setBackground(new java.awt.Color(199, 229,255));
                shb.add(tableB);
            }
            else {
                JOptionPane.showMessageDialog(shb,"THERE IS NO BOOKS");
            }

        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        JButton backPA = new JButton("back");
        backPA.setBounds(450,510,60,20);
        backPA.setBackground(Color.white);
        backPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shb.setVisible(false);
                um.setVisible(true);
            }
        });
        shb.add(backPA);
    }

    public void sortedB(String s){
        if(s.equals("rating")){
            sortByR();
        }
        else if(s.equals("price")){
            sortByP();
        }
        else if(s.equals("count")){
            sortByC();
        }
        else {
            JOptionPane.showMessageDialog(um,"INCORRECT");
        }
    }

    public void sortByR(){
        try {
            oos.writeObject(new Request("SORT_BOOK_R"));
            sortTable = new JPanel(null);
            sortTable.setSize(600,600);
            sortTable.setBackground(new java.awt.Color(199,229,255));
            sortTable.setVisible(true);
            add(sortTable);

            Reply rep = (Reply) ois.readObject();
            sortedBooks = rep.getBooks();
            String[][] str7 = null;
            String[] column1 = null;
            column1 = new String[8];
            column1[0] =  "id";
            column1[1] = "name";
            column1[2] = "author";
            column1[3] = "year";
            column1[4] = "rating";
            column1[5] = "price";
            column1[6] = "count";
            column1[7] = "genre";
            str7 = new String[sortedBooks.size()][8];
            for(int i = 0; i<sortedBooks.size(); i++){
                str7[i][0] = String.valueOf(sortedBooks.get(i).getId());
                str7[i][1] = sortedBooks.get(i).getName();
                str7[i][2] = sortedBooks.get(i).getAuthor();
                str7[i][3] = String.valueOf(sortedBooks.get(i).getYear());
                str7[i][4] = String.valueOf(sortedBooks.get(i).getRating());
                str7[i][5] = String.valueOf(sortedBooks.get(i).getPrice());
                str7[i][6] = String.valueOf(sortedBooks.get(i).getCount());
                str7[i][7] = sortedBooks.get(i).getGenre();
            }

            JTable table1 = new JTable(str7,column1);
            JScrollPane pane1 = new JScrollPane(table1);
            getContentPane().add(pane1,BorderLayout.CENTER);
            table1.setSize(600,300);
            table1.setBackground(new java.awt.Color(199, 229,255));
            sortTable.add(table1);

            JLabel idW = new JLabel("write id");
            idW.setBounds(80,310,100,45);
            idW.setBackground(Color.white);
            sortTable.add(idW);

            JTextField id = new JTextField();
            id.setBounds(125,310,100,45);
            id.setBackground(Color.white);
            sortTable.add(id);

            String[] n ={
                    "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"
            };
            JComboBox jcb = new JComboBox(n);
            jcb.setBackground(Color.white);
            jcb.setBounds(226,310,60,25);
            sortTable.add(jcb);


            JButton buy = new JButton("buy");
            buy.setBackground(Color.white);
            buy.setBounds(125,355,100,45);
            buy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Long idB = Long.valueOf(id.getText());
                    int n1 = (Integer) jcb.getSelectedIndex();
                    int c = 0;
                    for (int i =0; i<sortedBooks.size();i++){
                        if(idB -1 == sortedBooks.get(i).getId()){
                            if(n1 <= sortedBooks.get(i).getCount()){
                                n1 = sortedBooks.get(i).getCount();
                              //  b.addBuyer(new Buyer(u.getName(),sortedBooks.get(i).getName(),n1,sortedBooks.get(i).getPrice()));
                                c = sortedBooks.get(i).getCount() - n1;
                            }
                            else if(n1 > sortedBooks.get(i).getCount()){
                                JOptionPane.showMessageDialog(sortTable,"NOT ENOUGH BOOKS");
                            }
                            else{
                                JOptionPane.showMessageDialog(sortTable,"INCORRECT");
                            }
                        }
                    }
                    try {
                        oos.writeObject(new Request("BUY_BOOK",u,idB, n1));

                        Reply rep = (Reply) ois.readObject();
                        if(rep.getCode().equals("SUCCESS")){
                            JOptionPane.showMessageDialog(sortTable,"SUCCESSFULLY BOUGHT");
                        }
                        else
                            JOptionPane.showMessageDialog(sortTable,"INCORRECT");
                       // oos.writeObject(new Request("MINUS_COUNT",u.getId(),c));

                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            sortTable.add(buy);

            JButton backMainU = new JButton("back");
            backMainU.setBounds(125,400,100,45);
            backMainU.setBackground(Color.white);
            backMainU.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortTable.setVisible(false);
                    um.setVisible(true);
                }
            });
            sortTable.add(backMainU);
        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void sortByP(){
        try {
            oos.writeObject(new Request("SORT_BOOK_P"));
            sortTable = new JPanel(null);
            sortTable.setBounds(0,0,600,600);
            sortTable.setBackground(new java.awt.Color(199,229,255));
            sortTable.setVisible(true);
            add(sortTable);

            Reply rep = (Reply) ois.readObject();
            sortedBooks = rep.getBooks();
            String[][] str7 = null;
            String[] column1 = null;
            column1 = new String[8];
            column1[0] =  "id";
            column1[1] = "name";
            column1[2] = "author";
            column1[3] = "year";
            column1[4] = "rating";
            column1[5] = "price";
            column1[6] = "count";
            column1[7] = "genre";
            str7 = new String[sortedBooks.size()][8];
            for(int i = 0; i<sortedBooks.size(); i++){
                str7[i][0] = String.valueOf(sortedBooks.get(i).getId());
                str7[i][1] = sortedBooks.get(i).getName();
                str7[i][2] = sortedBooks.get(i).getAuthor();
                str7[i][3] = String.valueOf(sortedBooks.get(i).getYear());
                str7[i][4] = String.valueOf(sortedBooks.get(i).getRating());
                str7[i][5] = String.valueOf(sortedBooks.get(i).getPrice());
                str7[i][6] = String.valueOf(sortedBooks.get(i).getCount());
                str7[i][7] = sortedBooks.get(i).getGenre();
            }

            JTable table1 = new JTable(str7,column1);
            JScrollPane pane1 = new JScrollPane(table1);
            getContentPane().add(pane1,BorderLayout.CENTER);
            table1.setSize(600,300);
            table1.setBackground(new java.awt.Color(199, 229,255));
            sortTable.add(table1);

            JLabel idW = new JLabel("write id");
            idW.setBounds(80,310,100,45);
            idW.setBackground(Color.white);
            sortTable.add(idW);

            JTextField id = new JTextField();
            id.setBounds(125,310,100,45);
            id.setBackground(Color.white);
            sortTable.add(id);

            String[] n ={
                    "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"
            };
            JComboBox jcb = new JComboBox(n);
            jcb.setBackground(Color.white);
            jcb.setBounds(226,310,60,25);
            sortTable.add(jcb);


            JButton buy = new JButton("buy");
            buy.setBackground(Color.white);
            buy.setBounds(125,355,100,45);

            buy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Long idB = Long.valueOf(id.getText());
                    int n1 = (Integer) jcb.getSelectedIndex();
                    int c = 0;
                    Long idB1 = null;
                    for (int i =0; i<sortedBooks.size();i++){
                        if(idB -1 == sortedBooks.get(i).getId()){
                            if(n1 <= sortedBooks.get(i).getCount()){
                                n1 = sortedBooks.get(i).getCount();
                                b = new Buyer(u.getName(),sortedBooks.get(i).getName(),n1,sortedBooks.get(i).getPrice());
                                buyers.add(b);
                                c = sortedBooks.get(i).getCount() - n1;
                                idB1 = sortedBooks.get(i).getId();
                            }
                            else if(n1 > sortedBooks.get(i).getCount()){
                                JOptionPane.showMessageDialog(sortTable,"NOT ENOUGH BOOKS");
                            }
                            else{
                                JOptionPane.showMessageDialog(sortTable,"INCORRECT");
                            }
                        }
                    }
                    try {
                        oos.writeObject(new Request("BUY_BOOK",idB, n1));

                        Reply rep = (Reply) ois.readObject();
                        if(rep.getCode().equals("SUCCESS")){
                            JOptionPane.showMessageDialog(sortTable,"SUCCESSFULLY BOUGHT");
                        }
                        else
                            JOptionPane.showMessageDialog(sortTable,"INCORRECT");
                     oos.writeObject(new Request("MINUS_COUNT",idB1,c));

                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            sortTable.add(buy);

            JButton backMainU = new JButton("back");
            backMainU.setBounds(125,400,100,45);
            backMainU.setBackground(Color.white);
            backMainU.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortTable.setVisible(false);
                    um.setVisible(true);
                }
            });
            sortTable.add(backMainU);
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void sortByC(){
        try {
            oos.writeObject(new Request("SORT_BOOK_C"));
            sortTable = new JPanel(null);
            sortTable.setBounds(0,0,600,600);
            sortTable.setBackground(new java.awt.Color(199,229,255));
            sortTable.setVisible(true);
            add(sortTable);

            Reply rep = (Reply) ois.readObject();
            sortedBooks = rep.getBooks();
            String[][] str7 = null;
            String[] column1 = null;
            column1 = new String[8];
            column1[0] =  "id";
            column1[1] = "name";
            column1[2] = "author";
            column1[3] = "year";
            column1[4] = "rating";
            column1[5] = "price";
            column1[6] = "count";
            column1[7] = "genre";
            str7 = new String[sortedBooks.size()][8];
            for(int i = 0; i<sortedBooks.size(); i++){
                str7[i][0] = String.valueOf(sortedBooks.get(i).getId());
                str7[i][1] = sortedBooks.get(i).getName();
                str7[i][2] = sortedBooks.get(i).getAuthor();
                str7[i][3] = String.valueOf(sortedBooks.get(i).getYear());
                str7[i][4] = String.valueOf(sortedBooks.get(i).getRating());
                str7[i][5] = String.valueOf(sortedBooks.get(i).getPrice());
                str7[i][6] = String.valueOf(sortedBooks.get(i).getCount());
                str7[i][7] = sortedBooks.get(i).getGenre();
            }

            JTable table1 = new JTable(str7,column1);
            JScrollPane pane1 = new JScrollPane(table1);
            getContentPane().add(pane1,BorderLayout.CENTER);
            table1.setSize(600,300);
            table1.setBackground(new java.awt.Color(199, 229,255));
            sortTable.add(table1);

            JLabel idW = new JLabel("write id");
            idW.setBounds(80,310,100,45);
            idW.setBackground(Color.white);
            sortTable.add(idW);

            JTextField id = new JTextField();
            id.setBounds(125,310,100,45);
            id.setBackground(Color.white);
            sortTable.add(id);

            String[] n ={
                    "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"
            };
            JComboBox jcb = new JComboBox(n);
            jcb.setBackground(Color.white);
            jcb.setBounds(226,310,60,25);
            sortTable.add(jcb);


            JButton buy = new JButton("buy");
            buy.setBackground(Color.white);
            buy.setBounds(125,355,100,45);

            buy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Long idB = Long.valueOf(id.getText());
                    int n1 = (Integer) jcb.getSelectedIndex();
                    int c = 0;
                    for (int i =0; i<sortedBooks.size();i++){
                        if(idB -1 == sortedBooks.get(i).getId()){
                            if(n1 <= sortedBooks.get(i).getCount()){
                                n1 = sortedBooks.get(i).getCount();
                                b = new Buyer(u.getName(),sortedBooks.get(i).getName(),n1,sortedBooks.get(i).getPrice());
                                buyers.add(b);
                                c = sortedBooks.get(i).getCount() - n1;
                            }
                            else if(n1 > sortedBooks.get(i).getCount()){
                                JOptionPane.showMessageDialog(sortTable,"NOT ENOUGH BOOKS");
                            }
                            else{
                                JOptionPane.showMessageDialog(sortTable,"INCORRECT");
                            }
                        }
                    }
                    try {
                        oos.writeObject(new Request("BUY_BOOK",u,idB, n1));

                        Reply rep = (Reply) ois.readObject();
                        if(rep.getCode().equals("SUCCESS")){
                            JOptionPane.showMessageDialog(sortTable,"SUCCESSFULLY BOUGHT");
                        }
                        else
                            JOptionPane.showMessageDialog(sortTable,"INCORRECT");
                        oos.writeObject(new Request("MINUS_COUNT",u.getId(),c));

                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            sortTable.add(buy);

            JButton backMainU = new JButton("back");
            backMainU.setBounds(125,400,100,45);
            backMainU.setBackground(Color.white);
            backMainU.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortTable.setVisible(false);
                    um.setVisible(true);
                }
            });
            sortTable.add(backMainU);
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void adminMenu(){
        am = new JPanel(null);
        am.setBounds(0,0,600,600);
        am.setBackground(new java.awt.Color(199, 229,255));
        am.setVisible(true);
        add(am);

        JLabel img = new JLabel(new ImageIcon("adminmenu.jpg"));
        img.setSize(430,30);
        am.add(img);

        JButton addb = new JButton("ADD BOOK");
        addb.setBounds(150,50,150,40);
        addb.setBackground(Color.white);
        addb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am.setVisible(false);
                addBook();

            }
        });
        am.add(addb);

        JButton showb = new JButton("SHOW ALL BOOKS");
        showb.setBounds(150,100,150,40);
        showb.setBackground(Color.white);
        showb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am.setVisible(false);
                showBook();
            }
        });
        am.add(showb);

        JButton removeb = new JButton("REMOVE BOOK");
        removeb.setBounds(150,150,150,40);
        removeb.setBackground(Color.white);
        removeb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am.setVisible(false);
                removeBook();

            }
        });
        am.add(removeb);

        JButton income = new JButton("INCOME STATEMENT");
        income.setBounds(150,200,160,40);
        income.setBackground(Color.white);
        income.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am.setVisible(false);
                incStatement();
            }
        });
        am.add(income);

        JButton addG = new JButton("ADD GENRE");
        addG.setBounds(150,250,150,40);
        addG.setBackground(Color.white);
        addG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am.setVisible(false);
                addGenre();
            }
        });
        am.add(addG);

        JButton backMain = new JButton("BACK");
        backMain.setBounds(390,410,100,20);
        backMain.setBackground(Color.white);
        backMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                am.setVisible(false);
                main.setVisible(true);
            }
        });
        am.add(backMain);

        JButton exit = new JButton("EXIT");
        exit.setBounds(390,430,100,20);
        exit.setBackground(Color.white);
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
        am.add(exit);
    }

    public void addBook(){
        try {
            oos.writeObject(new Request("GETGENRE"));

            Reply rep = (Reply) ois.readObject();
            allGenre = rep.getGenres();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        bookP = new JPanel(null);
        bookP.setBounds(0,0,600,600);
        bookP.setBackground(new java.awt.Color(199, 229,255));
        bookP.setVisible(true);
        add(bookP);
        JButton backAM,saveBook;
        JLabel name,author,year,rating,price,count;
        JTextField nameWrite,authorWrite,yearWrite,ratingWrite,priceWrite,countWrite;

        name =new JLabel("Name: ");
        name.setBounds(50,50,100,50);
        name.setBackground(Color.white);
        bookP.add(name);
        nameWrite = new JTextField();
        nameWrite.setBounds(150,50,250,50);
        bookP.add(nameWrite);
        JLabel genreB = new JLabel("Genre");
        genreB.setBounds(50,100,100,50);
        genreB.setBackground(Color.white);
        bookP.add(genreB);

        String[] genres = {
                " ",
                allGenre.get(0).getGenre(),
                allGenre.get(1).getGenre(),
                allGenre.get(2).getGenre(),
                allGenre.get(3).getGenre(),
                allGenre.get(4).getGenre(),
                allGenre.get(5).getGenre(),
                allGenre.get(6).getGenre(),
                allGenre.get(7).getGenre(),
                allGenre.get(8).getGenre(),
                allGenre.get(9).getGenre(),
                allGenre.get(10).getGenre(),
                allGenre.get(11).getGenre(),
                allGenre.get(12).getGenre(),
                allGenre.get(13).getGenre(),
                allGenre.get(14).getGenre()
        };
        JComboBox jcb = new JComboBox(genres);
        jcb.setBounds(150,100,100,50);
        jcb.setBackground(Color.white);
        bookP.add(jcb);

        author =new JLabel("Author: ");
        author.setBounds(50,150,100,50);
        author.setBackground(Color.white);
        bookP.add(author);
        authorWrite = new JTextField();
        authorWrite.setBounds(150,150,250,50);
        bookP.add(authorWrite);
        year = new JLabel("Year of release: ");
        year.setBounds(50,200,100,50);
        year.setBackground(Color.white);
        bookP.add(year);
        yearWrite = new JTextField();
        yearWrite.setBounds(150,200,250,50);
        bookP.add(yearWrite);
        rating = new JLabel("Rating: ");
        rating.setBounds(50,250,100,50);
        rating.setBackground(Color.white);
        bookP.add(rating);
        ratingWrite = new JTextField();
        ratingWrite.setBounds(150,250,250,50);
        bookP.add(ratingWrite);
        price = new JLabel("Price");
        price.setBounds(50, 300, 100, 50);
        price.setBackground(Color.white);
        bookP.add(price);
        priceWrite = new JTextField();
        priceWrite.setBounds(150,300,250,50);
        bookP.add(priceWrite);
        count = new JLabel("Quantity: ");
        count.setBounds(50,350,105,50);
        count.setBackground(Color.white);
        bookP.add(count);
        countWrite = new JTextField();
        countWrite.setBounds(150,350,250,50);
        bookP.add(countWrite);

        saveBook = new JButton("save");
        saveBook.setBounds(50,400,100,50);
        saveBook.setBackground(Color.white);
        saveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n=nameWrite.getText();
                String a = authorWrite.getText();
                int y = Integer.valueOf(yearWrite.getText());
                double r = Double.valueOf(ratingWrite.getText());
                int p = Integer.valueOf(priceWrite.getText());
                int c = Integer.valueOf(countWrite.getText());
                String genre = (String)jcb.getSelectedItem();
                Book b = new Book(null,n,a,y,r,p,c,genre);
                try {
                    oos.writeObject(new Request("ADD_BOOK",b));

                    rep = (Reply) ois.readObject();
                    JOptionPane.showMessageDialog(bookP,rep.getCode());

                    nameWrite.setText(null);
                    authorWrite.setText(null);
                    yearWrite.setText(null);
                    ratingWrite.setText(null);
                    priceWrite.setText(null);
                    countWrite.setText(null);

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });
        bookP.add(saveBook);

        backAM = new JButton("back");
        backAM.setBounds(50,450,100,50);
        backAM.setBackground(Color.white);
        backAM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookP.setVisible(false);
                am.setVisible(true);

                nameWrite.setText(null);
                authorWrite.setText(null);
                yearWrite.setText(null);
                ratingWrite.setText(null);
                priceWrite.setText(null);
                countWrite.setText(null);
            }
        });
        bookP.add(backAM);
    }

    public void showBook(){
        shb = new JPanel(null);
        shb.setBounds(0,0,600,600);
        shb.setBackground(new java.awt.Color(199, 229,255));
        shb.setVisible(true);
        add(shb);
        try {
            oos.writeObject(new Request("VIEW_BOOKS"));
            Reply rep = (Reply) ois.readObject();
            books = rep.getBooks();
            String[][] strBook = null;
            String[] columnB = null;
            if(books.size()!=0){
                columnB = new String[8];
                columnB[0] =  "id";
                columnB[1] = "name";
                columnB[2] = "author";
                columnB[3] = "year";
                columnB[4] = "rating";
                columnB[5] = "price";
                columnB[6] = "count";
                columnB[7] = "genre";
                strBook = new String[books.size()][8];
                for(int i = 0; i<books.size(); i++){
                    strBook[i][0] = String.valueOf(books.get(i).getId());
                    strBook[i][1] = books.get(i).getName();
                    strBook[i][2] = books.get(i).getAuthor();
                    strBook[i][3] = String.valueOf(books.get(i).getYear());
                    strBook[i][4] = String.valueOf(books.get(i).getRating());
                    strBook[i][5] = String.valueOf(books.get(i).getPrice());
                    strBook[i][6] = String.valueOf(books.get(i).getCount());
                    strBook[i][7] = books.get(i).getGenre();
                }

                JTable tableB = new JTable(strBook,columnB);
                JScrollPane pane = new JScrollPane(tableB);
                getContentPane().add(pane,BorderLayout.CENTER);
                tableB.setSize(600,300);
                tableB.setBackground(new java.awt.Color(199, 229,255));
                shb.add(tableB);
            }
            else {
                JOptionPane.showMessageDialog(shb,"THERE IS NO BOOKS");
            }

        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        JButton backPA = new JButton("back");
        backPA.setBounds(300,400,80,50);
        backPA.setBackground(Color.white);
        backPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shb.setVisible(false);
                am.setVisible(true);
            }
        });
        shb.add(backPA);

    }

    public void removeBook(){
        rem = new JPanel(null);
        rem.setBounds(0,0,600,600);
        rem.setBackground(new java.awt.Color(199, 229,255));
        rem.setVisible(true);
        add(rem);
        try {
            oos.writeObject(new Request("VIEW_BOOKS"));
            rep = (Reply) ois.readObject();
            books = rep.getBooks();
            String[][] str = null;
            String[] column = null;
            if(books.size()!=0){
                column = new String[8];
                column[0] =  "id";
                column[1] = "name";
                column[2] = "author";
                column[3] = "year";
                column[4] = "rating";
                column[5] = "price";
                column[6] = "count";
                column[7] = "genre";
                str = new String[books.size()][8];
                for(int i = 0; i<books.size(); i++){
                    str[i][0] = String.valueOf(books.get(i).getId());
                    str[i][1] = books.get(i).getName();
                    str[i][2] = books.get(i).getAuthor();
                    str[i][3] = String.valueOf(books.get(i).getYear());
                    str[i][4] = String.valueOf(books.get(i).getRating());
                    str[i][5] = String.valueOf(books.get(i).getPrice());
                    str[i][6] = String.valueOf(books.get(i).getCount());
                    str[i][7] = books.get(i).getGenre();
                }

                JTable table = new JTable(str,column);
                JScrollPane pane = new JScrollPane(table);
                getContentPane().add(pane,BorderLayout.CENTER);
                table.setSize(600,300);
                table.setBackground(new java.awt.Color(199, 229,255));
                rem.add(table);
            }
            else {
                JOptionPane.showMessageDialog(shb,"THERE IS NO BOOKS");
            }

        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        JLabel choose = new JLabel("write id");
        choose.setBackground(Color.white);
        choose.setBounds(100,310,100,45);
        rem.add(choose);

        JTextField idBook = new JTextField();
        idBook.setBounds(150,310,100,45);
        rem.add(idBook);

        JLabel countBook = new JLabel("count");
        countBook.setBackground(Color.white);
        countBook.setBounds(100,355,100,45);
        rem.add(countBook);

        JTextField countWriteB = new JTextField();
        countWriteB.setBounds(150,355,100,45);
        rem.add(countWriteB);

        JButton remove = new JButton("remove");
        remove.setBounds(150,400,100,45);
        remove.setBackground(Color.white);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long idB = Long.valueOf(idBook.getText());
                int countB = Integer.parseInt(countWriteB.getText());
                removeBook(idB,countB);
                try {
                    tableBookShow();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                idBook.setText(null);
                countWriteB.setText(null);

            }
        });
        rem.add(remove);

        JButton backAa = new JButton("back");
        backAa.setBounds(150,445,100,45);
        backAa.setBackground(Color.white);
        backAa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rem.setVisible(false);
                am.setVisible(true);

                idBook.setText(null);
                countWriteB.setText(null);
            }
        });
        rem.add(backAa);
    }

    public void addGenre(){
        addGg = new JPanel(null);
        addGg.setBounds(0,0,600,600);
        addGg.setBackground(new java.awt.Color(199, 229,255));
        addGg.setVisible(true);
        add(addGg);

        JLabel g1 = new JLabel("Genre:");
        g1.setBounds(150,50,150,40);
        addGg.add(g1);

        JTextField g1W = new JTextField();
        g1W.setBackground(Color.white);
        g1W.setBounds(200,50,150,40);
        addGg.add(g1W);

        JButton aG = new JButton("add");
        aG.setBackground(Color.white);
        aG.setBounds(200,110,150,40);
        aG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = String.valueOf(g1W.getText());
                try {
                    oos.writeObject(new Request("ADD_GENRE",s));

                    Reply rep = (Reply) ois.readObject();
                    if(rep.getCode().equals("SUCCESS")){
                        JOptionPane.showMessageDialog(addGg,"SUCCESSFULLY ADDED");
                    }
                    else
                        JOptionPane.showMessageDialog(addGg,"INCORRECT");

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                g1W.setText(null);
            }
        });
        addGg.add(aG);

        JButton b1 = new JButton("back");
        b1.setBackground(Color.white);
        b1.setBounds(350,250,150,40);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGg.setVisible(false);
                am.setVisible(true);
            }
        });
        addGg.add(b1);
    }

    public void incStatement() {
        incS = new JPanel(null);
        incS.setBounds(0,0,600,600);
        incS.setBackground(new java.awt.Color(199, 229,255));
        incS.setVisible(true);
        add(incS);

        buyers = b.getBuyer();

        if(buyers.size() != 0){
        String[][] str9 = null;
        String[] column11 = null;
        column11 = new String[3];
        column11[0] =  "name user";
        column11[1] = "name book";
        column11[2] = "incStatement";

        str9 = new String[buyers.size()][3];
        for(int i = 0; i<buyers.size(); i++){
            str9[i][0] = buyers.get(i).getNameU();
            str9[i][1] = buyers.get(i).getNameB();
            str9[i][2] = String.valueOf(buyers.get(i).getInc());
        }

        JTable table11 = new JTable(str9,column11);
        JScrollPane pane11 = new JScrollPane(table11);
        getContentPane().add(pane11,BorderLayout.CENTER);
        table11.setSize(600,300);
        table11.setBackground(new java.awt.Color(199, 229,255));
        incS.add(table11);
        }
        else
            JOptionPane.showMessageDialog(incS,"NO INCOME STATEMENT AVAILABLE");

        JButton back = new JButton("back");
        back.setBackground(Color.white);
        back.setBounds(400,400,100,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incS.setVisible(false);
                am.setVisible(true);
            }
        });
        incS.add(back);

    }

    public void removeBook(Long idB,int countB){
        try{
            oos.writeObject(new Request("VIEW_BOOKS"));
                    Reply rep = (Reply) ois.readObject();
                    books = rep.getBooks();
                    for(int i = 0; i<books.size(); i++){
                        if(idB - 1 == books.get(i).getId()){
                            if(countB  == books.get(i).getCount()){
                                oos.writeObject(new Request("REMOVE",idB));

                                Reply r = (Reply) ois.readObject();
                               if(r.getCode().equals("SUCCESS")){
                                    JOptionPane.showMessageDialog(rem,"SUCCESSFULLY REMOVED");
                                }
                               else
                                   JOptionPane.showMessageDialog(rem,"INCORRECT");
                            }
                            else if(countB < books.get(i).getCount()){
                                int c = Integer.valueOf(books.get(i).getCount() - countB);
                                oos.writeObject(new Request("UPDATE_BOOK",idB,c));
                                Reply r = (Reply) ois.readObject();
                                if(r.getCode().equals("UPDATE")){
                                    JOptionPane.showMessageDialog(rem,"UPDATED BOOK");
                                }
                            }
                            else if( countB > books.get(i).getCount()){
                                JOptionPane.showMessageDialog(rem,"INCORRECT");
                            }

                        }
    }
                    tableBookRem();
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }

}

    public void tableBookRem() throws IOException, ClassNotFoundException {
        oos.writeObject(new Request("VIEW_BOOKS"));
        Reply rep = (Reply) ois.readObject();
        books = rep.getBooks();
        String[][] strBook = null;
        String[] columnB = null;
        if(books.size()!=0){
            columnB = new String[8];
            columnB[0] =  "id";
            columnB[1] = "name";
            columnB[2] = "author";
            columnB[3] = "year";
            columnB[4] = "rating";
            columnB[5] = "price";
            columnB[6] = "count";
            columnB[7] = "genre";
            strBook = new String[books.size()][8];
            for(int i = 0; i<books.size(); i++){
                strBook[i][0] = String.valueOf(books.get(i).getId());
                strBook[i][1] = books.get(i).getName();
                strBook[i][2] = books.get(i).getAuthor();
                strBook[i][3] = String.valueOf(books.get(i).getYear());
                strBook[i][4] = String.valueOf(books.get(i).getRating());
                strBook[i][5] = String.valueOf(books.get(i).getPrice());
                strBook[i][6] = String.valueOf(books.get(i).getCount());
                strBook[i][7] = books.get(i).getGenre();
            }

            JTable tableB = new JTable(strBook,columnB);
            JScrollPane pane = new JScrollPane(tableB);
            getContentPane().add(pane,BorderLayout.CENTER);
            tableB.setSize(600,300);
            tableB.setBackground(new java.awt.Color(199, 229,255));
            rem.add(tableB);
        }

    }

    public void tableBookShow() throws IOException, ClassNotFoundException {
        oos.writeObject(new Request("VIEW_BOOKS"));
        Reply rep = (Reply) ois.readObject();
        books = rep.getBooks();
        String[][] strBook = null;
        String[] columnB = null;
        if(books.size()!=0){
            columnB = new String[8];
            columnB[0] =  "id";
            columnB[1] = "name";
            columnB[2] = "author";
            columnB[3] = "year";
            columnB[4] = "rating";
            columnB[5] = "price";
            columnB[6] = "count";
            columnB[7] = "genre";
            strBook = new String[books.size()][8];
            for(int i = 0; i<books.size(); i++){
                strBook[i][0] = String.valueOf(books.get(i).getId());
                strBook[i][1] = books.get(i).getName();
                strBook[i][2] = books.get(i).getAuthor();
                strBook[i][3] = String.valueOf(books.get(i).getYear());
                strBook[i][4] = String.valueOf(books.get(i).getRating());
                strBook[i][5] = String.valueOf(books.get(i).getPrice());
                strBook[i][6] = String.valueOf(books.get(i).getCount());
                strBook[i][7] = books.get(i).getGenre();
            }

            JTable tableB = new JTable(strBook,columnB);
            JScrollPane pane = new JScrollPane(tableB);
            getContentPane().add(pane,BorderLayout.CENTER);
            tableB.setSize(600,300);
            tableB.setBackground(new java.awt.Color(199, 229,255));
            shb.add(tableB);
        }
    }

    public void signIn(){
        jp = new JPanel(null);
        jp.setBounds(0,0,600,600);
        jp.setBackground(new java.awt.Color(199, 229,255));
        jp.setVisible(true);
        add(jp);

        JLabel llab = new JLabel("Enter login: ");
        llab.setBounds(50, 50, 100, 50);
        llab.setBackground(Color.white);
        jp.add(llab);
        JTextField lf = new JTextField(10);
        lf.setBounds(150, 50, 100, 50);
        jp.add(lf);

        JLabel plab = new JLabel("Enter password: ");
        plab.setBounds(50, 100, 100, 50);
        plab.setBackground(Color.white);
        jp.add(plab);
        JPasswordField pf = new JPasswordField(10);
        pf.setBounds(150, 100, 100, 50);
        jp.add(pf);

        JButton ok = new JButton("Login");
        ok.setBounds(150, 200, 80, 30);
        ok.setBackground(Color.white);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = lf.getText();
                String password = pf.getText();

                try {
                    oos.writeObject(new Request("CHECK",login,password));

                    Request req = (Request) ois.readObject();

                    if(req.getCode().equals("ADMIN")){
                        u = req.getU();
                        jp.setVisible(false); //sign in
                        adminMenu();

                    }
                    else if(req.getCode().equals("USER")){
                        u = req.getU();
                        jp.setVisible(false);
                        userMenu();
                        id_u = req.getId();

                    }
                    else if(req.getCode().equals("INCORRECT")){
                        JOptionPane.showMessageDialog(jp,"INCORRECT");
                    }
                    lf.setText(null);
                    pf.setText(null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        jp.add(ok);

        JButton backS = new JButton("back");
        backS.setBounds(150,230,80,30);
        backS.setBackground(Color.white);
        backS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setVisible(false);
                main.setVisible(true);

                lf.setText(null);
                pf.setText(null);
            }
        });
        jp.add(backS);
    }

    public void signUp(){
        suc= new JPanel(null);
        suc.setBounds(0,0,600,600);
        suc.setBackground(new java.awt.Color(199, 229,255));
        suc.setVisible(true);
        add(suc);

        JLabel l = new JLabel("Login");
        l.setBounds(50, 50, 100, 45);
        l.setBackground(Color.white);
        suc.add(l);

        JLabel p = new JLabel("Password");
        p.setBounds(50, 100, 100, 45);
        p.setBackground(Color.white);
        suc.add(p);

        JTextField lWrite = new JTextField(10);
        lWrite.setBounds(110, 50, 100, 45);
        suc.add(lWrite);

        JTextField pWrite = new JPasswordField(10);
        pWrite.setBounds(110, 100, 100, 45);
        suc.add(pWrite);

        JButton save = new JButton("sign up");
        save.setBounds(200,300,80,25);
        save.setBackground(Color.white);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = lWrite.getText();
                String password = pWrite.getText();
                try {
                    oos.writeObject(new Request("SAVE",login,password));

                    Reply rep = (Reply) ois.readObject();

                    if(rep.getCode().equals("SUCCESS")){
                        JOptionPane.showMessageDialog(suc,"SUCCESS");
                    }
                    else
                        JOptionPane.showMessageDialog(suc,"INVALID");

                    lWrite.setText(null);
                    pWrite.setText(null);

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });
        suc.add(save);

        JButton backM = new JButton("back");
        backM.setBounds(200,350,80,25);
        backM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suc.setVisible(false);
                main.setVisible(true);

                lWrite.setText(null);
                pWrite.setText(null);
            }
        });
        backM.setBackground(Color.white);
        suc.add(backM);
    }

}


