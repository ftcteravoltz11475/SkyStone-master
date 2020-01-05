
package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "BlueBlocksAuto" , group = "Main Op Modes")
public class BlueBlocksAuto extends Robot{

    @Override
    public void runOpMode() throws InterruptedException{
        final int FOOT = 3200;

        InitializeHardware();

        waitForStart();
         while(!opModeIsActive()) {
             sleep(10);
         }

         for(int i = 0; i < 2; i++) {
            TurnClaw();
            sleep(200);
            ResetClaw();
            sleep(200);
        }

        AutoDriveFB((int)(2.416 * FOOT), 1);
        TurnClaw();
        sleep(200);
        AutoDriveFB((int) (-1 * FOOT), -1);
        Rotate(90);
        AutoDriveFB((int) (4.2 * FOOT), 1);
        ResetClaw();
        sleep(100);
        AutoDriveFB((int) (-3.33 * FOOT), -1);
        Rotate(-90);
        AutoDriveFB((int)(1.4 * FOOT), 1);
        sleep(200);
        TurnClaw();
        AutoDriveFB((int)(-1 * FOOT), -1);
        Rotate(90);
        AutoDriveFB((int)(2.5 * FOOT), 1);
    }
}


