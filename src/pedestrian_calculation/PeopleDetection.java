package pedestrian_calculation;

import java.util.Arrays;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.HOGDescriptor;

public class PeopleDetection {
	public static MatOfRect detectPeoples(Mat img) {
		double scaling = 1.15;
		double hitThreshold = 0.1;
		Size winStride = new Size(4,4);
		Size padding = new Size(8,8);
		double finalThreshold = 1;
		boolean useMeanshiftGrouping = false;
		return detectPeoples(img, hitThreshold, winStride, padding, scaling, finalThreshold, useMeanshiftGrouping);
	}
  public static MatOfRect detectPeoples(Mat img, double hitThreshold, Size winStride, Size padding, double scaling, double finalThreshold, boolean useMeanshiftGrouping) {
	  MatOfRect foundPeoples = new MatOfRect();
	  MatOfDouble foundWeights = new MatOfDouble();
//		  return HogDescriptor("/home/szozda/git/opencv/data/hogcascades/hogcascade_pedestrians.xml");
	  HOGDescriptor hog = new HOGDescriptor();
	  hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
	  hog.detectMultiScale(img, foundPeoples, foundWeights, hitThreshold, winStride, padding, scaling, finalThreshold, useMeanshiftGrouping);
	  return foundPeoples;
  }
    
  public static MatOfRect NonMaxima(MatOfRect foundPeoples) {
	  Rect[] aryfoundPeoples = foundPeoples.toArray();
	  Arrays.sort(aryfoundPeoples, new RectComparator());
	  MatOfRect nonmaximafoundPeoples = new MatOfRect();
	  return nonmaximafoundPeoples;
  }
}
