import java.awt.Graphics2D;
import java.awt.Image;

public class TetrisGrid {
	int width, height, wOff, hOff;
	int[][] grid;
	final static int SQUARE_SIZE = 25;
	private Image[] tetrisBlocks;
	
	public TetrisGrid(int width, int height, int wOff, int hOff, Image[] blocks) {
		this.wOff = wOff;
		this.hOff = hOff;
		this.width = width - wOff / SQUARE_SIZE;
		this.height = height - hOff / SQUARE_SIZE;
		grid = new int[this.height][this.width];
		for(int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++) {
				grid[y][x] = -1;
			}
		}
		grid[0][0] = 0;
		grid[0][1] = 2;
		for(int x = 0; x < this.width; x++) {
			grid[1][x] = 0;
		}
		removeRow(1);
		grid[0][0] = 2;
		tetrisBlocks = blocks;
	}
	
	public int checkForRemoval() {
		int rowsRemoved = 0;
		for(int y = 0; y < height; y++){
			boolean isRowFull = true;
			for(int x = 0; x < width; x++) {
				if(grid[y][x] == -1) {
					isRowFull = false;
					break;
				}
			}
			if(isRowFull)
			{
				removeRow(y);
				rowsRemoved++;
			}
		}
		return rowsRemoved;
	}
	
	private void removeRow(int row) {
		for(int y = row; y > 0; y--) {
			for(int x = 0; x < width; x++) {
				grid[y][x] = grid[y-1][x];
			}
		}
		for(int x = 0; x < width; x++) {
			grid[0][x] = -1;
		}
	}
	
	public void drawGrid(Graphics2D g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(grid[y][x] != -1) {
					g.drawImage(tetrisBlocks[grid[y][x]], wOff + x * SQUARE_SIZE,
							hOff + y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
				}
			}
		}
	}
}
