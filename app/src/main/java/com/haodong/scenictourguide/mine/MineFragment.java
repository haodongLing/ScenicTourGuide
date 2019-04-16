package com.haodong.scenictourguide.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.mine.data.MineAdapter;
import com.haodong.scenictourguide.mine.data.MineItem;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class MineFragment extends MyFragment {
    private RecyclerView mRecyclerView;
    private MineAdapter mineAdapter;

    @Override
    public Object setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mRecyclerView = root.findViewById(R.id.mine_recycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        int[] imgId = new int[]{R.drawable.ic_shoucang_color, R.drawable.ic_download, R.drawable.ic_dingdan, R.drawable
                .ic_youji, R
                .drawable.ic_wenda, R.drawable.ic_dianpng, R.drawable.ic_youhuiquan, R.drawable.ic_qianbao, R.drawable
                .ic_huodong, R.drawable.ic_fenxiang, R.drawable.ic_wuping};
        String[] strings = new String[]{"我的收藏", "我的下载", "我的订单", "我的游记", "我的问答", "我的点评", "优惠券", "我的钱包", "我的活动",
                "分享有礼", "旅游好物"};
        ArrayList<MineItem> mDatas = new ArrayList<>();
        for (int i = 0; i < imgId.length; i++) {
            MineItem mineItem = new MineItem();
            mineItem.setString(strings[i]);
            mineItem.setId(imgId[i]);
            mDatas.add(mineItem);
        }
        mineAdapter = new MineAdapter(mDatas,getContext());
        mRecyclerView.setAdapter(mineAdapter);

    }
}
