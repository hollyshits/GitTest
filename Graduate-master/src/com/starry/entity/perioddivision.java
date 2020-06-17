package com.starry.entity;

public class perioddivision {
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSchid() {
        return schid;
    }

    public void setSchid(int schid) {
        schid = schid;
    }

    public String getSchdate() {
        return schdate;
    }

    public void setSchdate(String schdate) {
        schdate = schdate;
    }

    public String getTimeDiv() {
        return timeDiv;
    }

    public void setTimeDiv(String timeDiv) {
        this.timeDiv = timeDiv;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "perioddivison{" +
                "pid=" + pid +
                ", Schid=" + schid +
                ", Schdate='" + schdate + '\'' +
                ", timeDiv='" + timeDiv + '\'' +
                ", state=" + state +
                ", docid='" + docid + '\'' +
                '}';
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    private Integer pid;
    private Integer schid;
    private String schdate;
    private String timeDiv;

    public perioddivision(Integer pid, Integer schid, String schdate, String timeDiv, Integer state, Integer docid) {
        this.pid = pid;
        this.schid = schid;
        this.schdate = schdate;
        this.timeDiv = timeDiv;
        this.state = state;
        this.docid = docid;
    }


    private Integer state;
    private Integer docid;
}
