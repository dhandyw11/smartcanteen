package com.campus.smartcanteen.MenuRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.R;

import java.util.List;

public class MenuTodayAdapter extends RecyclerView.Adapter<MenuTodayAdapter.MenuTodayViewHolder> {
    Context context;
    List<MenuTodayClass>MenuTodayFoodList;

    public MenuTodayAdapter(Context context, List<MenuTodayClass> MenuTodayFoodList){
        this.context = context;
        this.MenuTodayFoodList = MenuTodayFoodList;
    }

    @NonNull
    @Override
    public MenuTodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_today_food_row,parent, false);
        return new MenuTodayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuTodayViewHolder holder, int position) {
        holder.Gambar.setImageResource(MenuTodayFoodList.get(position).getGambar());
        holder.Nama.setText(MenuTodayFoodList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return MenuTodayFoodList.size();
    }

    public static class MenuTodayViewHolder extends RecyclerView.ViewHolder{
        ImageView Gambar;
        TextView Nama;

        public MenuTodayViewHolder(@NonNull View itemView) {

            super(itemView);

            Gambar = (ImageView) itemView.findViewById(R.id.gambar);
            Nama = (TextView) itemView.findViewById(R.id.nama);
        }
    }
}
