package com.zemult.yovollserver.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.zemult.yovollserver.R;
import com.zemult.yovollserver.adapter.BaseListAdapter;
import com.zemult.yovollserver.aip.Merchant2ProductListRequest;
import com.zemult.yovollserver.app.BaseActivity;
import com.zemult.yovollserver.model.M_Product;
import com.zemult.yovollserver.model.apimodel.APIM_ProductList;
import com.zemult.yovollserver.util.Convert;
import com.zemult.yovollserver.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zema.volley.network.ResponseListener;

/**
 * Created by Wikison on 2017/7/3.
 * 产品推荐
 */

public class ProductRecommendActivity extends BaseActivity {
    @Bind(R.id.lh_btn_back)
    Button lhBtnBack;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.lh_tv_title)
    TextView lhTvTitle;
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.tv_select)
    TextView tvSelect;
    @Bind(R.id.rll_sure)
    RoundLinearLayout rllSure;

    Merchant2ProductListRequest merchant2ProductListRequest;
    ArrayList<M_Product> selectedIds = new ArrayList<M_Product>();
    List<M_Product> mDatas = new ArrayList<M_Product>();
    Context mContext;
    Activity mActivity;
    RecommendAdapter adapter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_product_recommend);
    }

    @Override
    public void init() {
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mContext = this;
        mActivity = this;

        getRecommendList();
    }

    private void initView() {
        lhTvTitle.setText("营销产品推荐");
        adapter = new RecommendAdapter(mContext, mDatas);
        lv.setAdapter(adapter);
    }

    private void initListener() {

    }

    private void getRecommendList() {
        if (merchant2ProductListRequest != null) {
            merchant2ProductListRequest.cancel();
        }
        Merchant2ProductListRequest.Input input = new Merchant2ProductListRequest.Input();
        input.merchantId = 354256;
        input.page = 1;
        input.rows = 100;
        input.convertJson();
        merchant2ProductListRequest = new Merchant2ProductListRequest(input, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(Object response) {
                dismissPd();
                if (((APIM_ProductList) response).status == 1) {
                    mDatas = ((APIM_ProductList) response).productList;
                    if (mDatas == null || mDatas.isEmpty()) {
                    } else {
                        adapter.setData(mDatas);
                    }
                }

            }
        });

        sendJsonRequest(merchant2ProductListRequest);
    }

    class RecommendAdapter extends BaseListAdapter<M_Product> {
        public RecommendAdapter(Context context, List<M_Product> list) {
            super(context, list);
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public M_Product getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final M_Product entity = mDatas.get(position);
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_product, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (!StringUtils.isBlank(entity.pics)) {
                String[] arrayStr = entity.pics.split(",");
                imageManager.loadUrlImage(arrayStr[0], holder.iv, "@320h");
                holder.tvPicNum.setText(arrayStr.length+"");
            }
            holder.tvName.setText(entity.name);
            holder.tvNewPrice.setText(String.format("￥%s", Convert.getMoneyString(entity.newPrice)));
            holder.tvOldPrice.setText(String.format("￥%s", Convert.getMoneyString(entity.oldPrice)));
            holder.tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            holder.tvPrePrice.setText(String.format("订金￥%s", Convert.getMoneyString(entity.prePrice)));
            holder.tvDiscount.setText(String.format("佣金￥%s", Convert.getMoneyString(entity.discount)));
            holder.tvSaleNum.setText(String.format("已售 %d", entity.saleNum));
            holder.tvStockNum.setText(String.format("库存 %d", entity.stockNum));

            boolean isSelected = selectedIds.contains(entity);
            holder.btnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedIds.contains(entity)) {
                        selectedIds.remove(entity);
                    } else {
                        selectedIds.add(entity);
                    }
                    v.setSelected(selectedIds.contains(entity));
                    tvSelect.setText("已选择" + selectedIds.size() + "项");
                }
            });
            holder.btnCheck.setSelected(isSelected);

            return convertView;
        }

        public void setData(List<M_Product> list) {
            clearAll();
            addALL(list);
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv_pic_num)
        TextView tvPicNum;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.btn_check)
        Button btnCheck;
        @Bind(R.id.tv_new_price)
        TextView tvNewPrice;
        @Bind(R.id.tv_old_price)
        TextView tvOldPrice;
        @Bind(R.id.tv_pre_price)
        RoundTextView tvPrePrice;
        @Bind(R.id.tv_discount)
        RoundTextView tvDiscount;
        @Bind(R.id.tv_sale_num)
        TextView tvSaleNum;
        @Bind(R.id.tv_stock_num)
        TextView tvStockNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @OnClick({R.id.lh_btn_back, R.id.ll_back, R.id.rll_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lh_btn_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.rll_sure:

                break;
        }
    }
}
