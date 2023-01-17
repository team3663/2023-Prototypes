// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.RobotPoseEstimator;
import org.photonvision.RobotPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.Filesystem;
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
  private GenericEntry robotX;
  private GenericEntry robotY;
  private GenericEntry robotTheta;
  private GenericEntry targetAmbiguity;

  private PhotonCamera camera;
  private PhotonPipelineResult data;

  private AprilTagFieldLayout  layout;
  private final Path sillyPath = Paths.get(Filesystem.getDeployDirectory().toString(), "2023-chargedup.json");
  private RobotPoseEstimator poseEstimator;
  private final Transform3d cameraPose = new Transform3d();
  private ArrayList<Pair<PhotonCamera, Transform3d>> cameraList = new ArrayList<Pair<PhotonCamera, Transform3d>>();
  private Optional<Pair<Pose3d, Double>> robotPoseOptional;
  private Pose3d robotPose;

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem(PhotonCamera camera) {
    this.camera = camera;
    data = camera.getLatestResult();
    try {
      layout = new AprilTagFieldLayout(sillyPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    cameraList.add(new Pair<PhotonCamera, Transform3d>(camera, cameraPose));
    poseEstimator = new RobotPoseEstimator(layout, PoseStrategy.LOWEST_AMBIGUITY, cameraList);

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
    if (data.hasTargets()) {
      PhotonTrackedTarget chosenTarget = data.getBestTarget();
      updateTelemetry(chosenTarget);
      robotPoseOptional = poseEstimator.update();
      robotPose = robotPoseOptional.get().getFirst();
      System.out.println("Target Acquired");
    }
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

    robotX = tab.add("Robot xPos", 0.0)
      .withPosition(0, 1)
      .withSize(1, 1)
      .getEntry();

    robotY = tab.add("Robot yPos", 0.0)
      .withPosition(1, 1)
      .withSize(1, 1)
      .getEntry();

    robotTheta = tab.add("Robot Angle", 0.0)
      .withPosition(2, 1)
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
    robotX.setValue(robotPose.getX());
    robotY.setValue(robotPose.getY());
    robotTheta.setValue(robotPose.getRotation().toRotation2d().getDegrees());
  }

  // this is useless, delete it.
  public boolean exampleCondition () {
    return false;
  }
}
