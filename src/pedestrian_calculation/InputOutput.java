package pedestrian_calculation;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class InputOutput {
	  String filePath;
	  String readingPrefix;
	  String writingPrefix;
	  int readingFileNumber;
	  private String stringReadingFileNumber;
	  int writingFileNumber;
	  private String stringWritingFileNumber;
	  String writingPostfix;
	  String readingPostfix;
	  String extension;
	  boolean parseWithFileNumber;

	  public void setReadingParameters(String filePath, String readingPrefix, int readingFileNumber, String readingPostfix, String extension, boolean parseWithFileNumber) {
		  this.setFilePath(filePath);
		  this.readingPrefix = readingPrefix;
		  this.readingFileNumber = readingFileNumber;
		  this.stringReadingFileNumber = Integer.toString(readingFileNumber);
		  this.readingPostfix = readingPostfix;
		  this.setExtension(extension);
		  this.parseWithFileNumber = parseWithFileNumber;
	  }
	  	  
	  public void setWritingParameters(String filePath, String writingPrefix, int writingFileNumber, String writingPostfix, String extension, boolean parseWithFileNumber) {
		  this.setFilePath(filePath);
		  this.writingPrefix = writingPrefix;
		  this.writingFileNumber = writingFileNumber;
		  this.stringWritingFileNumber = Integer.toString(writingFileNumber);
		  this.writingPostfix = writingPostfix;
		  this.setExtension(extension);
		  this.parseWithFileNumber = parseWithFileNumber;		  
	  }
	  
	  public String getFullReadingPath() {
		  if(this.parseWithFileNumber) {
			  return this.filePath + this.readingPrefix + this.readingPostfix + this.extension;  
		  }
		  else {
			  return this.filePath + this.readingPrefix + this.stringReadingFileNumber + readingPostfix + this.extension;
		  }
		  
	  }
	  
	  public String getFullWritingPath() {
		  if(this.parseWithFileNumber) {
			  return this.filePath + this.writingPrefix + this.writingPostfix + this.extension;  
		  }
		  else {
			  return this.filePath + this.writingPrefix + this.stringWritingFileNumber + writingPostfix + this.extension;
		  }
		  
	  }
 	  
	  public boolean writeImRectangles(Mat img, Rect[] aryRect, Scalar colour) {
		  
		  String fullPath = this.getFullWritingPath();
		  int i = 0;
			  for(Rect rect: aryRect)
			  {
			    Core.rectangle(img, new Point(rect.x,rect.y), new Point(rect.x + rect.width,rect.y + rect.height), colour, 1);
			    Core.putText(img, Integer.toString(i), new Point(rect.x,rect.y), Core.FONT_HERSHEY_SIMPLEX, 8, colour);
			    i++;
			  }
			  System.out.println(fullPath);
			  boolean result = Highgui.imwrite(fullPath, img);
			  System.out.println("writeImRectangles: " + Boolean.toString(result));
			  return result;
	  }
	  
	  public boolean writeImRectangles(Mat img, Rect[] aryRect) {
		  if(img.channels()==1)
			  return this.writeImRectangles(img, aryRect, new Scalar(255));
		  else if(img.channels()==3) {
			  return this.writeImRectangles(img, aryRect, new Scalar(255,0,0));
		  }
		  else {
			  return false;
		  }
	  }
	  
	  private void setFileNameToNext(boolean reading) {//TBD TESTING
		  int fileNumber;
		  int desiredLength = 4;
		  
		  if(reading) {
			  fileNumber = ++readingFileNumber;
		  }
		  else {
			  fileNumber = ++writingFileNumber;
		  }
		  
		  String stringFileNumber = Integer.toString(fileNumber);
		  while(stringFileNumber.length() < desiredLength) {
			  stringFileNumber = '0' + stringFileNumber;
		  }
		  if(reading) {
			  this.stringReadingFileNumber = Integer.toString(fileNumber);
		  }
		  else {
			  this.stringWritingFileNumber = Integer.toString(fileNumber);
		  }
	  }
	  
	  public Mat readFrame() {
		  Mat img = Highgui.imread(filePath + readingPrefix, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		  this.setFileNameToNext(true);
		  return img;
	  }
	  
	  private void setFilePath(String filePath) {
		  if(filePath.charAt(filePath.length() - 1) != '/') {
			  filePath += '/';
		  }
		  this.filePath = filePath;
	  }
	  
	  private void setExtension(String extension) {
		  if(extension.charAt(0) != '.') {
			  extension = '.' + extension;
		  }
		  this.extension = extension;
	  }
	  
	  InputOutput(){
		  this.filePath = "filePath";
		  this.readingPrefix = "readingPrefix";
		  this.writingPrefix = "writingPrefix";
		  this.writingPostfix = "writingPostfix";
		  this.readingPostfix = "readingPostfix";
		  this.readingFileNumber = 5;
		  this.writingFileNumber = 5;
		  this.stringReadingFileNumber = "5";
		  this.stringWritingFileNumber = "5";
		  this.extension = "extension";
		  this.parseWithFileNumber = false;
	  }
}
