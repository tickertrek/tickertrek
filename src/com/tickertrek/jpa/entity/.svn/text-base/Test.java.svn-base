/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.entity;


import com.tickertrek.jpa.util.JPAUtil;
import org.json.JSONObject;

/**
 *
 * @author rajesroy
 */
public class Test {
    
    public static void main(String[] args) throws Exception {
        Strategy s = new Strategy();
        // Set dummy data for all vars except id
        JPAUtil.setDummyData(s);
        
        String[] columns = {"profitfactor","strategyname"};
        JSONObject sj = JPAUtil.getJSON(s,columns);
        System.out.println(sj.toString());
        
        sj = JPAUtil.getJSON(s);
        System.out.println(sj.toString());
        
    }
    
}
