package arif.games.Exploring.bulletandexplode;

import arif.games.Exploring.JumpActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import arif.games.Exploring.R;


public class Explode {
	
	int index;
	boolean isdone;
	
	int CoorX;
	public int CoorY;
	public Bitmap[] explodes;
	
	public Explode(int _CoorX, int _CoorY, JumpActivity context){
		index = 0;
		isdone = false;
		this.CoorX = _CoorX;
		this.CoorY = _CoorY;
		initBitmap(context);
		new Thread(new ExplodeThread()).start();
	}
	

	
	private void initBitmap(JumpActivity context) {
		explodes = new Bitmap[6];
		explodes[0] =  BitmapFactory.decodeResource(context.getResources(), R.drawable.explode1);
		explodes[1] =  BitmapFactory.decodeResource(context.getResources(), R.drawable.explode2); ;
		explodes[2] =  BitmapFactory.decodeResource(context.getResources(), R.drawable.explode3); ;
		explodes[3] =  BitmapFactory.decodeResource(context.getResources(), R.drawable.explode4); ;
		explodes[4] =  BitmapFactory.decodeResource(context.getResources(), R.drawable.explode5); ;
		explodes[5] =  BitmapFactory.decodeResource(context.getResources(), R.drawable.explode6); ;
	}


	public void DrawSelf(Canvas canvas){
		if(index <= 5)
			canvas.drawBitmap(explodes[index], CoorX, CoorY, null);
	}
	
	public boolean isDone(){
		return isdone;
	}
	
	private class ExplodeThread implements Runnable{

		@Override
		public void run() {
			while(!isdone){
				try {
					Thread.sleep(90);
				} catch (Exception e) {
				}
				if(!isdone){
					index ++;
					if(index > 5)
						isdone = true;
				}
			}
		}
		
	}
	

}
