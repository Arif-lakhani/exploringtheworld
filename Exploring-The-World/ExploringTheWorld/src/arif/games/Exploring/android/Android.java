package arif.games.Exploring.android;
import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.LogicManager;
import arif.games.Exploring.R;

import arif.games.Exploring.OBjectsManager;
import arif.games.Exploring.otherviews.CharacterView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public class Android extends AbstractAndroid {
	
	private boolean ishurt = false;
	private int  invincible_time = 0;
	//animation
	public Bitmap[] normal_android;
	public Bitmap[] hurt_android;
	public Bitmap bullet;
	public Bitmap life_littleandroid;
	
	public Android(JumpActivity context, int X, int Y){
		this(context);
		this.LTCoorX = X;
		this.LTCoorY = Y;
		
	}
	//drawing alvin on screen
	public Android(JumpActivity context){
		this.LTCoorX = INITIAL_COORX;
		this.LTCoorY = INITIAL_COORY;
		this.horizonal_speed = 0;
		this.vertical_speed = INITIAL_VERTICAL_SPEED + 5 * JumpActivity.height_mul;//increase speed
		this.current_state = STATE_GO_UP;
	    accelerameter = DEFAULT_VERTICAL_ACCELERATE;
	    this.life_bar = INITIAL_LIFE_BAR;
	    this.life_num = INITIAL_LIFE_NUM;
	    this.bullet_times = INITIAL_LUANCHER_BULLET_TIMES;
	    this.bitmap_index = HANDS_DOWN; 
	    this.isfalldown = false;
	    this.bullet_level = 1;
		initBitMap(context);
	}
	//animate alvin
	private void initBitMap(JumpActivity context){
		normal_android = new Bitmap[2];
		hurt_android = new Bitmap[2];
		 if(CharacterView.theodore){
			normal_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android1)).getBitmap();
			normal_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android1_launch)).getBitmap();

			hurt_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android1_hurt)).getBitmap();
			hurt_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android1_hurt_launch)).getBitmap();
			life_littleandroid = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.life_bar_1)).getBitmap();
		}else if(CharacterView.simon) {
			normal_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android2)).getBitmap();
			normal_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android2_launch)).getBitmap();

			hurt_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.anroid2_hurt)).getBitmap();
			hurt_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android2_launch_hurt)).getBitmap();
			life_littleandroid = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.life_bar_1)).getBitmap();
		}else if(CharacterView.arif){
			 normal_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.arifl)).getBitmap();
			 normal_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.arif)).getBitmap();

			 hurt_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.arifl)).getBitmap();
			 hurt_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.arif)).getBitmap();
			 life_littleandroid = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.life_bar_1)).getBitmap();
		 }else if(CharacterView.satya){
			 normal_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.satya)).getBitmap();
			 normal_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.satya)).getBitmap();

			 hurt_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.satya)).getBitmap();
			 hurt_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.satya)).getBitmap();
			 life_littleandroid = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.life_bar_1)).getBitmap();
		 }else if(CharacterView.muzz){
			 normal_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.muzzs)).getBitmap();
			 normal_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.muzzs)).getBitmap();

			 hurt_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.muzzs)).getBitmap();
			 hurt_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.muzzs)).getBitmap();
			 life_littleandroid = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.life_bar_1)).getBitmap();
		 }
		 else
		 {

				 normal_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android)).getBitmap();
				 normal_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android_launch)).getBitmap();

				 hurt_android[0] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android_hurt)).getBitmap();
				 hurt_android[1] = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.android_hurt_launch)).getBitmap();
				 life_littleandroid = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.life_bar_1)).getBitmap();
		 }
		bullet = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.bullet)).getBitmap();
	}
	@Override
	public void DrawSelf(Canvas canvas) {
		if(!ishurt)
			canvas.drawBitmap(normal_android[bitmap_index], LTCoorX, LTCoorY, null);
		else{
			if(invincible_time %2 == 0)
				canvas.drawBitmap(normal_android[bitmap_index], LTCoorX, LTCoorY, null);
			else
				canvas.drawBitmap(hurt_android[bitmap_index], LTCoorX, LTCoorY, null);
			invincible_time ++;
			if(invincible_time > 20){
				invincible_time = 0;
				ishurt = false;
			}
		}
		//life bar on screen
		DrawLifeBar(canvas);
		DrawLifeNumandBulletTimes(canvas);
		if(bitmap_index == HANDS_UP)
			bitmap_index = HANDS_DOWN;
	}
	private void DrawLifeNumandBulletTimes(Canvas canvas)//numer of lifes and bullets left
	{
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(17 * JumpActivity.height_mul);
		paint.setColor(Color.parseColor("#ffffff"));
		canvas.drawText("Life x", JumpActivity.screen_width/3, 12* JumpActivity.height_mul, paint);
		for(int i=0; i<life_num; i++){
			canvas.drawBitmap(life_littleandroid, JumpActivity.screen_width/3+25* JumpActivity.height_mul+i*17* JumpActivity.height_mul, -2 * JumpActivity.height_mul, null);
		}
		canvas.drawBitmap(bullet, JumpActivity.screen_width/3+130* JumpActivity.height_mul, 15* JumpActivity.height_mul, null);
		canvas.drawText(" X"+bullet_times, JumpActivity.screen_width/3+140* JumpActivity.height_mul, 25* JumpActivity.height_mul, paint);
	}

	private void DrawLifeBar(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#ffffff"));
		paint.setTextSize(17* JumpActivity.height_mul);
		canvas.drawText("HP:", JumpActivity.screen_width/3, 25* JumpActivity.height_mul, paint);
		if(ishurt)
			paint.setColor(Color.RED);
		else
			paint.setColor(Color.GREEN);
		canvas.drawRect(JumpActivity.screen_width/3+25* JumpActivity.height_mul, 15* JumpActivity.height_mul, JumpActivity.screen_width/3+25* JumpActivity.height_mul+life_bar * JumpActivity.height_mul, 25* JumpActivity.height_mul, paint);
	}

	public static AbstractAndroid AndroidFactory(JumpActivity context){
		return new Android(context);
	}

	@Override
	public void Move() {
		vertical_speed += accelerameter*1;
		LTCoorX += horizonal_speed;
		horizonal_speed = 0;
	}

	@Override
	public void CheckAndroidCoor(OBjectsManager oBjectsManager) {
		if(LTCoorY > JumpActivity.screen_height){
			this.MinusLifeBar(LogicManager.FALL_DOWN_DAMAGE);
			vertical_speed = Android.INITIAL_VERTICAL_SPEED;
			current_state = Android.STATE_GO_UP;
			oBjectsManager.isrepeated = false;
		}
		if(LTCoorX <= -40 * JumpActivity.width_mul)
			LTCoorX = JumpActivity.screen_width - 40 * JumpActivity.width_mul;
		if(LTCoorX >= JumpActivity.screen_width)
			LTCoorX = 0;
	}

	@Override
	public void MinusLifeBar(int num) {
		if(!ishurt){
		   // SoundPlayer.playSound(SoundPlayer.SOUND_INJURY);
			ishurt = true;
			this.life_bar -= num;
			if(this.life_bar <= 0){
				this.life_bar = INITIAL_LIFE_BAR;
				this.life_num --;
				this.bullet_level = 1;
			}
		}
	}

	
	@Override
	public void AddLifeBar(int num) {
		this.life_bar += num;
		if(this.life_bar > INITIAL_LIFE_BAR)
			this.life_bar = INITIAL_LIFE_BAR;
	}

}
