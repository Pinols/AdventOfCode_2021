import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.awt.Point;

public class main2 {

	static List<Point> points=new ArrayList<Point>();
	static Integer tot=0;
	static boolean[][]check=new boolean[100][100];
	
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// \r\n
		int[][] grid=new int[100][100];
		int i=0,j=0;
		for(String s:fileContent.split("\r\n")) {
			j=0;
			for(char c:s.toCharArray()) {
				grid[i][j]=Character.getNumericValue(c);
				j++;
			}
			i++;
		}
		i=0;j=0;
		System.out.println("sol A: "+findA(grid));
		List<Integer> pits=new ArrayList<Integer>();
		for(Point p:points) {
			findBasin(grid,(int)Math.round(p.getX()),(int)Math.round(p.getY()));
			System.out.println("Current Basin: "+tot);
			pits.add(tot.intValue());
			tot=0;
		}
		pits.sort(Comparator.naturalOrder());
		for(Integer pit:pits) {
			System.out.println("PIT: "+pit);
		}
		System.out.println("Sol B: "+(pits.get(pits.size()-1)*pits.get(pits.size()-2)*pits.get(pits.size()-3)));
	}

	
	private static void findBasin(int[][] grid, int x, int y) {
		{
			// If OOB or on a "mountain"(9) -> return
		    if ( y < 0 || x <0 || x>=grid.length || y>=grid.length ||check[x][y]==true )
		        return;
		    if(grid[x][y]==9)return;
		    
		    // Else set cell as visited and count it for current basin
		    check[x][y]=true;
		    tot++;
		    
		    // Ricorro per tutte le direzioni
		    findBasin(grid, x+1, y);
		    findBasin(grid, x-1, y);
		    findBasin(grid, x, y+1);
		    findBasin(grid, x, y-1);
		}
	}
	// modified to include points.add() ptB
	// awful code >_>
	private static int findA(int[][] grid) {
		int total=0;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				if(i==0&&j==0) {
					if(grid[i][j+1]>grid[i][j]&&grid[i+1][j]>grid[i][j]) {
						total+=(grid[i][j]+1);
						points.add(new Point(i,j));
					} 
				}
				else if(i==0&&j==99) {
					if(grid[i][j-1]>grid[i][j]&&grid[i+1][j]>grid[i][j]) {
						points.add(new Point(i,j));
						total+=(grid[i][j]+1);
					} 
				}
				else if(i==99&&j==0) {
					if(grid[i-1][j]>grid[i][j]&&grid[i][j+1]>grid[i][j]) {
						total+=(grid[i][j]+1);
						points.add(new Point(i,j));
					} 
				}
				else if(i==0&&j<99) {
					if(grid[i][j+1]>grid[i][j]&&grid[i][j-1]>grid[i][j]&&grid[i+1][j]>grid[i][j]) {
						points.add(new Point(i,j));
						total+=(grid[i][j]+1);
					} 
				}
				else if(j==0&&i<99) {
					if(grid[i][j+1]>grid[i][j]&&grid[i-1][j]>grid[i][j]&&grid[i+1][j]>grid[i][j]) {
						points.add(new Point(i,j));
						total+=(grid[i][j]+1);
					} 
				}
				else if(i==99&&j==99) {
					if(grid[i][j-1]>grid[i][j]&&grid[i-1][j]>grid[i][j]) {
						total+=(grid[i][j]+1);
						points.add(new Point(i,j));
					}
				}
				else if(i==99) {
					if(grid[i][j-1]>grid[i][j]&&grid[i][j+1]>grid[i][j]&&grid[i-1][j]>grid[i][j] ) {
						points.add(new Point(i,j));
						total+=(grid[i][j]+1);
					} 
				}
				else if(j==99) {
					if(grid[i-1][j]>grid[i][j]&&grid[i+1][j]>grid[i][j]&&grid[i][j-1]>grid[i][j] ) {
						total+=(grid[i][j]+1);
						points.add(new Point(i,j));
					}
				}
				else if(grid[i-1][j]>grid[i][j]&&grid[i+1][j]>grid[i][j]&&grid[i][j-1]>grid[i][j]&&grid[i][j+1]>grid[i][j] ) {
						points.add(new Point(i,j));
						total+=(grid[i][j]+1);
				}
			}
		}
		return total;
	}
}