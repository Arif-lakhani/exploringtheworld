package arif.games.Exploring.monsters;

import java.util.Random;

import arif.games.Exploring.GameView;
import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.android.AbstractAndroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import arif.games.Exploring.R;

public class RotateMonster extends AbstractMonster {

	private Bitmap[] rotate;
	private Bitmap[] rotate_attacked;
	private boolean isstop = false;
	private int stoptime = 0;
	private Paint paint;
	private int attacked_times = 0;
	private boolean isattacked = false;
	
	public RotateMonster(float f, JumpActivity context){
		this.CoorX = new Random().nextInt(271) + 25;
		this.direction = new Random().nextInt(2) + 1;
		bitmap_index = 0;
		this.CoorY = f;
		this.horizontal_speed = (int) (direction == DIRECTION_LEFT ? -10 * JumpActivity.height_mul : 10 * JumpActivity.height_mul) ;
		initBitmap(context);
		isrunning = true;
		paint = new Paint();
		paint.setAntiAlias(true);
		new Thread(new MoveThread()).start();
	}
	
	private void initBitmap(JumpActivity context) {
		rotate = new Bitmap[3];
		rotate[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rotate1);
		rotate[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rotate2);
		rotate[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rotate3);
		
		rotate_attacked = new Bitmap[3];
		rotate_attacked[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rotate1_attacked); ;
		rotate_attacked[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rotate2_attacked); ;
		rotate_attacked[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rotate3_attacked); ;
	}

	@Override
	public int GetX() {
		return CoorX;
	}

	@Override
	public void DrawSelf(Canvas canvas) {
				if(!isstop){
					if(!isattacked)
				canvas.drawBitmap(rotate[bitmap_index], CoorX-25* JumpActivity.height_mul, CoorY-25* JumpActivity.height_mul, paint);
			else{
				isattacked = false;
				canvas.drawBitmap(rotate_attacked[bitmap_index], CoorX-25* JumpActivity.height_mul, CoorY-25* JumpActivity.height_mul, paint);
			}
		}
		else{ 
			if(!isattacked)
				canvas.drawBitmap(rotate[0], CoorX-25* JumpActivity.height_mul, CoorY-25* JumpActivity.height_mul, paint);
			else{
				isattacked = false;
				canvas.drawBitmap(rotate_attacked[0], CoorX-25* JumpActivity.height_mul, CoorY-25* JumpActivity.height_mul, paint);
			}
		}
	}

	private int GetDistance(float temp_x, float temp_y, int x, float y) {
		double x_length = temp_x - x;
		double y_length = temp_y - y;
		return (int) Math.sqrt(x_length*x_length + y_length*y_length);
	}
	
	@Override
	public void CheckDistance(AbstractAndroid android) {
		float temp_x = android.LTCoorX + 22* JumpActivity.height_mul;
		float temp_y = android.LTCoorY + 22* JumpActivity.height_mul;
		if(GetDistance(temp_x, temp_y, CoorX, CoorY) <= 40* JumpActivity.height_mul)
			android.MinusLifeBar(20);
		
	}
	
	private void Move(){
		this.CoorX += this.horizontal_speed;
		if(this.CoorX <= 25* JumpActivity.height_mul){
			this.direction = DIRECTION_RIGHT;
			this.horizontal_speed *= -1;
			isstop = true;
		}
		if(this.CoorX >= JumpActivity.screen_width - 25* JumpActivity.height_mul){
			this.direction = DIRECTION_LEFT;
			this.horizontal_speed *= -1;
			isstop = true;
		}
	}
	
	private class MoveThread implements Runnable{

		@Override
		public void run() {
			while(isrunning){
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
				}
				finally{
					if(!GameView.ispause){
						if(!isstop){
							Move();
							bitmap_index ++;
							if(bitmap_index > 2)
								bitmap_index = 0;
						}
						else{
							stoptime ++;
							if(stoptime > 20){
								isstop = false;
								stoptime = 0;
							}
						}
							
					}
				}
			}
		}
		
	}

	@Override
	public boolean IsDestory() {
		// TODO Auto-generated method stub
		return isdestory;
	}

	@Override
	public void BeingAttacked() {
		if(!isattacked){
			isattacked = true;
			attacked_times ++;
			if(attacked_times > 1)
				isdestory = true;
		}
		else{
			
		}
	}

	@Override
	public boolean IsBeingStep(float CoorX, float CoorY) {
		if(CoorX >= this.CoorX - 55 * JumpActivity.height_mul && CoorX <= this.CoorX + 10 * JumpActivity.height_mul&& CoorY + 70 * JumpActivity.height_mul <= this.CoorY && this.CoorY - CoorY <= 70 * JumpActivity.height_mul)
			return true;
		return false;
	}

}
