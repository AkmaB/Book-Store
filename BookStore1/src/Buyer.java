import javax.swing.plaf.basic.BasicButtonUI;
import java.util.ArrayList;

public class Buyer {
    private Long id_u;
    private Long id_b;
    private String nameB;
    private String nameU;
    private int count;
    private double price;
    private double inc;
    private ArrayList<Buyer> buyers = null;

    public Buyer(){
    }

    public Buyer(Long id_u, Long id_b, String nameB, String nameU) {
        this.id_u = id_u;
        this.id_b = id_b;
        this.nameB = nameB;
        this.nameU = nameU;
    }

    public void addBuyer(Buyer b){
        buyers = new ArrayList<>();
        buyers.add(b);
    }

    public ArrayList<Buyer> getBuyer(){
        return buyers;
    }

    public Buyer(Long id_u, Long id_b, int count) {
        this.id_u = id_u;
        this.id_b = id_b;
        this.count = count;
    }

    public Buyer(String nameU, String nameB,int count, double price){
        this.nameU = nameU;
        this.nameB = nameB;
        this.inc = this.count*this.price;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(ArrayList<Buyer> buyers) {
        this.buyers = buyers;
    }

    public Long getId_u() {
        return id_u;
    }

    public void setId_u(Long id_u) {
        this.id_u = id_u;
    }

    public Long getId_b() {
        return id_b;
    }

    public void setId_b(Long id_b) {
        this.id_b = id_b;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getInc() {
        return inc;
    }

    public void setInc(double inc) {
        this.inc = inc;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id_u=" + id_u +
                ", id_b=" + id_b +
                ", nameB='" + nameB + '\'' +
                ", nameU='" + nameU + '\'' +
                '}';
    }
}
