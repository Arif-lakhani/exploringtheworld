package arif.games.Exploring.otherviews;

import arif.games.Exploring.JumpActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import arif.games.Exploring.R;

public class AboutView extends View {

	JumpActivity jumpActivity;
	boolean isclick = false;
	
	private Animation alphAnimation;

	
	public AboutView(JumpActivity context) {
		super(context);
		jumpActivity = context;
		new Thread(new AboutThread()).start();

	}
	
	
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {

		setBackgroundResource(R.drawable.main);
		DrawTitle(canvas);
		DrawButton(canvas);
		DrawAbout(canvas);
		super.onDraw(canvas);
	}





	private void DrawAbout(Canvas canvas) {
		Paint paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setTextSize(20* JumpActivity.height_mul);
		paint2.setColor(Color.parseColor("#f97f09"));
		int Y = (int) (JumpActivity.screen_height/8+100 * JumpActivity.height_mul);
		int X = (int) (JumpActivity.screen_width/2 - 70 * JumpActivity.height_mul);
		canvas.drawText("Exploring the world", X, Y, paint2);
		canvas.drawText("Version 1.0.0", X+30, Y+30 * JumpActivity.height_mul, paint2);
		canvas.drawText("Devloped by Arif lakhani ,Satya Raja ,Abhilash venkat", X-160 * JumpActivity.height_mul, Y+60 * JumpActivity.height_mul, paint2);
		canvas.drawText("and Muzamil hamid", X-15 * JumpActivity.height_mul, Y+85 * JumpActivity.height_mul, paint2);
		canvas.drawText("Original concept and game design", X - 80 * JumpActivity.height_mul, Y + 125 * JumpActivity.height_mul, paint2);
		canvas.drawText("Reference DoodleJump", X-20 * JumpActivity.height_mul, Y+165 * JumpActivity.height_mul, paint2);
		canvas.drawText("Miniproject 3-2 i.t dept m.j.c.e.t", X-50 * JumpActivity.height_mul, Y+200 * JumpActivity.height_mul, paint2);
	}





	private void DrawButton(Canvas canvas) {
		int y = (int) (JumpActivity.screen_height-50* JumpActivity.height_mul);
		int x = (int) (JumpActivity.screen_width/4*3);
		Paint paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setTextSize(30* JumpActivity.height_mul);
		paint2.setColor(Color.parseColor("#f97f09"));
		canvas.drawText("Back", JumpActivity.screen_width/4*3, JumpActivity.screen_height-50* JumpActivity.height_mul, paint2);
		paint2.setColor(Color.parseColor("#f97f09"));
		paint2.setAlpha(60);
		if(isclick)
			canvas.drawRect(x - 20 * JumpActivity.height_mul, y - 40 * JumpActivity.height_mul, x + 70 * JumpActivity.height_mul, y + 20 * JumpActivity.height_mul, paint2);
	}





	private void DrawTitle(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(50* JumpActivity.height_mul);
		paint.setColor(Color.parseColor("#f97f09"));
		int y = (int) (JumpActivity.screen_height / 12);
		canvas.drawText("About", JumpActivity.screen_width/2 - 60 * JumpActivity.height_mul, y, paint);
	}







	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int y = (int) (JumpActivity.screen_height-50* JumpActivity.height_mul);
		int x = (int) (JumpActivity.screen_width/4*3);
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			float preX = event.getX();
			float preY = event.getY();
			if(preX > x - 20 * JumpActivity.height_mul && preX < x+70 * JumpActivity.height_mul && preY > y - 40 * JumpActivity.height_mul && preY < y + 20 * JumpActivity.height_mul)
				isclick = true;
		}
		if(event.getAction() == MotionEvent.ACTION_UP){
			if(isclick){
				jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
			}
		}
		return true;
	}


	private class AboutThread implements Runnable{

		@Override
		public void run() {
			while(true){
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
