/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

/**
 *
 * @author pc
 */
public class Contacts {
    private int id;
    private String fname ;
    private String mname ;
    private String lname ;
    private String email ;
    private String phone ;
    

    public Contacts(int id, String fname, String mname, String lname, String email, String phone) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    
    
}
