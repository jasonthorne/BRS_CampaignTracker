import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	
		//have a list of campaigns
		
		
		Campaign campaign = new Campaign();
		
		
		//-----------https://stackoverflow.com/questions/2269803/how-to-get-all-enum-values-in-java
		List<PilotSkill> test = Arrays.asList(PilotSkill.values());
		test.forEach(PilotSkill -> {
		    System.out.println(PilotSkill);
		    });
		//---------------
		HistoricDate a = new HistoricDate();
		
		System.out.println(a.getNames());
		//create a campaign
		
		//open a campaign
		
		//delete a campaign
		
		//close program

		
	}

}
