/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.util;

import java.io.Serializable;
import com.tickertrek.jpa.entity.TradeOrder;

/**
 *
 * @author rajesroy
 */

public class ControlPacket implements Serializable{
    public int OpCode;
    public int OrderCount;
    public TradeOrder[] OrderList;
    public String Msg ="";
    
    public ControlPacket(int op, int count){
        OpCode = op;
        OrderList = new TradeOrder[count];
        OrderCount = count;
    }
    
}
