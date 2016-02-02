/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.dao;

import com.bullfinder.exception.BullFinderException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public interface PublicDataHandleDAO extends BaseDAO{

    public JSONArray[] getTSDataLineChart(String strategyId) throws BullFinderException;

    public JSONArray getROIMatrix(String strategyId) throws BullFinderException;

    public JSONArray getPIEChart(String strategyId) throws BullFinderException;

    public JSONObject getStatistics(String strategyId) throws BullFinderException;

    public JSONObject getFilterMenu() throws BullFinderException;

    public JSONArray getCompanyName(String term, String ex) throws BullFinderException;
    
    public String getSector(String symbol) throws BullFinderException;

    public JSONObject getChampionshipList() throws BullFinderException;

    public JSONArray getChampionshipRanking(String champId) throws BullFinderException;

    public JSONObject getChampInfo(String strategyId) throws BullFinderException;



}
