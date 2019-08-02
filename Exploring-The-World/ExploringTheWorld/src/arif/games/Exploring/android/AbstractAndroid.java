package arif.games.Exploring.android;

import arif.games.Exploring.JumpActivity;
import arif.games.Exploring.OBjectsManager;
import android.graphics.Canvas;

public abstract class AbstractAndroid {
	
	
	
	
	public static final float DEFAULT_VERTICAL_ACCELERATE       = 1 * JumpActivity.height_mul;//initialize acceleration as initial height
	protected static final float INITIAL_COORX                  = 145 * JumpActivity.width_mul;//initial x cordinate
	protected static final float INITIAL_COORY                  = 380 * JumpActivity.height_mul;//initial y cordinate
	public static final float INITIAL_VERTICAL_SPEED            = -19 * JumpActivity.height_mul;//initial vertical speed
	public static final float MAX_VERTICAL_SPEED                = 19 * JumpActivity.height_mul;//final vertical speed
	public static final int   STATE_GO_UP                       = 1;//
	public static final int   STATE_GO_DOWN                     = 2;
    public static final int   INITIAL_LIFE_NUM                  = 1;//number of life's
	public static final int   INITIAL_LIFE_BAR                  = 20;//hit points
    public static final int   INITIAL_LUANCHER_BULLET_TIMES     = 100;//number of bullets
	public static final int   HANDS_UP                          = 1;//animation
	public static final int   HANDS_DOWN                        = 0;//animation
    // x and y cordinates for alvin
	public float LTCoorX;
	public float LTCoorY;

	public float accelerameter;
	public float horizonal_speed;
	public float vertical_speed;
	
	public int current_state;//go up or down?
	public int life_bar;
	public int bullet_times;
	public int life_num;
	public boolean isfalldown;
	public int bitmap_index;
	public int bullet_level;
	
	public abstract void DrawSelf(Canvas canvas);
	public abstract void Move();
	public abstract void CheckAndroidCoor(OBjectsManager oBjectsManager);
	public abstract void MinusLifeBar(int num);
	public abstract void AddLifeBar(int num);
}
