package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements SelectListener{
    private String TAG = "Main activity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.v(TAG,"On Create!");
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        ArrayList<User> userList = (ArrayList<User>) bundle.getSerializable("userList");

        RecyclerView recyclerView = findViewById(R.id.recycler);
        Adapter mAdaptor = new Adapter(userList,this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager((mLayoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdaptor);

    }

    @Override
    public void onItemClicked(User user) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this).create();
        alertDialog.setTitle("Profile");
        alertDialog.setMessage(user.getName());
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(MainActivity2.this,MainActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("User", (Serializable) user);
                myIntent.putExtra("Bundle",args);
                startActivity(myIntent);
            }
        });
        alertDialog.show();
    }

}