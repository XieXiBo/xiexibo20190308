package com.bwie.xiexibo20190308.wiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 15:52:20
 * @Description:自定义水波纹
 */
public class CreateView extends View {

    private Path path2;
    private Path path1;
    private float f;
    private float A = 8f;
    private float K = 0f;
    private float y;
    private float y1;
    private Paint paint2;
    private Paint paint1;

    public CreateView(Context context) {
        super(context);
        initView();
    }


    public CreateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CreateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLUE);
        paint1.setStyle(Paint.Style.FILL);
        path1 = new Path();

        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);
        path2 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path1.reset();
        path2.reset();

        path1.moveTo(getLeft(), getBottom());
        path2.moveTo(getLeft(), getBottom());

        double v = (2 * Math.PI / getWidth());
        f -= 0.1f;

        for (int x = 0; x <= getWidth(); x += 20) {
            y = (float) (A * Math.sin(v * x + f));
            y1 = (float) (A * Math.cos(v * x + f) + 5);
            path1.lineTo(x, y);
            path2.lineTo(x, y1);
            onCreateView.ongetY(y);
        }
        path1.moveTo(getRight(), getBottom());
        path2.moveTo(getRight(), getBottom());


        canvas.drawPath(path1, paint1);
        canvas.drawPath(path2, paint2);
        postInvalidateDelayed(20);
    }
    public interface onCreateView{
        void ongetY(float y);
    }
    public onCreateView onCreateView;

    public void setOnCreateView(CreateView.onCreateView onCreateView) {
        this.onCreateView = onCreateView;
    }
}
