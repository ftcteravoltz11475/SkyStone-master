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
        double angle = 0;
        int topPos = 6000;
        boolean mecDrive = false;


        waitForStart();

        while (opModeIsActive()) {

            rightDriveY = gamepad1.right_stick_y;
            leftDriveY = gamepad1.left_stick_y;


            //right bumper closes the claw and vice versa
            if (gamepad2.right_bumper) {
                TurnClaw(0.55);
            }
            if(gamepad2.left_bumper){
                ResetClaw();
            }

            //y lifts and a lowers lift, otherwise stop the lift
            if(gamepad2.a && getPosLift() >= 0) {
                Lift(1);
            }
            else if(gamepad2.y && getPosLift()  <= topPos) {
                Lift(-1);
            }
            else {
                Lift(0);
            }


            //b closes the foundation servo and x opens it.
            if (gamepad2.b) {
                TurnFoundationServo(0.5);
            }
            if(gamepad2.x){
                ResetFoundationServo();
            }

            //If the dpad up is pressed, turn off slow drive
            if (gamepad1.dpad_up) {
                slowDrive = false;
            }
            //If dpad down is pressed, turn on slow drive
            if (gamepad1.dpad_down) {
                slowDrive = true;
            }

            if (slowDrive) {
                rightDriveY *= SLOWSPEED;
                leftDriveY *= SLOWSPEED;
//                rightDriveX *= SLOWSPEED;
            }

/*
            if(gamepad1.right_stick_button){
                mecDrive = true;
            }
            if(gamepad1.left_stick_button){
                mecDrive = false;
            }

 */

            if(gamepad1.dpad_left){
                MecanumDrive(-1);
            }
            else if(gamepad1.dpad_right){
                MecanumDrive(1);
            }
            else{
                TankDrive(rightDriveY, leftDriveY);
            }


            telemetry.addData("Lift Pos:", getPosLift());
            telemetry.addData("Wheel Pos:", getPosWheel());
            telemetry.addData("Angle: ",angle);
            telemetry.addData("Slow Drive: ", slowDrive);
            telemetry.addData("Mecanum Drive", mecDrive);
            telemetry.update();
        }
    }
}
