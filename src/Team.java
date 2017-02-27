import java.util.*;
import java.lang.Math;

public class Team /*implements Comparable */{
	public String name;
	public ArrayList<Player> roster;
	public Coach c;
	private int wins;
	private int losses;
	public ArrayList<Player> onFloor;

	/*
		what I want to do to determine the winners of games is as follows:
		-determine who starts with the ball on a team's possession, giving each player a ranking based on position,
		and the following offensive stats: Handles, Wing/Post Passing, and court vision
		-the following options will be ranked for a player holding the ball:
			-their opponent steals the ball (onballdefense, handles) 
			-driving to layup (handles, layup range, onballspeed, vertical)
			-driving to mid range (handles, mid range)
			-shooting 3 (3PM)
			-passing to players, who will be ranked by
				-Their ability to score in locations near them, determined by position
				-their speed vs their defenders speed
				-their opponents offball defense (add offball offense stat? probably should)
			-as pass count increases, both shot likelihood AND successful attempt likelihood increase
		-once player decides to shoot:
			-shooting rating and opponents defensive ratings
			-number of passes on this possession
			-shot can be blocked, giving possession to the other team
		-if shot misses
			-include vertical
			-rebounding rates, determined by position and which position shot the ball
			-heavily leads towards defense
			-box out rates boosts own rebounding rating and adds to friendly rebounding chance
			-if offense rebounds, count as new possession, reset pass counter and begin again, begin again




	*/

	public Team(){

	}

	public void chooseStarters(){
		//for(int i = 0; )
	}

	public void playGame(Team t){
		int current_tick = 0;//0-480 (32 minutes, 4 seconds a tick)
		int hScore = 0;//home team
		int vScore = 0;//visiting team
		Team visitor = t;
		int playerWithBall;//1-5
		char teamWithBall;//h or v
	}

	//FOR GAME METHODS, VISITING TEAM PASSES AS PARAMETER, HOME TEAM PASSES NO PARAMETER

	private void startPossession(){
		/*
		HOW THIS FUNCTION WORKS:
			-calculates 2* handles + postPassing + wingPassing, giving a stat that is the overall ballhandling
			-puts these values into an array based on position, giving extra/less weight based on position
			-adds them all up to find the total ball handling on the team, then finds proportion of player on team
			-generates random number 0-100, and chooses position based on created weighted proportions
		
		Given expected numbers during player creation, ratios should be approximately this:
			-PG: 35%
			-SG: 25%
			-SF: 18%
			-PF: 12%
			-C: 10%
		*/
		double multiplier = 1.5;
		int weightedStatSum = 0;
		int[] wStatArr = new int[5];
		int ind = 0;
		for(Player p : onFloor){//This for loop generates the ballhandling stats and gives a multiplier based on position
			int temp = (int) ((multiplier*((2*p.getHandles()) + p.getPostPassing() + p.getWingPassing()))/4);
			wStatArr[ind] = temp;
			weightedStatSum += temp;
			ind++;
			multiplier -= .2;
		}

		for(int i = 4; i>0; i--){//this loop adds the 
			wStatArr[i] = wStatArr[i]/weightedStatSum;
			for(int j = i-1; j>-1; j--){
				wStatArr[i] += wStatArr[j];
			}
		}
		Random r = new Random();
		int possDetermines = r.nextInt(101);
		int curTotal = (wStatArr[0]/weightedStatSum);

	}

	private void startPossessionSimple(){
		//TODO: determine which player of t.onFloor will start the possession with the ball
		Random r = new Random();
		int ballCarrier = r.nextInt(101);
		if(ballCarrier<35){
			//POINT GUARD
		}
		else if(ballCarrier<60){
			//SHOOTING GUARD
		}
		else if(ballCarrier<78){
			//SMALL FORWARD
		}
		else if(ballCarrier<90){
			//POWER FORWARD
		}
		else{
			//CENTER
		}

	}

	public boolean shootLayup(int offShoot, int offVert, int defPost, int defVert, int passCount){
		/*
		Parameters:
			-offshoot is the 2Close stat
			-Verts are the verticals
			-defPost is the onball post defense
		Returns:
			bool on whether shot goes in
		*/
		Random r = new Random();
		int chance = 45 + (passCount*2) + ((offShoot - defPost)/4) + ((offVert - defVert)/6);
		if(chance>75){
			return 75 >= r.nextInt(101);//75% is max
		}
		else if(chance<30){
			return 30 >= r.nextInt(101);
		}
		else{
			return chance>=r.nextInt(101);
		}
	}

	public boolean shootMidRange(int offShoot, int defOn, int passCount){
		/*
		Parameters:
			-offShoot is 2Mid
			-defOn is onball defense of whatever position the offense is
		Returns:
			bool on whether shot goes in
		*/
		int chance = 37 + (passCount*2) + ((offShoot - defOn)/4);
		Random r = new Random();
		if(chance>75){
			return 75 >= r.nextInt(101);//75% is max
		}
		else if(chance<30){
			return 30 >= r.nextInt(101);
		}
		else{
			return chance>=r.nextInt(101);
		}

	}

	public boolean shoot3(int off, int def, int passCount){
		/*
		Parameters:
			-off is the shooters 3pt ability based upon location
			-def is the defenders onWingDef
			-passCount is current pass count on possession
		Returns:
			bool on whether shot goes in
		*/
		int chance = 30 + (passCount*2);
		Random r = new Random();
		if(off-def >=20){
			chance+= 20;
		}
		else if(off-def >=-20){
			chance += (off-def);
		}
		else{
			chance -= 20;
		}
		return (chance >= r.nextInt(101));
	}

	public boolean shotBlock(int offShoot, int offVert, int offHeight, int defOn, int defVert, int defHeight){
		/*
		Parameters:
			-offShoot is the shooting ability of a given shot
			-both offVert and defVert are verticals of players given stats
			-both offHeight and defHeight are the heights of players
			-defOn is the defenders onball defense
		Returns:
			bool on whether shot is blocked
		*/
		Random r = new Random();
		int chance = 3;
		chance += (defHeight - offHeight) + ((defVert - offVert)/5); //example 66" vs 63" with 70 vert vs 40 vert ends up being a 9" reach advantage
		if(chance < 1){
			return r.nextInt(1001)<10;//1% chance MIN
		}
		else if(chance > 10){
			return r.nextInt(101)<10; //10% chance MAX
		}
		else{
			return r.nextInt(101)<chance;//1-10% chance
		}
	}

	public boolean passSteal(int offPass, int offHandles, int offSpeed, int defOn, int defOff, int defSpeed){
		/*
		Parameters:
			-offPass is the PASSER'S passing stat to the desired location
			-offHandles is the CATCHER's handles stat
			-offSpeed is the CATCHER's speed stat (breakaways)
			-defOn is the PASSER'S DEFENDER'S OnPositionDef stat
			-defOff is the CATCHER'S DEFENDER'S OffPositionDef stat
			-defSpeed is the CATCHER'S DEFENDER'S speed stat

		Returns:
			bool on whether the pass/drive is stolen
		*/
		Random r = new Random();
		int chance = 200; //2.00%
		chance += (10*(defOn - offPass))+ (5*(defSpeed - offSpeed)) + (5*(defOff - offHandles));

		if(chance>1000){
			return r.nextInt(101)<10;//10%
		}
		else if(chance<0){
			return r.nextInt(101)==1;//1%
		}
		else{
			return r.nextInt(10000)<=chance;//1-10%
		}
	}



}