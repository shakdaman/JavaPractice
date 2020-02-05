package com.foruswithus.excelSheet;

import java.util.ArrayList;
import java.util.Date;

public class ExcelRecord {
    private String claimSfx;
    private int claimNbr;
    private double checkAmt;
    private Date checkDate;

    public ExcelRecord(String claimSfx, int claimNbr, double checkAmt, Date checkDate) {
        this.claimSfx = claimSfx;
        this.claimNbr = claimNbr;
        this.checkAmt = checkAmt;
        this.checkDate = checkDate;
    }

    public String getClaimSfx() {
        return claimSfx;
    }

    public int getClaimNbr() {
        return claimNbr;
    }

    public double getCheckAmt() {
        return checkAmt;
    }

    public Date getCheckDate() {
        return checkDate;
    }
}
