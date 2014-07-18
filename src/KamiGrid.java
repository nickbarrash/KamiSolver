
public class KamiGrid {
	public int[][] colorGrid;
	
	public KamiGrid(int[][] colorGrid, int goldMoves){
		this.colorGrid = colorGrid;
	}
	
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < colorGrid.length; i++){
			for(int j = 0; j < colorGrid[0].length; j++){
				str.append(colorGrid[i][j] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
}
