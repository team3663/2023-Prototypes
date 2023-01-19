// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.photonvision.PhotonCamera;
import org.photonvision.RobotPoseEstimator;
import org.photonvision.RobotPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;  
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.math.util.Units;

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

  private boolean n_hasTargets;
  private int n_targetID;
  private double n_targetX;
  private double n_targetY;
  private double n_targetZ;
  private double n_robotX;
  private double n_robotY;
  private double n_robotTheta;
  private double n_targetAmbiguity;

  private PhotonCamera camera;

  private AprilTagFieldLayout  layout;
  private final Path fieldJsonPath = Paths.get(Filesystem.getDeployDirectory().toString(), "2023-chargedup.json");
  private RobotPoseEstimator poseEstimator;
  private final Transform3d cameraPose = new Transform3d();
  private ArrayList<Pair<PhotonCamera, Transform3d>> cameraList = new ArrayList<Pair<PhotonCamera, Transform3d>>();
  private Optional<Pair<Pose3d, Double>> robotPoseOptional;
  private Pose3d robotPose;

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem(PhotonCamera camera) {
    this.camera = camera;

    try {
      layout = new AprilTagFieldLayout(fieldJsonPath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    robotPose = new Pose3d(0, 0, 0, new Rotation3d(0, 0, 0));

    cameraList.add(new Pair<PhotonCamera, Transform3d>(camera, cameraPose));
    poseEstimator = new RobotPoseEstimator(layout, PoseStrategy.LOWEST_AMBIGUITY, cameraList);

    initTelemetry();
  }

  @Override
  public void periodic() {
    PhotonPipelineResult data = camera.getLatestResult();
    n_hasTargets = data.hasTargets();
    if (n_hasTargets) {
      PhotonTrackedTarget chosenTarget = data.getBestTarget();

      n_targetID = chosenTarget.getFiducialId();
      n_targetX = processDistance(chosenTarget.getBestCameraToTarget().getX());
      n_targetY = processDistance(chosenTarget.getBestCameraToTarget().getY());
      n_targetZ = processDistance(chosenTarget.getBestCameraToTarget().getZ());
      n_targetAmbiguity = chosenTarget.getPoseAmbiguity();
      n_robotX = processDistance(robotPose.getX());
      n_robotY = processDistance(robotPose.getY());
      n_robotTheta = ((double) Math.round(robotPose.getRotation().toRotation2d().getDegrees() * 100)) / 100;

      robotPoseOptional = poseEstimator.update();
      robotPose = robotPoseOptional.get().getFirst();
    } else {
      n_targetID = 0;
      n_targetX = 0;
      n_targetY = 0;
      n_targetZ = 0;
      n_targetAmbiguity = 0;
      n_robotX = 0;
      n_robotY = 0;
      n_robotTheta = 0;
    }
    updateTelemetry();
  }

  private double processDistance (double dist) {
    double dp = Units.metersToInches(dist);
    dp = (double) Math.round(dp *= 100);
    dp /= 100;    

    return dp;
  }

  private void initTelemetry () {
    ShuffleboardTab visionTab = Shuffleboard.getTab("Vision");

    hasTargets = visionTab.add("Target(s) Acquired", false)
      .withPosition(0, 0)
      .withSize(1, 1)
      .getEntry();

    targetID = visionTab.add("Target ID", 0)
      .withPosition(1, 0)
      .withSize(1, 1)
      .getEntry();
    
    targetX = visionTab.add("Target X", 0.0)
      .withPosition(2, 0)
      .withSize(1, 1)
      .getEntry();

    targetY = visionTab.add("Target Y", 0.0)
      .withPosition(3, 0)
      .withSize(1, 1)
      .getEntry();

    targetZ = visionTab.add("Target Z", 0.0)
      .withPosition(4, 0)
      .withSize(1, 1)
      .getEntry();

    targetAmbiguity = visionTab.add("Target Ambiguity", 0.0)
      .withPosition(5, 0)
      .withSize(1, 1)
      .getEntry();

    robotX = visionTab.add("Robot xPos", 0.0)
      .withPosition(0, 1)
      .withSize(1, 1)
      .getEntry();

    robotY = visionTab.add("Robot yPos", 0.0)
      .withPosition(1, 1)
      .withSize(1, 1)
      .getEntry();

    robotTheta = visionTab.add("Robot Angle", 0.0)
      .withPosition(2, 1)
      .withSize(1, 1)
      .getEntry();
  }

  private void updateTelemetry () {
    hasTargets.setBoolean(n_hasTargets);
    targetID.setValue(n_targetID);
    targetX.setValue(n_targetX);
    targetY.setValue(n_targetY);
    targetZ.setValue(n_targetZ);
    targetAmbiguity.setValue(n_targetAmbiguity);
    robotX.setValue(n_robotX);
    robotY.setValue(n_robotY);
    robotTheta.setValue(n_robotTheta);
  }

  // this is useless, delete it.
  public boolean exampleCondition () {
    return false;
  }
}
