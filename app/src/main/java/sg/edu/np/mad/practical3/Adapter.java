package sg.edu.np.mad.practical3;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//take class adapter and extend view holder
public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    //Define type of data that pass by adapter
    ArrayList<User> data;
    SelectListener selectListener;
    public Adapter (ArrayList <User> input, SelectListener listener){
        data = input;
        selectListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false );
        return new ViewHolder(item);
    }

    //Position 是指list里的第几个object
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position){
        String textUserName = data.get(position).getName();
        String textUserDescription = data.get(position).getDescription();
        holder.userName.setText(textUserName);
        holder.userDescription.setText(textUserDescription);
        holder.userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(data.get(position));
            }
        });

    }

    public int getItemCount(){
        return data.size();
    }
}