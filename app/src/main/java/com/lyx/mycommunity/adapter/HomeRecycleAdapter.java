package com.lyx.mycommunity.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.lyx.mycommunity.bean.NoteInfo;
import com.lyx.shoppingcity.R;

import java.util.List;

public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.MyViewHolder> {
    private Context context;
    private List<NoteInfo> list;
    private View inflater;

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView rand_icon;
        TextView item_note_title;
        // TextView item_note_date;
        TextView des;
        TextView pre;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_note_title = (TextView) itemView.findViewById(R.id.item_note_title);
            rand_icon = itemView.findViewById(R.id.rand_icon);
            // item_note_date = (TextView) itemView.findViewById(R.id.item_note_date);
            des = (TextView) itemView.findViewById(R.id.des);
            pre = (TextView) itemView.findViewById(R.id.pre);

        }


    }
    public void refreshDataSet(){
        notifyDataSetChanged();
    }
    //构造方法，传入数据
    public HomeRecycleAdapter(Context context, List<NoteInfo> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        inflater = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HomeRecycleAdapter.MyViewHolder holder, int position) {
        //将数据和控件绑定
        NoteInfo noteInfo = list.get(position);
        byte [] imgData = list.get(position).getPhoto();
        Bitmap bm  = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
        holder.rand_icon.setImageBitmap(bm);

        holder.item_note_title.setText(list.get(position).getTitle());

        holder.des.setText(list.get(position).getDes());
        holder.pre.setText("热度："+list.get(position).getPre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

