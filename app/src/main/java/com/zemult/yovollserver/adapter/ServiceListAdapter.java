package com.zemult.yovollserver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.model.M_Merchant;
import com.zemult.yovollserver.model.M_Service;
import com.zemult.yovollserver.util.StringUtils;
import com.zemult.yovollserver.util.ToastUtil;
import com.zemult.yovollserver.view.FixedGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wikison on 2017/7/5.
 */

public class ServiceListAdapter extends BaseListAdapter<M_Service> {
    ItemServiceClickListener itemServiceClickListener;
    ArrayList<M_Service> selectedIds = new ArrayList<M_Service>();
    M_Merchant merchant;
    int maxIds;

    public interface ItemServiceClickListener {
        void onItemClick(View v, M_Service entity);
    }

    public void setOnTextTaskClickListener(ItemServiceClickListener itemServiceClickListener) {
        this.itemServiceClickListener = itemServiceClickListener;
    }

    public ServiceListAdapter(Context context, List<M_Service> list, M_Merchant merchant) {
        super(context, list);
        this.merchant = merchant;
        maxIds = (merchant.reviewstatus == 2 ? Integer.MAX_VALUE : 1);

    }

    public void setData(List<M_Service> list) {
        clearAll();
        addALL(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_merchant_service_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        M_Service entity = getData().get(position);
        mImageManager.loadUrlImage(StringUtils.isBlank(entity.icon) ? "" : entity.icon, holder.iv, "@320h");
        holder.tvName.setText(entity.name);
        if (entity.childs != null
                && !entity.childs.isEmpty()) {
            holder.fgvList.setVisibility(View.VISIBLE);
            holder.fgvList.setAdapter(new ServiceAdapter(entity));
        } else
            holder.fgvList.setVisibility(View.GONE);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.fgv_list)
        FixedGridView fgvList;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ServiceAdapter extends BaseAdapter {
        private List<M_Service> serviceList = new ArrayList<>();

        private ServiceAdapter(M_Service entity) {
            serviceList = entity.childs;

        }

        @Override
        public int getCount() {
            return serviceList.size();
        }

        @Override
        public Object getItem(int position) {
            return serviceList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_merchant_service, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(R.string.app_name, holder);
            } else {
                holder = (ViewHolder) convertView.getTag(R.string.app_name);
            }

            final M_Service entity = serviceList.get(position);
            holder.rtvName.setText(entity.name);
            boolean isSelected = selectedIds.contains(entity.serviceId);
            holder.rtvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!v.isSelected() && selectedIds.size() + 1 > maxIds) {
                        ToastUtil.showMessage("最多选择" + maxIds + "项");
                        return;
                    }
                    if (selectedIds.contains(entity)) {
                        selectedIds.remove(entity);
                        holder.rtvName.getDelegate().setBackgroundColor(0xffeeeeee);
                        holder.rtvName.setTextColor(0xff666666);
                    } else {
                        selectedIds.add(entity);
                        holder.rtvName.getDelegate().setBackgroundColor(0xffb88e42);
                        holder.rtvName.setTextColor(0xffffffff);
                    }
                    v.setSelected(selectedIds.contains(entity));
                }
            });
            holder.rtvName.setSelected(isSelected);

            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.rtv_name)
            RoundTextView rtvName;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
