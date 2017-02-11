package priyanshu.zappos.com.ilovezappos;

/**
 * Created by PRIYANSHU on 2/7/2017.
 * It Performs the bounce animation when a button is clicked.
 */

public class BounceInterpolate implements android.view.animation.Interpolator{
    double mAmplitude = 1;
    double mFrequency = 10;

    BounceInterpolate(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
