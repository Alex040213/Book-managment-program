import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverConnectivity {
    private  String userName = "";
    private  String password = "";
    private  String url = "";
    private Connection con;
    public DriverConnectivity( Connection con) throws  Exception{
        this.con = con;
    }
    public String[][] getBooks() throws SQLException{
        ArrayList<List<String>> s =new ArrayList<List<String>>();
        Statement st = con.createStatement();
        String query = "select * from knigi";
        ResultSet rs = st.executeQuery(query);
        String[][] sarr = new String[0][0];

        while (rs.next()){
            String invenBr = String.valueOf(rs.getInt("inventarenBroj"));
            String ime = rs.getString("Ime");
            String avtor = rs.getString("Avtor");
            String izd = rs.getString("Izdavatel");
            String godIzd = rs.getString("GodinaNaIzdavanje");
            String cena = String.valueOf(rs.getInt("Cena"));
            s.add(List.of(invenBr,ime,avtor,izd,godIzd,cena));
        }
        sarr = s.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return sarr;




    }
    public void getBook(String quer) throws SQLException {
            Statement st = con.createStatement();
            String query = quer;
            ResultSet rs = st.executeQuery(query);
            String userData ="";
                System.out.println("IME | AVTOR | IZDAVATEL | GODINA NA IZDAVANJE | CENA");
                int count=1;
                while(rs.next()){
                    userData = count+" | "+rs.getString(2) +" | "+rs.getString(3)+" | "+ rs.getString(4)+" | "+rs.getString(5) +" | "+rs.getInt(6);
                    System.out.println(userData);
                    count++;
                }
        System.out.println();
    }
    public void insertBook(Books book) throws SQLException{
        String query = "insert into knigi values ("+book.getInventarenBroj()+",'"+book.getIme()+"','"+book.getAvtor()+"','"+book.getIzdavatel()+"','"+book.getGodinaNaIzdavanje()+"',"+book.getCena()+");";
        PreparedStatement st = con.prepareStatement(query);
        int count = st.executeUpdate(query);
        System.out.println(count+ " row/s affected");
    }

}
