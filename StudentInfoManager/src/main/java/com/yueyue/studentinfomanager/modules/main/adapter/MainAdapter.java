package com.yueyue.studentinfomanager.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.modules.edit.ui.EditActivity;
import com.yueyue.studentinfomanager.modules.main.domain.Person;

import java.util.ArrayList;

/**
 * 项目名称：StudentInfoManager
 * 创建人：Double2号
 * 创建时间：2016/7/26 10:25
 * 修改备注：
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<Person> mDataList;
    private Context mContext;

    public MainAdapter(Context context,
                       ArrayList<Person> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_show, parent, false);
        return new MainAdapter.ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        final Person person = mDataList.get(position);
        holder.tvNumber.setText(person.number);
        holder.tvName.setText(person.name);
        holder.tvGender.setText(person.gender);

        holder.cvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.launch(mContext, EditActivity.TYPE_EDIT, person);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvShow;
        TextView tvNumber;
        TextView tvName;
        TextView tvGender;

        public ViewHolder(View itemView) {
            super(itemView);
            cvShow = (CardView) itemView.findViewById(R.id.cv_show);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_show_number);
            tvName = (TextView) itemView.findViewById(R.id.tv_show_name);
            tvGender = (TextView) itemView.findViewById(R.id.tv_show_gender);
        }

    }


    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


}
