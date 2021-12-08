import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class main2 {

	public static List<Integer> check=new ArrayList<Integer>();
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String[] rows=fileContent.split("\r\n");
		List<String[]> couples=new ArrayList<String[]>();
		List<Integer> allNs=new ArrayList<Integer>();
		int i=0;
		for(String s:rows) {
			for(String z:s.split(" -> ")) {
				for(String v:z.split(",")) {
					allNs.add(Integer.parseInt(v));
				}
			}
		}
		int[][] grid=new int[1000][1000];
		List<Integer> xS=new ArrayList<Integer>();
		List<Integer> yS=new ArrayList<Integer>();
		List<Integer> xE=new ArrayList<Integer>();
		List<Integer> yE=new ArrayList<Integer>();
		i=0;
		for(Integer n:allNs) {
			if(i==0) {
				xS.add(n);
				i++;
			}else if(i==1) {
				yS.add(n);
				i++;
			}else if(i==2) {
				xE.add(n);
				i++;
			}else {
				yE.add(n);
				i=0;
			}
		}
		//processo linee e modifico griglia
		for(i=0;i<xS.size();i++) {
			grid=processLines(grid,xS.get(i),yS.get(i),xE.get(i),yE.get(i));
		}
		//conto griglia per soluzione		
		int result=count(grid);		
		System.out.println("**************");
		System.out.println("Result: "+result);
	}
	private static int count(int[][] grid) {
		int count=0;
		for(int[] arr:grid) {
			for(int n:arr) {
				if(n>1)count++;
			}
		}
		return count;
	}
	static int[][] processLines(int[][] grid,int xS, int yS, int xE, int yE) {
		int maxX=xS>xE?xS:xE;
		int maxY=yS>yE?yS:yE;
		int minX=xS<xE?xS:xE;
		int minY=yS<yE?yS:yE;
		if(xS==xE) {
			int diff=maxY-minY;
			for(int i=0;i<diff+1;i++) {
				grid[xS][minY]++;
				minY++;
			}
		}
		else if(yS==yE) {
			int diff=maxX-minX;
			for(int i=0;i<diff+1;i++) {
				grid[minX][yS]++;
				minX++;
			}
		}
		else{
			int diff=maxX-minX;
			int i=0;
			do {
				grid[xS][yS]++;
				if(xS>xE)xS--;
				else xS++;
				if(yS>yE)yS--;
				else yS++;
				i++;
			}while(i<diff+1);
		}
		return grid;
	}

}