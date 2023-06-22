package com.example.addmeal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewListAdapter extends RecyclerView.Adapter<ViewListAdapter.AccountViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList Item_id;
    private ArrayList Category;
    private ArrayList FoodName_Type;
    private ArrayList Address;  //Item_id,Modle_Name, Brand_Type, Processor, Price
    int position;

    ViewListAdapter(Activity activity, Context context, ArrayList Item_id, ArrayList Category, ArrayList FoodName_Type, ArrayList Address){

        this.activity = activity;
        this.context=context;
        this.Item_id=Item_id;
        this.Category=Category;
        this.FoodName_Type=FoodName_Type;
        this.Address=Address;


    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.view_recycler,parent,false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder,@SuppressLint("RecyclerView")  int position) {
        this.position=position;
        holder.tv_category.setText((String.valueOf(Category.get(position))));
        holder.tv_foodName.setText((String.valueOf(FoodName_Type.get(position))));
        holder.tv_address.setText((String.valueOf(Address.get(position))));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override  //adala eka click krma ilaga page ekata nevegate wenwa
            public void onClick(View v) {
                Toast.makeText(context," Account selected ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("Item_id",  String.valueOf(Item_id.get(position)));
                intent.putExtra("Category",  String.valueOf(Category.get(position)));
                intent.putExtra("FoodName_Type",  String.valueOf(FoodName_Type.get(position)));
                intent.putExtra("Address",  String.valueOf(Address.get(position)));

                //context.startActivity(intent);
                activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount(){
        return Item_id.size();
        }

    public class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView tv_foodName, tv_category, tv_address;
        LinearLayout mainLayout;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_category = itemView.findViewById(R.id.tv_category);
            tv_foodName = itemView.findViewById(R.id.tv_foodName);
            tv_address = itemView.findViewById(R.id.tv_address);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}