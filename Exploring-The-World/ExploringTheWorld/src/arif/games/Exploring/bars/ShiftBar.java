package arif.games.Exploring.bars;

import arif.games.Exploring.GameView;
import arif.games.Exploring.Items.AbstractItem;
import arif.games.Exploring.JumpActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import arif.games.Exploring.R;


public class ShiftBar extends AbstractBar {

	int horizonal_speed = 3;
	Bitmap shift_bar;
	boolean isrunning = true;
	
	public ShiftBar(int CoorX, int CoorY, AbstractItem item, JumpActivity context){
		this.TLCoorX = CoorX;
		this.TLCoorY = CoorY;
		this.type = TYPE_SHIFT;
		this.item = item;
		this.isitemeaten = false;
		shift_bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.up_down_bar);
		new Thread(new ShiftThread()).start();
	}
	@Override
	public void drawSelf(Canvas canvas) {
		if(this.item != null && !this.isitemeaten)
			this.item.DrawSelf(canvas, TLCoorX + 15* JumpActivity.height_mul, TLCoorY - 20* JumpActivity.height_mul);
		canvas.drawBitmap(shift_bar, TLCoorX, TLCoorY, null);
	}

	
	private class ShiftThread implements Runnable{

		@Override
		public void run() {
			while(isrunning){
				try {
					Thread.sleep(40);
				} catch (Exception e) {
				}
				finally{
					if(!GameView.ispause){
						TLCoorX += horizonal_speed;
						if(TLCoorX >= 270){
							TLCoorX = 270;
							horizonal_speed = -3;
						}
						if(TLCoorX <= 0){
							TLCoorX = 0;
							horizonal_speed = 3;
						}
					}
				}
			}
		}
		
	}


	@Override
	public boolean IsBeingStep(float CoorX, float CoorY) {
		if(CoorX >= TLCoorX - 35 * JumpActivity.width_mul && CoorX <= TLCoorX + 45 * JumpActivity.width_mul && CoorY + 45 * JumpActivity.height_mul <= TLCoorY && TLCoorY - CoorY <= 45 * JumpActivity.height_mul){
		//	DoodleJumpActivity.soundPlayer.playSound(SoundPlayer.SOUND_NORMAL_BAR);
			return true;
		}
		return false;
	}
	@Override
	public void clear() {
		isrunning = false;
		//shift_bar.recycle();
	}
}
