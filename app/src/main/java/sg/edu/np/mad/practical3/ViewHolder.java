package sg.edu.np.mad.practical3;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder {
    TextView userName;
    TextView userDescription;
    CardView userIcon;
    public ViewHolder(View itemView){
        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        userDescription = itemView.findViewById(R.id.userDescription);
        userIcon = itemView.findViewById(R.id.userIcon);
    }

}