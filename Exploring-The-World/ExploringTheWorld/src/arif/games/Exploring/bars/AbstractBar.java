package arif.games.Exploring.bars;

import arif.games.Exploring.Items.AbstractItem;

import android.graphics.Canvas;

public abstract class AbstractBar {
	
	public static final int TYPE_NORMAL = 0;//normal bar (green)
	public static final int TYPE_SPRING = 1;//bar with spring (white)
	public static final int TYPE_SHIFT  = 2;//bar which either moves or disappers(red and yellow)
	//cordinates for bar
	public int TLCoorX;
	public float TLCoorY;
	public int type;
	public AbstractItem item;
	public boolean isitemeaten;
	
	public abstract void clear();
	public abstract void drawSelf(Canvas canvas);
	public abstract boolean IsBeingStep(float CoorX, float CoorY);
}