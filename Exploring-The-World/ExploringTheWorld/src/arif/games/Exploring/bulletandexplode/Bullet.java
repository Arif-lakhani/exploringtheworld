package arif.games.Exploring.bulletandexplode;

import arif.games.Exploring.JumpActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import arif.games.Exploring.R;


public class Bullet {
	

	private static final int INITIAL_VERTICAL_SPEED  = (int) (-20 * JumpActivity.height_mul);
	public Bitmap bullet;
	int CoorX; 
	int CoorY;
	
	int horizontal_speed;
	int vertical_speed;
	
	public Bullet(int _CoorX, int _CoorY, int _horizontal_speed, JumpActivity context){
		this.CoorX = _CoorX;
		this.CoorY = _CoorY;
		this.horizontal_speed = _horizontal_speed;
		this.vertical_speed = INITIAL_VERTICAL_SPEED;	
		bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
	}
	
	public void Move(){
		CoorY += vertical_speed;
		CoorX += horizontal_speed;
	}
	
	public void DrawSelf(Canvas canvas){
		canvas.drawBitmap(bullet, CoorX - 5 * JumpActivity.height_mul, CoorY - 5 * JumpActivity.height_mul, null);
	}

}
