package pedestrian_calculation;
import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Rect;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.core.MatOfDouble;

class mainClass {

  static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

  public static int main(String[] args) {
    System.out.println("Welcome to OpenCV " + Core.VERSION);
    Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
    System.out.println("OpenCV Mat: " + m);
    Mat mr1 = m.row(1);
    mr1.setTo(new Scalar(1));
    Mat mc5 = m.col(5);
    mc5.setTo(new Scalar(5));
    System.out.println("OpenCV Mat data:\n" + m.dump());
    
//    Imgproc.equalizeHist(mc5, mc5);
//    "/home/szozda/git/opencv/data/hogcascades/hogcascade_pedestrians.xml"
    return 0;
  }
  
  public Vector<Rect> detectPeoples(Mat frame) {
	  Vector<Rect> peoples = new Vector<Rect>();
	  MatOffoundWeights 
//	  return HogDescriptor("/home/szozda/git/opencv/data/hogcascades/hogcascade_pedestrians.xml");
	  HOGDescriptor hog = new HOGDescriptor();
	  hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
	  hog.detectMultiScale(frame, peoples, foundWeights, hitThreshold, winStride = (4,4), padding=(8,8), 1.05, finalThreshold, useMeanshiftGrouping);
	  return peoples;
  }
  
  public static NonMaxima(Array boxes) {
	  return nonredundantBoxes;
  }
  
  public static Mat track(Mat frame) {
	  int i = 0;
	  Vector<String> vec = new Vector<String>();
	  while(true)
	  {
		  if (i%10 == 0) {			  
			  boxes = this.detectPeoples();
			  nonRedundantBoxes =  NonMaxima(boxes)
		  }
		  newPositions, correspondedBoxes = cv.track(boxes);
		  [forward, backward] = wasTransition(boxes, newPositions, )
	  } 

	  return aryOfTracked;
  } 
}

