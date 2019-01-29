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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;


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
  private int counter = 0;

  private DigitalInput SW1;
  private DigitalInput SW2;
  private DigitalInput SW3;
  private DigitalInput SW4;
  private DigitalOutput LED1;
  private DigitalOutput LED2;
  private DigitalOutput LED3;

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
    SW1 = new DigitalInput(0);
    SW2 = new DigitalInput(1);
    SW3 = new DigitalInput(2);
    SW4 = new DigitalInput(3);
    LED1 = new DigitalOutput(4);
    LED2 = new DigitalOutput(5);
    LED3 = new DigitalOutput(6);


    timer = new Timer();
    timer.start();
  }

  @Override 
  public void robotPeriodic() {

    if( ++counter > 9999 )
      counter = 0;

    if(timer.get() > 5) {
      System.out.println("Wrote to Arduino " + counter );
      arduino.write(new byte[] {0x12}, 1);
      timer.reset();

      SmartDashboard.putBoolean("SW1", SW1.get());
      SmartDashboard.putBoolean("SW2", SW2.get());
      SmartDashboard.putBoolean("SW3", SW3.get());
      SmartDashboard.putBoolean("SW4", SW4.get());
    }

    if(arduino.getBytesReceived() > 0) {
      System.out.print(arduino.readString());
    }
  
   

  }



  @Override
  public void autonomousInit() {
    LED1.set(false);
    LED2.set(true);
    LED3.set(true);
    
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    LED1.set(true);
    LED2.set(false);
    LED3.set(true);
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    LED1.set(true);
    LED2.set(true);
    LED3.set(false);
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledInit() {
    LED1.set(true);
    LED2.set(true);
    LED3.set(true);
  }

}
