package pedestrian_calculation;
import java.util.Collections;
import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Rect;
import org.opencv.core.Point;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import java.util.Arrays;
import org.opencv.highgui.Highgui;

class mainClass {

  static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
   
  public static void main(String[] args) {
    System.out.println("Welcome to OpenCV " + Core.VERSION);
    Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
    System.out.println("OpenCV Mat: " + m);
    Mat mr1 = m.row(1);
    mr1.setTo(new Scalar(1));
    Mat mc5 = m.col(5);
    mc5.setTo(new Scalar(5));
    System.out.println("OpenCV Mat data:\n" + m.dump());
    
    InputOutput inputOutput = new InputOutput();
    inputOutput.setReadingParameters("/home/szozda/Downloads/", "metro", 0, "", ".jpg", false);
    inputOutput.setWritingParameters("/home/szozda/Downloads/", "metro", 0, "_rectangles", ".jpg", false);
    
    Mat img = Highgui.imread(inputOutput.getFullReadingPath(), Highgui.CV_LOAD_IMAGE_GRAYSCALE);
    Imgproc.equalizeHist(img, img);
    Rect[] aryrect = (PeopleDetection.detectPeoples(img)).toArray();

	inputOutput.writeImRectangles(img, aryrect);
	Rect[] tmparyRect = new Rect[1];
	tmparyRect[0] = aryrect[1];
	System.out.println(aryrect[1]);
	inputOutput.writeImRectangles(img, tmparyRect, new Scalar(0));
//    Imgproc.equalizeHist(mc5, mc5);
//    "/home/szozda/git/opencv/data/hogcascades/hogcascade_pedestrians.xml"
	System.out.println("===========FINISHED============");    	
    return;
  }
}

