package ksp.com.animationssample;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity  {

    private  Button btn_expand;
    private  Button btn_collapse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_expand = (Button) findViewById(R.id.btn_expand);
        btn_collapse = (Button) findViewById(R.id.btn_collapse);
        LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout layout = (LinearLayout)findViewById(R.id.imageLayout);

       // Initially add one
//
//        final ImageView image = new ImageView(MainActivity.this);
//        image.setLayoutParams(vp);
//        image.setImageDrawable(getResources().getDrawable(R.drawable.icic_card));
//        layout.addView(image);


        for(int i=0;i<3;i++)
        {
            final ImageView image = new ImageView(MainActivity.this);
            image.setLayoutParams(vp);
            if(i == 0)
            image.setImageDrawable(getResources().getDrawable(R.drawable.icic_card));
            else image.setImageDrawable(getResources().getDrawable(R.drawable.icic_card1));
            if (i == 2)
                image.setVisibility(View.VISIBLE);
            else
                image.setVisibility(View.GONE);
            layout.addView(image);

//            image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                            layout.removeView(image);
////                    expandOrCollapse(image,false,layout);
////
//
//                }
//            });
        }




        btn_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i < layout.getChildCount(); i++) {
////                    ViewAnimationUtils.expand(layout.getChildAt(i));
////                    expandOrCollapse(layout.getChildAt(1),true,layout.getChildAt(0).getHeight());
//                }
                expandOrCollapse(layout.getChildAt(2),true,layout.getChildAt(0).getHeight() + layout.getChildAt(1).getHeight());
                expandOrCollapse(layout.getChildAt(1),true,layout.getChildAt(0).getHeight());
                expandOrCollapse(layout.getChildAt(0),true,0);


            }
        });
        btn_collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 1; i < layout.getChildCount(); i++) {
////                    ViewAnimationUtils.expand(layout.getChildAt(i));
//                    expandOrCollapse(layout.getChildAt(i),false,layout.getChildAt(i-1).getHeight());
//                }
//                expandOrCollapse(layout.getChildAt(1),false,layout.getChildAt(0).getHeight());

                expandOrCollapse(layout.getChildAt(0),false,0);
                expandOrCollapse(layout.getChildAt(1),false,layout.getChildAt(0).getHeight());
                expandOrCollapse(layout.getChildAt(2),false,layout.getChildAt(0).getHeight() + layout.getChildAt(1).getHeight());
            }
        });



    }


//    public static void expand(final View v) {
//        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        final int targetHeight = v.getMeasuredHeight();
//
//        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
//        v.getLayoutParams().height = 1;
//        v.setVisibility(View.VISIBLE);
//        Animation a = new Animation()
//        {
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                v.getLayoutParams().height = interpolatedTime == 1
//                        ? LinearLayout.LayoutParams.WRAP_CONTENT
//                        : (int)(targetHeight * interpolatedTime);
//                v.requestLayout();
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//
//        // 1dp/ms
//        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
//        v.startAnimation(a);
//    }
//
//    public static void collapse(final View v) {
//        final int initialHeight = v.getMeasuredHeight();
//
//        Animation a = new Animation()
//        {
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                if(interpolatedTime == 1){
//                    v.setVisibility(View.GONE);
//                }else{
//                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
//                    v.requestLayout();
//                }
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//
//        // 1dp/ms
//        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
//        v.startAnimation(a);
//    }


    public void expandOrCollapse(final View v,boolean is_expand, final int animheight) {
        TranslateAnimation anim = null;
        if(is_expand)
        {
            anim = new TranslateAnimation(0.0f, 0.0f, -animheight, 0.0f);
            v.setVisibility(View.VISIBLE);
            Animation.AnimationListener expandedlistener= new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {


                }
            };

            anim.setAnimationListener(expandedlistener);
        }
        else{
            anim = new TranslateAnimation(0.0f, 0.0f, 0.0f, -animheight);
            Animation.AnimationListener collapselistener= new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }
            };

            anim.setAnimationListener(collapselistener);
        }

        // To Collapse
        //

        anim.setDuration(1500);
        anim.setInterpolator(new AccelerateInterpolator(0.5f));
        v.startAnimation(anim);
    }


//    public static Animation expand(final View v, final boolean expand) {
//        try {
//            Method m = v.getClass().getDeclaredMethod("onMeasure", int.class, int.class);
//            m.setAccessible(true);
//            m.invoke(
//                    v,
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(((View)v.getParent()).getMeasuredWidth(), View.MeasureSpec.AT_MOST)
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        final int initialHeight = v.getMeasuredHeight();
//
//        if (expand) {
//            v.getLayoutParams().height = 0;
//        }
//        else {
//            v.getLayoutParams().height = initialHeight;
//        }
//        v.setVisibility(View.VISIBLE);
//
//        Animation a = new Animation() {
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                int newHeight = 0;
//                if (expand) {
//                    newHeight = (int) (initialHeight * interpolatedTime);
//                } else {
//                    newHeight = (int) (initialHeight * (1 - interpolatedTime));
//                }
//                v.getLayoutParams().height = newHeight;
//                v.requestLayout();
//
//                if (interpolatedTime == 1 && !expand)
//                    v.setVisibility(View.GONE);
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//        a.setDuration(3000);
//        return a;
//    }
}
