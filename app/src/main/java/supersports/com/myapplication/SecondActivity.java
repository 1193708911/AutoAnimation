package supersports.com.myapplication;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private LinearLayout mParent;
    private LinearLayout mViewRoot;
    private GestureDetector gestureDetector;
    private float DEFAULT_EXIT_SCALE = 0.5f;
    private float mMoveRel;
    TypeEvaluator<Integer> mColorEvaluator = new TypeEvaluator<Integer>() {
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int startColor = startValue;
            int endColor = endValue;

            int alpha = (int) (Color.alpha(startColor) + fraction * (Color.alpha(endColor) - Color.alpha(startColor)));
            int red = (int) (Color.red(startColor) + fraction * (Color.red(endColor) - Color.red(startColor)));
            int green = (int) (Color.green(startColor) + fraction * (Color.green(endColor) - Color.green(startColor)));
            int blue = (int) (Color.blue(startColor) + fraction * (Color.blue(endColor) - Color.blue(startColor)));
            return Color.argb(alpha, red, green, blue);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();


        gestureDetector = new GestureDetector(this, this);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP://抬起之后进行判断当前是否要放回指定位置

                if (mMoveRel <= DEFAULT_EXIT_SCALE) {
                    onBackPressed();
                } else {//放回原处
                    final float mScaleX = mParent.getScaleX();
                    final float mScaleY = mParent.getScaleY();
                    final float transitionX = mParent.getTranslationX();
                    final float transitionY = mParent.getTranslationY();
                    //执行动画恢复原有的效果
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                    valueAnimator.setDuration(500);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float animatedValue = (float) animation.getAnimatedValue();
                            float mEndScaleX = mScaleX + (1 - mScaleX) * animatedValue;
                            float mEndScaleY = mScaleY + (1 - mScaleY) * animatedValue;
                            float mEndTransitionX = transitionX + (0 - transitionX) * animatedValue;
                            float mEndTransitionY = transitionY + (0 - transitionY) * animatedValue;
                            mParent.setScaleY(mEndScaleY);
                            mParent.setScaleX(mEndScaleX);
                            mParent.setTranslationX(mEndTransitionX);
                            mParent.setTranslationY(mEndTransitionY);
                            mViewRoot.setBackgroundColor(mColorEvaluator.evaluate(animatedValue, 0x00000000, 0xFF000000));


                        }
                    });

                    valueAnimator.start();

                }


                break;
        }


        return gestureDetector.onTouchEvent(event);
    }

    private void initView() {
        mParent = findViewById(R.id.parent);
        mParent =  findViewById(R.id.parent);
        mViewRoot =  findViewById(R.id.view_root);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        float mMoveX = e2.getX() - e1.getX();
        float mMoveY = e2.getY() - e1.getY();

        mMoveRel = 1;
        int mViewHeight = mParent.getHeight();

        if (mMoveY > 0) {
            mMoveRel = (mMoveRel - mMoveY / mViewHeight);

        } else {
            mMoveRel = mMoveRel - (-mMoveY) / mViewHeight;
        }


        if(mMoveRel>1){
            mViewRoot.setBackgroundColor(mColorEvaluator.evaluate(2-mMoveRel, 0x00000000, 0xFF000000));

        }else{
            mViewRoot.setBackgroundColor(mColorEvaluator.evaluate(mMoveRel, 0x00000000, 0xFF000000));

        }
        mParent.setTranslationX(mMoveX);
        mParent.setTranslationY(mMoveY);
        mParent.setScaleX(mMoveRel);
        mParent.setScaleY(mMoveRel);


        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}
