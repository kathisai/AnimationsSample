package ksp.com.animationssample;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class WidthExpandAnimation extends Animation
{
    int _targetWidth;
    View _view;

    public WidthExpandAnimation(View view)
    {
        _view = view;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t)
    {
        if (interpolatedTime < 1.f)
        {
            int newWidth = (int) (_targetWidth * interpolatedTime);

            _view.layout(_view.getLeft(), _view.getTop(),
                    _view.getLeft() + newWidth, _view.getBottom());
        }
        else
            _view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight)
    {
        super.initialize(width, height, parentWidth, parentHeight);

        _targetWidth = width;
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}