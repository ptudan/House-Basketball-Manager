
import java.util.*;

public class main{
	public static void main(String[] arguments){
		ArrayList<Player> Team = new ArrayList<Player>();

		for(int i = 0; i<10000; i++){
			Team.add(new Player());
		}

		int[] positionCount = new int[6];
		int[] positionStatSum = new int[6]; 
		for(Player p : Team){
			positionCount[p.getPosition()]++;
			positionStatSum[p.getPosition()] += p.getPlayerStatAverage();
		}

		for(int i = 1; i<6; i++){
			System.out.println("position " + i + ":" +positionCount[i]);
			System.out.println("average: " + positionStatSum[i]/positionCount[i]);

		}
	}

	public static void testActions(){

		Team ash = new Team();
		int blocks = 0;
		int steals = 0;
		int threes = 0;
		int layups = 0;
		int mids = 0;
		for(int i = 1; i<10000; i++){
			if(ash.shootLayup(Gauss(50, 20), Gauss(50, 20), Gauss(50, 20), Gauss(50, 20), 0)){
				layups++;
			}

			if(ash.shootMidRange(Gauss(50, 20), Gauss(50, 20), 0)){
				mids++;
			}

			if(ash.shoot3(Gauss(50, 20), Gauss(50, 20), 0)){
				threes++;
			}

			if(ash.shotBlock(Gauss(50, 20), Gauss(50, 20), 60, Gauss(50, 20), Gauss(50, 20), 60)){
				blocks++;
			}

			if(ash.passSteal(Gauss(50, 20), Gauss(50, 20), Gauss(50, 20), Gauss(50, 20), Gauss(50, 20), Gauss(50,20))){
				steals++;
			}

		}

		System.out.println("Steals: " + steals + " Blocks: " + blocks + " threes: " + threes + " mids: " + mids + " layups: " + layups);
	}

	public static int Gauss(int std, int mean){
		Random r = new Random();
		int ret = -1;
		do{
			ret = (int)Math.round((r.nextGaussian()*std)+mean);
		}
		while(ret<0 || ret>100);
		return ret;
	}
}