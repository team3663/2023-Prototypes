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
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.GenericEntry;

public class VisionSubsystem extends SubsystemBase {

  private GenericEntry hasTargets;
  private GenericEntry targetID;
  private GenericEntry targetPose;
  private GenericEntry targetAmbiguity;

  private PhotonCamera camera;
  private PhotonPipelineResult data;
  List<PhotonTrackedTarget> targets;
  // TODO: figure out how to get this to work

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem(PhotonCamera camera) {
    this.camera = camera;
    data = camera.getLatestResult();
    initTelemetry(targets.get(0));
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
    PhotonTrackedTarget chosenTarget = targets.get(0);
    data = camera.getLatestResult();
    targets = data.getTargets();
    if (data.hasTargets()) printTarget(chosenTarget);
    updateTelemetry(chosenTarget);
  }

  private void initTelemetry (PhotonTrackedTarget target) {
    ShuffleboardTab tab = Shuffleboard.getTab("Vision");

    hasTargets = tab.add("Target Acquired", data.hasTargets())
      .withPosition(0, 0)
      .withSize(1, 1)
      .getEntry();

    targetID = tab.add("Target ID", target.getFiducialId())
      .withPosition(1, 0)
      .withSize(1, 1)
      .getEntry();
    
    targetPose = tab.add("Target Position", target.getBestCameraToTarget())
      .withPosition(2, 0)
      .withSize(1, 1)
      .getEntry();

    targetAmbiguity = tab.add("Target Ambiguity", target.getPoseAmbiguity())
      .withPosition(3, 0)
      .withSize(1, 1)
      .getEntry();
  }

  private void updateTelemetry (PhotonTrackedTarget target) {
    hasTargets.setBoolean(data.hasTargets());
    targetID.setValue(target.getFiducialId());
    targetPose.setValue(target.getBestCameraToTarget());
    targetAmbiguity.setValue(target.getPoseAmbiguity());
  }
}
