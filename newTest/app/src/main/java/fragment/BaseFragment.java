package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by yls on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment {
    public View mView;
    private Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null){
            mView = LayoutInflater.from(mActivity).inflate(getFragmentRes(),container,false);

            initView();
            initListener();
            initData();
        }
        return mView;
    }

    protected abstract int getFragmentRes();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    private Toast mToast;

    public void showToast(String str){
        if(mToast == null){
            mToast = Toast.makeText(mActivity,"",Toast.LENGTH_LONG);
        }
        mToast.setText(str);
        mToast.show();
    }
}
