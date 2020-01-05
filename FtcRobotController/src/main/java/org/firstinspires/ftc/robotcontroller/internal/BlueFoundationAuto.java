
package org.firstinspires.ftc.robotcontroller.internal;
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

        InitializeHardware();

        waitForStart();
        while(!opModeIsActive()) {
            sleep(10);
        }
        ResetFoundationServo();
        AutoDriveFB((int)(-0.5 * FOOT), -1);
        Rotate(30);
        AutoDriveFB((int)(-1.7 * FOOT), -1);
        Rotate(-24);
        AutoDriveFB((int)(-0.8 * FOOT), -1);
        TurnFoundationServo();
        sleep(400);
        AutoDriveFB((int)(2 * FOOT), 1);
        ResetFoundationServo();
        sleep(300);
        AutoDriveFB((int)(0.5 * FOOT), 1);
        Rotate(90);
        AutoDriveFB((int)(2.3  * FOOT), -1);
        Rotate(85);
        AutoDriveFB((int)(3.5 * FOOT), 1);
        Rotate(85);
    }
}


