package pedestrian_calculation;
import org.opencv.core.Mat;
import org.opencv.core.Rect;


public class TrackObjects {
	int i = 0;
	int peoplesToDown = 0;
	int peoplesToUp = 0;
	public Rect[] track(Mat frame0, Mat frame1) {
		AryMatPeoples aryMatPeoples = new AryMatPeoples();
		
		while(true) {
			this.lowCostTracking(aryMatPeoples);
		}
		
				
//			newPositions, correspondedBoxes = cv.track(boxes, frame0, frame1);
//			[forward, backward] = wasTransition(boxes, newPositions, )
		} 
			
	public void lowCostTracking(AryMatPeoples aryMatPeoples) {
		InputOutput inputOutput = new InputOutput();
		Rect[] foundPeoples = PeopleDetection.detectPeoples(inputOutput.readFrame()).toArray();
		while(i < 100)
		{
			aryMatPeoples.prepareData(inputOutput.readFrame(), foundPeoples);
			
			i++;
		}
		i = 0; 
	}
}
//	flowpyrlk with prediction on last detected peoplel
//for(loop image with mask) {
//	goodFeaturesToTrack(Mat image, MatOfPoint corners, int maxCorners, double qualityLevel, double minDistance, Mat mask, int blockSize, boolean useHarrisDetector, double k);
//}
//	
//	gaussEveryPoint(aryImages);
//	normalizeMaxTo16(aryImages);
//	
//	
//	for(img0 in images) {
//		normalizeBrighterImage(img0, ptsImg0, img1, ptsImg1);// ex. pts0=10, pts1=12 => Core.divide(img1, 12/10, img1); 
//		Image3 = multiplyImages(Image1, Image2)//result in Image3
//		vector.pushback(integrate(Image3))
//	}
	
//