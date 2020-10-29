package animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/** shakes node to alert itself to user */

public interface Shakeable {
	
	public static void shake(Node node) {
		//add node to translate transition of 50ms:
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50), node);
		translateTransition.setFromX(0f); //starting x pos
		translateTransition.setByX(10f); //end x pos 
		translateTransition.setCycleCount(4); //how many times the animation should happen (cycle)
		translateTransition.setAutoReverse(true); //whether animation reverses on alternating cycles
		translateTransition.playFromStart(); //Play animation from initial position in forward direction
	}
}