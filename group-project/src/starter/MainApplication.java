package starter;

public class MainApplication extends GraphicsApplication{
	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 900;
	public static final String MUSIC_FOLDER = "sounds";
	
	private Board board = new Board();
	private ChessboardPane chessPane;
	private MainMenuPane menu;
	private PieceShopPane piecePane;
	private RulesPane rules;
	private VictoryPane victory;
	private Board originalBoard = new Board();
	private Board savedBoard = new Board();
	private Board emptyBoard = new Board();

	public void init(){
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Welcome to the Custom Chess Game!");
		chessPane = new ChessboardPane(this);
		menu = new MainMenuPane(this);
		piecePane = new PieceShopPane(this);
		rules = new RulesPane(this);
		victory = new VictoryPane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}
	public void switchToGame(){
		//board.flipBoard();
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
	
	public Board getBoard() {
		return board;
	}
	
	public void saveOriginalBoard() {
		originalBoard.setEqual(board);
	}
	
	public void loadOriginalBoard() {
		board.setEqual(originalBoard);
	}
	
	public void quicksave() {
		savedBoard.setEqual(board);
	}
	
	public void quickload() {
		board.setEqual(savedBoard);
	}
	
	public void loadEmptyBoard() {
		board.setEqual(emptyBoard);
	}
}

