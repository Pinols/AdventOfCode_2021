import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
		System.out.println("asdasd");
		//String[] rows=fileContent.split("\r\n");
		int daysB=256;
		int daysA=80;
		List<Integer> pesci=new ArrayList<Integer>();
		String[] infishes=fileContent.split(",");
		for(String s:infishes) {
			pesci.add(Integer.parseInt(s));
		}
		int[] pesciArray = new int[pesci.size()];
		pesciArray=pesci.stream().mapToInt(i->i).toArray();
		long[] fishes=new long[9];
		long[] fishes2=new long[fishes.length];
		fishes2=fishes;
		for(int i=0;i<pesciArray.length;i++) {
			fishes[pesciArray[i]]++;
		}
		for(int i=0;i<daysB;i++) {
			fishes=arrayDay(fishes);
		}
		long total=0;
		for(int i=0;i<fishes.length;i++) {
			total+=fishes[i];
		}
		System.out.println("Soluzione B: "+total);		
	}	
	private static long[] arrayDay(long[] fishes) {
		long parents=fishes[0];
		for(int i=0;i<fishes.length-1;i++) {
			fishes[i]=fishes[i+1];
		}
		fishes[6]+=parents;
		fishes[8]=parents;		
		return fishes;
	}
}