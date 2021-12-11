import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class main2 {
	static int steps=0;
	static int currentStepFlashes=0;
	static int flashesA=0;
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// \r\n
		int i=0,j=0;
		int[][] grid=new int[10][10];
		for(String s:fileContent.split("\r\n")) {
			for(j=0;j<grid[i].length;j++) {
				grid[i][j]=Character.getNumericValue(s.toCharArray()[j]);
			}
			i++;
		}
		i=j=0;
		int stepsA=100;
		for(i=0;i<stepsA;i++) {
			step(grid);
		}
		System.out.println("sol A: "+flashesA);
		do {
			step(grid);
		}while(currentStepFlashes<100);
		System.out.println("Sol B: "+steps);
	}
	public static void step(int[][] grid) {
		currentStepFlashes=0;
		steps++;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
		   		grid[i][j]++;
		    }
		}
		for(int i=0;i<grid.length;i++) {
		   	for(int j=0;j<grid[i].length;j++) {
		   		if(grid[i][j]>9) {
		   			flash(grid,i,j);
		   		}
		    }
		}
	}
	private static void flash(int[][] grid, int x, int y) {
		currentStepFlashes++;
		grid[x][y]=0;
		flashesA++;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				//need to find a better way for this >_>
				if((i==x+1&&j==y+1)||(i==x+1&&j==y)||(i==x+1&&j==y-1)||(i==x&&j==y+1)
						||(i==x&&j==y-1)||(i==x-1&&j==y+1)||(i==x-1&&j==y)||(i==x-1&&j==y-1)) {
					if(grid[i][j]!=0) {
						grid[i][j]++;     //add 1 to surrounds of flash if not flashed already this step,
						if(grid[i][j]==10) { //then flash again if charged
							flash(grid,i,j);
						}
					}
				}
			}
		}
	}
}