package arif.games.Exploring.otherviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.R;


/**
 * Created by Arif-Pc on 3/12/2016.
 */
public class InstructionsView extends View {
    JumpActivity jumpActivity;
    boolean isclick = false;

    public InstructionsView(JumpActivity context) {
        super(context);
        jumpActivity = context;
        new Thread(new InstructionThread()).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //	canvas.drawColor(Color.parseColor("#faf0cc"));
        //	DrawBackground(canvas);
        setBackgroundResource(R.drawable.main);
        DrawTitle(canvas);
        DrawButton(canvas);
        DrawAbout(canvas);
        super.onDraw(canvas);
    }


    private void DrawAbout(Canvas canvas) {
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setTextSize(20 * JumpActivity.height_mul);
        paint2.setColor(Color.parseColor("#f97f09"));
        int Y = (int) (JumpActivity.screen_height / 8 + 100 * JumpActivity.height_mul);
        int X = (int) (JumpActivity.screen_width /3 - 70 * JumpActivity.height_mul);
        canvas.drawText("Use your motion sensing capabiltiy to move alvin ", X-85, Y, paint2);
        canvas.drawText("Touch the screen to release bullets ", X, Y + 30 * JumpActivity.height_mul, paint2);
        canvas.drawText("Jump on the tiles to make a high score ", X - 25 * JumpActivity.height_mul, Y + 80 * JumpActivity.height_mul, paint2);
        canvas.drawText("Tackle the obstacles and unlock new regions", X - 50 * JumpActivity.height_mul, Y + 110 * JumpActivity.height_mul, paint2);
        canvas.drawText("Set a high score ", X+100  * JumpActivity.height_mul, Y + 140 * JumpActivity.height_mul, paint2);
        canvas.drawText("All the best.", X+120  * JumpActivity.height_mul, Y + 180 * JumpActivity.height_mul, paint2);
       // canvas.drawText("miniproject 3-2 i.t dept m.j.c.e.t", X - 50 * DoodleJumpActivity.height_mul, Y + 220 * DoodleJumpActivity.height_mul, paint2);
    }


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
        paint.setTextSize(50 * JumpActivity.height_mul);
        paint.setColor(Color.parseColor("#f97f09"));
        int y = (int) (JumpActivity.screen_height / 12);
        canvas.drawText("Instructions", JumpActivity.screen_width / 3 - 40 * JumpActivity.height_mul, y, paint);
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
                //  startExitAnim();
                jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
            }
        }
        return true;
    }


    private class InstructionThread implements Runnable {

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
