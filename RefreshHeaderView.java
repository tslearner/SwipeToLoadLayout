package com.example.tianshuai.swipetoloadlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by tianshuai on 2017/5/31.
 */

public class RefreshHeaderView extends LinearLayout implements SwipeRefreshTrigger, SwipeTrigger {
    String TAG = "RefreshHeaderView";
    private Context mContext;
    private TextView description;
    private ImageView arrow;
    private ProgressBar progressBar;
    private TextView updateTime;
    private int currentStatus = -1; //-1初始,0下拉可刷新  1释放立即刷新
    private long lastTime;
    public static final String LAST_UPDATE_TIME = "last_update_time";

    public RefreshHeaderView(Context context) {
        super(context);
        init(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.refresh_header, this);

    }

    @Override
    protected void onFinishInflate() {
        Log.d(TAG, "onFinishInflate: ");
        super.onFinishInflate();
        initView();
    }

    public void initView() {
        description = (TextView) findViewById(R.id.description);
        arrow = (ImageView) findViewById(R.id.arrow);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        updateTime = (TextView) findViewById(R.id.update_time);

    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");
        description.setText("正在刷新数据中");
        progressBar.setVisibility(VISIBLE);
        arrow.setVisibility(GONE);
    }

    @Override
    public void onPrepare() {
        Log.d(TAG, "onPrepare: ");
        description.setText("下拉后刷新");
        arrow.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        lastTime = (long) SharePreferenceUtils.getParam(getContext(), LAST_UPDATE_TIME, 0L);
        if (lastTime == 0) {
            updateTime.setText("无");
        } else {
            updateTime.setText(TimeUtil.timeShow(lastTime));
        }

    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                description.setText("释放立即刷新");
                if (currentStatus != 1) {
                    currentStatus = 1;
                    rotateArrow();
                }
            } else {

                description.setText("下拉后刷新");
                if (currentStatus == -1) {
                    return;
                }
                if (currentStatus != 0) {
                    currentStatus = 0;
                    rotateArrow();
                }
            }

        } /*else {
            tv.setText("REFRESH RETURNING");
        }*/
    }


    @Override
    public void onRelease() {
        Log.d(TAG, "onRelease: ");
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        description.setText("完成刷新");
        arrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        SharePreferenceUtils.setParam(getContext(), LAST_UPDATE_TIME, System.currentTimeMillis());

    }

    @Override
    public void onReset() {
        Log.d(TAG, "onReset: ");
        currentStatus = -1;
        description.setText("");
        arrow.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }


    // 根据当前的状态来旋转箭头

    private void rotateArrow() {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (currentStatus == 0) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currentStatus == 1) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(300);
        animation.setRepeatCount(0);//设置重复次数
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }
}
