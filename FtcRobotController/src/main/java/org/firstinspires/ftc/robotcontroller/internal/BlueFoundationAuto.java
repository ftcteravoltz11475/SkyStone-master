
package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "BlueFoundationAuto" , group = "Main Op Modes")
public class BlueFoundationAuto extends Robot{

    @Override
    public void runOpMode() throws InterruptedException{
        final int FOOT = 3200;
        final double sR = scaleRight;
        InitializeHardware();

        waitForStart();
        while(!opModeIsActive()) {
            sleep(10);
        }
        ResetFoundationServo();
        scaleRight = 0.6;
        AutoDriveFB((int)(-2.4 * FOOT), -1);
        scaleRight = sR;
        Rotate(-24);
        AutoDriveFB((int)(-1 * FOOT),-1);
        TurnFoundationServo();
        sleep(200);
        Rotate(20);
        AutoDriveFB((int)(2.7 * FOOT), 1);
        AutoDriveTank(-6000, 6000, 1);
        Rotate(-10);
        ResetFoundationServo();
        ResetClaw();
        sleep(300);
        TurnClaw();
        sleep(800);
        AutoDriveFB(-FOOT, -1);

        AutoDriveFB(FOOT, 1);
        Rotate(-20);
        AutoDriveFB((int)(2.7 * FOOT), 1);
        /*
        AutoDriveFB((int)(2.4 * FOOT), 1);
        ResetFoundationServo();
        sleep(200);
        AutoDriveFB((int)(0.3 * FOOT), 1);
        Rotate(90);
        AutoDriveFB((int)(2.3  * FOOT), 1);
        Rotate(85);
        AutoDriveFB((int)(3.5 * FOOT), 1);
        Rotate(87);
        AutoDriveFB((int)(2.2 * FOOT), 1);
        Rotate(87);
        AutoDriveFB((int)(1.7 * FOOT), 1);
         */

    }
}


