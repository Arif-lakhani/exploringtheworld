package arif.games.Exploring.otherviews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.os.Vibrator;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.R;



/**
 * Created by Arif-Pc on 3/13/2016.
 */
public class CharacterView extends View {
    public static boolean alvin;
    public  static boolean theodore;
    public static boolean simon;
    public static boolean satya,arif,muzz;
    JumpActivity jumpActivity;
    Paint paint;
    private boolean[] isclick;
    boolean isclick1=false;
    Vibrator viabrator;
    int index;
    int r=100;

    private boolean isviewrunning = true;

    public CharacterView(JumpActivity context) {
        super(context);
        jumpActivity = context;
        isclick = new boolean[6];
        for(int i=0; i<6; i++)
            isclick[i] = false;
        index=0;
        new Thread(new CharacterThread()).start();
    }
    @Override
    protected void onDraw(Canvas canvas) {

        setBackgroundResource(R.drawable.back);
        DrawCharacters(canvas);
        DrawButton(canvas);
        super.onDraw(canvas);
        DrawTitle(canvas);
    }
    private void DrawTitle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(45 * JumpActivity.height_mul);
        paint.setColor(Color.parseColor("#f97f09"));
        int y = (int) (JumpActivity.screen_height / 6);
        canvas.drawText("Select your character", JumpActivity.screen_width / 15 - 20, y, paint);
        //canvas.drawText("Jump", DoodleJumpActivity.screen_width/4+100* DoodleJumpActivity.height_mul, y+50* DoodleJumpActivity.height_mul, paint);
    }
    private void DrawButton(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int X = (int) (JumpActivity.screen_width/2);
        int Y = (int) (JumpActivity.screen_height/3);
        paint.setColor(Color.parseColor("#a0f60b"));
        paint.setAlpha(60);
        if(isclick[0]){
            canvas.drawRect(X - 60 * JumpActivity.height_mul, Y - 40 * JumpActivity.height_mul, X + 40 * JumpActivity.height_mul, Y + 20 * JumpActivity.height_mul, paint);
            //doodleJumpActivity.handler.sendEmptyMessage(DoodleJumpActivity.GAME_START);
        }
        if(isclick[1]){
            canvas.drawRect(X - 60 * JumpActivity.height_mul, Y + 30 * JumpActivity.height_mul, X + 140 * JumpActivity.height_mul, Y + 90 * JumpActivity.height_mul, paint);
            //doodleJumpActivity.handler.sendEmptyMessage(DoodleJumpActivity.SCORE);
        }
        if(isclick[2]){
            canvas.drawRect(X - 60 * JumpActivity.height_mul, Y + 100 * JumpActivity.height_mul, X + 50 * JumpActivity.height_mul, Y + 160 * JumpActivity.height_mul, paint);
            //doodleJumpActivity.handler.sendEmptyMessage(DoodleJumpActivity.OPTION);
        }Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setTextSize(30 * JumpActivity.height_mul);
        paint2.setColor(Color.parseColor("#faf0cc"));
        canvas.drawText("alvin", X - 40 * JumpActivity.height_mul, Y, paint2);
        canvas.drawText("Theodore", X - 70* JumpActivity.height_mul, Y+60* JumpActivity.height_mul, paint2);
        canvas.drawText("Simon", X - 40* JumpActivity.height_mul, Y+120* JumpActivity.height_mul, paint2);
        canvas.drawText("Arif", X - 25* JumpActivity.height_mul, Y+180* JumpActivity.height_mul, paint2);
        canvas.drawText("satya", X - 30* JumpActivity.height_mul, Y+240* JumpActivity.height_mul, paint2);
        canvas.drawText("hamid", X - 30* JumpActivity.height_mul, Y+300* JumpActivity.height_mul, paint2);
    }

    public void DrawCharacters(Canvas canvas) {
        Bitmap aa,th,sim;
        Bitmap arif,satya,muzz;

        paint=new Paint();
        paint.setColor(Color.parseColor("#f97f09"));
        aa = BitmapFactory.decodeResource(getResources(), R.drawable.aalvin);
        th = BitmapFactory.decodeResource(getResources(), R.drawable.theodore);
        sim = BitmapFactory.decodeResource(getResources(), R.drawable.simonpng);
        arif = BitmapFactory.decodeResource(getResources(), R.drawable.arif1);
        satya=BitmapFactory.decodeResource(getResources(),R.drawable.satya1);
        muzz=BitmapFactory.decodeResource(getResources(),R.drawable.muzz);
        int Y = (int) (JumpActivity.screen_height/8+100 * JumpActivity.height_mul);
        int X = (int) (JumpActivity.screen_width/3 - 70 * JumpActivity.height_mul);

        canvas.drawBitmap(aa, X * JumpActivity.height_mul - 200, Y * JumpActivity.height_mul + 400, null);//alvin
       // canvas.drawCircle(X * JumpActivity.height_mul - 80, Y * JumpActivity.height_mul + 80, r, paint);
        canvas.drawBitmap(th, X * JumpActivity.height_mul +50, Y * JumpActivity.height_mul+400, null);//theodore
        canvas.drawBitmap(sim, X * JumpActivity.height_mul -200, Y * JumpActivity.height_mul+600, null);//simon
        canvas.drawBitmap(arif, X * JumpActivity.height_mul +50, Y * JumpActivity.height_mul+600, null);
        canvas.drawBitmap(satya, X * JumpActivity.height_mul +290, Y * JumpActivity.height_mul+400, null);//simon
        canvas.drawBitmap(muzz, X * JumpActivity.height_mul + 290, Y * JumpActivity.height_mul+600, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int X = (int) (JumpActivity.screen_width/2);
            int Y = (int) (JumpActivity.screen_height/3);
            int touch_x = (int) event.getX();
            int touch_Y= (int) event.getY();

            if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y - 40 * JumpActivity.height_mul&& touch_Y < Y + 20* JumpActivity.height_mul)
                isclick[0] = true;
            else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 20* JumpActivity.height_mul && touch_Y < Y + 80* JumpActivity.height_mul)
                isclick[1] = true;
            else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 90* JumpActivity.height_mul && touch_Y < Y + 150* JumpActivity.height_mul)
                isclick[2] = true;
            else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 150* JumpActivity.height_mul && touch_Y < Y + 210* JumpActivity.height_mul)
                isclick[3] = true;
            else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 170* JumpActivity.height_mul && touch_Y < Y + 270* JumpActivity.height_mul)
                isclick[4] = true;
            else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 230* JumpActivity.height_mul && touch_Y < Y + 340* JumpActivity.height_mul)
                isclick[5] = true;}

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (isclick[0]) {
                    //startExitAnim();
                    alvin = true;

                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                    Log.i("alvin seleected", "onTouchEvent ");
                }
                if (isclick[1]) {
                    //	startExitAnim();
                    theodore = true;
                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                    Log.i("Theodore", "onTouchEvent ");
                }
                if (isclick[2]) {

                    simon = true;
                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                    Log.i("simon selected", "onTouchEvent ");
                }
                if(isclick[3]){
                   arif=true;
                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                    //doodleJumpActivity.handler.sendEmptyMessage(DoodleJumpActivity.ABOUT);
                }
                if(isclick[4])
                {
                    satya = true;
                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                }
                if(isclick[5])
                {
                    muzz = true;
                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                }
                if (isclick1) {
                    jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
                }
            }
            return true;
        }




    private class CharacterThread implements Runnable {

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
