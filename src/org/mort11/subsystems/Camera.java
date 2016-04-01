package org.mort11.subsystems;

import com.ni.vision.NIVision; 
import com.ni.vision.NIVision.Image; 
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.RawData;

import edu.wpi.first.wpilibj.CameraServer; 

import edu.wpi.first.wpilibj.command.Subsystem; 

/*********************************************************************
 * This subsystem is designed to be able to take a picture and also  *
 * run a color threshold on it.                                      *
 *********************************************************************/
public class Camera extends Subsystem {
	
	int session; //declares a session variable to store camera info
	
	//declares RGB variable to hold image data
	Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
	
	//declares U8 variable to hold image data
	Image thresh = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
		
	RawData imageData = new RawData(); //declares a raw data variable
	
	/*************************************************
	 * Takes a picture and stores it for processing. *
	 *************************************************/
	public void takePicture() {
		
		frame.free(); //clears the filespace for the frame variable
		
		/*
		 * Opens the session at camera name assigned
		 */
		session = NIVision.IMAQdxOpenCamera("cam1", 
				NIVision.IMAQdxCameraControlMode.CameraControlModeController); 
		
		NIVision.IMAQdxConfigureGrab(session); // configures grab for set session
		NIVision.IMAQdxStartAcquisition(session); // starts aquisition for set session
		NIVision.IMAQdxGrab(session, frame, 1); // grabs image taken by camera for editing
		
		NIVision.IMAQdxStopAcquisition(session); // stops aquisition for set session
	}
	
	public Image getPicture() {
		return frame; //returns the picture that takePicture() method took
	}
	
	/******************************************************************
	 * Runs a color threshold on the image, eliminating all particles * 
	 * that do not fit within a specific hue, saturation, and value.  *
	 ******************************************************************/
	public void threshold(Image picture) {
		
		thresh.free(); //clears the filespace for the thresh variable
		
		CameraServer.getInstance().setImage(picture); // gets instance in order to manually adjust image
		
		/*
		 * Enacts a color threshold on the image, fully converting the
		 * image to binary and only retaining particles falling within
		 * the set ranges for hue, saturation, and value.
		 */
	    NIVision.imaqColorThreshold(thresh, picture, 255, NIVision.ColorMode.HSV, 
	    		new Range(0,0),new Range(0,0), new Range(0,0));
	}
	
	public Image getThreshold() {
		return thresh; //returns the edited picture created by threshold() method
	}
	
    public void initDefaultCommand() {
       //set the default command for this subsystem here.
    }
    
    public double getBlob() {
    	Image image = thresh;
		double numParticles = NIVision.imaqCountParticles(image, 0);
		double largestSize = 0,size;
		int particleIndex = 0;
		if(numParticles > 0) {
			for(int i = 0; i < numParticles; i++) {
				size = NIVision.imaqMeasureParticle(image, i, 0,
						NIVision.MeasurementType.MT_AREA);
				if(size > largestSize) {
					particleIndex = i;
					largestSize = size;					
				}
			}
		double loc_x = NIVision.imaqMeasureParticle(
				image, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
		double loc_y = NIVision.imaqMeasureParticle(
				image, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_Y);
		image.free();
		return loc_x;
		} else {
			image.free();
			return -1;
		}
	}
    
    public void clearBuffs() {
    	frame.free();
    	imageData.free();
    	thresh.free();
    }
}
