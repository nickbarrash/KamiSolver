

public class TestMain {
	public static void main(String[] args){
//		int[][] testArr = 
//			new int[][]{
//				new int[]{1,1,1,1,1,1},
//				new int[]{1,1,2,2,2,2},
//				new int[]{1,1,2,3,3,3},
//				new int[]{1,1,2,3,1,1},
//				new int[]{3,1,3,2,1,2},
//				new int[]{3,3,1,1,2,2},
//				new int[]{3,3,3,2,2,2},
//			};
		
//		int[][] testArr = 
//				new int[][]{
//					new int[]{3,3,1,1},
//					new int[]{3,2,1,2},
//					new int[]{1,1,2,2},
//					new int[]{2,2,2,2},
//				};
//		int[][] testArr = 
//				new int[][]{
//					new int[]{1,1,1},
//					new int[]{1,2,1},
//					new int[]{1,1,1},
//					new int[]{3,3,1},
//					new int[]{1,1,1},
//					new int[]{1,4,4},
//					new int[]{1,1,1},
//					new int[]{1,5,1},
//					new int[]{6,7,6},
//					new int[]{6,6,6}
//				};
		
//		int[][] testArr = 
//		new int[][]{
//			new int[]{1,1,1},
//			new int[]{2,2,2},
//			new int[]{1,1,1},
//			new int[]{3,4,5},
//			new int[]{1,1,1},
//			new int[]{2,2,2},
//			new int[]{1,1,1},
//			new int[]{2,2,2}
//		};
		
		
//		int[][] testArr = 
//				new int[][]{
//					new int[]{1,1,1,1,1,1,1,1,1},
//					new int[]{2,2,2,2,2,2,2,2,2},
//					new int[]{2,1,1,1,1,1,1,1,2},
//					new int[]{2,1,3,3,3,3,3,1,2},
//					new int[]{2,1,3,2,2,2,3,1,2},
//					new int[]{2,1,3,2,3,2,3,1,2},
//					new int[]{2,1,3,2,2,2,3,1,2},
//					new int[]{2,1,3,3,3,3,3,1,2},
//					new int[]{2,1,1,1,1,1,1,1,2},
//					new int[]{2,2,2,2,2,2,2,2,2},
//					new int[]{1,1,1,1,1,1,1,1,1},
//				};
		
//		int[][] testArr = 
//				new int[][]{
//					new int[]{2,1},
//					new int[]{3,1},
//					new int[]{1,1},
//					new int[]{3,3},
//					new int[]{4,5},
//				};

		
//		int[][] testArr = 
//		new int[][]{
//			new int[]{1,1,1,1,1,1,1,1,1,1},
//			new int[]{1,1,2,1,2,1,1,1,1,1},
//			new int[]{1,1,1,2,1,1,1,1,1,3},
//			new int[]{1,1,2,1,2,1,1,1,3,1},
//			new int[]{1,1,1,1,1,1,1,3,1,1},
//			new int[]{1,1,1,1,1,1,3,1,1,1},
//			new int[]{1,1,1,1,1,3,1,1,1,1},
//			new int[]{3,1,1,1,4,1,1,1,1,1},
//			new int[]{1,4,1,3,1,1,2,1,2,1},
//			new int[]{1,1,3,1,1,1,1,2,1,1},
//			new int[]{1,1,1,3,1,1,2,1,2,1},
//			new int[]{1,1,1,1,4,1,1,1,1,1},
//			new int[]{1,1,1,1,1,3,1,1,1,1},
//			new int[]{1,1,1,1,1,1,3,1,1,1},
//			new int[]{1,1,1,1,1,1,1,3,1,1},
//			new int[]{1,1,1,1,1,1,1,1,3,1},
//		};		
		
		int[][] testArr = 
				new int[][]{
					new int[]{1,1,1,1,1,1,1,1,1,1},
					new int[]{2,1,2,1,2,1,2,1,2,1},
					new int[]{1,1,1,1,1,1,1,1,1,1},
					new int[]{1,1,1,3,3,3,1,1,1,1},
					new int[]{1,1,3,1,1,1,3,1,1,1},
					new int[]{1,1,3,1,1,1,3,1,1,1},
					new int[]{4,4,4,3,3,3,4,4,4,4},
					new int[]{4,4,4,4,4,4,2,2,2,2},
					new int[]{1,1,1,1,1,1,2,2,2,2},
					new int[]{3,3,3,3,3,3,3,3,3,3},
					new int[]{2,1,1,1,1,1,2,2,2,2},
					new int[]{1,2,3,3,1,1,1,1,1,1},
					new int[]{1,3,2,1,3,1,1,1,1,1},
					new int[]{1,3,1,2,3,1,1,1,1,1},
					new int[]{1,1,3,3,2,1,1,1,1,1},
					new int[]{1,1,1,1,1,2,1,1,1,1},
				};
		
		KamiGrid grid = new KamiGrid(testArr,0);
		KamiGraph graph = new KamiGraph(grid);
		KamiSolver solver = new KamiSolver(6, graph);
		System.out.println("----");
		System.out.println(solver.solveProblem());
	}
}

