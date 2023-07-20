import java.awt.print.Book;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knigi","myuser","19304045");
        DriverConnectivity dc = new DriverConnectivity(con);
//        boolean cond = true;
//        while (cond) {
//            System.out.println("1.Prebaraj Kniga\n2.Vnesi Kniga\n3.'D' ZA IZLEZ");
//            Scanner sc = new Scanner(System.in);
//            String input = sc.nextLine().toLowerCase();
//
//            if(input.equals("1")){
//                prebaraj(dc);
//            }if(input.equals("2")){
//                dc.insertBook(kreirajKniga());
//            }if(input.equals("d")){
//                cond =false;
//            }
//        }
//        con.close();

        MyFrame frame = new MyFrame(dc);
    }

    public static Books kreirajKniga(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Vnesi podatoci za knigata");
        String ime,avtor,izdavatel,godinaNaIzd;
        int invBr,cena;
        System.out.println("Ime na knigata:");
        ime = sc.nextLine();
        System.out.println("Avtor:");
        avtor = sc.nextLine();
        System.out.println("Izdavatel:");
        izdavatel = sc.nextLine();
        System.out.println("Godina na izdavanje");
        godinaNaIzd = sc.nextLine();
        System.out.println("Cena (VO MKD)");
        cena = sc.nextInt();
        System.out.println("Inventaren broj");
        invBr = sc.nextInt();
        Books book = new Books(invBr,ime,avtor,izdavatel,godinaNaIzd,cena);
        System.out.println(book);
        return book;

    }
    public static Books prebaraj(DriverConnectivity dc) throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Po shto sakate da pobarate kniga?");
        System.out.println("1.Ime");
        System.out.println("2.Avtor");
        System.out.println("3.Cena");
        System.out.println("4.Izdavatel");
        switch (sc.nextLine()){
            case "1":
                System.out.println("Vnesete ime");
                dc.getBook("select * from knigi where Ime='"+sc.nextLine()+"';");
                break;
            case "2":
                System.out.println("Vnesete Avtor");
                dc.getBook("select * from knigi where Avtor='"+sc.nextLine()+"';");

                break;
            case "3":
                System.out.println("Vnesete cena");
                dc.getBook("select * from knigi where Cena="+sc.nextLine()+";");

                break;
            case "4":
                System.out.println("Vnesete izdavatel");
                dc.getBook("select * from knigi where Izdavatel='"+sc.nextLine()+"';");
                break;
            default:
                System.out.println("Nevaliden vnes probajte pak:");
                prebaraj(dc);
                break;
        }
        return new Books();
    }
}