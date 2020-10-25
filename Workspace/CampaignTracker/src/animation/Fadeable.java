package animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/** fades nodes in/out of view */

public interface Fadeable {
	
	enum FadeOption {
		
		//fade options:
		FADE_IN(0.0, 1.0),
		FADE_OUT(1.0, 0.0);
		
		//opacity transition values:
		private double fromVal;
		private double toVal;
		
		//constructor sets transition values:
		private FadeOption(double fromVal, double toVal) {
			this.fromVal = fromVal;
			this.toVal = toVal;
		}
	}
	
	static void fade(Node node, FadeOption fadeOption) {
		//add node to fade transition of 300ms:
		FadeTransition ft = new FadeTransition(Duration.millis(300), node);
		ft.setFromValue(fadeOption.fromVal); //set starting opacity value
		ft.setToValue(fadeOption.toVal); //set end opacity value
		ft.setCycleCount(1); //how many times the animation should happen (cycle)
		ft.setAutoReverse(false); //whether animation reverses on alternating cycles
		ft.play(); //play transition
	}
}
