
package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "WaitThenPark" , group = "Main Op Modes")
public class WaitThenPark extends Robot{

    @Override
    public void runOpMode() throws InterruptedException{
        final int FOOT = 3200;
        scaleLeft -= 0.05;
        InitializeHardware();

        waitForStart();
        while(!opModeIsActive()) {
            sleep(10);
        }
        AutoDriveFB((int)(1 * FOOT), -1);
        scaleLeft += 0.05;
    }
}


