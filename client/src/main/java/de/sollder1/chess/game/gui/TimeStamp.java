package de.sollder1.chess.game.gui;

public class TimeStamp {

    private int hour;
    private int min;
    private int sec;
    private boolean ended = false;

    public TimeStamp(int sec) {
        this.sec = sec;
    }

    public TimeStamp(int min, int sec) {
        this.min = min;
        this.sec = sec;
    }

    public TimeStamp(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    //Increments the Timestamp by 1 sec.
    public void decrement() {

        if (sec - 1 == 0 && min == 0 && hour == 0) {
            sec--;
            ended = true;
            return;
        }

        if (sec - 1 >= 0) {
            sec--;
            return;
        }

        if (min - 1 >= 0) {
            sec = 59;
            min--;
            return;
        }

        if (hour > 0) {
            sec = 59;
            min = 59;
            hour--;
        }

    }

    @Override
    public String toString() {
        return "" + (hour < 10 ? 0 + "" + hour : hour) + ":"
                + (min < 10 ? 0 + "" + min : min) + ":"
                + (sec < 10 ? 0 + "" + sec : sec);
    }

    public boolean ended() {
        return ended;
    }
}
