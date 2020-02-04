package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MasterOpMode" , group = "Main Op Modes")
public class MasterOpMode extends Robot{

    @Override
    public void runOpMode() throws InterruptedException{

        //Initialize Hardware
        InitializeHardware();

        //Other Variables
        final double SLOWSPEED = .7;
        boolean slowDrive = false;
        float rightDriveY, leftDriveY, rightDriveX, leftDriveX;
        float last_rightDriveY = 0;
        float last_leftDriveY = 0;

        double angle = 0;
        int topPos = 6000;

        waitForStart();

        while (opModeIsActive()) {

            rightDriveY = (gamepad1.right_stick_y);
            leftDriveY = (gamepad1.left_stick_y);


            //right bumper closes the claw and vice versa
            if (gamepad2.right_bumper) {
                TurnClaw(0.55);
            }
            if(gamepad2.left_bumper){
                ResetClaw();
            }

            //y lifts and a lowers lift, otherwise stop the lift
            if(gamepad2.a && getPosLift() >= 0) {
                Lift(-1);
            }
            else if(gamepad2.y && getPosLift()  <= topPos) {
                Lift(1);
            }
            else {
                Lift(0);
            }


            //b closes the foundation servo and x opens it.
            if (gamepad2.b) {
                ResetFoundationServo();
            }
            if(gamepad2.x){
                TurnFoundationServo();
            }

            //If the dpad up is pressed, turn off slow drive
            if (gamepad1.dpad_up) {
                slowDrive = false;
            }
            //If dpad down is pressed, turn on slow drive
            if (gamepad1.dpad_down) {
                slowDrive = true;
            }
            if(gamepad1.dpad_right){
                accel += 0.001;
            }
            if(gamepad1.dpad_left){
                accel -= 0.001;
            }

            if (slowDrive) {
                rightDriveY *= SLOWSPEED;
                leftDriveY *= SLOWSPEED;
            }

            TestTankDrive(-rightDriveY, -leftDriveY);

            if(gamepad1.right_bumper){
                resetPosWheel();
            }

            telemetry.addData("Lift Pos:", getPosLift());
            telemetry.addData("Left Wheel Pos:", getPosWheelLeft());
            telemetry.addData("Right Wheel Pos:",getPosWheelRight());
            telemetry.addData("Angle: ",angle);
            telemetry.addData("Slow Drive: ", slowDrive);
            telemetry.addData("Left Power: ", scaleLeft * ScalePower(-leftDriveY));
            telemetry.addData("Right Power: ", scaleRight * ScalePower(-rightDriveY));
            telemetry.update();
        }
    }
}
