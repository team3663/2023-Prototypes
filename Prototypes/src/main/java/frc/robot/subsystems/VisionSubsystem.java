// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;  
import edu.wpi.first.networktables.GenericEntry;

public class VisionSubsystem extends SubsystemBase {

  private GenericEntry hasTargets;
  private GenericEntry targetID;
  private GenericEntry targetX;
  private GenericEntry targetY;
  private GenericEntry targetZ;
  private GenericEntry targetAmbiguity;

  private PhotonCamera camera;
  private PhotonPipelineResult data;
  List<PhotonTrackedTarget> targets;

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem(PhotonCamera camera) {
    this.camera = camera;
    data = camera.getLatestResult();
    targets = data.getTargets();
    initTelemetry();
  }

  public void printTarget(PhotonTrackedTarget target) {
    Transform3d pose = target.getBestCameraToTarget();
    double px = pose.getX();
    double py = pose.getY();
    double pz = pose.getZ();
    System.out.println("AprilTag " + target.getFiducialId() + " is at X: " + px + " Y: " + py + " Z: " + pz + " with " + target.getPoseAmbiguity() + " ambiguity.");
  }

  @Override
  public void periodic() {
    data = camera.getLatestResult();
    targets = data.getTargets();
    PhotonTrackedTarget chosenTarget = data.getBestTarget();
    printTarget(chosenTarget);
    updateTelemetry(chosenTarget);
  }

  private void initTelemetry () {
    ShuffleboardTab tab = Shuffleboard.getTab("Vision");

    hasTargets = tab.add("Target Acquired", false)
      .withPosition(0, 0)
      .withSize(1, 1)
      .getEntry();

    targetID = tab.add("Target ID", 0)
      .withPosition(1, 0)
      .withSize(1, 1)
      .getEntry();
    
    targetX = tab.add("Target X", 0.0)
      .withPosition(2, 0)
      .withSize(1, 1)
      .getEntry();

    targetY = tab.add("Target Y", 0.0)
      .withPosition(3, 0)
      .withSize(1, 1)
      .getEntry();

    targetZ = tab.add("Target Z", 0.0)
      .withPosition(4, 0)
      .withSize(1, 1)
      .getEntry();

    targetAmbiguity = tab.add("Target Ambiguity", 0.0)
      .withPosition(5, 0)
      .withSize(1, 1)
      .getEntry();
  }

  private void updateTelemetry (PhotonTrackedTarget target) {
    hasTargets.setBoolean(data.hasTargets());
    targetID.setValue(target.getFiducialId());
    targetX.setValue(target.getBestCameraToTarget().getX());
    targetY.setValue(target.getBestCameraToTarget().getY());
    targetZ.setValue(target.getBestCameraToTarget().getZ());
    targetAmbiguity.setValue(target.getPoseAmbiguity());
  }

  // this is useless, delete it.
  public boolean exampleCondition () {
    return false;
  }
}
