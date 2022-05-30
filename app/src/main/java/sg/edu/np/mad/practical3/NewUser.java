package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        EditText myCreateUsername = findViewById(R.id.newUsername);
        EditText myCreatePassword= findViewById(R.id.newPassword);
        Button createButton = findViewById(R.id.createButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        createButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User dbData = dbHandler.findUser(myCreateUsername.getText().toString());
                if(dbData == null){
                    long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                    int userID = (int)number;
                    User dbUserData = new User();
                    dbUserData.setName(myCreateUsername.getText().toString());
                    dbUserData.setPassword(myCreatePassword.getText().toString());
                    dbUserData.setDescription(myCreateUsername.getText().toString()+userID);
                    dbUserData.setId(userID);
                    dbUserData.setFollowed(false);
                    dbHandler.addUser(dbUserData);
                    Toast.makeText(NewUser.this,"User Created!",Toast.LENGTH_SHORT).show();
                    Intent myCreateIntent = new Intent(NewUser.this,Login.class);
                    startActivity(myCreateIntent);
                }
                else{
                    Toast.makeText(NewUser.this,"User already exist! Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewUser.this,"Cancelled", Toast.LENGTH_SHORT).show();
                Intent myCancelIntent = new Intent(NewUser.this,Login.class);
                startActivity(myCancelIntent);
            }
        });
    }
}