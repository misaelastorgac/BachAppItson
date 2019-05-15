package itson.mx.bachapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    private DatabaseReference usuariosDB = FirebaseDatabase.getInstance().getReference();

    public String
        fName,
        lName,
        phone,
        email,
        city,
        password;

    public User() {

    }

    public User(String fName, String lName, String phone, String email, String city, String password) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.password = password;
    }

    private void createUser(String userId, String fName, String lName, String phone, String email,
                            String city, String password) {
        User user = new User(fName, lName, phone, email, city, password);

        usuariosDB.child("users").child(userId).child("first_name").setValue(fName);
        usuariosDB.child("users").child(userId).child("last_name").setValue(lName);
        usuariosDB.child("users").child(userId).child("phone").setValue(phone);
        usuariosDB.child("users").child(userId).child("email").setValue(email);
        usuariosDB.child("users").child(userId).child("city").setValue(city);
        usuariosDB.child("users").child(userId).child("password").setValue(password);
    }

}
