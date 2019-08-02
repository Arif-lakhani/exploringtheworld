package arif.games.Exploring;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View /* SurfaceView implements SurfaceHolder.Callback*/ {
   
	private static final int SLEEP_TIME = 50;

	//public static BitmapManager bitmapManager;
	public LogicManager logicManager;

	private Paint paint;
	public  static boolean isGameOver;
	JumpActivity jumpActivity;
	public static boolean ispause = false;
	int save_time = 0;
	public  static int highscore;
	private boolean isgamerunning = true;



	public GameView(JumpActivity context) {
		super(context);
		//bitmapManager = BitmapManager.getBitmapManager(context);
		logicManager = new LogicManager(context);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#c5c5c5"));
		isGameOver = false;
		this.jumpActivity = context;
//		dataBaseOperation = new DataBaseOperation(context);
		ispause = false;
		new Thread(new GameThread()).start();
	//	startEntryAnim();
	}

	public void ReFresh(){

	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		drawBackground();
		if(!ispause){
			logicManager.DrawAndroidAndBars(canvas);
			DrawTitle(canvas);
		}
		else{
			DrawPause(canvas);
		}
		super.onDraw(canvas);
	}
	
	private void DrawPause(Canvas canvas) {
		Paint paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setTextSize(30 * JumpActivity.height_mul);
		paint2.setColor(Color.parseColor("#173403"));
		canvas.drawText("resume", JumpActivity.screen_width/2-40* JumpActivity.height_mul, JumpActivity.screen_height/10+50* JumpActivity.height_mul, paint2);
		canvas.drawText("exit", JumpActivity.screen_width/2-20* JumpActivity.height_mul, JumpActivity.screen_height/10+150* JumpActivity.height_mul, paint2);
	}


	private void DrawTitle(Canvas canvas) {
		Log.e("title", "DrawTitle ");
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#00006f"));
		paint.setAlpha(60);
		canvas.drawRect(0, 0, JumpActivity.screen_width, JumpActivity.screen_height / 16, paint);

	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
    	if(event.getAction() == MotionEvent.ACTION_DOWN){
    		if(!ispause){
				logicManager.SetAndroidHands();
				logicManager.AddNewBulletSet();
    		}
    		else{
    			float x = event.getX();
    			float y = event.getY();
    			if(x > JumpActivity.screen_width/2-60* JumpActivity.height_mul && x < JumpActivity.screen_width/2+80* JumpActivity.height_mul
    			   && y > JumpActivity.screen_height/10+30* JumpActivity.height_mul && y< JumpActivity.screen_height/10+90* JumpActivity.height_mul)
    				ispause = false;
    			if(x > JumpActivity.screen_width/2-40* JumpActivity.height_mul && x < JumpActivity.screen_width/2+100* JumpActivity.height_mul
    			   && y > JumpActivity.screen_height/10+130* JumpActivity.height_mul && y< JumpActivity.screen_height/10+190* JumpActivity.height_mul){
    				LogicManager.isrunning = false;
    				isgamerunning = false;
    				jumpActivity.handler.sendEmptyMessage(JumpActivity.WELCOME);
    				GameView.ispause = false;
    			}
    		}
		}
		
		return super.onTouchEvent(event);
	}

	public void drawBackground()
	{
		long x=10000;
		if(OBjectsManager.sum>x && OBjectsManager.sum<22000){
		setBackgroundResource(R.drawable.background2);
			x=x+10000;}
		else if(OBjectsManager.sum>22000)
		{
			setBackgroundResource(R.drawable.background);
		}
		else setBackgroundResource(R.drawable.background1);
	}
		

	private class GameThread implements Runnable{


		@Override
		public void run() {
		
				while(isgamerunning){
					try {
						//repaint();
						Thread.sleep(GameView.SLEEP_TIME);
					} catch (Exception e) {
						// TODO: handle exception
					}
					postInvalidate();
				//	repaint();
					if(isGameOver){

						if(save_time == 0)
					//		dataBaseOperation.SaveHeight(OBjectsManager.sum, GetDate());
					//	save_time ++;
						isgamerunning = false;
	    				logicManager.Clear();
						jumpActivity.handler.sendEmptyMessage(JumpActivity.GAME_OVER);
						try{
							highscore= (int) JumpActivity.getHighScore();
							if(OBjectsManager.sum>  highscore)
								highscore= (int) OBjectsManager.sum;
							JumpActivity.setHighScore(highscore);
						}catch (Exception e){}

						}

					}
				}
				
			}

		
		
	}


