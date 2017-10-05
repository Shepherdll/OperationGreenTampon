package com.example.leakypete.operationgreentampon.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Peter on 5/20/2017.
 */

public class User
{
    private String Name;
    private String Email;
    private String image;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    String about = "";

    public User() {

    }

    public User(String name, String email, String pic) {
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        this.Name = name;
        this.Email = email;
        this.image = pic;
    }

    public String getName() {return Name;}

    public String getEmail() {
        return Email;
    }



    public String getPic() {return image;}


}
