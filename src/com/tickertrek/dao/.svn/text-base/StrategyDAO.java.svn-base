/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.dao;

import com.bullfinder.exception.BullFinderException;
import com.tickertrek.jpa.entity.ChampUserProfile;
import com.tickertrek.jpa.entity.Championship;
import com.tickertrek.jpa.entity.MonthlyReturn;
import com.tickertrek.jpa.entity.TradeOrder;
import com.tickertrek.jpa.entity.StrategyUserProfile;
import com.tickertrek.jpa.entity.Strategy;
import com.tickertrek.jpa.entity.Ts;
import com.tickertrek.jpa.entity.YearlyReturn;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public interface StrategyDAO extends BaseDAO {

    public void persistStrategy(Strategy s) throws BullFinderException;
    public void setStrategy(TradeOrder o)throws BullFinderException;
    public void setOpentrades(Strategy s)throws BullFinderException;
    public Strategy getStrategy(String strategyid) throws BullFinderException;
    public Strategy getStrategyByName(String name) throws BullFinderException;
    public double getTotalValueForStrategyAtDate(Strategy strategy, Date date) throws Exception;
    
    public List<Strategy> getStrategyList() throws BullFinderException;
    public JSONObject getStrategyList(String userID) throws BullFinderException;
    public JSONObject searchStrategy(String sortName, String sortOrder,int page, int rp,  JSONArray filterSet)
            throws BullFinderException;
    

    public int getRelation(String userID, String strategyID) throws BullFinderException;
    public StrategyUserProfile getStrategyUserProfile(String userID, String strategyID) throws BullFinderException;
    public void deleteAllRelations(String strategyID) throws BullFinderException;
    
    public void persistChamp(Championship c) throws BullFinderException;
    public Championship getChampionship(String championshipid) throws BullFinderException;
    public List<Championship> getRunningChampionships() throws BullFinderException;
    public ChampUserProfile getChampUserProfile(String champID, String userID) throws BullFinderException;
    public List<ChampUserProfile> getChampUserProfileByChampId(String champID) throws BullFinderException;
    public ChampUserProfile getChampUserProfile(String strategyID) throws BullFinderException;
    public JSONArray getChampionshipRanking(String champid, int count)throws BullFinderException;
    public JSONObject getChampionshipList()throws BullFinderException;
    
    public Ts getTs(String strategyID,Date currentDate)throws BullFinderException;
    public YearlyReturn getYearlyReturn(String strategyID,Date currentDate)throws BullFinderException;
    public MonthlyReturn getMonthlyReturn(String strategyID,Date currentDate)throws BullFinderException;
    
    
    public void clearSectors(String strategyID)throws BullFinderException;
    public void emptyTrash()throws BullFinderException;

    public JSONObject signupForChampionship(String userID, String champID, String nickName)
            throws BullFinderException;

}
