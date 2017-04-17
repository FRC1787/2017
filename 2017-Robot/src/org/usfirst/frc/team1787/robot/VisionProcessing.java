package org.usfirst.frc.team1787.robot;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessing {

	private UsbCamera camera_shooter;
	private UsbCamera camera_gear;
	
	private CvSink sink_shooter;
	private CvSink sink_gear;
	private CvSource source;
	
	private Mat mat_frame;
	private Mat mat_process;
	
	private Preferences prefs;
	
	private Scalar hsv_low;
	private Scalar hsv_high;
	
	private Mat mat_heirarchy;
	private Mat kernel;
	
	private List<MatOfPoint> contours;
	
	// If the camera is off, and we need to adjust to a point
	// not exactly centered in the middle of the camera, this
	// is the value to change
	private int goal_pixel_offset = Constants.VISION.GOAL_PIXEL_OFFSET_DEFAULT;
	
	private String currentCamera = Constants.VISION.CAMERA_SHOOTER_NAME;
	private Mat currentCameraFrame;
	private Mat otherMat;
	
	private boolean cameraSwitchDown = false;
	
	public VisionProcessing()
	{
		camera_shooter = new UsbCamera(Constants.VISION.CAMERA_SHOOTER_NAME, 1);
		camera_gear = new UsbCamera(Constants.VISION.CAMERA_GEAR_NAME, 0);
		
		camera_shooter.setResolution(Constants.VISION.CAMERA_WIDTH, Constants.VISION.CAMERA_HEIGHT);
		camera_shooter.setWhiteBalanceManual(Constants.VISION.CAMERA_SHOOTER_WHITEBALANCE);
		camera_shooter.setBrightness(Constants.VISION.CAMERA_SHOOTER_BRIGHTNESS);
		camera_shooter.setExposureManual(Constants.VISION.CAMERA_SHOOTER_EXPOSURE);
		camera_shooter.setFPS(Constants.VISION.CAMERA_SHOOTER_FPS);
		
		camera_gear.setResolution(Constants.VISION.CAMERA_WIDTH, Constants.VISION.CAMERA_HEIGHT);
		camera_gear.setBrightness(Constants.VISION.CAMERA_GEAR_BRIGHTNESS);
		camera_gear.setFPS(5);
		
		sink_shooter = CameraServer.getInstance().getVideo(camera_shooter);
		sink_gear = CameraServer.getInstance().getVideo(camera_gear);
		source = CameraServer.getInstance().putVideo("Ananas", Constants.VISION.CAMERA_WIDTH, Constants.VISION.CAMERA_HEIGHT);
		
		mat_frame = new Mat();
		mat_process = new Mat();
		
		prefs = Preferences.getInstance();
		
		hsv_low = new Scalar(0, 0, 0);
		hsv_high = new Scalar(180, 255, 255);
		
		updateHSV();
		updateGoalPixelOffset();
		
		mat_heirarchy = new Mat();
		kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
						new Size(Constants.VISION.MORPHOLOGY_KERNEL_SIZE, Constants.VISION.MORPHOLOGY_KERNEL_SIZE));
		
		contours = new ArrayList<MatOfPoint>();
		
		currentCameraFrame = new Mat();
		otherMat = new Mat();
	}
	
	public double getHorizontalDegreesError()
	{
		
		if (sink_shooter.grabFrame(mat_frame) == 0)
		{
			source.notifyError(sink_shooter.getError());
			return 0;
		}
		
		// Get HSV image
		Imgproc.cvtColor(mat_frame, mat_process, Imgproc.COLOR_BGR2HSV);
		
		// Filter
		Core.inRange(mat_process, hsv_low, hsv_high, mat_process);
		
		// Erode and then dilate
		Imgproc.morphologyEx(mat_process, mat_process, Imgproc.MORPH_OPEN, kernel);
		
		// Find the contours
		contours.clear();
		Imgproc.findContours(mat_process, contours, mat_heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

		// If any contours are found
		if (contours.size() > 0)
		{
			// Remove noise by removing contours that are too small
			for (int i = contours.size() - 1; i >= 0; i--)
			{
				if (Imgproc.contourArea(contours.get(i)) < Constants.VISION.CONTOUR_MIN_AREA)
					contours.remove(i);
			}
			
			
			// If any contours remain
			if (contours.size() > 0)
			{
				// Get the largest contour
				int largestContourIndex = 0;
				double largestContourArea = Imgproc.contourArea(contours.get(0));
				for (int i = 1; i < contours.size(); i++)
				{
					if (Imgproc.contourArea(contours.get(i)) > largestContourArea)
					{
						largestContourArea = Imgproc.contourArea(contours.get(i));
						largestContourIndex = i;
					}
				}
				MatOfPoint largestContour = contours.get(largestContourIndex);
								
				// Get length to width ratio error
				Rect bb = Imgproc.boundingRect(largestContour);
				double l2wRatio = bb.width / bb.height;
				double score = l2wRatio / Constants.VISION.TAPE_TARGET_LENGTH_WIDTH_RATIO; //Perfect score is 1
				
				// If error is not too big, meaning that the ratio is correct enough to probably be the right object
				/*if (score > 1 - Constants.VISION.LENGTH_WIDTH_RATIO_TOLERABLE_ERROR &&
					score < 1 + Constants.VISION.LENGTH_WIDTH_RATIO_TOLERABLE_ERROR)
				{*/
					
					// Find and return the error in degrees
					double targetCenterX = bb.x + (bb.width / 2);
					double pixelError = targetCenterX - ((mat_process.width() / 2) - goal_pixel_offset);
					double angleError = pixelError * Constants.VISION.DEGREES_PER_PIXEL;
					
					SmartDashboard.putNumber("Pixel Error", pixelError);
					SmartDashboard.putNumber("Degrees Error", angleError);
					
					//Imgproc.rectangle(mat_frame, bb.tl(), bb.br(), new Scalar(0, 0, 255));
					//source.putFrame(mat_frame);
					return angleError;
				/*}*/
			}	
		}
		else
			System.out.println("No Contours");
		return 0;
	}
	
	public void updateHSV()
	{
		double[] lowVals = {
				prefs.getDouble(Constants.VISION.HSV_LOW_H, 0),
				prefs.getDouble(Constants.VISION.HSV_LOW_S, 0),
				prefs.getDouble(Constants.VISION.HSV_LOW_V, 0)
		};
		hsv_low.set(lowVals);
		
		double[] highVals = {
				prefs.getDouble(Constants.VISION.HSV_HIGH_H, 360),
				prefs.getDouble(Constants.VISION.HSV_HIGH_S, 360),
				prefs.getDouble(Constants.VISION.HSV_HIGH_V, 360)
		};
		hsv_high.set(highVals);
	}
	
	public void updateGoalPixelOffset()
	{
		goal_pixel_offset = (int)prefs.getDouble(Constants.VISION.GOAL_PIXEL_OFFSET_LABEL, 0);
	}
	
	public void sendCurrentCamera()
	{
		if (currentCamera.equals(Constants.VISION.CAMERA_SHOOTER_NAME))
			sink_shooter.grabFrameNoTimeout(currentCameraFrame);
		else if (currentCamera.equals(Constants.VISION.CAMERA_GEAR_NAME))
			sink_gear.grabFrameNoTimeout(currentCameraFrame);
		
		source.putFrame(currentCameraFrame);
	}
	
	public void sendHSVFilteredImage()
	{
		sink_shooter.grabFrameNoTimeout(currentCameraFrame);
		Imgproc.cvtColor(currentCameraFrame, otherMat, Imgproc.COLOR_BGR2HSV);
		Core.inRange(otherMat, hsv_low, hsv_high, otherMat);
		source.putFrame(otherMat);
	}
	
	public void switchCamera()
	{
		if (currentCamera.equals(Constants.VISION.CAMERA_SHOOTER_NAME))
			currentCamera = Constants.VISION.CAMERA_GEAR_NAME;
		else
			currentCamera = Constants.VISION.CAMERA_SHOOTER_NAME;
	}
	
	public void incrementGoalPixelOffset()
	{
		goal_pixel_offset += Constants.VISION.GOAL_PIXEL_OFFSET_ADJUST_AMOUNT;
	}
	
	public void decrementGoalPixelOffset()
	{
		goal_pixel_offset -= Constants.VISION.GOAL_PIXEL_OFFSET_ADJUST_AMOUNT;
	}
	
	public int getGoalPixelOffset()
	{
		return goal_pixel_offset;
	}
}
