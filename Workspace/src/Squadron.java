import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Squadron {
	
	private List<Pilot>pilots = new ArrayList<Pilot>();
	private final int MAX_PILOT_NUM = 12;
	private final int MIN_PILOT_NUM = 6;
	private int pilotNum = 0;
	
	private final int STARTING_SKILL_POINTS = 24;
	private int skillPoints = 0;
	
	void addPilot(){
		pilots.add(new Pilot());
	}
	
	
	
}

