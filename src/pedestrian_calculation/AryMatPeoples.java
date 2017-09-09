package pedestrian_calculation;
import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class AryMatPeoples {
	private ArrayList<Rect> detectedPeoples;
	private Rect[] pastPeoples;
	private Rect[] nowPeoples;
	private Rect[] futurePeoples;
	private final int scaler = 2;
	
	public ArrayList<Rect> getDetectedPeoples() {
		return this.detectedPeoples;
	}
	public void prepareData(Mat image, Rect[] nowPeoples) {
		this.pastPeoples = this.nowPeoples;
		this.nowPeoples = nowPeoples;
		this.estimateFutureRegio();
		this.prepareDetectedPeoples(image);
	}
	
	private void estimateFutureRegio() {
		for(int i = 0; i < nowPeoples.length; i++){
			futurePeoples[i].height = nowPeoples[i].height;
			futurePeoples[i].width = nowPeoples[i].width;
			futurePeoples[i].x = this.nowPeoples[i].x + this.nowPeoples[i].x - this.pastPeoples[i].x;
			futurePeoples[i].y = this.nowPeoples[i].y + this.nowPeoples[i].y - this.pastPeoples[i].y;
		}
	}
	
	private void prepareDetectedPeoples(Mat image) {
		double hitThreshold = 0.08;
		Size winStride = new Size(4,4);
		Size padding = new Size(8,8);
		double scaling = 1.2;
		double finalThreshold = 1;
		boolean useMeanshiftGrouping = true;
		
		this.detectedPeoples.clear();
		for(Rect person : this.futurePeoples) {
			person.x = person.x - person.width/2 * scaler;
			person.y = person.y - person.height/2 * scaler;
			person.height = person.height * scaler;
			person.width = person.width * scaler;
			
			Rect hitedPerson = getBiggestRect(PeopleDetection.detectPeoples(this.prepareImage(image, person), hitThreshold, winStride, padding, scaling, finalThreshold, useMeanshiftGrouping));
			if(hitedPerson.width == 0) {
				hitThreshold /= 2;
				getBiggestRect(PeopleDetection.detectPeoples(this.prepareImage(image, person), hitThreshold, winStride, padding, scaling, finalThreshold, useMeanshiftGrouping));
				hitThreshold *= 2;
			}
			this.detectedPeoples.add(hitedPerson);
		}
	}
	
	private Mat prepareImage(Mat img, Rect person) {
		Mat preparedImg = img.submat(person);
		return preparedImg;
	}
	
	private Rect getBiggestRect(MatOfRect matOfRect) {
		Rect biggestRect = new Rect(0,0,1,1);
		Rect[] aryRect = matOfRect.toArray();
		for(Rect rect : aryRect) {
			if(rect.area() > biggestRect.area()) {
				biggestRect = rect;
			}
		}
		return biggestRect;
	}
}
