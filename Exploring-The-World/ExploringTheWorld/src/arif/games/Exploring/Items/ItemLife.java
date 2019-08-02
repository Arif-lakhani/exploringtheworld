package arif.games.Exploring.Items;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.android.AbstractAndroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import arif.games.Exploring.R;


public class ItemLife extends AbstractItem {

	Bitmap item_life;
	
	public ItemLife(JumpActivity context){
		this.type = TYPE_LIFE;
		item_life = BitmapFactory.decodeResource(context.getResources(), R.drawable.life_item);
	}
	
	@Override
	public void DrawSelf(Canvas canvas, float CoorX, float CoorY) {
		canvas.drawBitmap(item_life, CoorX, CoorY, null);

	}

	@Override
	public void ModifyAndroid(AbstractAndroid android) {
		android.life_num ++;
	}

}
