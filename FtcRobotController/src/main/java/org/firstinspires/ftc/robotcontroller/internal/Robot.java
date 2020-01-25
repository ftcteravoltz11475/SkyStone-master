package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class  Robot extends LinearOpMode {
    private Servo foundationServo, clawServo;
    private DcMotor rightFrontMotor, leftFrontMotor, rightBackMotor, leftBackMotor, liftMotorLeft, liftMotorRight;
    private int wheelLeftBase, posLift, wheelRightBase;
    public double scaleRight = 0.97;
    public double scaleLeft = 1;
    //private WebcamName webcam;

    public void InitializeHardware() {
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

    public float ScalePower(float power) {
        //Scales the power to be power * |power|
        return Math.abs(power) * power;
    }

    public void TankDrive(float power1, float power2) {
        //Sets the power for the right to power1 scaled and left power2 scaled
        rightFrontMotor.setPower(scaleRight * ScalePower(power1));
        rightBackMotor.setPower(scaleRight * ScalePower(power1));
        leftFrontMotor.setPower(scaleLeft * ScalePower(power2));
        leftBackMotor.setPower(scaleLeft * ScalePower(power2));
    }

    public void TurnClaw() {
        clawServo.setPosition(0.5);
    }

    public void TurnClaw(double angle) {
        clawServo.setPosition(angle);
    }

    public void ResetClaw() {
        clawServo.setPosition(0);
    }

    public void Lift(double power) {
        liftMotorLeft.setPower(power);
        liftMotorRight.setPower(power);
    }

    public void ResetFoundationServo() {
        foundationServo.setPosition(0.5);
    }

    public void TurnFoundationServo(double pos) {
        foundationServo.setPosition(pos);
    }

    public void TurnFoundationServo() {
        foundationServo.setPosition(0);
    }

    public int getPosWheelLeft() {
        //Get the position of the motor in the lift, 0 is starting position.
        return wheelLeftBase - leftBackMotor.getCurrentPosition();
    }

    public int getPosWheelRight() {
        return wheelRightBase - rightBackMotor.getCurrentPosition();
    }

    public int getPosLift() {
        return -posLift + liftMotorLeft.getCurrentPosition();
    }

    public void resetPosWheel() {
        wheelLeftBase = leftBackMotor.getCurrentPosition();
        wheelRightBase = rightBackMotor.getCurrentPosition();
    }

    public void AutoDriveTank(int targetLeft, int targetRight, double power) {
        int startLeft = leftFrontMotor.getCurrentPosition();
        int startRight = rightFrontMotor.getCurrentPosition();

        leftFrontMotor.setTargetPosition(startLeft + (int)(targetLeft * scaleLeft));
        rightFrontMotor.setTargetPosition(startRight + (int) (targetRight * scaleRight));
        leftBackMotor.setTargetPosition(startLeft + (int)(targetLeft * scaleLeft));
        rightBackMotor.setTargetPosition(startRight + (int) (targetRight * scaleRight));

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontMotor.setPower(power * scaleLeft);
        rightFrontMotor.setPower(power * scaleRight);
        leftBackMotor.setPower(power * scaleLeft);
        rightBackMotor.setPower(power * scaleRight);

        while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy()) {
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

    }

    public void AutoDriveFB(int target, double power) {
        int startLeft = leftFrontMotor.getCurrentPosition();
        int startRight = rightFrontMotor.getCurrentPosition();

        leftFrontMotor.setTargetPosition(startLeft + (int)(target * scaleLeft));
        rightFrontMotor.setTargetPosition(startRight + (int) (target * scaleRight));
        leftBackMotor.setTargetPosition(startLeft + (int)(target * scaleLeft));
        rightBackMotor.setTargetPosition(startRight + (int) (target * scaleRight));

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontMotor.setPower(power * scaleLeft);
        rightFrontMotor.setPower(power * scaleRight);
        rightBackMotor.setPower(power * scaleLeft);
        leftBackMotor.setPower(power * scaleRight);

        while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy()) {
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
    }

    //    !! CLOCKWISE IS NEGATIVE !! COUNTERCLOCKWISE IS POSITIVE!!
    public void AutoLift(int position) {
        liftMotorLeft.setTargetPosition(position + liftMotorLeft.getCurrentPosition());
        liftMotorRight.setTargetPosition(position + liftMotorRight.getCurrentPosition());

        liftMotorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotorLeft.setPower(1);
        liftMotorRight.setPower(1);

        while (liftMotorLeft.isBusy() && liftMotorRight.isBusy()) {
            sleep(5);
        }

        liftMotorLeft.setPower(0);
        liftMotorRight.setPower(0);

        liftMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void Rotate(int degrees) {
        int NINETYDEGREES = 2900/4;

        AutoDriveTank(-(NINETYDEGREES * degrees) / 90, (NINETYDEGREES * degrees) / 90, .75);
    }
}