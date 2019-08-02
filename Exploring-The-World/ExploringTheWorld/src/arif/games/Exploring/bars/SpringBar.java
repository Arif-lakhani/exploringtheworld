package arif.games.Exploring.bars;

import arif.games.Exploring.JumpActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import arif.games.Exploring.R;


public class SpringBar extends AbstractBar {
	
	Bitmap spring_bar;
	
	
	public SpringBar(int CoorX, int CoorY, JumpActivity context){
		this.TLCoorX = CoorX;
		this.TLCoorY = CoorY;
		this.type = TYPE_SPRING;
		spring_bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.spring_bar);
	}
	

	@Override
	public void drawSelf(Canvas canvas) {
		canvas.drawBitmap(spring_bar, TLCoorX, TLCoorY, null);

	}


	@Override
	public boolean IsBeingStep(float CoorX, float CoorY) {
		if(CoorX >= TLCoorX - 35 * JumpActivity.width_mul && CoorX <= TLCoorX + 45 * JumpActivity.width_mul && CoorY + 45 * JumpActivity.height_mul <= TLCoorY && TLCoorY - CoorY <= 45 * JumpActivity.height_mul){
			return true;
		}
		return false;
	}


	@Override
	public void clear() {
	}

}
