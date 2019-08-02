package arif.games.Exploring.bars;

import arif.games.Exploring.Items.AbstractItem;
import arif.games.Exploring.JumpActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import arif.games.Exploring.R;


public class NormalBar extends AbstractBar {
	
	private Bitmap normal_bar;
	
	public NormalBar(int CoorX, float CoorY,  JumpActivity context){
		this.TLCoorX = CoorX;
		this.TLCoorY = CoorY;
		this.type = TYPE_NORMAL;

		this.isitemeaten = false;
		normal_bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.bar);
	}

	@Override
	public void drawSelf(Canvas canvas) {
		if(this.item != null && !this.isitemeaten)
			this.item.DrawSelf(canvas, TLCoorX + 15 * JumpActivity.height_mul, TLCoorY - 20 * JumpActivity.height_mul);
		canvas.drawBitmap(normal_bar, TLCoorX, TLCoorY, null);
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
