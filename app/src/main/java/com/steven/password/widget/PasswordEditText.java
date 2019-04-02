package com.steven.password.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.steven.password.R;

/**
 * Description:
 * Data：7/10/2018-9:10 AM
 *
 * @author yanzhiwen
 */
public class PasswordEditText extends AppCompatEditText {
    // 画笔-->绘制背景框
    private Paint mRectPaint;
    // 画笔--> 绘制密码
    private Paint mPasswordPaint;
    // 一个密码所占的宽度
    private int mPasswordItemWidth;
    // 密码的个数默认为6位数
    private int mPasswordNumber = 6;
    // 背景边框颜色
    private int mBgColor = Color.parseColor("#d1d2d6");
    // 背景边框大小
    private int mBgSize = 1;
    // 背景边框圆角大小
    private int mBgCorner = 0;
    // 分割线的颜色
    private int mDivisionLineColor = mBgColor;
    // 分割线的大小
    private int mDivisionLineSize = 1;
    // 密码圆点的颜色
    private int mPasswordColor = Color.parseColor("#000000");
    // 密码圆点的半径大小
    private int mPasswordRadius = 4;
    //密码输入完毕需要一个接口回调出去
    private PasswordFullListener mPasswordFullListener;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);
        //不显示光标
        setCursorVisible(false);
        //不弹出系统软键盘
        setInputType(InputType.TYPE_NULL);
        //背景去掉
        setBackground(null);
        initPaint();
    }

    /**
     * 初始化属性
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText);
        // 获取大小
        mDivisionLineSize = (int) array.getDimension(R.styleable.PasswordEditText_divisionLineSize, dip2px(mDivisionLineSize));
        mPasswordRadius = (int) array.getDimension(R.styleable.PasswordEditText_passwordRadius, dip2px(mPasswordRadius));
        mBgSize = (int) array.getDimension(R.styleable.PasswordEditText_bgSize, dip2px(mBgSize));
        mBgCorner = (int) array.getDimension(R.styleable.PasswordEditText_bgCorner, 0);
        // 获取颜色
        mBgColor = array.getColor(R.styleable.PasswordEditText_bgColor, mBgColor);
        mDivisionLineColor = array.getColor(R.styleable.PasswordEditText_divisionLineColor, mDivisionLineColor);
        mPasswordColor = array.getColor(R.styleable.PasswordEditText_passwordColor, mPasswordColor);
        array.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //初始化绘制边框的画笔
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setDither(true);
        mRectPaint.setColor(mBgColor);
        //初始化密码远点的画笔
        mPasswordPaint = new Paint();
        mPasswordPaint.setAntiAlias(true);
        mPasswordPaint.setDither(true);
        mPasswordPaint.setColor(mPasswordColor);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //不需要调用super.onDraw(canvas); 为什么不需要呢？你去调用试试看，就明白为什么了
        //super.onDraw(canvas);
        //一个密码的宽度
        mPasswordItemWidth = (getWidth() - mBgSize * 2 - (mPasswordNumber - 1) * mDivisionLineSize) / mPasswordNumber;
        drawRect(canvas);
        drawDivisionLine(canvas);
        drawPassword(canvas);
        if (mPasswordFullListener != null) {
            //获取输入的密码
            String password = getText().toString().trim();
            if (password.length() == mPasswordNumber) {
                mPasswordFullListener.passwordFull(password);
            }
        }
    }

    /**
     * 绘制背景框
     *
     * @param canvas 画布
     */
    private void drawRect(Canvas canvas) {
        //矩形
        RectF rect = new RectF(mBgSize >> 1, mBgSize >> 1, getWidth() - (mBgSize >> 1), getHeight() - (mBgSize >> 1));
        mRectPaint.setStrokeWidth(mBgSize);
        //画空心
        mRectPaint.setStyle(Paint.Style.STROKE);
        if (mBgCorner == 0) {
            canvas.drawRect(rect, mRectPaint);
        } else {
            canvas.drawRoundRect(rect, mBgCorner, mBgCorner, mRectPaint);
        }
    }

    /**
     * 绘制分割线
     *
     * @param canvas 画布
     */
    private void drawDivisionLine(Canvas canvas) {
        mRectPaint.setStrokeWidth(mDivisionLineSize);
        for (int i = 0; i < mPasswordNumber - 1; i++) {
            int startX = mBgSize + (i + 1) * mPasswordItemWidth + i * mDivisionLineSize;
            int startY = 0;
            int endX = startX;
            int endY = getHeight() - mBgSize;
            canvas.drawLine(startX, startY, endX, endY, mRectPaint);
        }
    }

    /**
     * 绘制圆点密码
     *
     * @param canvas 画布
     */
    private void drawPassword(Canvas canvas) {
        //圆点密码是实行的
        mPasswordPaint.setStyle(Paint.Style.FILL);
        int length = getText().toString().length();
        for (int i = 0; i < length; i++) {
            int cx = mBgSize + i * mDivisionLineSize + i * mPasswordItemWidth + mPasswordItemWidth / 2;
            int cy = getHeight() / 2;
            canvas.drawCircle(cx, cy, mPasswordRadius, mPasswordPaint);
        }
    }

    public void addPassword(String number) {
        if (TextUtils.isEmpty(number)) {
            return;
        }
        //把密码取取出来
        String password = getText().toString().trim();
        if (password.length() <= mPasswordNumber) {
            //密码叠加
            password += number;
            setText(password);
        }
    }

    /**
     * 删除密码
     */
    public void deletePassword() {
        String password = getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            return;
        }
        password = password.substring(0, password.length() - 1);
        setText(password);
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }

    /**
     * 设置一个密码输入完毕的监听器
     *
     * @param passwordFullListener Listener
     */
    public void setPasswordFullListener(PasswordFullListener passwordFullListener) {
        this.mPasswordFullListener = passwordFullListener;
    }

    public interface PasswordFullListener {
        void passwordFull(String password);
    }
}
