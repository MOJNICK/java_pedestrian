package pedestrian_calculation;
import org.opencv.core.Rect;
import java.util.Comparator;
//public class RectExtended extends Rect {
//	public static compareTo()
//}

public class RectComparator implements Comparator<Rect> {
    @Override
    public int compare(Rect r1, Rect r2) {
    	if(r1.y>r2.y)
    		return 1;
    	else if(r1.y<r2.y) {
    		return -1;
    	}
    	else {
    		return 0;
    	}
    }
}