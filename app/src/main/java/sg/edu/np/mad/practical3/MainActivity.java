package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main activity";
    DBHandler dbHandler = new DBHandler(MainActivity.this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting data from previous activity
//        Intent receiving = getIntent();
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User currentUser = (User) bundle.getSerializable("User");
        //If get data from a bundle, we have to get the detail from data one by one
        //eg. the bundle contain username and password
        //then we get username = receiving.getStringExtra("Username");
        //password = receiving.getStringExtra("Password");
        Log.v(TAG,"On Create!");
        //Setting user detail

        TextView idInfo = findViewById(R.id.idDisplay);
        idInfo.setText("MAD "+currentUser.getId());
        //Listen for follow button
        Button myButton = findViewById(R.id.followButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Follow Button Pressed");
                String buttonText = (String) myButton.getText();
                Log.v(TAG,buttonText);

                Context context = getApplicationContext();
                CharSequence text = "Toast message";

                //Check if followed or not
                if(!currentUser.isFollowed()){
                    myButton.setText("UNFOLLOW");
                    currentUser.setFollowed((true));
                    dbHandler.updateUserData(currentUser);
                    text = "Followed";
                }else if (buttonText == "UNFOLLOW"){
                    currentUser.setFollowed((false));
                    myButton.setText("FOLLOW");
                    text = "Unfollowed";
                }
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
        //Listen for message button
        Button messageButton = findViewById(R.id.messageButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Message Button Pressed");
                Intent myIntent = new Intent(MainActivity.this,MessageGroup.class);
                startActivity(myIntent);
            }
        });
    }
}