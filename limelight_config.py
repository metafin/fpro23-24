# also see: https://docs.limelightvision.io/docs/docs-limelight/apis/limelightlib-python
import limelight
import limelightresults
import json
import time

discovered_limelights = limelight.discover_limelights(debug=True)
print("discovered limelights:", discovered_limelights)

if discovered_limelights:
    limelight_address = discovered_limelights[0]
    ll = limelight.Limelight(limelight_address)

    # Switch to pipeline 0 (AprilTag detection)
    ll.pipeline_switch(0)

    # Enable WebSocket for receiving live data
    ll.enable_websocket()

    try:
        while True:
            result = ll.get_latest_results()
            parsed_result = limelightresults.parse_results(result)

            if parsed_result is not None:
                print(
                    "valid targets: ", parsed_result.validity, ", pipelineIndex: ", parsed_result.pipeline_id,
                    ", Targeting Latency: ", parsed_result.targeting_latency
                )
                for tag in parsed_result.fiducialResults:
                    print(f"AprilTag ID: {tag.fiducial_id}, Robot Pose: {tag.robot_pose_target_space}")
            time.sleep(1)

    except KeyboardInterrupt:
        print("Program interrupted by user, shutting down.")
    finally:
        ll.disable_websocket()
