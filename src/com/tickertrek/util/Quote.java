/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.util;

//import com.bullfinder.util.Constants;

import java.util.*;
/**
 *
 * @author root
 */
public class Quote {
public double Price;
public Date TimeStamp;
public String Symbol;
public String Exchange;
public Quote(String t, Date d, float p, String ex){
    Symbol = t;
    Price = p;
    TimeStamp = d;
    Exchange = ex;
}

    public String getExchange() {
        return Exchange;
    }

    public void setExchange(String Exchange) {
        this.Exchange = Exchange;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date TimeStamp) {
        this.TimeStamp = TimeStamp;
    }


@Override
public String toString()
    {
       
         return "Quote Symbol:" + Symbol + " Price:"+Price+" Time:" + Constants.istDateFormat.format(TimeStamp);
    }
}
