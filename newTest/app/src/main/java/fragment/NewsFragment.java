package fragment;

import android.util.Log;
import android.widget.ListView;

import com.example.yls.newtest.R;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import adapter.NewsAdapter;
import bean.NewsEntity;
import util.URLManager;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsFragment extends BaseFragment {
    private ListView mListView;

    /** 新闻类别id */
    private String channelId;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    protected int getFragmentRes() {
        return R.layout.fragment_news ;
    }

    @Override
    protected void initView() {
/*        mTextView = (TextView) mView.findViewById(R.id.tv_01);
        mTextView.setText("新闻类别id："+channelId);*/
        mListView = (ListView) mView.findViewById(R.id.lv_01);
        Log.i("AAA", "aaaaaaaa");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getNewsDataFromServer();

    }

    public void getNewsDataFromServer() {
        String url = URLManager.getUrl(channelId);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                    String json = responseInfo.result;
                System.out.println("----服务器返回的json数据a:" + json);
                json=json.replace(channelId,"result");
                Gson gson = new Gson();
                NewsEntity newsDatas = gson.fromJson(json, NewsEntity.class);
                System.out.println("----服务器返回的json数据b:" + newsDatas.getResult());

                // 显示数据到列表中
                showDatas(newsDatas);


            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
            }
        });
    }
    // 显示服务器数据
    private void showDatas(NewsEntity newsDatas) {
        if (newsDatas == null || newsDatas.getResult() == null || newsDatas.getResult().size() == 0) {
            System.out.println("----没有获取到服务器的新闻数据");
            return;
        }

        // （1）显示轮播图

        // （2）显示新闻列表
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), newsDatas.getResult());

        mListView.setAdapter(newsAdapter);
    }

}
