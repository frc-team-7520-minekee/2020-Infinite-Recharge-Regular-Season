/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

public class LiftSub extends SubsystemBase {

  private TalonSRX masterMotor;
  private TalonSRX followerMotor; 

  private DoubleSolenoid solenoid;

  /**
   * Creates a new LiftSub.
   */
  public LiftSub(TalonSRX master, TalonSRX follower, DoubleSolenoid solenoid) {
    masterMotor = master;
    followerMotor = follower;
    followerMotor.set(ControlMode.Follower, masterMotor.getDeviceID());
    masterMotor.configOpenloopRamp(0.15);
    masterMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 0, 120, 1.5));
    this.solenoid = solenoid;
  }

  public void armOpen() {
    this.solenoid.set(kForward);
  }

  public void armClose() {
    this.solenoid.set(kReverse);
  }

  public void winchLoosen() {
    masterMotor.set(ControlMode.PercentOutput, 0.30);
  }

  public void winchTighten() {
    masterMotor.set(ControlMode.PercentOutput, -0.30);
  }
  
  public void stop(){
    masterMotor.set(ControlMode.PercentOutput, 0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
