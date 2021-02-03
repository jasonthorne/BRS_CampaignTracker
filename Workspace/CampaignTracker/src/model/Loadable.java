package model;

public interface Loadable {
	
	/*boolean wasCreated = false;
	boolean wasUploaded = false;
	boolean wasDownloaded = false;*/
	
	//force boolean getters:
	abstract boolean getWasCreated();
	abstract boolean getIsDownloaded();
	abstract boolean getIsUploaded();
	
	
}
