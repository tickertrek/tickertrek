/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.dao;


import com.bullfinder.exception.BullFinderException;
import com.tickertrek.jpa.entity.TradeOrder;
import com.tickertrek.jpa.entity.Trade;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public interface OrderDAO extends BaseDAO{

    
    public TradeOrder getOrder(String orderid)throws Exception;
    public void persistOrder(TradeOrder o) throws BullFinderException;
    public List<Object> getOrders(String strategyID, String sortOrder, String sortName, int page, int rp) throws Exception;
    public List<TradeOrder> getOpenOrders() throws BullFinderException;
    public List<Object> getFollowedOrders(String userID, String sortOrder, String sortName, int page, int rp) throws Exception;
    
    public Trade getTrade(String tradeID)throws Exception;
    public List<Object> getTrades(String strategyID, String sortOrder, String sortName, int page, int rp, boolean closeTrades) throws Exception;
    public int getOpenTradeCount(String strategyID, String symbol, String ex) throws BullFinderException;
    public List<Trade> getOpenTrades(String strategyID, String symbol, String exchange) throws BullFinderException;

}
