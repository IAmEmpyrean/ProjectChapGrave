import java.util.Scanner;

public class ShapeData {
	
	boolean[][][] rotationStates = new boolean[4][4][4];
	String name;
	int defaultState;
	int colorIndex;
	
	public static final ShapeData line = new ShapeData("/line.txt", "line", 1, 0);
	public static final ShapeData square = new ShapeData("/square.txt", "line", 0, 1);
	public static final ShapeData Ishape = new ShapeData("/Ishape.txt", "line", 1, 2);
	public static final ShapeData jshape = new ShapeData("/jshape.txt", "line", 3, 3);
	public static final ShapeData tee = new ShapeData("/tee.txt", "line", 0, 4);
	public static final ShapeData sshape = new ShapeData("/sshape.txt", "line", 1, 5);
	public static final ShapeData zshape = new ShapeData("/zshape.txt", "line", 1, 6);
	
	public ShapeData(String filename, String name, int defaultState, int colorIndex) {
		this.name = name;
		this.defaultState = defaultState;
		this.colorIndex = colorIndex;
		try {
			Scanner s = new Scanner(ShapeData.class.getResourceAsStream(filename));
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					String line = s.nextLine();
					for(int k = 0; k < 4; k++) {
						rotationStates[i][j][k] = line.charAt(k) == '1';
					}
				}
			}
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
