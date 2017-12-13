import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{
	
	private BufferedImage blocks;	// img buffer
	
	private final int blockSize = 30; // static var
	
	private final int WIDTH = 10, HEIGHT = 20; // static vars
	
	private int[][] board = new int[HEIGHT][WIDTH]; // the board
	
	private Shape[] shapes = new Shape[7]; // arr of shapes
	
	private Shape currentShape; // the current tetris shape
	
	private Timer timer; // the timer for almost everything
	
	private final int FPS = 60; // the framerate of the game
	
	private final int delay = 1000/FPS; // the delay of actions
	
	private boolean gameOver = false; // bool for controlling if game is over
	
	public Board(){
		
		try {
			blocks = ImageIO.read(Board.class.getResource("/tiles.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		timer = new Timer(delay, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		
		timer.start();
		
		// shapes
		shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1, 1} // IShape
		}, this, 1);
		shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][]{
			{1, 1, 0},
			{0, 1, 1}   // ZShape
		}, this, 2);
		shapes[2] = new Shape(blocks.getSubimage(blockSize*2, 0, blockSize, blockSize), new int[][]{
			{0, 1, 1},
			{1, 1, 0}   // S-Shape
		}, this, 3);
		shapes[3] = new Shape(blocks.getSubimage(blockSize*3, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1},
			{0, 0, 1}   // J-Shape
		}, this, 4);
		shapes[4] = new Shape(blocks.getSubimage(blockSize*4, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1},
			{1, 0, 0}   // L-Shape
		}, this, 5);
		shapes[5] = new Shape(blocks.getSubimage(blockSize*5, 0, blockSize, blockSize), new int[][]{
			{1, 1, 1},
			{0, 1, 0}   // T-Shape
		}, this, 6);
		shapes[6] = new Shape(blocks.getSubimage(blockSize*6, 0, blockSize, blockSize), new int[][]{
			{1, 1},
			{1, 1}   // O-Shape
		}, this, 7);
		
		setNextShape();
		
	}
	
	public void update(){
		currentShape.update();
		if(gameOver)
			timer.stop();
	}
	
	
	public void paintComponent(Graphics g){//graphics rendering
		super.paintComponent(g);	
		
		currentShape.render(g);
		
		for(int row = 0; row < board.length; row++)
			for(int col = 0; col < board[row].length; col++)
				if(board[row][col] != 0)
					g.drawImage(blocks.getSubimage((board[row][col]-1)*blockSize, 0, blockSize, blockSize),
					col*blockSize, row*blockSize, null);
		
		
		
		for(int i = 0; i < HEIGHT; i++){
			g.drawLine(0, i*blockSize, WIDTH*blockSize, i*blockSize);
		}
		for(int j = 0; j < WIDTH; j++){
			g.drawLine(j*blockSize, 0, j*blockSize, HEIGHT*blockSize);
		}
		
	}
	
	public void setNextShape(){//sets the next shape
		
		int index = (int)(Math.random()*shapes.length);
		
		Shape newShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(),
				this, shapes[index].getColor());
		
		currentShape = newShape;
		
		for(int row = 0; row < currentShape.getCoords().length; row++)
			for(int col = 0; col < currentShape.getCoords()[row].length; col++)
				if(currentShape.getCoords()[row][col] != 0){
					
					if(board[row][col + 3] != 0)
						gameOver = true;
				}
		
		
		
	}
	
	
	public int getBlockSize(){
		return blockSize;
	}

	public int[][] getBoard(){
		return board;
	}

	public void keyPressed(KeyEvent e) {//keylogger ;)
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			currentShape.setDeltaX(-1);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			currentShape.setDeltaX(1);
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentShape.speedDown();
		if(e.getKeyCode() == KeyEvent.VK_UP)
			currentShape.rotate();
	}

	public void keyReleased(KeyEvent e) {//And anotha one
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			currentShape.normalSpeed();
	}
	
	public void keyTyped(KeyEvent e) {//and anotha one
		// TODO Auto-generated method stub
		
	}
}
