package arif.games.Exploring.Items;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.android.AbstractAndroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import arif.games.Exploring.R;


public class ItemBullet extends AbstractItem {

	Bitmap item_bullet;
	
	public ItemBullet(JumpActivity context){
		this.type = TYPE_BULLET;
		item_bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_item) ;
	}
	
	@Override
	public void DrawSelf(Canvas canvas, float CoorX, float CoorY) {
		canvas.drawBitmap(item_bullet, CoorX, CoorY, null);

	}

	@Override
	public void ModifyAndroid(AbstractAndroid android) {
		android.bullet_times += 5;
	}

}
