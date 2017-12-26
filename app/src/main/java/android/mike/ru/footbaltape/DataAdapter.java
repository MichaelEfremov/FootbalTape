package android.mike.ru.footbaltape;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Michael on 26.12.2017.
 */

// Адаптер для RecyclerView
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private List<Content> mContents;
    private ViewListener mViewListener;

    public DataAdapter(Context context, List<Content> contents) {
        mContext = context;
        mContents = contents;
    }

    public ViewListener setViewListener(ViewListener viewListener){
        return this.mViewListener = viewListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mContents.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mContents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CardView cvItem;

        public ViewHolder(View item) {
            super(item);
            tvTitle = (TextView)item.findViewById(R.id.tvTitle);
            cvItem = (CardView)item.findViewById(R.id.cvItem);
            cvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewListener.onClick(getLayoutPosition());
                }
            });
        }
    }
}
