package com.mygt.vrapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mygt.vrapp.R;
import com.mygt.vrapp.api.bean.GameVo;

import java.util.List;

/**
 * Created on 2017/5/14.
 *
 * @author lkuan
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameHolder> {

    private List<GameVo> gameVos;
    private OnGameListClickListener listener;

    public GameListAdapter(List<GameVo> gameVos){
        this.gameVos = gameVos;
    }

    @Override
    public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        return new GameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameHolder holder, int position) {
        GameVo gameVo = gameVos.get(position);
        holder.name.setText(gameVo.getGamename());
        long apkSize = Long.getLong(gameVo.getApksize());
        String appSize = 1.0 * ( apkSize >> 10 ) + "MB";
        holder.memory.setText(appSize);

        Glide.with(holder.itemView.getContext()).load("").into(holder.icon);

        final int p = position;
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listener){
                    listener.onGameListClick(p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == gameVos ? 0 : gameVos.size();
    }

    class GameHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView name;
        private TextView memory;
        private Button down;

        private GameHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            name = (TextView) itemView.findViewById(R.id.name);
            memory = (TextView) itemView.findViewById(R.id.memory);
            down = (Button) itemView.findViewById(R.id.down);
        }
    }

    public interface OnGameListClickListener{
        void onGameListClick(int position);
    }

    public void setOnGameListClickListener(OnGameListClickListener listener){
        this.listener = listener;
    }
}
