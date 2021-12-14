import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.StringSeqHelper;

public class main2 {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// \r\n    //"x=655\r\n"
		
		int i=0,j=0;
		String fold="y=447\r\n"
				+ "x=327\r\n"
				+ "y=223\r\n"
				+ "x=163\r\n"
				+ "y=111\r\n"
				+ "x=81\r\n"
				+ "y=55\r\n"
				+ "x=40\r\n"
				+ "y=27\r\n"
				+ "y=13\r\n"
				+ "y=6";
		//String fold="y=7\r\nx=5";
		/*int[] xs=new int[1020];
		int[] ys=new int[1020];*/
		int[] xs=new int[1020];
		int[] ys=new int[1020];
		int maxx=0,maxy=0;
		for(String s:fileContent.split("\r\n")) {
			xs[i]=Integer.parseInt(s.split(",")[0]);
			ys[i]=Integer.parseInt(s.split(",")[1]);
			if(xs[i]>maxx)maxx=xs[i];
			if(ys[i]>maxy)maxy=ys[i];
			i++;
		}
		boolean[][] grid=new boolean[maxy+1][maxx+1];
		for(i=0;i<xs.length;i++) {
			grid[ys[i]][xs[i]]=true;
		}
		
		grid=foldX(grid,655);
		
		int totA=0;
		for(boolean[] arr:grid) {
			for(boolean b:arr) {
				if(b)totA++;
			}
		}
		System.out.println("Sol A: "+totA);
		
		String[] folds=fold.split("\r\n");
		for(String s:folds) {
			if(s.split("=")[0].equals("x"))grid=foldX(grid,Integer.parseInt(s.split("=")[1]));
			else if(s.split("=")[0].equals("y"))grid=foldY(grid,Integer.parseInt(s.split("=")[1]));
		}
		for(i=0;i<grid.length;i++) {
			System.out.println(" ");
			for(j=0;j<grid[0].length;j++) {
				/*if(grid[i][j]&&(i>9||j>9))System.out.print("O");
				else System.out.print("   ");
				if(grid[i][j])System.out.print(i+""+j);
				else System.out.print("  ");*/
				if(grid[i][j])System.out.print(grid[i][j]);
				if(!grid[i][j])System.out.print("    ");
			}
		}
		System.out.println("debug");
	}
	
	public static boolean[][] foldX(boolean[][] grid,int x) {
		boolean[][] right=new boolean[grid.length][(grid[0].length-x)-1];
		boolean[][] left=new boolean[grid.length][x];
		System.out.println("right: "+right[0].length+" left "+left[0].length);
		System.out.println("    fold X             ");
		for(int i=0;i<left.length;i++) {
			for(int j=0;j<left[0].length;j++) {
				left[i][j]=grid[i][j];
			}
		}
		int a=0;
		for(int i=0;i<right.length;i++) {
			a=0;
			for(int j=x+1;j<grid[i].length;j++) {
				right[i][a]=grid[i][j];
				a++;
			}
		}
		for(int i=0;i<left.length;i++) {
			a=right[0].length-1;
			for(int j=0;j<left[0].length;j++) {
				if(right[i][a])left[i][j]=true;
				a--;
			}
		}
		return left;
	}	
	public static boolean[][] foldY(boolean[][] grid,int y) {
		boolean[][] top=new boolean[y][grid[0].length];
		boolean[][] bot=new boolean[y][grid[0].length];
		//System.out.println("top: "+top.length+" bot "+bot.length);
		System.out.println("      fold Y           ");
		for(int i=0;i<top.length;i++) {
			for(int j=0;j<top[0].length;j++) {
				top[i][j]=grid[i][j];
			}
		}		
		int a=0;
		for(int i=y+1;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				bot[a][j]=grid[i][j];
			}
			a++;
		}
		a=bot.length-1;
		for(int i=0;i<top.length;i++) {			
			for(int j=0;j<top[0].length;j++) {
				if(bot[a][j])top[i][j]=bot[a][j];
			}
			a--;
		}
		return top;
	}
	
}