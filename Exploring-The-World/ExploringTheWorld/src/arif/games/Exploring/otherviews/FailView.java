package arif.games.Exploring.otherviews;

//import com.juzi.main.AppConnect;
import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.R;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class FailView extends View {

	JumpActivity jumpActivity;
	boolean isrunning = true;
	
	public FailView(JumpActivity context) {
		super(context);
		jumpActivity = context;
	 //   SoundPlayer.playSound(SoundPlayer.SOUND_FAIL);
		JumpActivity.isGame_Running = false;
		new Thread(new FailThread()).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//canvas.drawColor(Color.parseColor("#faf0cc"));
		//DrawBackground(canvas);
		setBackgroundResource(R.drawable.main);
		DrawButton(canvas);
		super.onDraw(canvas);
	}

	private void DrawButton(Canvas canvas) {
		Paint paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setTextSize(30* JumpActivity.height_mul);
		paint2.setColor(Color.parseColor("#faf0cc"));
		canvas.drawText("home", JumpActivity.screen_width/2-40* JumpActivity.height_mul, JumpActivity.screen_height/10+50* JumpActivity.height_mul, paint2);
		canvas.drawText("exit", JumpActivity.screen_width/2-20* JumpActivity.height_mul, JumpActivity.screen_height/10+150* JumpActivity.height_mul, paint2);
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
    			float x = event.getX();
    			float y = event.getY();
    			if(x > JumpActivity.screen_width/2-60* JumpActivity.height_mul && x < JumpActivity.screen_width/2+80* JumpActivity.height_mul
    			   && y > JumpActivity.screen_height/10+30* JumpActivity.height_mul && y< JumpActivity.screen_height/10+90){

    				jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
    			}
    			if(x > JumpActivity.screen_width/2-40* JumpActivity.height_mul && x < JumpActivity.screen_width/2+100* JumpActivity.height_mul
    			   && y > JumpActivity.screen_height/10+130* JumpActivity.height_mul && y< JumpActivity.screen_height/10+190* JumpActivity.height_mul){

    				android.os.Process.killProcess(android.os.Process.myPid());
    				isrunning = false;
    			}
    	    	
    		}
		
		return super.onTouchEvent(event);
	}
	
	private class FailThread implements Runnable{

		@Override
		public void run() {
			while(isrunning){
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				postInvalidate();
			}
			
		}
		
	}

}
