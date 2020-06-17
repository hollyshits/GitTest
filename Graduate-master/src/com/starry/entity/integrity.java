package com.starry.entity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class integrity {
    private Integer integrityId;
    private Integer uid;
    private Integer docid;
    private String time;
    private String reason;
    private Integer score;
    //非数据库字段
    private String docname;

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }
    public integrity(Integer integrityId, Integer uid, Integer docid, String time, String reason, Integer score,String docname) {
        this.integrityId = integrityId;
        this.uid = uid;
        this.docid = docid;
        this.time = time;
        this.reason = reason;
        this.score = score;
        this.docname=docname;
    }
    public integrity(Integer uid, Integer docid, String time, String reason, Integer score) {
        this.uid = uid;
        this.docid = docid;
        this.time = time;
        this.reason = reason;
        this.score = score;
    }

    public integrity(Integer integrityId, Integer uid, Integer docid, String time, String reason, Integer score) {
        this.integrityId = integrityId;
        this.uid = uid;
        this.docid = docid;
        this.time = time;
        this.reason = reason;
        this.score = score;
    }

    public Integer getIntegrityId() {
        return integrityId;
    }

    public void setIntegrityId(Integer integrityId) {
        this.integrityId = integrityId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
