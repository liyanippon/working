package model;

import java.math.BigDecimal;

/**
 * Created by admin on 2017/5/24.
 * 时间查询
 */

public class FinancialBillingGetWXsettlementMonth {

    /**
     * jz1 : 2257.0
     * ce : 1023.0
     * cz1 : 1234.0
     * ye : 2017
     * mon : 5
     */

    private BigDecimal jz1;
    private BigDecimal ce;
    private BigDecimal cz1;
    private int ye;
    private int mon;

    public BigDecimal getJz1() {
        return jz1;
    }

    public void setJz1(BigDecimal jz1) {
        this.jz1 = jz1;
    }

    public BigDecimal getCe() {
        return ce;
    }

    public void setCe(BigDecimal ce) {
        this.ce = ce;
    }

    public BigDecimal getCz1() {
        return cz1;
    }

    public void setCz1(BigDecimal cz1) {
        this.cz1 = cz1;
    }

    public int getYe() {
        return ye;
    }

    public void setYe(int ye) {
        this.ye = ye;
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }
}
