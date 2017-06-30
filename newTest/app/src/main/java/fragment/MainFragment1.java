package fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.example.yls.newtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yls on 2017/6/27.
 */

public class MainFragment1 extends BaseFragment {
    private TabLayout mTableLayout;
    private ViewPager mViewPager;


    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_main_01;
    }

    @Override
    protected void initView() {
        mTableLayout = (TabLayout) super.mView.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) super.mView.findViewById(R.id.view_pager_02);
        
        initViewPager();
    }

    private void initViewPager() {
        final String[] titles = new String[] {
                "头条", "社会", "科技", "财经", "体育", "汽车"
        };

        final String[] channelId = new String[] {
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };

        final List<Fragment> newsFragments = new ArrayList<>();
        for (int i=0; i<titles.length; i++){
            NewsFragment fragment = new NewsFragment();
            fragment.setChannelId(channelId[i]);
            newsFragments.add(fragment);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return newsFragments.get(position);
            }

            @Override
            public int getCount() {
                return newsFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        mTableLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initViewPager();
    }


}
