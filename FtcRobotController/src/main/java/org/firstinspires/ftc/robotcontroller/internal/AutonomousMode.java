
package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "AutonomousMode" , group = "Main Op Modes")
public class AutonomousMode extends Robot{

    @Override
    public void runOpMode() throws InterruptedException{
        final int FOOT = 3500;

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

         for(double distance = 3.5; distance <= 4.9; distance += 0.7) {
             TurnClaw();
             sleep(200);
             AutoLift(100);
             AutoDriveFB((int) (-1.5*FOOT), -1);
             Rotate(90);
             AutoDriveFB((int)(distance * FOOT), 1);
             ResetClaw();

             if(distance!=4.9) {
                 AutoDriveFB((int)((distance + 0.7)*FOOT), 1);
                 Rotate(90);
                 AutoDriveFB((int) (1.5*FOOT), -1);
             }
             if(distance==4.9) {
                 AutoDriveFB(-FOOT, 1);
             }
             AutoLift(-100);

         }




    }
}


