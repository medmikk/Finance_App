package com.medvedev.financeapp.domain.model;

public class StockPriceData
{
    private String c;
    private String pc;
    private String t;
    private String h;
    private String l;
    private String o;

    public String getC ()
    {
        return c;
    }

    public void setC (String c)
    {
        this.c = c;
    }

    public String getPc ()
    {
        return pc;
    }

    public void setPc (String pc)
    {
        this.pc = pc;
    }

    public String getT ()
    {
        return t;
    }

    public void setT (String t)
    {
        this.t = t;
    }

    public String getH ()
    {
        return h;
    }

    public void setH (String h)
    {
        this.h = h;
    }

    public String getL ()
    {
        return l;
    }

    public void setL (String l)
    {
        this.l = l;
    }

    public String getO ()
    {
        return o;
    }

    public void setO (String o)
    {
        this.o = o;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [c = "+c+", pc = "+pc+", t = "+t+", h = "+h+", l = "+l+", o = "+o+"]";
    }
}
