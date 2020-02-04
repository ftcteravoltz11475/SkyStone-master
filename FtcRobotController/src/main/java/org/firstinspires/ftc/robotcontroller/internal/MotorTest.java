package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="MotorTest", group="Testing")
public class MotorTest extends Robot {


    public void runOpMode() {
        //Initialize hardware
        InitializeHardware();
        waitForStart();
        int rightFront, rightBack, leftFront, leftBack;

        while (opModeIsActive()) {
            rightFront = rightFrontMotor.getCurrentPosition();
            rightBack = rightBackMotor.getCurrentPosition();
            leftFront = leftFrontMotor.getCurrentPosition();
            leftBack = leftBackMotor.getCurrentPosition();

            float power;

            if(gamepad1.dpad_right){
                rightFrontMotor.setPower(0.1);
            }
            else{
                rightFrontMotor.setPower(0);
            }
            if(gamepad1.dpad_up){
                rightBackMotor.setPower(0.1);
            }
            else{
                rightBackMotor.setPower(0);
            }
            if(gamepad1.dpad_left){
                leftFrontMotor.setPower(0.1);
            }
            else{
                leftFrontMotor.setPower(0);
            }
            if(gamepad1.dpad_down){
                leftBackMotor.setPower(0.1);
            }
            else{
                leftBackMotor.setPower(0);
            }

            if(gamepad1.y){
                rightFrontMotor.setPower(0.1);
                rightBackMotor.setPower(0.1);
                leftFrontMotor.setPower(0.1);
                leftBackMotor.setPower(0.1);
            }

            sleep(1000);

            telemetry.addData("Right Front: ", Math.abs(rightFrontMotor.getCurrentPosition() - rightFront));
            telemetry.addData("Right Back: ", Math.abs(rightBackMotor.getCurrentPosition() - rightBack));
            telemetry.addData("Left Front: ", Math.abs(leftFrontMotor.getCurrentPosition() - leftFront));
            telemetry.addData("Left Back: ", Math.abs(leftBackMotor.getCurrentPosition() - leftBack));
            telemetry.update();
        }
    }

}
