package com.example.eng2024hafta13001;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseHelper {
    private DatabaseReference ref;
    public FireBaseHelper(){
        this.ref= FirebaseDatabase.getInstance().getReference("user");
    }



    public void getUser(String userID, final DataStatus status){

        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User usr= snapshot.getValue(User.class);
                status.DataIsLoaded(usr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
public void addUser(User usr){
    String userId = ref.push().getKey();
    usr.setUserid(userId);
    ref.child(userId).setValue(usr);

}

public void updateUser(User usr){
    ref.child(usr.getUserid()).setValue(usr);
}

public void deleteUser(User usr){
ref.child(usr.getUserid()).removeValue();
}

public void getAllUser(final DataStatus status ){
ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        List<User> users= new ArrayList<>();
            for(DataSnapshot ds:snapshot.getChildren()){
                User user= ds.getValue(User.class);
                users.add(user);
            }
        status.DataIsLoaded(users);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

}
}
