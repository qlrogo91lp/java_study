package main.codetest.basic.architecture.java;

public class Data extends Object {
    private String sigId;
    private String acqTime;
    private int val;

    public Data(String sigId, String acqTime, int val) {
        this.sigId = sigId;
        this.acqTime = acqTime;
        this.val = val;
    }

    public String getSigId() {
        return sigId;
    }

    public void setSigId(String sigId) {
        this.sigId = sigId;
    }

    public String getAcqTime() {
        return acqTime;
    }

    public void setAcqTime(String acqTime) {
        this.acqTime = acqTime;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {

        return String.format("[%s, %s, %s]", sigId, acqTime, val);
    }
}
