import java.io.*;
import java.util.*;
import java.util.Random;

class popGen {

	public static boolean flipCoin() {
		Random rand = new Random();
		int coin = rand.nextInt(2); 
		if(coin == 1) { return true; }
		else { return false; }
	}

	public static String drawRandomCard(List<String> initPop) {
		Random rand = new Random();
    	int index = rand.nextInt(initPop.size());
    	String allele = initPop.get(index);
    	initPop.remove(index);
    	//System.out.println("Drew " + allele);
    	return allele;
	}

	public static void newCross(String p1, String p2, List<String> initPop) {
		//System.out.println("Pair " + p1 + " " + p2);
		if(p1.equals("AA") && p2.equals("AA")) {
			for(int i=0; i<6; i++) {
				initPop.add("AA");
			}
	 	} 
	 	else if(p1.equals("aa") && p2.equals("aa")) {
	 		for(int i=0; i<6; i++) {
				initPop.add("aa");
			}
	 	}
	 	else if(((p1.equals("AA") && p2.equals("Aa"))) || ((p1.equals("Aa") && p2.equals("AA")))) {
	 		for(int i=0; i<6; i++) {
				boolean coin = flipCoin();
				if(coin) { initPop.add("AA"); }
				else { initPop.add("Aa"); }
			}
	 	}
	 	else if(((p1.equals("aa") && p2.equals("Aa"))) || ((p1.equals("Aa") && p2.equals("aa")))) {
	 		for(int i=0; i<6; i++) {
				boolean coin = flipCoin();
				if(coin) { initPop.add("aa"); }
				else { initPop.add("Aa"); }
			}
	 	}
	 	else if(p1.equals("Aa") && p2.equals("Aa")) {
	 		for(int i=0; i<6; i++) {
				boolean c1 = flipCoin();
				boolean c2 = flipCoin();	
				if(c1 && c2) { initPop.add("AA"); }	
				else if (!c1 && !c2) { initPop.add("aa"); }	
				else { initPop.add("Aa"); }
	 		}
		}
		else {
			for(int i=0; i<6; i++) {
				initPop.add("Aa");
			}
		}
	}

	public static void initList(List<String> initPop) {
		for(int i=0; i< 6; i++) { initPop.add("AA"); }
	 	for(int i=0; i< 6; i++) { initPop.add("aa"); }
	 	for(int i=0; i< 12; i++) { initPop.add("Aa"); }
	}

	 public static void main(String[] args) throws InterruptedException{
	 	int generation = 0;
	 	String pairs[] = new String[8];
	 	// Initial Population
	 	List<String> initPop = new ArrayList<>();
	 	initList(initPop);
	 	Collections.shuffle(initPop);

	 	System.out.printf("\n\nGen: %2d | #AA: %2d | #Aa: %2d | #aa: %2d | Total: 24 | p: %f | q: %f\n", generation, 6, 12, 6, .5, .5);

	 	// Model next generations;
	 	while(generation <20) {
		 	int countaa = 0;
		 	int countAA = 0;
		 	int countAa = 0;
		 	for(int i=0; i<8; i++) {
		 		pairs[i] = drawRandomCard(initPop);
		 	}
		 	initPop.clear();
		 	for(int i=0; i<8; i+=2) {
		 		newCross(pairs[i], pairs[i+1], initPop);
		 	}
		 	for(String curr: initPop) {
		 		if(curr.equals("AA")) { countAA++; }
		 		else if(curr.equals("aa")) {countaa++; }
		 		else { countAa++; }
		 	}

		 	double p = (double)(countAA + (double)((.5) * countAa))/24;
		 	double q = (double)(countaa + (double)((.5) * countAa))/24;
		 	generation++;

		 	System.out.printf("Gen: %2d | #AA: %2d | #Aa: %2d | #aa: %2d | Total: %2d | p: %f | q: %f\n", generation, countAA, countAa, countaa, initPop.size(), p, q);
	 	}

	 System.out.println("\n\n");

	 }
}