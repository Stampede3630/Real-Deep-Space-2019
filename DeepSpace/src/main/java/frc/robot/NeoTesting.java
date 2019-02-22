package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;
public class NeoTesting {

    Timer spinTimer = new Timer();
    int deviceID = 1;

    CANSparkMax m_motor;
    CANEncoder m_encoder;

    public NeoTesting() {

        m_motor = new CANSparkMax(deviceID, MotorType.kBrushless);
        m_encoder = m_motor.getEncoder();
    }
    
    public void neoSpin() {
        if ( spinTimer.get() < 2) {
            m_motor.set(0.4);
        }

    }
}