package com.example.recyclerviewpractis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder> {
    ArrayList<CoffeeModel> ListCoffee;
    ITotalPrice iTotalPrice;

    public CoffeeAdapter(ArrayList<CoffeeModel> listCoffee,ITotalPrice iTotalPrice) {
        ListCoffee = listCoffee;
        this.iTotalPrice = iTotalPrice;
    }

    @NonNull
   // @org.jetbrains.annotations.NotNull
    @Override
    public CoffeeHolder onCreateViewHolder(@NonNull /*@org.jetbrains.annotations.NotNull*/ ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coffee,parent,false);
       CoffeeHolder coffeeHolder=new CoffeeHolder(view);
        return coffeeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull /*@org.jetbrains.annotations.NotNull*/ CoffeeAdapter.CoffeeHolder holder, int position) {

        CoffeeModel coffeeModel=ListCoffee.get(position);
        holder.textViewName.setText(coffeeModel.getName());
        Picasso.get().load(coffeeModel.getImageUrl()).into(holder.imageViewUrl);
        holder.textViewPrice.setText(coffeeModel.getPrice()+" EGP");
        holder.textViewCounter.setText(coffeeModel.quantity+"");

        holder.imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListCoffee.get(position).quantity++;
                notifyItemChanged(position);
                Calculate();
            }
        });
        holder.imageViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ListCoffee.get(position).quantity==0 )return;
                ListCoffee.get(position).quantity--;
                notifyItemChanged(position);
                Calculate();
            }
        });


    }
    //----------
   private void Calculate(){
      int totalPrice=0;
       for (CoffeeModel coffee: ListCoffee) {
           totalPrice +=coffee.getPrice() * coffee.quantity;
       }
       iTotalPrice.onPriceChange(totalPrice);
   }

    @Override
    public int getItemCount() {
        return ListCoffee.size();
    }

    class CoffeeHolder extends RecyclerView.ViewHolder {
        ImageView imageViewUrl,imageViewPlus,imageViewMinus;
        TextView textViewName,textViewPrice,textViewCounter;
        public CoffeeHolder(@NonNull  View itemView) {
            super(itemView);
            imageViewUrl=itemView.findViewById(R.id.iv_image);
            imageViewMinus=itemView.findViewById(R.id.iv_minus);
            imageViewPlus=itemView.findViewById(R.id.iv_add);
            textViewName=itemView.findViewById(R.id.tv_name);
            textViewPrice=itemView.findViewById(R.id.tv_price);
            textViewCounter=itemView.findViewById(R.id.tv_counter);


        }
    }


    //    ArrayList<CoffeeModel> listCoffee;
//
//    public CoffeeAdapter(ArrayList<CoffeeModel> listCoffee) {
//        this.listCoffee = listCoffee;
//    }
//
//    @NonNull
//
//    @Override
//    public CoffeeHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coffee,parent,false);
//        CoffeeHolder coffeeHolder=new CoffeeHolder(view);
//        return coffeeHolder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CoffeeAdapter.CoffeeHolder holder, int position) {
//
//        CoffeeModel coffeeModel=listCoffee.get(position);
//        holder.textviewCoffeeName.setText(coffeeModel.getName());
//        holder.textviewPrice.setText(coffeeModel.getPrice()+" EGP");
//        holder.textviewCounter.setText(coffeeModel.quantity+"");
//
//        Picasso.get().load(coffeeModel.getImageUrl()).into(holder.imageviewCoffee);
//
//        holder.imageviewAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listCoffee.get(position).quantity++;
//                notifyItemChanged(position);
//
//            }
//        });
//        holder.imageviewRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listCoffee.get(position).quantity== 0 ) return;
//                listCoffee.get(position).quantity--;
//                notifyItemChanged(position);
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listCoffee.size();
//    }
//
//    class CoffeeHolder extends RecyclerView.ViewHolder {
//        // بعرف الفيوز
//        ImageView imageviewCoffee, imageviewRemove, imageviewAdd;
//        TextView textviewCoffeeName,textviewCounter,textviewPrice;
//
//        public CoffeeHolder(@NonNull  View itemView) {
//            super(itemView);
//            imageviewCoffee=itemView.findViewById(R.id.item_coffee_iv);
//            imageviewRemove=itemView.findViewById(R.id.iv_minus);
//            imageviewAdd=itemView.findViewById(R.id.iv_add);
//            textviewCoffeeName=itemView.findViewById(R.id.name_tv);
//            textviewCounter=itemView.findViewById(R.id.tv_counter);
//            textviewPrice=itemView.findViewById(R.id.tv_price);
//
//
//        }
//    }
}
