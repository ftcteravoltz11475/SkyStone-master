
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
        InitializeHardware();
        waitForStart();
         while(!opModeIsActive()) {
             sleep(10);
         }
         AutoDrive(8000, 8000, 1);


    }
}


