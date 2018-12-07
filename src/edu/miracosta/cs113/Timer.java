package edu.miracosta.cs113;

public class Timer {

    private double lastLoopTime;
    private double autoMoveTime;

    public Timer(){
        init();
    }

    public void init()
    {
        autoMoveTime = 0;
        lastLoopTime = getTime();
    }

    public double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }

    public double getAutoMoveTime(){
        return autoMoveTime;
    }

    public void setAutoMoveTime(double time){
        this.autoMoveTime += time;
    }

    public void resetAutoMoveTime(){
        autoMoveTime = 0;
    }
}
