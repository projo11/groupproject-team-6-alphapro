package starter;
public class MainApplication extends GraphicsApplication{
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 900;
	public static final String MUSIC_FOLDER = "sounds";
	//private static final String [] SOUND_FILES = {"chessgamemusic.mp3"};

	private ChessboardPane chessPane;
	private MenuPane menu;
	private PieceShopPane piecePane;
	private RulesPane rules;
	private VictoryPane victory;

	public void init(){
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Welcome to the Custom Chess Game!");
		chessPane = new ChessboardPane(this);
		menu = new MenuPane(this);
		piecePane = new PieceShopPane(this);
		rules = new RulesPane(this);
		victory = new VictoryPane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}
	public void switchToGame(){
		switchToScreen(chessPane);
	}
	
	public void switchToPieceShop() {
		switchToScreen(piecePane);
	}
	
	public void switchToRules() {
		switchToScreen(rules);
	}
	
	public void switchToVic() {
		switchToScreen(victory);
	}
}


/*
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800; //This is where you define the window with and height for the application. For our app we'll probably make it a bit bigger.
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds"; //Basically "sounds" is the name of the folder that you put the .mp3 files into. We can name our folder something else but sounds makes the most sense
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" }; //These are just the sound files in the sounds folder, in our case we would have a sound for the menu, game screen, and end screen.

	private SomePane somePane; //Basically a some window; For our project we could put something like private InstrPane instructions;
	private MenuPane menu; //Menu window ; We can use the same format for our menu page
	private int count;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this); //initialzing
		menu = new MenuPane(this);
		switchToMenu();
	}

	public void switchToMenu() { // function to switch screen to menu
		playRandomSound(); // plays a sound when switched.
		count++;
		switchToScreen(menu);
	}

	public void switchToSome() { //function to switch the screen to some screen we can use this to switch to each window.
		playRandomSound();
		switchToScreen(somePane);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
} */

