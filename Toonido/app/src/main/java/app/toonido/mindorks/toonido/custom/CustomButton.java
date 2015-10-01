package app.toonido.mindorks.toonido.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import app.toonido.mindorks.toonido.R;

public class CustomButton extends Button {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
            String customFont = values.getString(R.styleable.CustomEditText_typeface);
            setTypeface(Typefaces.get(context, customFont));
            values.recycle();
        }
    }
}
