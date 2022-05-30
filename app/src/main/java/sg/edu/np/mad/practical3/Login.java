package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView newUser = findViewById(R.id.signUpButton);
        newUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent myNewUserIntent =new Intent(Login.this,NewUser.class);
                myNewUserIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); //Prevent multiple launching
                startActivity(myNewUserIntent);
                return false;
            }
        });

        Button myLoginButton = findViewById(R.id.loginButton);
        myLoginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText myUserName = findViewById(R.id.loginUserName);
                EditText myPassword = findViewById(R.id.loginPassword);

                Intent myIntent = new Intent(Login.this, ListActivity.class);
            }
        }));
    }
    public boolean isValidCredentials(String username, String password) {
        User dbData = dbHandler.findUser(username);
        if (dbData == null){
            Toast.makeText(Login.this, "User Does not Exist!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dbData.getName().equals(username) && dbData.getPassword().equals(password)){
                Toast.makeText(Login.this, "Login Success",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
}