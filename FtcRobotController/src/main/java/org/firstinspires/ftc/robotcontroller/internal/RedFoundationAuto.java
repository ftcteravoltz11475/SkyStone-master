
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
        final int FOOT = 800;
        final double sL = scaleLeft;
        InitializeHardware();

        waitForStart();
        while(!opModeIsActive()) {
            sleep(10);
        }
        ResetFoundationServo();
        AutoDriveFB((int) (-1.5*FOOT), -0.5);
        //Rotate(-30);
        sleep(100);
        AutoDriveFB((int) (-.75*FOOT), -0.5);
        Rotate(20);
        AutoDriveFB((int) (-FOOT), -0.25);
        TurnFoundationServo();
        sleep(200);
        //Rotate(-40);
        AutoDriveFB((int) (3 * FOOT), -0.25);
        AutoDriveTank(2000, -2000, 0.25);
        Rotate(20);
        ResetFoundationServo();
        ResetClaw();
        sleep(300);
        TurnClaw();
        sleep(800);
        ResetFoundationServo();
        AutoDriveFB((int)(.5*FOOT), -1);
        AutoDriveFB((int) (-1*FOOT), -1);
        sleep(500);
        AutoDriveFB((int) (3*FOOT), -0.5);

    }
}


