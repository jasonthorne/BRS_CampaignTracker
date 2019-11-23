
public class HistoricDate {
	
	private int startDate;
	private int endDate;
	private String airForce;
	private Name name;
	
	private enum Name{
		BATTLE_OF_BRITAIN, 
		GUADALCANAL, 
		STALINGRAD,
		ASSAULT_ON_THE_REICH, 
		KURSK, 
		THE_ITALIAN_CAMPAIGN,
		DEFENCE_OF_THE_HOME_ISLANDS;
	}
	
	HistoricDate(){ 
		setName(Name.BATTLE_OF_BRITAIN);
	}
	
	private void setName(Name name){
		this.name = name;
	}
	
	public Name getName() {
		return name;
	}
	
	
	
}
