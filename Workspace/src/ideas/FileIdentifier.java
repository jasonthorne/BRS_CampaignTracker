package ideas;

//for identifying files during path creation:
public enum FileIdentifier {
	
	DESCRIPTION("Description");
	
	private final String fileIdentifier; //name of identifier
	private FileIdentifier(String fileIdentifier) { this.fileIdentifier = fileIdentifier; } //constructor sets name of identifier
	@Override 
	public String toString() { return fileIdentifier; } //return identifier

}
