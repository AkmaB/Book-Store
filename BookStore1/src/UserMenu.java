//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//public class UserMenu extends Container {
//   private Shop shop = null;
//    private Socket socket = null;
//    private ObjectOutputStream oos = null;
//    private ObjectInputStream ois = null;
//    private JFrame userMenu;
//    private JButton addb, showb, buyb, back, exit; //обмен книгами, отзывы
//
//    public UserMenu(Socket socket){
//        //this.shop = shop;
//        this.socket = socket;
//        try {
//            oos = new ObjectOutputStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //User Menu Panel
//        JPanel um = new JPanel();
//        um.setBounds(0,0,500,500);
//        um.setVisible(true);
//        add(um);
//
//        JButton addB = new JButton("ADD BOOK");
//        addB.setBounds(150,50,200,50);
//        addB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                um.setVisible(false);
//                jf.setVisible(true);
//
//            }
//        });
//        um.add(addB);
//
//        JButton show = new JButton("SHOW ALL BOOKS");
//        show.setBounds(150,100,200,50);
//        show.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                um.setVisible(false);
//                shb.setVisible(true);
//            }
//        });
//        um.add(show);
//
//
////        userMenu= new JFrame("ADMIN MENU");
////        userMenu.setSize(500,500);
////        userMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        userMenu.setResizable(false);
////        userMenu.setLayout(null);
////        userMenu.setLocationRelativeTo(null);
////        userMenu.setVisible(true);
////
////        addb = new JButton("ADD BOOK");
////        addb.setBounds(150,50,200,50);
////        userMenu.add(addb);
////
////        showb = new JButton("SHOW ALL GOODS");
////        showb.setBounds(150,100,200,50);
////        userMenu.add(showb);
////
////        buyb = new JButton("BUY GOOD");
////        buyb.setBounds(150,150,200,50);
////        userMenu.add(buyb);
////
////        back = new JButton("BACK");
////        back.setBounds(300,350,150,50);
////        userMenu.add(back);
////
////        exit = new JButton("EXIT");
////        exit.setBounds(300,400,150,50);
////        userMenu.add(exit);
////
////        addb.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                AddBook ab = new AddBook(socket);
////            }
////        });
////
////
////        showb.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                userMenu.setVisible(false);
////                ShowBooks shb = new ShowBooks(socket);
////            }
////        });
////
////        buyb.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                //db table user_book user id ---> idbook
////            }
////        });
////
////        back.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                userMenu.setVisible(false);
////                try {
////                    shop = new Shop(socket);
////                } catch (IOException ex) {
////                    ex.printStackTrace();
////                }
////            }
////        });
////
////        exit.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                try {
////                    oos.writeObject(new Request("EXIT"));
////                    System.exit(0);
////                } catch (IOException ex) {
////                    ex.printStackTrace();
////                }
////            }
////        });
//
//
//
//    }
//}
