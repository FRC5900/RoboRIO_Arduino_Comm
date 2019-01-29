/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  private SerialPort arduino;
  private Timer timer;

  @Override
  public void robotInit() {

    try {
      arduino = new SerialPort(9600, SerialPort.Port.kUSB);
      System.out.println("Connected to kUSB");
    } catch (Exception e) {
      System.out.println("Failed to connect on kUSB, trying kUSB1");
      try {
        arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        System.out.println("Connected to kUSB 1");
      } catch (Exception e1) {
        System.out.println("Failed to connect on kUSB 1, trying kUSB2");        
        try {
          arduino = new SerialPort(9600, SerialPort.Port.kUSB2);
          System.out.println("Connected to kUSB2");
        } catch (Exception e2) {
          System.out.println("Failed to connect on kUSB2, all connects failed");
        }
      }
    }
    timer = new Timer();
    timer.start();
  }

  @Override 
  public void robotPeriodic() {

    if(timer.get() > 5) {
      System.out.println("Wrote to Arduino");
      arduino.write(new byte[] {0x12}, 1);
      timer.reset();
    }

    if(arduino.getBytesReceived() > 0) {
      System.out.print(arduino.readString());
    }
  
  }



  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
