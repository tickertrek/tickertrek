/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;

import com.tickertrek.util.Constants;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author rajesroy
 */
@Entity
public class TradeOrder extends BaseEntity{

    
    String      strategyid;
    String      exchange;
    String      symbol;
    String      instrument;
    String      ordertype;
    String      pricetype;
    Integer     quantity;
    String      term;
    Double      price;
    Double      executionprice;
    Date        initiatetime;
    Date        executiontime;
    String      status;
    String      comment;
    Double      brokeragefee;
    Strategy    strategy;
    /*@Transient
    int         ordertypeInt;
    @Transient
    int         pricetypeInt;
    @Transient
    int         statusInt;
    @Transient
    int         exchangeInt;
    */
    public void init(){
        strategyid="";
        exchange = "";
        symbol="";
        instrument = Constants.Instrument.STOCK;
        ordertype = Constants.OrderConst.ORDER_BUY+"";
        pricetype = Constants.OrderConst.PRICE_MARKET+"";
        quantity = 0;
        term = Constants.OrderConst.TERM_GTC;
        price = 0.0;
        executionprice = 0.0;
        initiatetime = new Date();
        executiontime= new Date();
        status = Constants.OrderConst.ORDER_PROCESSING;
        comment = "";
        brokeragefee = 0.0;
        
       // strategy = null;
    }

    
    @Transient
    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    @Transient
    public Integer getOrdertypeInt(){
        return Integer.parseInt(this.getOrdertype());
    }
    public void setOrdertypeInt(int i){
        this.setOrdertype(i+"");
    }
    @Transient
    public Integer getPricetypeInt(){
        return Integer.parseInt(this.getPricetype());
    }
    
    public void setPricetypeInt(int i){
        this.setPricetype(i+"");
    }
    @Transient
    public Integer getStatusInt(){
        return Integer.parseInt(this.getStatus());
    }
    public void setStatusInt(int i){
        this.setStatus(i+"");
    }
    @Transient
    public Integer getExchangeInt(){
        return Integer.parseInt(this.getExchange());
    }
     
     
    public void setExchangeInt(int i){
        this.setExchange(i+"");
    }
    
    @Override
    public String toString(){
       
        return " Order ID:"+id+" Symbol:"+symbol+" Exchange:"+exchange+" OrderType:" + ordertype + " PriceType:"+pricetype + " Price:"+price +" Quantity:"+quantity + " InitiateTime:"+Constants.istDateFormat.format(initiatetime.getTime());
    }
    public String getStrategyid() {
        return strategyid;
    }

    public void setStrategyid(String strategyid) {
        this.strategyid = strategyid;
    }
    
    

    
    public Double getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Double brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Double getExecutionprice() {
        return executionprice;
    }

    public void setExecutionprice(Double executionprice) {
        this.executionprice = executionprice;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
        public Date getExecutiontime() {
        return executiontime;
    }

    public void setExecutiontime(Date executiontime) {
        this.executiontime = executiontime;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
        public Date getInitiatetime() {
        return initiatetime;
    }

    public void setInitiatetime(Date initiatetime) {
        this.initiatetime = initiatetime;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    
    
}
