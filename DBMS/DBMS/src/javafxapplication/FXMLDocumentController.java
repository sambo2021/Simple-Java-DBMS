package javafxapplication;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable { 
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label11;
    @FXML
    private Label label111;
    @FXML
    private Label label1111;
    @FXML
    private Label label11111;
    @FXML
    private TableColumn<Contacts, Integer> IDcol;
    @FXML
    private TableColumn<Contacts, String> fnamecol;
    @FXML
    private TableColumn<Contacts, String> midnamecol;
    @FXML
    private TableColumn<Contacts, String> lastnamecol;
    @FXML
    private TableColumn<Contacts, String> emailcol;
    @FXML
    private TableColumn<Contacts, String> phonecol;
    @FXML
    private TableView<Contacts> contactsList;
    @FXML
    private Button insert;
    @FXML
    private TextField id;
    @FXML
    private TextField fname;
    @FXML
    private TextField mname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button first;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Button last;
    Connection conn ;
    ResultSet rs;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == insert){
            insertButton(); 
            showContactsTable();
            firstRow();
        }
        if(event.getSource() == update){
            updateButton(); 
            showContactsTable();
        }
        if(event.getSource() == delete){
            deleteButton(); 
            showContactsTable();
            firstRow();
        }
        if(event.getSource() == first){
            firstRow();
        }
        if(event.getSource() == last){
            lastRow();
        }
         if(event.getSource() == next){
            nextRow();
        }
         if(event.getSource() == prev){
            previousRow();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rs = RSback(); 
        showContactsTable();
    }    
    public  Connection ConnectDB(){
         try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB","root", ""); 
            return conn; 
        }catch(SQLException ex){
            ex.printStackTrace();
            return null; 
        }
    }
    public ResultSet RSback(){
        Statement statement;
        try {
            statement = ConnectDB().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery("SELECT * FROM Contacts");
        } catch (SQLException ex) {
            return null;
        }
        
        
    } 
    public  void insertButton(){
        try{
        String sql = "INSERT INTO Contacts(fname,mname,lname,email,phone)"
                    +"Values (?,?,?,?,?)" ; 
        Connection conn=ConnectDB();
        PreparedStatement statement = conn.prepareStatement(sql); 
        statement.setString(1,fname.getText());
        statement.setString(2,mname.getText());
        statement.setString(3,lname.getText());
        statement.setString(4,email.getText());
        statement.setString(5,phone.getText());
        int rows = statement.executeUpdate(); 
        conn.close() ; 
        fname.clear();mname.clear();lname.clear();email.clear();phone.clear();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    public  void updateButton(){
         try{
        String sql = "UPDATE Contacts SET  fname ='"+fname.getText()+
                     "',mname ='"+mname.getText()+"',lname ='"+lname.getText()+
                      "',email ='"+email.getText()+"',phone='"+phone.getText()+
                    "'WHERE id = '"+id.getText()+"' "; 
        Connection conn=ConnectDB();
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql); 
        conn.close() ; 
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    public  void deleteButton(){
         try{
        String sql = "DELETE FROM Contacts WHERE id = '"+id.getText()+"' "; 
        Connection conn=ConnectDB();
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql); 
        conn.close() ; 
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    public  void firstRow(){
        try {
            rs.first(); 
            id.setText(rs.getString(1));
            fname.setText(rs.getString(2));
            mname.setText(rs.getString(3));
            lname.setText(rs.getString(4));
            email.setText(rs.getString(5));
            phone.setText(rs.getString(6));     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public  void nextRow(){
        try { 
            if(!rs.isLast()){
            rs.next(); 
            id.setText(rs.getString(1));
            fname.setText(rs.getString(2));
            mname.setText(rs.getString(3));
            lname.setText(rs.getString(4));
            email.setText(rs.getString(5));
            phone.setText(rs.getString(6));
            }
            else if(rs.isLast()){
             rs.first(); 
             id.setText(rs.getString(1));
             fname.setText(rs.getString(2));
             mname.setText(rs.getString(3));
             lname.setText(rs.getString(4));
             email.setText(rs.getString(5));
             phone.setText(rs.getString(6));
            }
            }catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }
    public  void previousRow(){
        try {   
            if(!rs.isFirst()){
            rs.previous();
            id.setText(rs.getString(1));
            fname.setText(rs.getString(2));
            mname.setText(rs.getString(3));
            lname.setText(rs.getString(4));
            email.setText(rs.getString(5));
            phone.setText(rs.getString(6));
             //ConnectDB().close();
            }
            else if(rs.isFirst()){
            rs.last();
            id.setText(rs.getString(1));
            fname.setText(rs.getString(2));
            mname.setText(rs.getString(3));
            lname.setText(rs.getString(4));
            email.setText(rs.getString(5));
            phone.setText(rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public  void lastRow(){
        try {
            Statement statement = ConnectDB().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT * FROM Contacts");
            while(rs.next()){ 
            id.setText(rs.getString(1));
            fname.setText(rs.getString(2));
            mname.setText(rs.getString(3));
            lname.setText(rs.getString(4));
            email.setText(rs.getString(5));
            phone.setText(rs.getString(6));
             ConnectDB().close();
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());        }
    }
    public  ObservableList<Contacts> contactsList(){
      ObservableList<Contacts> conactsList = FXCollections.observableArrayList();
      String query = "SELECT * FROM Contacts" ; 
      Statement st;
      ResultSet rs ;
        try { 
            st = ConnectDB().createStatement();
            rs = st.executeQuery(query); 
            Contacts _contact ;
            while(rs.next()){
            _contact = new Contacts(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));  
            conactsList.add(_contact); 
            }
            ConnectDB().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
       return  conactsList ; 
   }
    public  void showContactsTable(){
        ObservableList<Contacts> List =contactsList() ;
        IDcol.setCellValueFactory(new PropertyValueFactory<Contacts,Integer>("id"));
        fnamecol.setCellValueFactory(new PropertyValueFactory<Contacts,String>("fname"));
        midnamecol.setCellValueFactory(new PropertyValueFactory<Contacts,String>("mname"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<Contacts,String>("lname"));
        emailcol.setCellValueFactory(new PropertyValueFactory<Contacts,String>("email"));
        phonecol.setCellValueFactory(new PropertyValueFactory<Contacts,String>("phone"));
        contactsList.setItems(List);
    }
    
}
