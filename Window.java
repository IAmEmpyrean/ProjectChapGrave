import javax.swing.JFrame;

public class Window {
	
	public static final int WIDTH = 307, HEIGHT = 630; // width of the jframe window
	private JFrame window;
	private Board board;
	
	public Window(){
		//sets the jframe up
		window = new JFrame("Tetris Game");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		board = new Board();//initializes the board object
		window.add(board);
		window.addKeyListener(board);
		window.setVisible(true); //makes the jframe visible
	}
	
	
	
	public static void main(String[] args) {
		new Window();//mank demes happen here ;)
	}

}
