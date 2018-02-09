package com.yueyue.studentinfomanager.modules.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.modules.edit.ui.EditActivity;
import com.yueyue.studentinfomanager.modules.main.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：StudentInfoManager
 * 创建人：Double2号
 * 创建时间：2016/7/26 10:25
 * 修改备注：
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Person> mDataList;
    private Context mContext;

    public SearchAdapter(Context context,
                         List<Person> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_search, parent, false);

        return new ViewHolder(views);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llSearch;
        TextView tvNumber;
        TextView tvName;

        ViewHolder(View itemView) {
            super(itemView);
            llSearch = (LinearLayout) itemView.findViewById(R.id.ll_search);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_search_number);
            tvName = (TextView) itemView.findViewById(R.id.tv_search_name);
        }

    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, final int position) {

        final Person person = mDataList.get(position);
        holder.tvNumber.setText(person.number);
        holder.tvName.setText(person.name);

        holder.llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.launch(mContext, EditActivity.TYPE_EDIT, person);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


}
