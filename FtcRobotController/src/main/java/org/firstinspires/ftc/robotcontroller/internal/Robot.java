package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class  Robot extends LinearOpMode{
    private Servo foundationServo, clawServo;
    private DcMotor rightFrontMotor,leftFrontMotor, rightBackMotor, leftBackMotor, liftMotorLeft, liftMotorRight;
    private int wheelLeftBase, posLift, wheelRightBase;
    //private WebcamName webcam;

    public void InitializeHardware(){
        //Hardware initiationlization
        foundationServo = hardwareMap.get(Servo.class, "foundationServo");

        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        liftMotorLeft = hardwareMap.get(DcMotor.class, "liftMotorLeft");
        liftMotorRight = hardwareMap.get(DcMotor.class, "liftMotorRight");

        //webcam  = hardwareMap.get(WebcamName.class,"Camera");

        // Lift initialize
        liftMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wheel initialize
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Turning Brake on for wheels
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Getting current positions
        wheelLeftBase = leftBackMotor.getCurrentPosition();
        wheelRightBase = rightBackMotor.getCurrentPosition();
        posLift = liftMotorLeft.getCurrentPosition();  // ???
        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //parameters.vuforiaLicenseKey = "AT0vdpL/////AAABmYfRfMJ4SEYKmqchn8mtyN4cj9kPD9y8s0MERpQOBt8B8ALWw/IuTuMrIMGp4bayqI7a4aAiW8Wx+Orm9xZ5O8K7mdmXrtFUuN0GF/agS/M6Sz4s4bJrgGio3Aauy1Shd18cGy7NWW4thm+vDQlOGgIAR0FEOEt4nv2RtcaK24Cusr4VgwGldY9/v6O7XGOwa4uAcR6Q3oI+wkuighO4Ag1wFAd3GyAmFMXTQvw41xbeNSZMOHpBJLx7eU348xWsaUNcdUoE02xyH/xZ6qUjNcDLcNnvP/+0LwFaRolPmNqCFfmB2cdgYsJfvkc32xgTSYALJYRi90bG72G/e2U9cs2Na2VzopyJM6a6nljBiQq9";
    }

    private float ScalePower(float power){
        //Scales the power to be power * |power|
        return Math.abs(power) * power;
    }

    public void TankDrive(float power1, float power2){
        //Sets the power for the right to power1 scaled and left power2 scaled
        rightFrontMotor.setPower(0.83 *ScalePower(power1));
        rightBackMotor.setPower(0.83 * ScalePower(power1));
        leftFrontMotor.setPower(ScalePower(power2));
        leftBackMotor.setPower(ScalePower(power2));
    }

    public void MecanumDrive(float power1){
        //Sets the power for the right to power1 scaled and left power2 scaled
        rightFrontMotor.setPower(0.83 * ScalePower(power1));
        rightBackMotor.setPower(0.83 * -ScalePower(power1));
        leftFrontMotor.setPower(-ScalePower(power1));
        leftBackMotor.setPower(ScalePower(power1));
    }

    public void TurnClaw(){
        clawServo.setPosition(0.5);
    }

    public void TurnClaw(double angle){
        clawServo.setPosition(angle);
    }

    public void ResetClaw(){
        clawServo.setPosition(0);
    }

    public void Lift(double power){
         liftMotorLeft.setPower(power);
         liftMotorRight.setPower(power);
    }

    public void TurnFoundationServo(){
        foundationServo.setPosition(0.5);
    }

    public void TurnFoundationServo(double angle){
        foundationServo.setPosition(angle);
    }

    public void ResetFoundationServo() {
        foundationServo.setPosition(0);
    }

    public int getPosWheelLeft(){
        //Get the position of the motor in the lift, 0 is starting position.
        return wheelLeftBase - leftBackMotor.getCurrentPosition();
    }

    public int getPosWheelRight(){
        return wheelRightBase - rightBackMotor.getCurrentPosition();
    }

    public int getPosLift(){
        return posLift - liftMotorLeft.getCurrentPosition();
    }

    public void resetPosWheel(){
        wheelLeftBase = leftBackMotor.getCurrentPosition();
        wheelRightBase = rightBackMotor.getCurrentPosition();
    }
    public void AutoDrive(int targetLeft, int targetRight, double power) {
        int startLeft = leftFrontMotor.getCurrentPosition();
        int startRight = rightFrontMotor.getCurrentPosition();

        leftFrontMotor.setTargetPosition(startLeft + targetLeft);
        rightFrontMotor.setTargetPosition(startRight + targetRight);
        leftBackMotor.setTargetPosition(startLeft + targetLeft);
        rightBackMotor.setTargetPosition(startRight + targetRight);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontMotor.setPower(power);
        rightFrontMotor.setPower(power * 0.83);
        leftBackMotor.setPower(power);
        rightBackMotor.setPower(power * 0.83);

        while(leftFrontMotor.isBusy() && rightFrontMotor.isBusy()) {
            sleep(5);
        }
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightBackMotor.setPower(0);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Done!", "");
        telemetry.update();
        sleep(1000);

    }
}
