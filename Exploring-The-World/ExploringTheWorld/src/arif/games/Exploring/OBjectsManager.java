package arif.games.Exploring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import arif.games.Exploring.Items.AbstractItem;
import arif.games.Exploring.android.AbstractAndroid;
import arif.games.Exploring.bars.AbstractBar;
import arif.games.Exploring.bars.InstantShiftBar;
import arif.games.Exploring.bars.NormalBar;
import arif.games.Exploring.bars.SpringBar;
import arif.games.Exploring.monsters.AbstractMonster;
import arif.games.Exploring.monsters.EatingHead;

import arif.games.Exploring.bars.ShiftBar;
import arif.games.Exploring.monsters.RotateMonster;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class OBjectsManager {
    
	
	public static  long sum;
	
	
	public int bar_level = 0;
	public int barApperrate = 35;
	public int monsterApperrate = 80;
	public int itemAppearate = 75;
	
	
	private int monstersappear = 0;
	public int itemappear = 0;
	
	private Map<String, AbstractBar> barMap;
	public Map<String, AbstractMonster> monsterMap;
	
	private long bar_identifier = 0;
	private long monster_identifier = 0;
	
	private long repeate_long = 100;
    public boolean isrepeated = false;
	public int touch_bar_type = AbstractBar.TYPE_NORMAL;

	public static boolean[] person;
	public static int choose;
	private JumpActivity context;
	private Paint paint;

	
	public OBjectsManager(JumpActivity context){

		this.context = context;
		barMap = new HashMap<String, AbstractBar>();
		monsterMap = new HashMap<String, AbstractMonster>();
		initBarMap();
		initPaint();
		person = new boolean[2];
     	person[0] = false;
		person[1] = false;
		sum = 0;

	}

	private boolean hasBar() {
		int temp = new Random().nextInt(100);
		if(temp > barApperrate)
			return true;
		return false;
	}
	private boolean hasMonster() {
		int temp = new Random().nextInt(100);
		if(temp > monsterApperrate)
			return true;
		return false;
	}
	public void DrawBarsAndMonsters(Canvas canvas){
		person[0] = true;
		choose = 1;
		for(String key : barMap.keySet()){
			barMap.get(key).drawSelf(canvas);
		}
		for(String key : monsterMap.keySet()){
			monsterMap.get(key).DrawSelf(canvas);
		}
		drawHeight(canvas);
		RemoveOuterBarsAndMonsters();

		person[0] = false;
	}
	

	private void drawHeight(Canvas canvas) {
		paint.setTextSize(17 * JumpActivity.height_mul);
		paint.setColor(Color.parseColor("#ffffff"));
		canvas.drawText(""+sum, 5 * JumpActivity.width_mul, 20 * JumpActivity.height_mul, paint);
	}
	
	public boolean isTouchBars(float CoorX, float CoorY){
		
		for(String key : barMap.keySet()){

			if(barMap.get(key).IsBeingStep(CoorX, CoorY)/*CoorX >= tempX - 35 && CoorX <= tempX + 45 && CoorY + 45 <= tempY && tempY - CoorY <= 45*/){
				if(repeate_long == Long.parseLong(key))
					isrepeated = true;
				else{
					isrepeated = false;
					repeate_long = Long.parseLong(key);
					touch_bar_type = barMap.get(key).type;
				}
				return true;
			}
		}
		
		if(isStepOnMonsters(CoorX, CoorY))
			return true;
		
		return false;
	}
	
	
	
	
	private boolean isStepOnMonsters(float CoorX, float CoorY) {	
		for(String key : monsterMap.keySet()){
			if(monsterMap.get(key).IsBeingStep(CoorX, CoorY)){
				return true;
			}
		}
		return false;
	}
	
	public void MoveBarsAndMonstersDown(float vertical_speed, float add){
		person[1] = true;
	choose = 0;
		for(String key : barMap.keySet()){
			barMap.get(key).TLCoorY -= vertical_speed;
			barMap.get(key).TLCoorY += add;
		}
		for(String key : monsterMap.keySet()){
			monsterMap.get(key).CoorY -= vertical_speed;
			monsterMap.get(key).CoorY += add;
		}
		sum += add - vertical_speed;
		monstersappear = itemappear += add - vertical_speed;
		bar_level += add - vertical_speed;
		AddNewBarsAndMonsters();
		person[1] = false;
	}
	
	private void RemoveOuterBarsAndMonsters() {
		List<String> temp = new ArrayList<String>();
		
		for(String key : barMap.keySet()){
			if(barMap.get(key).TLCoorY > JumpActivity.screen_height + 20){
				barMap.get(key).clear();
				temp.add(key);
			}
		}
		for(String key : temp){
			barMap.remove(key);
		}
		temp.clear();
		for(String key : monsterMap.keySet()){
			if(monsterMap.get(key).CoorY > JumpActivity.screen_height + 20){
				monsterMap.get(key).isrunning = false;
				temp.add(key);
			}
		}
		for(String key : temp){
			monsterMap.remove(key);
		}
	}
	
	public void AddNewBarsAndMonsters(){
		
		CheckLevel();
		
		AbstractBar bar;
		float tempY = 100;
		
		tempY = GetTopCoorY();
		if(tempY > (20 * JumpActivity.height_mul)){
			if(hasBar()){
				int temp = new Random().nextInt(100);
				int CoorX = new Random().nextInt((int) (JumpActivity.screen_width - 50));
				if(temp <= 5){
					bar = new SpringBar(CoorX, (int) (-15 * JumpActivity.height_mul), context);
				}


				else if(temp <= 50){
					bar = new InstantShiftBar(CoorX, (int) (-10 * JumpActivity.height_mul), context);
				}
				else{
					bar = new NormalBar(CoorX, 0, context);
				}
				barMap.put(""+bar_identifier, bar);
				bar_identifier ++;
				
			}
			else if(hasMonster() && monstersappear >= 300 * JumpActivity.height_mul){
				monstersappear = 0;
				int temp2 = new Random().nextInt(100);
				if(temp2 < 40)
					monsterMap.put(""+monster_identifier, new EatingHead(-10 * JumpActivity.height_mul, context));
				else if(temp2 < 80)
					monsterMap.put(""+monster_identifier, new RotateMonster(-10 * JumpActivity.height_mul, context));
				monster_identifier ++;
			}
		}
		
	}
	
	private void CheckLevel() {
		if(bar_level >= 2000){
			if(barApperrate < 55)
				barApperrate += 2;
			bar_level = 0;
		}
	}

	private float GetTopCoorY() {
		float tempY = 100;
		for(String key : barMap.keySet()){
			if(barMap.get(key).item == null){
				if(barMap.get(key).TLCoorY <= tempY)
					tempY = barMap.get(key).TLCoorY;
			}
			else if(barMap.get(key).TLCoorY - 20 * JumpActivity.height_mul <= tempY){
				tempY = barMap.get(key).TLCoorY - 20 * JumpActivity.height_mul;
			}
		}
		for(String key : monsterMap.keySet()){
			if(monsterMap.get(key).CoorY - 25  * JumpActivity.height_mul <= tempY)
				tempY = monsterMap.get(key).CoorY - 25 * JumpActivity.height_mul;
		}
		return tempY;
	}

	private void initPaint() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20* JumpActivity.height_mul);
		barApperrate = 40;
		monsterApperrate = 60;
		itemAppearate = 80;

	}

	public void Clear(){
		barMap.clear();
		monsterMap.clear();
	}


	public void initBarMap(){
		int count = 0;
		int CoorX;
		while(count < JumpActivity.screen_height / (20 * JumpActivity.height_mul)){
			if(hasBar()){
				int range = (int) (JumpActivity.screen_width - 50 * JumpActivity.width_mul);
				CoorX = new Random().nextInt(range);
				AbstractBar bar = new NormalBar(CoorX, count * (20 * JumpActivity.height_mul), context);
				barMap.put(""+bar_identifier, bar);
				bar_identifier ++;
			}
			count ++;
		}
	}


	public void CheckTouchMonsters(AbstractAndroid android) {
		for(String key : monsterMap.keySet()){
			monsterMap.get(key).CheckDistance(android);
		}
	}
}
