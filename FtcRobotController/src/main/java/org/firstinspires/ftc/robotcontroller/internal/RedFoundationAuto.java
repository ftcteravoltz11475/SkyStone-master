
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
        final int FOOT = 1600;
        final double sL = scaleLeft;
        InitializeHardware();

        waitForStart();
        while(!opModeIsActive()) {
            sleep(10);
        }
        ResetFoundationServo();
        Rotate(-10);
        AutoDriveFB((int) (-2*FOOT), -0.5);
        Rotate(-25);
        AutoDriveFB((int) (-FOOT), -0.5);
        sleep(100);
        TurnFoundationServo();
        sleep(200);
        Rotate(-40);
        AutoDriveFB((int) (2.7 * FOOT), -0.5);
        AutoDriveTank(3500, -3500, 0.5);
        Rotate(20);
        ResetFoundationServo();
        ResetClaw();
        sleep(300);
        TurnClaw();
        sleep(800);
        AutoDriveFB((int) (-1.5*FOOT), -0.5);
        AutoDriveFB((int) (.25*FOOT), -0.5);
        Rotate(20);
        AutoDriveFB((int) (3.5*FOOT), -0.5);
        ResetClaw();
        sleep(200);
    }
}


