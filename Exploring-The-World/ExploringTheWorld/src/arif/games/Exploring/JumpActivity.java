package arif.games.Exploring;



//import com.juzi.main.AppConnect;

import arif.games.Exploring.otherviews.AboutView;
import arif.games.Exploring.otherviews.CharacterView;
import arif.games.Exploring.otherviews.FailView;
import arif.games.Exploring.otherviews.ScoreView;
import arif.games.Exploring.otherviews.WelcomeView;
import arif.games.Exploring.otherviews.InstructionsView;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JumpActivity extends Activity {
	public static int getHighScore() throws IOException
	{
		FileInputStream fi=new FileInputStream("highscore.txt");
		int h=fi.read();
		fi.close();
		return h;
	}
	public static void setHighScore(int s) throws IOException
	{
		FileOutputStream fo=new FileOutputStream("highscore.txt");
		fo.write(s);
		fo.close();
	}


		/**
	 * Called when the activity is first created.
	 */
	public static final int GAME_OVER = 0;
	public static final int GAME_START = 1;
	public static final int Instructions = 2;
	public static final int ABOUT = 3;
	public static final int CHARACTER=4;
	public static final int SCORE = 5;
	public static final int WELCOME = 6;
	private GameView gameView;
	private WelcomeView welcomeView;

	private ScoreView scoreView;
	private InstructionsView instructionsView;
	private AboutView aboutView;
	private FailView failView;
	private CharacterView characterView;
	private SensorManager sensorManager;
	private MySensorEventListener sensorEventListener;
	int pre_speed = 0;
	View current_view;
	public static boolean isGame_Running = false;
	public static float screen_width;//to draw buttons
	public static float screen_height;//to draw buttons and text
	public static float width_mul;//intial position of alvin
	public static float height_mul;//inital hieght of alvin

	//selection of the views
	public Handler handler = new Handler() {
		public void handleMessage(Message msg) //used in all views
		{
			if (msg.what == GAME_OVER) {
				current_view = null;
				initFailView();
			}
			if (msg.what == GAME_START) {
				isGame_Running = true;
				welcomeView = null;
				initGameView();
			}
			if (msg.what == Instructions) {
				current_view = null;
				initInstructionsView();
			}
			if (msg.what == SCORE) {
				current_view = null;
				initExitView();
			}
			if (msg.what == WELCOME) {
				isGame_Running = false;
				current_view = null;
				initWelcomeView();
			}
			if (msg.what == ABOUT) {
				current_view = null;
				initAboutView();
			}
			if (msg.what==CHARACTER)
			{
				current_view= null;
				initcahracterView();
			}
		}
	};
	//initializations of views
	private void initcahracterView(){
		characterView=new CharacterView(JumpActivity.this);
		current_view=characterView;
		setContentView(characterView);
		gameView=null;
	}
	private void initFailView() {
		failView = new FailView(JumpActivity.this);
		current_view = failView;
		setContentView(failView);
		gameView = null;
	}
	private void initAboutView() {
		aboutView = null;
		aboutView = new AboutView(JumpActivity.this);
		current_view = aboutView;
		setContentView(aboutView);
		welcomeView = null;
	}
	private void initInstructionsView() {
		instructionsView = new InstructionsView(JumpActivity.this);
		current_view = instructionsView;
		setContentView(instructionsView);
		welcomeView = null;
	}
	private void initExitView() {
		scoreView = new ScoreView(JumpActivity.this);
		current_view = scoreView;
		setContentView(scoreView);
		welcomeView = null;
	}
	private void initGameView() {
		gameView = new GameView(JumpActivity.this);
		current_view = gameView;
		setContentView(gameView);
	}
	private void initWelcomeView() {
		welcomeView = new WelcomeView(JumpActivity.this);
		current_view = welcomeView;
		setContentView(welcomeView);
		failView = null;
	//	scoreView = null;
		scoreView = null;
		aboutView = null;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics dm;
		dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screen_width = dm.widthPixels;
		screen_height = dm.heightPixels;
		width_mul = screen_width / 320;
		height_mul = screen_height / 480;
		if (screen_height >= 800)
			height_mul = (float) 1.5;
		intiViews();//welcome view as default view
		setContentView(welcomeView);
		current_view = welcomeView;
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Log.e("hello", "" + JumpActivity.height_mul);
	}
	private void intiViews()//inititalizing welcome view as defaults view
	{
		welcomeView = new WelcomeView(this);
	}
	@Override
	protected void onResume() {
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorEventListener = new MySensorEventListener();
		sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}
	@Override
	protected void onPause() {
		sensorManager.unregisterListener(sensorEventListener);
		super.onPause();
	}
	private final class MySensorEventListener implements SensorEventListener{
        
		@Override//null implementation
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		@Override
		//Use sensor for movement
		public void onSensorChanged(SensorEvent event) {
			if(current_view == gameView){
				float X = event.values[SensorManager.DATA_X];
				pre_speed += X*1.8;//increment the speed
				if(X > 0.45 || X < -0.45){
					int temp = X > 0 ? 4 : -4;//decide the direction
					if(pre_speed > 7 || pre_speed < -7)
						pre_speed = pre_speed > 0 ? 7 : -7;//decide the speed
					gameView.logicManager.SetAndroid_HSpeed(pre_speed + temp);//set speed
				}
			}
		}
		
	}
	
    
}