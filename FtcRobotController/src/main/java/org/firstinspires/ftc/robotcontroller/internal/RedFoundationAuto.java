
package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "RedFoundationAuto" , group = "Main Op Modes")
public class RedFoundationAuto extends Robot{

    @Override
    public void runOpMode() throws InterruptedException{
        final int FOOT = 3200;
        final double sL = scaleLeft;
        InitializeHardware();

        waitForStart();
        while(!opModeIsActive()) {
            sleep(10);
        }
        ResetFoundationServo();
        scaleLeft = 0.7;
        AutoDriveFB((int)(-2.4 * FOOT), -1);
        scaleLeft = sL;
        Rotate(36);
        AutoDriveFB((int)(-1 * FOOT),-1);
        TurnFoundationServo();
        sleep(200);
        Rotate(-20);
        AutoDriveFB((int)(2.7 * FOOT), 1);
        AutoDriveTank(10000, -10000, 1);
        sleep(300);
        TurnClaw();
        sleep(800);
        AutoDriveFB(-FOOT, -1);
        ResetFoundationServo();
        sleep(300);
        AutoDriveFB((int)(2.7 * FOOT), 1);
        Rotate(-15);
        AutoDriveFB((int)(0.4 * FOOT), 1);

    }
}


