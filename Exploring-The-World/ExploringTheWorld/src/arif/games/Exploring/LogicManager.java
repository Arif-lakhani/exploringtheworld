package arif.games.Exploring;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.util.Log;

import arif.games.Exploring.android.AbstractAndroid;
import arif.games.Exploring.android.Android;
import arif.games.Exploring.bars.AbstractBar;
import arif.games.Exploring.bulletandexplode.BulletSet;
import arif.games.Exploring.bulletandexplode.Explode;
//import ldp.games.doodlejump.resource.SoundPlayer;


public class LogicManager {
	
	public  static final int FALL_DOWN_DAMAGE = 60;

	private static final int SLEEP_TIME       = 40;
//	private static LogicManager logicManager  = null;
	
	private OBjectsManager objectsManager;
	private AbstractAndroid android;
	private List<BulletSet> bulletSets = new ArrayList<BulletSet>();
	private List<Explode> explodes = new ArrayList<Explode>();
	
	
	private boolean game_started = false;
	public static boolean isrunning;

	//同步互斥，person算法
	public static boolean[] person;
	public static int choose;
	JumpActivity context;
	
	public LogicManager(JumpActivity context){
		this.context = context;
	    objectsManager = new OBjectsManager(context);
		android = Android.AndroidFactory(context);
		person = new boolean[2];
     	person[0] = false;
		person[1] = false;
		isrunning = true;
		new Thread(new LogicThread()).start();
	}
	
	public void Clear(){
		isrunning = false;
		android = null;
		bulletSets.clear();
		explodes.clear();
		objectsManager.Clear();
	}
	
	public void DrawAndroidAndBars(Canvas canvas){
		person[0] = true;
		choose = 1;
		objectsManager.DrawBarsAndMonsters(canvas);
		android.DrawSelf(canvas);
	    for(BulletSet bulletSet : bulletSets){
	    	bulletSet.DrawBullets(canvas);
	    }
	    for(Explode explode : explodes){
	    	explode.DrawSelf(canvas);
	    }
	    RemoveBulletSets();
	    RemoveExplodes();
	    

		person[0] = false;
	}
	
	private void RemoveExplodes() {
		 for(int i=0; i<explodes.size(); i++){
			 if(explodes.get(i) != null){
				 if(explodes.get(i).isDone())
						 explodes.remove(i);
			 }
		 }
	}


	private void RemoveBulletSets() {
		for(int i=0; i<bulletSets.size(); i++){
			if(bulletSets.get(i).GetY() < 0)
				bulletSets.remove(i);
		}
	}

	public void AddNewBulletSet(){
		if(android.bullet_times > 0){
			bulletSets.add(new BulletSet(android, context));
			android.bullet_times --;
		}
	}
	
	public void SetAndroidHands(){
		android.bitmap_index = Android.HANDS_UP;
	}
	

	public void SetAndroid_HSpeed(float horizonal_speed){
		android.horizonal_speed = - horizonal_speed;
	}
	
	

	
	private class LogicThread implements Runnable{

		float add;
		@Override
		public void run() {
			while(isrunning){
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (Exception e) {
					
				}
				finally{
					if(!GameView.ispause){
						android.Move();
						android.CheckAndroidCoor(objectsManager);
						CheckVertivalSpeed();
						isBulletToucnMonsters();
						objectsManager.CheckTouchMonsters(android);
						if(android.life_num < 0){
							isrunning = false;
							GameView.isGameOver = true;
						}
					}
				}
			}
		}
		
		

		private void isBulletToucnMonsters() {
			List<String> DestoryMonsters = new ArrayList<String>();
			
			person[1] = true;
			choose = 0;
			while(person[0]&&choose==0);

 			for(BulletSet bulletSet : bulletSets){
				for(String key : objectsManager.monsterMap.keySet()){
					if(bulletSet.isTouchBullet(objectsManager.monsterMap.get(key))){
						DestoryMonsters.add(key);
						explodes.add(new Explode((int) (objectsManager.monsterMap.get(key).GetX() - 25* JumpActivity.height_mul), (int) (objectsManager.monsterMap.get(key).CoorY - 25* JumpActivity.height_mul), context));
					}
				}
				for(String key : DestoryMonsters){
					objectsManager.monsterMap.remove(key);
				}
				DestoryMonsters.clear();
			}

 			person[1] = false;
 			
		}



		private void CheckVertivalSpeed() {
			if(android.current_state == Android.STATE_GO_UP){
				android.LTCoorY += android.vertical_speed;
				if(game_started){
					objectsManager.MoveBarsAndMonstersDown(android.vertical_speed, add);
					for(int i=0; i<explodes.size(); i++){
						 if(explodes.get(i) != null){
							explodes.get(i).CoorY -= android.vertical_speed;
							explodes.get(i).CoorY += add;
						 }
					 }
					
				}
				if(android.vertical_speed >= 0){
					android.vertical_speed = 0;
					android.current_state = Android.STATE_GO_DOWN;
					game_started = true;
				}
			
			}
		    if(android.current_state == Android.STATE_GO_DOWN) {
		    	if(android.vertical_speed >= Android.MAX_VERTICAL_SPEED)
			    		android.vertical_speed = Android.MAX_VERTICAL_SPEED;
		    	float temp =  android.vertical_speed;
		    	for(float i=0; i<=temp; i += 0.5){	//将增加的像素，分开加，每次加一
		    		android.LTCoorY += 0.5;
			    	if(objectsManager.isTouchBars(android.LTCoorX, android.LTCoorY)){
			    		if(objectsManager.isrepeated){
			    			android.vertical_speed = Android.INITIAL_VERTICAL_SPEED;
			    		}
			    		else{ 
			    			if(objectsManager.touch_bar_type == AbstractBar.TYPE_NORMAL 
			    			   || objectsManager.touch_bar_type == AbstractBar.TYPE_SHIFT){
			    				Log.e("eror", "error");

			    				add = getAdd(android.LTCoorY);
			    				android.vertical_speed = Android.INITIAL_VERTICAL_SPEED + add;
			    			}
			    			else{
			    				add = getAdd(android.LTCoorY);
			    				android.vertical_speed = Android.INITIAL_VERTICAL_SPEED + add;
			    				add = 30 * JumpActivity.height_mul;
			    			}
			    		}
			    		android.current_state = Android.STATE_GO_UP;
			    		break;
			    	}
		    	}
		      
			}
		}

		private int getAdd(float lTCoorY) {
			if(lTCoorY >= 350 * JumpActivity.height_mul)
				return (int) (0 * JumpActivity.height_mul);
			else if(lTCoorY >= 300 * JumpActivity.height_mul)
				return (int) (5 * JumpActivity.height_mul);
			else 
				return (int) (10 * JumpActivity.height_mul);
		}
		
	}

}
