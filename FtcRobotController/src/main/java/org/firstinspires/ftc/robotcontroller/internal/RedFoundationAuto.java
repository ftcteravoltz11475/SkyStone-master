
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
        Rotate(-30);
        AutoDriveFB((int) (-.75*FOOT), -0.5);
        Rotate(30);
        AutoDriveFB((int) (-FOOT), -0.25);
        TurnFoundationServo();
        sleep(200);
        Rotate(-40);
        AutoDriveFB((int) (3.1 * FOOT), -0.25);
        AutoDriveTank(3000, -3000, 0.25);
        Rotate(20);
        ResetFoundationServo();
        ResetClaw();
        sleep(300);
        TurnClaw();
        sleep(800);
        AutoDriveFB((int) (-.5*FOOT), -0.5);
        AutoDriveFB((int) (3.5*FOOT), -0.5);

    }
}


