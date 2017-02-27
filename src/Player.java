import java.util.*;
import java.lang.Math;


public class Player{
	private String name;
	private int age;
	private int year;
	private int height;
	private HashMap<String, Integer> stats;
	private int position; //1 2 3 4 5 (you should know what these are if you're reading the comments on my code :D)


	/* Don't know how to store all of these statistics yet but the ones I want are:
		3pt mid, 3pt wings - determines likelyhood to be passed the ball while there, chances shot goes in
		2pt 0-5, 2pt 5-10, 2pt 10-15, 2pt 15+ 
		offball speed, onball speed
		passing to post, passing to wings - higher means lower chances of interception
		handles - higher means less chance of being stolen from, higher chance of ankle breaker
		vertical - affects 2pt 0-5 and rebounding, purely magnitude stat
		boxing out - higher raises other members rebounding, lower lowers it
		rebounding - 
		humor type, humor rating
		drug/alcohol use -- probably not in all likelihood
		offball/onball post/wing defense
		court vision - lowers chance to be stolen, raises 
		popularity - closer in average the team is the higher chemistry is
		BBIQ - the higher the more likely they choose the correct shots/ passes
		jealousy - the higher the lower stats will be when PT is low
		selfishness - higher likelyhood to shoot (can be good when players are good)

		

	*/

	public Player(){
		name = "Connor";
		Random r = new Random();
		stats = new HashMap<>(100);
		initializeStats();
		generateRandomFreshman();

	}

	public int getAge(){
		return age;
	}

	public int getHeight(){
		return height;
	}

	public int year(){
		return year;
	}

	private int Gaussian(int std, int mean){
		Random r = new Random();
		return (int)Math.round((r.nextGaussian()*std)+mean);
	}

	private int GaussianLoop(int std, int mean){
		Random r = new Random();
		int ret = -1;
		do{
			ret = (int)Math.round((r.nextGaussian()*std)+mean);
		}
		while(ret<0 || ret>100);
		return ret;
	}

	private void initializeStats(){
		//CRAP CODE, IF I WRITE A NEW STAT I MUST ADD ITS KEY TO THIS LIST
		String[] temp = {"3PM", "3PW", "Layup Range", "Mid Range",
		"Speed", "PostPassing", "WingPassing", "Handles","Vertical", "Box Out", 
		"Rebounding", "funny", "Offball Post Defense", "Offball Wing Defense",
		"Onball Post Defense", "Onball Wing Defense", "Court Vision", "Fetchlol",
		"buzzword", "not Ron", "selfishness"};
		for(String s : temp){
			stats.put(s, 0);
		}
	}

	public int get3Mid(){
		return stats.get("3PM");
	}

	public int get3Wings(){
		return stats.get("3PW");
	}

	public int get2Close(){
		return stats.get("Layup Range");
	}

	public int get2Mid(){
		return stats.get("Mid Range");
	}

	public int getSpeed(){
		return stats.get("Speed");
	}

	public int getPostPassing(){
		return stats.get("PostPassing");
	}

	public int getWingPassing(){
		return stats.get("WingPassing");
	}

	public int getHandles(){
		return stats.get("Handles");
	}

	public int getVertical(){
		return stats.get("Vertical");
	}

	public int getBoxOut(){
		return stats.get("Box Out");
	}

	public int getRebounding(){
		return stats.get("Rebounding");
	}

	public int getHumor(){
		return stats.get("funny");
	}

	public int getOffPostDef(){
		return stats.get("Offball Post Defense");
	}

	public int getOffWingDef(){
		return stats.get("Offball Wing Defense");
	}

	public int getOnPostDef(){
		return stats.get("Onball Post Defense");
	}

	public int getOnWingDef(){
		return stats.get("Onball Wing Defense");
	}


	public int getPopularity(){
		return stats.get("Fetchlol");
	}

	public int getJealousy(){
		return stats.get("not Ron");
	}

	public int getSelfishness(){
		return stats.get("selfishness");
	}

	public void update3Mid(int value){
		stats.put("3PM", stats.get("3PM") + value);
	}

	public void update3Wings(int value){
		stats.put("3PW", stats.get("3PW") + value);
	}

	public void update2Close(int value){
		stats.put("Layup Range", stats.get("Layup Range") + value);
	}


	public void update2Mid(int value){
		stats.put("Mid Range", stats.get("Mid Range") + value);
	}

	public void updateSpeed(int value){
		stats.put("Speed", stats.get("Speed") + value);
	}

	public void updatePostPassing(int value){
		stats.put("PostPassing", stats.get("PostPassing") + value);
	}

	public void updateWingPassing(int value){
		stats.put("WingPassing", stats.get("WingPassing") + value);
	}

	public void updateHandles(int value){
		stats.put("Handles", stats.get("Handles") + value);
	}

	public void updateVertical(int value){
		stats.put("Vertical", stats.get("Vertical") + value);
	}

	public void updateBoxOut(int value){
		stats.put("Box Out", stats.get("Box Out") + value);
	}

	public void updateRebounding(int value){
		stats.put("Rebounding", stats.get("Rebounding") + value);
	}

	public void updateHumor(int value){
		stats.put("funny", stats.get("funny") + value);
	}

	public void updateOffPostDef(int value){
		stats.put("Offball Post Defense", stats.get("Offball Post Defense") + value);
	}

	public void updateOffWingDef(int value){
		stats.put("Offball Wing Defense", stats.get("Offball Wing Defense") + value);
	}

	public void updateOnPostDef(int value){
		stats.put("Onball Post Defense", stats.get("Onball Post Defense") + value);
	}

	public void updateOnWingDef(int value){
		stats.put("Onball Wing Defense", stats.get("Onball Wing Defense") + value);
	}

	public void updatePopularity(int value){
		stats.put("Fetchlol", stats.get("Fetchlol") + value);
	}

	public void updateJealousy(int value){
		stats.put("not Ron", stats.get("not Ron") + value);
	}

	public void updateSelfishness(int value){
		stats.put("selfishness", stats.get("selfishness") + value);
	}

	private void generateRandomFreshman(){
		//System.out.println("at fresh");
		year = 9;
		generatePersonality();
		Random r = new Random();
		age = 14 + r.nextInt(2);
		do{
			height = Gaussian(3, 63);//Generates a random height for the player based on age 14-15
		}while(height<54 || height >78);
		choosePosition();

	}

	private void generatePersonality(){
		Random r = new Random();
		int funny = GaussianLoop(20, 50);
		updateHumor(funny);

		int popularity = GaussianLoop(20, 40) + (funny/5);//GOING OVER 100 IS ALLOWED.
		updatePopularity(popularity);

		int jealousy = GaussianLoop(20, 50) - (popularity/5);
		if(jealousy < 0){
			jealousy = 0;
		}
		updateJealousy(jealousy);

		int selfishness = GaussianLoop(20, 50);
		updateSelfishness(selfishness);

	}

	private void choosePosition(){
		Random r = new Random();
		if(height>=Gaussian(2, 67)){
			makeCenter();
		}
		else if(height >= Gaussian(2, 65)){
			makePower();
		}
		else if(height >= Gaussian(2, 63)){
			makeSmall();
		}
		else if(height >= Gaussian(2, 61)){
			makeShooting();
		}
		else{
			makePoint();
		}
	}

	private void makeCenter(){
		position = 5;
		update3Mid(GaussianLoop(15, 25));
		update3Wings(GaussianLoop(15, 40));
		update2Close(GaussianLoop(15, 70));
		update2Mid(GaussianLoop(15, 55));
		updateSpeed(GaussianLoop(20, 45));
		updatePostPassing(GaussianLoop(15, 55));
		updateWingPassing(GaussianLoop(15, 56));
		updateHandles(GaussianLoop(10, 30));
		updateVertical(GaussianLoop(20, 50));
		updateBoxOut(GaussianLoop(15, 75));
		updateRebounding(GaussianLoop(15, 75));
		updateOffPostDef(GaussianLoop(15, 70));
		updateOnPostDef(GaussianLoop(15, 75));
		updateOffWingDef(GaussianLoop(15, 50));
		updateOnWingDef(GaussianLoop(15, 50));
	}

	private void makePower(){
		position = 4;
		update3Mid(GaussianLoop(15, 35));
		update3Wings(GaussianLoop(15, 55));
		update2Close(GaussianLoop(10, 63));
		update2Mid(GaussianLoop(15, 60));
		updateSpeed(GaussianLoop(20, 50));
		updatePostPassing(GaussianLoop(15, 60));
		updateWingPassing(GaussianLoop(15, 50));
		updateHandles(GaussianLoop(10, 35));
		updateVertical(GaussianLoop(20, 50));
		updateBoxOut(GaussianLoop(15, 63));
		updateRebounding(GaussianLoop(15, 63));
		updateOffPostDef(GaussianLoop(15, 60));
		updateOnPostDef(GaussianLoop(15, 60));
		updateOffWingDef(GaussianLoop(15, 55));
		updateOnWingDef(GaussianLoop(15, 55));
		
	}

	private void makeSmall(){
		position = 3;
		update3Mid(GaussianLoop(15, 55));
		update3Wings(GaussianLoop(15, 55));
		update2Close(GaussianLoop(20, 45));
		update2Mid(GaussianLoop(15, 60));
		updateSpeed(GaussianLoop(20, 50));
		updatePostPassing(GaussianLoop(15, 55));
		updateWingPassing(GaussianLoop(15, 55));
		updateHandles(GaussianLoop(15, 50));
		updateVertical(GaussianLoop(20, 50));
		updateBoxOut(GaussianLoop(15, 53));
		updateRebounding(GaussianLoop(15, 55));
		updateOffPostDef(GaussianLoop(15, 52));
		updateOnPostDef(GaussianLoop(15, 52));
		updateOffWingDef(GaussianLoop(15, 63));
		updateOnWingDef(GaussianLoop(15, 63));
		
	}
	
	private void makeShooting(){
		position = 2;
		update3Mid(GaussianLoop(15, 65));
		update3Wings(GaussianLoop(15, 65));
		update2Close(GaussianLoop(10, 63));
		update2Mid(GaussianLoop(15, 70));
		updateSpeed(GaussianLoop(20, 55));
		updatePostPassing(GaussianLoop(15, 60));
		updateWingPassing(GaussianLoop(15, 50));
		updateHandles(GaussianLoop(10, 55));
		updateVertical(GaussianLoop(20, 56));
		updateBoxOut(GaussianLoop(15, 45));
		updateRebounding(GaussianLoop(15, 50));
		updateOffPostDef(GaussianLoop(15, 45));
		updateOnPostDef(GaussianLoop(15, 45));
		updateOffWingDef(GaussianLoop(15, 63));
		updateOnWingDef(GaussianLoop(15, 68));
		
	}

	private void makePoint(){
		position = 1;
		update3Mid(GaussianLoop(15, 60));
		update3Wings(GaussianLoop(15, 60));
		update2Close(GaussianLoop(10, 59));
		update2Mid(GaussianLoop(15, 55));
		updateSpeed(GaussianLoop(20, 50));
		updatePostPassing(GaussianLoop(15, 65));
		updateWingPassing(GaussianLoop(15, 70));
		updateHandles(GaussianLoop(10, 75));
		updateVertical(GaussianLoop(20, 50));
		updateBoxOut(GaussianLoop(15, 40));
		updateRebounding(GaussianLoop(15, 60));
		updateOffPostDef(GaussianLoop(15, 45));
		updateOnPostDef(GaussianLoop(15, 30));
		updateOffWingDef(GaussianLoop(15, 70));
		updateOnWingDef(GaussianLoop(15, 70));
		
	}

	public int getPlayerStatAverage(){
		int sum = 0;
		int count = 0;
		for(Integer i : stats.values()){
			count++;
			sum+=i;
		}
		return sum/count;
	}

	public void printPlayer(){
		System.out.println(name + " is " + height + "\", " + age + " years old he is a " + position);
		for(Map.Entry<String, Integer> entry : stats.entrySet()){
			System.out.println("Statistic: " + entry.getKey() + " Value: " + entry.getValue());
		}
	}
	public int getPosition(){
		return position;
	}
}