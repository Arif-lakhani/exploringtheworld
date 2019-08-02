package arif.games.Exploring.otherviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.R;

/**
 * Created by Arif-Pc on 3/13/2016.
 */
public class ScoreView extends View {
    JumpActivity jumpActivity;
    boolean isclick = false;
    int highscore;
    public int getscore(){
        int high=0;
        try{
             high=JumpActivity.getHighScore();
        }catch (Exception e){}
        return  high;
    }



    public ScoreView(JumpActivity context) {
        super(context);
        jumpActivity = context;
        new Thread(new ScoreThread()).start();


    }


    @Override
    protected void onDraw(Canvas canvas) {

        setBackgroundResource(R.drawable.main);
        DrawTitle(canvas);
        DrawButton(canvas);
        DrawScore(canvas);
        super.onDraw(canvas);
    }

    private void DrawScore(Canvas canvas) {
        Paint paint2 = new Paint();

        paint2.setAntiAlias(true);
        paint2.setTextSize(30 * JumpActivity.height_mul);
        paint2.setColor(Color.parseColor("#f97f09"));
        int Y = (int) (JumpActivity.screen_height / 8 + 100 * JumpActivity.height_mul);
        int X = (int) (JumpActivity.screen_width / 2 - 70 * JumpActivity.height_mul);
     //   try{
         //   highscore=JumpActivity.getHighScore();
      //  canvas.drawText("last score: ", X -30, Y + 40, paint2);
        highscore=getscore();
        canvas.drawText("HighScore: "+highscore, X - 30, Y + 100 * JumpActivity.height_mul, paint2);}

   // }catch (Exception e){}}


    private void DrawButton(Canvas canvas) {
        int y = (int) (JumpActivity.screen_height - 50 * JumpActivity.height_mul);
        int x = (int) (JumpActivity.screen_width / 4 * 3);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setTextSize(30 * JumpActivity.height_mul);
        paint2.setColor(Color.parseColor("#f97f09"));
        canvas.drawText("Back", JumpActivity.screen_width / 4 * 3, JumpActivity.screen_height - 50 * JumpActivity.height_mul, paint2);
        paint2.setColor(Color.parseColor("#f97f09"));
        paint2.setAlpha(60);
        if (isclick)
            canvas.drawRect(x - 20 * JumpActivity.height_mul, y - 40 * JumpActivity.height_mul, x + 70 * JumpActivity.height_mul, y + 20 * JumpActivity.height_mul, paint2);
    }


    private void DrawTitle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(60 * JumpActivity.height_mul);
        paint.setColor(Color.parseColor("#f97f09"));
        int y = (int) (JumpActivity.screen_height / 12);
        canvas.drawText("High Score", JumpActivity.screen_width / 2 - 60 * JumpActivity.height_mul-70 , y, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) (JumpActivity.screen_height - 50 * JumpActivity.height_mul);
        int x = (int) (JumpActivity.screen_width / 4 * 3);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float preX = event.getX();
            float preY = event.getY();
            if (preX > x - 20 * JumpActivity.height_mul && preX < x + 70 * JumpActivity.height_mul && preY > y - 40 * JumpActivity.height_mul && preY < y + 20 * JumpActivity.height_mul)
                isclick = true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isclick) {
                jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
            }
        }
        return true;
    }


    private class ScoreThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                postInvalidate();
            }
        }

    }
}
