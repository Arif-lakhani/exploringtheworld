package arif.games.Exploring.bars;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.GameView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import arif.games.Exploring.R;


public class InstantShiftBar extends AbstractBar {

	private static final int STATE_DISAPPEAR  = 1;
	private static final int STATE_APPEAR = 2;
	private Bitmap InstantShift;
	private int interval = 0;
	private static final int DEFAULT_INTERVAL = 15;
	private static final int DISAPPEAR_RATE = 20;

	private boolean isdisappear = false;
	private int appear_time = 0;
	private int appear_state = STATE_APPEAR;
	boolean isrunning = true;
	private static boolean[] person;

	
	public InstantShiftBar(int CoorX, int CoorY, JumpActivity context){
		this.TLCoorX = CoorX;
		this.TLCoorY = CoorY;
		InstantShift = BitmapFactory.decodeResource(context.getResources(), R.drawable.instantshift);
		person = new boolean[2];
     	person[0] = false;
		person[1] = false;
		new Thread(new ShiftThread()).start();
	}

	@Override
	public void drawSelf(Canvas canvas) {
		if(!isdisappear)
			canvas.drawBitmap(InstantShift, TLCoorX, TLCoorY, null);
		else{
			Paint paint = new Paint();
			paint.setAlpha(appear_time * DISAPPEAR_RATE);
			canvas.drawBitmap(InstantShift, TLCoorX, TLCoorY, paint);
		}
	}


	@Override
	public boolean IsBeingStep(float CoorX, float CoorY) {

		/*****************critical section**************************/
		if(isdisappear){

			return false;
		}
		if(CoorX >= TLCoorX - 35 * JumpActivity.width_mul && CoorX <= TLCoorX + 45 * JumpActivity.width_mul && CoorY + 45 * JumpActivity.height_mul <= TLCoorY && TLCoorY - CoorY <= 45 * JumpActivity.height_mul){

			return true;
		};
		return false;
	}
	private class ShiftThread implements Runnable{
		@Override
		public void run() {
			while(isrunning){
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				if(!GameView.ispause){
					/*****************critical section**************************/
					if(isdisappear){
						if(appear_state == STATE_APPEAR){
							appear_time ++;
							if(appear_time > 3){
								appear_state = STATE_DISAPPEAR;
							}
							
						}
						else{
							appear_time --;
							if(appear_time < 0){
								appear_time = 0;
								appear_state = STATE_APPEAR;
								isdisappear = false;
								interval = 0;
							}
						}
					}
					else{
						interval ++;
						if(interval > DEFAULT_INTERVAL){
							isdisappear = true;
							appear_state = STATE_APPEAR;
						}
					}
					
					
				}
					/*****************critical section**************************/
			}
		}
		
		
	}


	@Override
	public void clear() {
		isrunning = false;
	}

}
