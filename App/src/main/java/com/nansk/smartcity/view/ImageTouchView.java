package com.nansk.smartcity.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;
/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 15:21
 * @description 自定义图片显示View,实现图片可以通过手势滑动放大缩小
 */

@SuppressLint("AppCompatCustomView")
public class ImageTouchView extends ImageView {
    private static PointF startPoint = new PointF();
    private static Matrix matrix = new Matrix();
    private Matrix currentMatrix = new Matrix();
    private int mode = 0;//标记模式
    private static final int DRAG = 1; //拖动
    private static final int ZOOM = 2; //缩放
    private float startDis = 0;
    private PointF midPoint;//中心点

    public ImageTouchView(Context context) {
        super(context);
    }

    /**
     * @Create 2021/10/9 15:22
     * @Role 设置显示图片
     * @Param
     */
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);

    }

    public ImageTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = DRAG;
                currentMatrix.set(this.getImageMatrix());//记录imageView当前的移动位置
                startPoint.set(event.getX(), event.getY());//开始点
                break;

            case MotionEvent.ACTION_MOVE:
                //移动
                if (mode == DRAG) {
                    float dx = event.getX() - startPoint.x;//x轴移动距离
                    float dy = event.getY() - startPoint.y;
                    matrix.set(currentMatrix);//在当前的位置基础移动
                    matrix.postTranslate(dx, dy);
                    //放大
                } else if (mode == ZOOM) {
                    float endDis = distance(event);//结束距离
                    if (endDis > 10f) {
                        float scale = endDis / startDis;//放大倍数
                        matrix.set(currentMatrix);
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                break;
            //有手指离开屏幕，但屏幕还有触点(手指)
            case MotionEvent.ACTION_POINTER_UP:
                mode = 0;
                break;
            //当屏幕上已经有触点（手指）,再有一个手指压下屏幕
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = ZOOM;
                startDis = distance(event);
                if (startDis > 10f) {//避免手指上有两个茧
                    midPoint = mid(event);
                    currentMatrix.set(this.getImageMatrix());//记录当前的缩放倍数
                }
                break;
        }
        this.setImageMatrix(matrix);
        return true;
    }

    /**
     * @Create 2021/10/9 15:45
     * @Role 计算两点之间的距离
     * @Param
     */
    private static float distance(MotionEvent event) {
        //两线距离
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return dx * dx + dy * dy;
    }

    /**
     * @Create 2021/10/9 15:45
     * @Role 计算两点之间的距离
     * @Param
     */
    private static PointF mid(MotionEvent event) {
        //两线距离
        float midx = event.getX(1) + event.getX(0);
        float midy = event.getY(1) - event.getY(0);
        return new PointF(midx / 2, midy);
    }


}
