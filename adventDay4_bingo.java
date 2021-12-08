import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main2 {

	public static List<Integer> check=new ArrayList<Integer>();
	public static void main(String[] args) {
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		int[] estratti=new int[] {15,62,2,39,49,25,65,28,84,59,75,24,20,76,60,55,17,7,93,69,32,23,44,81,8,67,41,56,43,89,95,97,61,77,64,37,29,10,79,26,51,48,5,86,71,58,78,90,57,82,45,70,11,14,13,50,68,94,99,22,47,12,1,74,18,46,4,6,88,54,83,96,63,66,35,27,36,72,42,98,0,52,40,91,33,21,34,85,3,38,31,92,9,87,19,73,30,16,53,80};
		String[] rows=fileContent.split("\r\n");
		List<Integer> allns=new ArrayList<Integer>();
		for(String s:rows) {
			for(String z:s.split(" ")) {
				if(!z.equals("")) allns.add(Integer.parseInt(z));
			}
		}
		//costruzione matrici
		System.out.println(""+allns.size());
		List<int[][]>boards=new ArrayList<int[][]>();
		int m=0;
		for(int i=0;i<100;i++) {
			int boardsMat[][]=new int[5][5];
			for(int j=0;j<5;j++) {
				for(int k=0;k<5;k++) {
					boardsMat[j][k]=allns.get(m);
					m++;
				}
			}
			boards.add(boardsMat);
		}
		//controllo bingo
		boolean bingo=false;
		List<Integer>scores=new ArrayList<Integer>();
		for(int n:estratti) {
			check.add(n);
			System.out.println("estratto "+n);
			for(int i=0;i<boards.size();i++) {				
				bingo=bingo(n,boards.get(i));
				if(bingo) {
					scores.add(calculateScore(boards.get(i),n));
					boards.remove(i);
				}
			}
		}
		int a=0;
		//stampa risultati
		for(int j:scores) {
			System.out.println("score "+a+" "+j);
			a++;
		}
		System.out.println("**************");
		System.out.println("soluzione 1 : "+scores.get(0));
		System.out.println("*********");
		System.out.println("soluzione 2: "+scores.get(scores.size()-1));
	}
	public static Integer calculateScore(int[][] mat,int ls) {
		int n=0;
		for(int[]a:mat) {
			for(int b:a) {
				if(!check.contains(b))n+=b;
			}
		}
		return n*ls;
	}
	public static boolean bingo(int m,int[][] mat) {
		int row=0;
		int col=0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(mat[i][j]==m) {
					col=row=0;
					for(int a=0;a<5;a++) {
						if(check.contains(mat[a][j]))col++;
						if(col==5)return true;
					}
					for(int a=0;a<5;a++) {
						if(check.contains(mat[i][a]))row++;
						if(row==5)return true;
					}
				}
			}
		}		
		return false;		
	}
}
