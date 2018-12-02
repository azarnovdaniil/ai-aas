package ru.daniilazarnov.calc.model;

public class UnityLogRow extends LogRow {

    private double x;
    private double y;
    private double z;

    public UnityLogRow(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            this.setValue(strings, i);
        }
        if (this.action.equals("Move")) {
            this.target = "";
        }
    }

    @Override
    public void setValue(String[] values, int i) {
        switch (i) {
            case 0:
                this.timeStamp = values[0];
                break;
            case 1:
                this.sessionId = values[1];
                break;
            case 2:
                this.actor = values[2];
                break;
            case 3:
                this.action = values[3];
                break;
            case 4:
                this.x = Double.valueOf(values[4]);
                break;
            case 5:
                this.y = Double.valueOf(values[5]);
                break;
            case 6:
                this.z = Double.valueOf(values[6]);
                break;
            case 7:
                this.target = values[7];
                break;
            default:
                break;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

}
