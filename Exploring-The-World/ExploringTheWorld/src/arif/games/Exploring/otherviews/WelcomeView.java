package arif.games.Exploring.otherviews;


import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.R;
import arif.games.Exploring.android.Android;
import arif.games.Exploring.bars.NormalBar;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class WelcomeView extends View/* SurfaceView implements SurfaceHolder.Callback*/{

	private boolean[] isclick;
	JumpActivity jumpActivity;
	
	Android[] android;
	int index;
	Paint paint;
	NormalBar[] normalBars;

	
	
	private boolean isviewrunning = true;


	public WelcomeView(JumpActivity context) {
		super(context);
		this.jumpActivity = context;
		isclick = new boolean[5];
		for(int i=0; i<5; i++)
			isclick[i] = false;
		index = 0;
		new Thread(new WelcomeThread()).start();

	}
	@Override
	protected void onDraw(Canvas canvas) {

			setBackgroundResource(R.drawable.main);


		DrawTitle(canvas);
		DrawButton(canvas);
		super.onDraw(canvas);

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
		}
		if(isclick[3]){
			canvas.drawRect(X - 60 * JumpActivity.height_mul, Y + 170 * JumpActivity.height_mul, X + 60 * JumpActivity.height_mul, Y + 230 * JumpActivity.height_mul, paint);
			//doodleJumpActivity.handler.sendEmptyMessage(DoodleJumpActivity.ABOUT);
		}
		if(isclick[4]){
			canvas.drawRect(X - 60 * JumpActivity.height_mul, Y + 240 * JumpActivity.height_mul, X + 30 * JumpActivity.height_mul, Y + 300 * JumpActivity.height_mul, paint);
			//doodleJumpActivity.handler.sendEmptyMessage(DoodleJumpActivity.EXIT);
		}
		Paint paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setTextSize(30 * JumpActivity.height_mul);
		paint2.setColor(Color.parseColor("#f97f09"));
		canvas.drawText("Start", X - 40 * JumpActivity.height_mul, Y, paint2);
		canvas.drawText("Instructions", X - 70* JumpActivity.height_mul, Y+70* JumpActivity.height_mul, paint2);
		canvas.drawText("About", X - 40* JumpActivity.height_mul, Y+140* JumpActivity.height_mul, paint2);
		canvas.drawText("Character", X - 60* JumpActivity.height_mul, Y+210* JumpActivity.height_mul, paint2);
		canvas.drawText("High Score", X - 70* JumpActivity.height_mul, Y+280* JumpActivity.height_mul, paint2);
	}

	private void DrawTitle(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(50* JumpActivity.height_mul);
		paint.setColor(Color.parseColor("#faf0cc"));
		int y = (int) (JumpActivity.screen_height / 6);
		canvas.drawText("Exploring The World", JumpActivity.screen_width/15-20, y, paint);
		//canvas.drawText("Jump", DoodleJumpActivity.screen_width/4+100* DoodleJumpActivity.height_mul, y+50* DoodleJumpActivity.height_mul, paint);
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
    		else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 30* JumpActivity.height_mul && touch_Y < Y + 90* JumpActivity.height_mul)
    			isclick[1] = true;
    		else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 100* JumpActivity.height_mul && touch_Y < Y + 160* JumpActivity.height_mul)
    			isclick[2] = true;
    		else if(touch_x > X - 60* JumpActivity.height_mul && touch_x < X + 60 * JumpActivity.height_mul&& touch_Y > Y + 170* JumpActivity.height_mul && touch_Y < Y + 230* JumpActivity.height_mul)
    			isclick[3] = true;
    		else if(touch_x > X - 60 * JumpActivity.height_mul&& touch_x < X + 60* JumpActivity.height_mul && touch_Y > Y + 240* JumpActivity.height_mul && touch_Y < Y + 300* JumpActivity.height_mul)
    			isclick[4] = true;
    		}
    	
    	if(event.getAction() == MotionEvent.ACTION_UP){
    		if(isclick[0]){
    		//startExitAnim();
    			isviewrunning = false;
    			jumpActivity.handler.sendEmptyMessage(JumpActivity.GAME_START);
    		}
    		if(isclick[1]){
    		//	startExitAnim();
    			isviewrunning = false;
    			jumpActivity.handler.sendEmptyMessage(JumpActivity.Instructions);
    		}
    	if(isclick[2]){

    			isviewrunning = false;
    			jumpActivity.handler.sendEmptyMessage(JumpActivity.ABOUT);
    		}
    		if(isclick[3]){
    		//	startExitAnim();
    			isviewrunning = false;
    			jumpActivity.handler.sendEmptyMessage(JumpActivity.CHARACTER);
			}

				if(isclick[4]){
					//	startExitAnim();
					isviewrunning = false;
					jumpActivity.handler.sendEmptyMessage(JumpActivity.SCORE);
    		}
    	}
		return true;
	}
	

	private class WelcomeThread implements Runnable{

		@Override
		public void run() {
			while(isviewrunning){
				try {
					//repaint();
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
				/*for(int i=0; i<2; i++){
					android[i].Move();
					android[i].LTCoorY += (int)android[i].vertical_speed;
					if(android[i].vertical_speed > Android.MAX_VERTICAL_SPEED)
						android[i].vertical_speed = Android.MAX_VERTICAL_SPEED;
					if(android[i].LTCoorY >= JumpActivity.screen_height/3+ 200 * JumpActivity.height_mul)
						android[i].vertical_speed = Android.INITIAL_VERTICAL_SPEED;*/
				}
				postInvalidate();
				//repaint();
			}
		}
		
	}
	
/*
	public void repaint(){
		SurfaceHolder surfaceHolder = this.getHolder();
		Canvas canvas = surfaceHolder.lockCanvas();
		try{
			synchronized (surfaceHolder) {
				//onDraw(canvas);
				canvas.drawColor(Color.parseColor("#faf0cc"));
				DrawBackground(canvas);
				monster2.DrawSelf(canvas);
				DrawTitle(canvas);
				DrawButton(canvas);
				monster.DrawSelf(canvas);
				DrawAndroid(canvas);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(canvas != null)
				surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		repaint();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	*/

