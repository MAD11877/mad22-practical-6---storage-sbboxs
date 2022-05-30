package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private String TAG = "List Activity";
    DBHandler dbHandler = new DBHandler(ListActivity.this,null,null,1);
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG,"On Create!");


        ImageView imageButton = findViewById(R.id.imageView);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Refer to week 3 activity for another way of create Alert Dialog
            public void onClick(View v) {

                Log.v(TAG, "Image Pressed");
                AlertDialog alertDialog = new AlertDialog.Builder(ListActivity.this).create();
                alertDialog.setTitle("Profile");
                alertDialog.setMessage("MADness");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }
                    });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "View",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                checkNumberOfUserExists();
                                storeUserDataToArray();
                                Intent myIntent = new Intent(ListActivity.this,MainActivity2.class);
                                Bundle args = new Bundle();
                                args.putSerializable("userList", (Serializable) userList);
                                myIntent.putExtra("Bundle",args);
                                startActivity(myIntent);

                                //If got multiple data to pass, eg pass username and password to next activity
                                //Use Bundle myBundle = new Bundle();
                                //myBundle.PutString("Username", user.getUsername());
                                //myBundle.Put String("Password", user.getPassword());
                                //Intent myIntent = new Intent(ListActivity.this,MainActivity.class);
                                //myIntent.putExtras(myBundle);  **Take note of extras and extra
                                //If putExtra(s) means passing multiple data, if putExtra means passing one data
                            }
                        });
                alertDialog.show();
            }
        });
    }
    private void checkNumberOfUserExists(){
        Cursor cursor = dbHandler.readAllData();
        if (cursor.getCount() < 5) {
            Toast.makeText(this, "Auto creating more user", Toast.LENGTH_LONG).show();
            for (int i = 0; i < 15; i++) {
                long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                int userID = (int)number;
                String userName = "Name" + userID;
                String password = "Name" + userID;
                String userDescription = "Description" + number;
                User dbData = dbHandler.findUser(userName);
                if(dbData == null) {
                    User dbUserData = new User();
                    dbUserData.setName(userName);
                    dbUserData.setPassword(password);
                    dbUserData.setDescription(userDescription);
                    dbUserData.setId(userID);
                    dbUserData.setFollowed(false);
                    dbHandler.addUser(dbUserData);
                }
                else{
                    continue;
                }
            }
        }
        else{
            Toast.makeText(this,"Enough User for Test Case", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeUserDataToArray() {
        Cursor cursor = dbHandler.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                User user = new User();
                user.setName(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                user.setDescription(cursor.getString(2));
                user.setId(Integer.valueOf(cursor.getString(3)));
                user.setFollowed(Boolean.parseBoolean(cursor.getString(4)));
                userList.add(user);
            }
        }
    }
}
