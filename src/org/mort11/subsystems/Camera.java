package org.usfirst.frc.team11.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ni.vision.NIVision; // "..."
import com.ni.vision.NIVision.Image; // "..."
import com.ni.vision.NIVision.RawData; // "..."
import com.ni.vision.NIVision.Range; // "..."

import edu.wpi.first.wpilibj.CameraServer; // "..."

/**
 * This class takes a picture, finds the location of the largest object of a pretested 
 * color in terms of X and Y, and also identifies its size. These variables can also be
 * returned, and will be printed to the Riolog.
 */
public class Camera extends Subsystem {
	
	int session; // declares session variable
	Image frame, thresh, filter; // declares image variables
	RawData imageData; // declares raw data variable
	Range rangeH, rangeS, rangeV; // declares three new range variables
	int num_particles, largest_particle, largest_particle2; // declares integer variables
	int i, j; // declares integer variables
	double particle_size, largest_size, Location_X;
	
	//takes a picture of the area and identifies the x/y location of the largest particle
	public void setPicture() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0); // assigns new RGB Image Value
		thresh = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0); // assigns new U8 Image Value
		filter = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0); // assigns new U8 Image Value

		/**
		 * Open session at camera name assigned
		 */
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController); 
	
		NIVision.IMAQdxConfigureGrab(session); // configures grab for set session

		/**
		 * Set range values
		 */
		rangeH = new Range(37, 180);
		rangeS = new Range(0, 255);
		rangeV = new Range(220, 255);

		imageData = new RawData(); // creates new raw data object
		
		i = 0; // assigns integer value
		largest_particle = 0; // assigns integer value
		largest_particle2 = 0; // assigns integer value
		particle_size = 0; // assigns double value
		largest_size = 0; // assigns double value
		
		NIVision.IMAQdxStartAcquisition(session); // starts aquisition for set session
		
		NIVision.IMAQdxGrab(session, frame, 1); // grabs image taken by camera for editing

		CameraServer.getInstance().setImage(frame); // gets instance in order to manually adjust image
		
		/**
		 * Enact color threshold on image
		 */
	    NIVision.imaqColorThreshold(thresh, frame, 255, NIVision.ColorMode.HSV, rangeH, rangeS, rangeV);

		num_particles = NIVision.imaqCountParticles(thresh, 0); // assigns value equal to particle number
		//System.out.println("num_particles " + num_particles); // prints num_particles value
		
		largest_particle = 0;
		largest_size = 0;
		Location_X = 0;
		if (num_particles > 0) {
			for (int k = 0; k < num_particles; k++) {

				/**
				 * Measure particle size
				 */
				particle_size = NIVision.imaqMeasureParticle(thresh, k, 0, NIVision.MeasurementType.MT_AREA);
				//System.out.println("particle_area " + particle_size); // prints particle size to the riolog

				/**
				 * Checks if particle is largest particle
				 */
				if (particle_size > largest_size) {
					largest_size = particle_size;
					largest_particle = k;
					
				}
			}

			//System.out.println("largest particle " + largest_size); // prints largest particle size to riolog

			/**
			 * Measure tote location
			 */
			Location_X = NIVision.imaqMeasureParticle(thresh, largest_particle, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
			System.out.println("location X " + Location_X); // prints final location to riolog			
		}
		
		i++; // increases value of i by one
		
		NIVision.IMAQdxStopAcquisition(session); // stops aquisition for set session
		frame.free();
		thresh.free();
	}
	
	public double getX() {
		return Location_X;
	}
	
	public double getSize() {
		return particle_size;
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
}
