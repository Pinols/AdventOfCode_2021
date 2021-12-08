import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class main2 {

	static int[] gamma;
	static int[] epsilon;
	public static void main(String[] args) {
		
		String fileContent="";
		try {
			fileContent =new String(Files.readAllBytes(Paths.get("C:\\Users\\Stefano\\Desktop\\advent.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String sarr[]=fileContent.split("\r\n");
		int[][] mat = new int[1000][12];
		int i=0,j=0;
		for(String s:sarr) {
			j=0;
			for(char c:s.toCharArray()) {
				mat[i][j]=c-'0';
				j++;
			}
			i++;
		}
		gamma=new int[12];
		epsilon=new int[12];
		
		int[] gamma2=new int[12];
		int[] epsilon2=new int[12];
		
		int zero=0;
		int uno=0;
		i=0;
		int y=0;
		do {
			zero=0;
			uno=0;
			for(int x=0;x<mat.length;x++) {
				switch(mat[x][y]) {
				case 0:
					zero++;
					break;
				case 1:
					uno++;
					break;
				}
			}
			gamma[i]=zero>uno?0:1;
			epsilon[i]=zero<uno?0:1;
			
			if(zero==uno) {
				gamma2[i]=1;
				epsilon2[i]=0;
			}else {
				gamma2[i]=zero>uno?0:1;
				epsilon2[i]=zero<uno?0:1;
			}			
			i++;
			y++;
		}while(i<12);
		
		System.out.println("gamma: ");
		for(int n:gamma) {			
			System.out.print(n);
		}
		System.out.println(" ");
		System.out.println("epsilon: ");
		for(int n:epsilon) {			
			System.out.print(n);
		}
		
		//
		// part 2 
		//
		System.out.println(" ");
		System.out.println("gamma2: ");
		for(int n:gamma2) {			
			System.out.print(n);
		}
		System.out.println(" ");
		System.out.println("epsilon2: ");
		for(int n:epsilon2) {			
			System.out.print(n);
		}
		
		// oxy
		zero=uno=i=j=y=0;
		List<int[]> oxy=new ArrayList<int[]>();
		for(i=0;i<mat.length;i++) {
			if(mat[i][0]==gamma2[0])oxy.add(mat[i]);
		}
		for(i=1;i<12;i++) {
			int z=0;
			z=findCommon(oxy,i);
			//if(oxy.size()>1)gamma2[i]=findCommon(oxy,i);
			if(oxy.size()>1)oxy=filtraOxy(oxy,i,z);
		}
		
		//co2
		zero=uno=i=j=y=0;
		List<int[]> co2=new ArrayList<int[]>();
		for(i=0;i<mat.length;i++) {
			if(mat[i][0]==epsilon2[0])co2.add(mat[i]);
		}
		for(i=1;i<12;i++) {
			int z=0;
			if(co2.size()>1)z=findUncommon(co2,i);
			if(co2.size()>1)co2=filtraCo2(co2,i,z);
		}
		
		//printing
		System.out.println(" ");
		System.out.println("o2 rating: ");
		for(int n[]:oxy) {
			for(int m:n) {
				System.out.print(""+m);
			}
		}
		System.out.println(" ");
		System.out.println("co2 rating: ");
		for(int n[]:co2) {
			for(int m:n) {
				System.out.print(""+m);
			}
		}
		System.out.println();
		
		
	
	}
	public static int findCommon(List<int[]> l,int n){
		int zero=0;
		int uno=0;
		int i=0;
		for(int[] arr:l){
			if(arr[n]==0)zero++;
			else if(arr[n]==1)uno++;
		}
		if(zero==uno)return 1;
		return zero>uno?0:1;
	}
	public static int findUncommon(List<int[]> l,int n){
		int zero=0;
		int uno=0;
		int i=0;
		for(int[] arr:l){
			if(arr[n]==0)zero++;
			else if(arr[n]==1)uno++;
		}
		if(zero==uno)return 0;
		return zero<uno?0:1;
	}
	
	public static List<int[]> filtraOxy(List<int[]> l,int col,int bit){
		List<int[]> toReturn=new ArrayList<int[]>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i)[col]==bit) {
				toReturn.add(l.get(i));
			}
		}
		return toReturn;
	}
	public static List<int[]> filtraCo2(List<int[]> l,int col,int bit){
		List<int[]> toReturn=new ArrayList<int[]>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i)[col]==bit) {
				toReturn.add(l.get(i));
			}
		}
		return toReturn;
	}
	

}