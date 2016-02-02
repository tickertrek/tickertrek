/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.jpa.dao.impl;

import com.bullfinder.exception.BullFinderException;
import com.tickertrek.dao.StrategyDAO;
import com.tickertrek.jpa.entity.ChampUserProfile;
import com.tickertrek.jpa.entity.Championship;
import com.tickertrek.jpa.entity.MonthlyReturn;
import com.tickertrek.jpa.entity.TradeOrder;
import com.tickertrek.jpa.entity.StrategyUserProfile;
import com.tickertrek.jpa.entity.Strategy;
import com.tickertrek.jpa.entity.Trash;
import com.tickertrek.jpa.entity.Ts;
import com.tickertrek.jpa.entity.YearlyReturn;
import com.tickertrek.jpa.util.JPAUtil;
import com.tickertrek.util.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public class StrategyDAOImpl extends BaseDAOImpl implements StrategyDAO {

    @Override
    public void setStrategy(TradeOrder o)throws BullFinderException{
        //o.setStrategy(this.getEntity(Strategy.class, o.getStrategyid()));
    }
    
    @Override
    public void setOpentrades(Strategy st)throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select t from com.tickertrek.jpa.entity.Trade t " +
              "where t.strategyid =:s ");
            query.setParameter("s",st.getStrategyid());
            st.setOpentrades(query.getResultList());
            }
        finally {
            if (em!=null) {
                em.close();
            }
        }
            
    }
    
    @Override
    public List<Strategy> getStrategyList() throws BullFinderException{
        EntityManager em = null;

        try {
                em = factory.createEntityManager();
                Query query = em.createQuery("select s from com.tickertrek.jpa.entity.Strategy s ");

                return query.getResultList();
            }
            finally {
                if (em!=null) {
                    em.close();
                }
        }
    }
    
    @Override
    public JSONObject getStrategyList(String userID) throws BullFinderException {
         EntityManager em = null;
        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select o from com.tickertrek.jpa.entity.StrategyUserProfile o " +
              "where o.userid =:u");
            query.setParameter("u",userID);
            
            List<StrategyUserProfile> sup = query.getResultList();
            
            
            query = em.createQuery("select s from com.tickertrek.jpa.entity.Strategy s " +
              this.getORString("s.strategyid", sup.size()));
            //System.out.println("size of sup:" + sup.size());
            //query.setParameter("sid",sup);
            for(int i = 0; i < sup.size(); i++){
                query.setParameter("p"+i,sup.get(i).getStrategyid());
                //System.out.println("sup:"+sup.get(i).getStrategyid());
            }
            JSONObject strategyList = new JSONObject();
           //if(sup.size() > 1){
            List<Strategy> strategies = query.getResultList();
            System.out.println("Size of strategylist:"+strategies.size());
            for(Strategy s: strategies){
                JSONObject js = JPAUtil.getJSON(s);
                js.put("relation", this.getRelationFromList(sup, s.getId()));
                strategyList.put(s.getId(),js);
            }
            /*}else{
                List<Strategy> s = /*this.getStrategy(sup.get(0).getStrategyid());query.getResultList();
                JSONObject js = JPAUtil.getJSON(s);
                js.put("relation", this.getRelationFromList(sup, s.get(0).getId()));
            }*/
           
            return strategyList;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    private String getRelationFromList(List<StrategyUserProfile> list, String strategyID){
        for(int i =0 ; i < list.size();i++)
            if(list.get(i).getStrategyid().equals(strategyID)) return list.get(i).getRelation(); 
        return Constants.Strategy.PUBLIC+"";
    }
    
    @Override
    public void persistStrategy(Strategy s) throws BullFinderException{
        this.persistEntity(s);
        s.setStrategyid(s.getId());
        this.persistEntity(s);
    }
    @Override
    public Strategy getStrategy(String strategyID) throws BullFinderException {
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Strategy s = em.find(Strategy.class, strategyID);
            return s;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
   
   
    
    @Override
    public Strategy getStrategyByName(String name) throws BullFinderException {
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select s from com.tickertrek.jpa.entity.Strategy s " +
              "where s.strategyname=:name ");
            query.setParameter("name",name);
            return (Strategy)query.getSingleResult();
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public StrategyUserProfile getStrategyUserProfile(String userID, String strategyID) throws BullFinderException {
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select f from com.tickertrek.jpa.entity.StrategyUserProfile f " +
              "where f.userid =:u AND f.strategyid =:s");
            query.setParameter("u",userID);
            query.setParameter("s",strategyID);
            StrategyUserProfile r = (StrategyUserProfile)query.getSingleResult();
            return r;
        }catch(Exception e){
            return null;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public double getTotalValueForStrategyAtDate(Strategy strategy, Date date) throws Exception{
        Ts ts = this.getTs(strategy.getStrategyid(), date);
        if(ts == null ) return strategy.getInitialbalance();
        return ts.getTotalvalue();
    }
    
    @Override
    public int getRelation(String userID, String strategyID) throws BullFinderException {
            StrategyUserProfile sup = this.getStrategyUserProfile(userID, strategyID);
            if(sup == null) return Constants.Strategy.PUBLIC;
            return Integer.parseInt(sup.getRelation());
       
    }
    
    @Override
    public void deleteAllRelations(String strategyID) throws BullFinderException{
        EntityManager em = null;
        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select o from com.tickertrek.jpa.entity.StrategyUserProfile o " +
              "where o.strategyid =:u");
            query.setParameter("u",strategyID);
            
            List<StrategyUserProfile> sup = query.getResultList();
            for(StrategyUserProfile s : sup)
                em.remove(s);
            
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public void persistChamp(Championship c) throws BullFinderException{
        this.persistEntity(c);
        c.setChampionshipid(c.getId());
        this.persistEntity(c);
    }
    @Override
    public Championship getChampionship(String championshipID) throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Championship c = em.find(Championship.class, championshipID);
            return c;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    @Override
    public ChampUserProfile getChampUserProfile(String champID, String userID) throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.ChampUserProfile c " +
              "where c.championshipid =:cid AND c.userid =:uid");
            
            query.setParameter("cid",champID);
            query.setParameter("uid",userID);
            try{
                return (ChampUserProfile)query.getSingleResult();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    @Override
    public List<ChampUserProfile> getChampUserProfileByChampId(String champID) throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.ChampUserProfile c " +
              "where c.championshipid =:cid");
            
            query.setParameter("cid",champID);
           return (List<ChampUserProfile>)query.getSingleResult();
          
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public ChampUserProfile getChampUserProfile(String strategyID) throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.ChampUserProfile c " +
              "where c.strategyid =:sid");
            
            query.setParameter("sid",strategyID);
            
            try{
                return (ChampUserProfile)query.getSingleResult();
            }catch(Exception e){
                return null;
            }
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    @Override
    public JSONObject getChampionshipList()throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.Championship c " );
             // "where c.status =:s1 OR c.status =:s2");
           // query.setParameter("s1",Constants.Championship.ACTIVE);
           // query.setParameter("s2",Constants.Championship.ENDED);
            
            List<Championship> champs = query.getResultList();
            JSONObject champList = new JSONObject();
            
            for(int i = 0; i <champs.size(); i++){
                Championship c = champs.get(i);
                JSONObject jc = JPAUtil.getJSON(c);
                champList.put(c.getId(),jc);
            }
            return champList;
        }catch(Exception e){
            e.printStackTrace();
            throw new BullFinderException(e);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }

    }
    
    @Override
    public List<Championship> getRunningChampionships()throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.Championship c " +
              "where c.status =:s1");
            query.setParameter("s1",Constants.Championship.RUNNING);
           // query.setParameter("s2",Constants.Championship.ENDED);
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            throw new BullFinderException(e);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }

    }
    
    @Override
    public JSONArray getChampionshipRanking(String champid, int count)throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.ChampUserProfile c " +
              "where c.championshipid =:cid AND c.position > :p order by c.position asc");
            query.setParameter("cid",champid);
            query.setParameter("p",0);
            //Query setMaxResults = query.setMaxResults(count);
            
            List<ChampUserProfile> champs = query.getResultList();//setMaxResults.getResultList();
            JSONArray cupList = new JSONArray();
            for(ChampUserProfile c : champs) {
                if(count == 0)
                    break;
                cupList.put(JPAUtil.getJSON(c));
                count--;
            }
            return cupList;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }

    }
    
    
    
    
    @Override
    public JSONObject searchStrategy(String sortName, String sortOrder,int page, int rp,  JSONArray filterSet)
            throws BullFinderException
    {
        List<String> fields = new ArrayList<String>();
        fields.add("id");
        String where = "WHERE";// s.listingtype = '"+Constants.Strategy.LISTING_PUBLIC+"'";
        //if ($query) $where = " WHERE $qtype LIKE '%$query%' ";
        int fieldCount = 1;
        String sql = "SELECT s from com.tickertrek.jpa.entity.Strategy s ";
        Map<String, Object> paramValues = new HashMap<String, Object>();
        int paramIndex = 1;
        String paramNameBase = "param";
        for(int i=0;i<filterSet.length();i++ )
         {
            JSONObject f = filterSet.getJSONObject(i);

            if(f.getInt("filtertype") != Constants.Filter.FILTER_BASIC_SEARCH)
            {
                //sql += ("," + f.getString("field"));
                fields.add(f.getString("field"));
                fieldCount++;
            }
            String condition ="";

            if(f.getInt("filtertype") == Constants.Filter.FILTER_RANGE) { // adavanced range filter
                   condition = " s." + f.getString("field") + " >= :"
                           +paramNameBase+paramIndex+ " AND s." + f.getString("field")
                           +" <= :"+paramNameBase+(paramIndex+1);
                   
                   paramValues.put(paramNameBase+paramIndex, convertToAttribubteType(Strategy.class,
                           f.getString("field"), f.getString("min")));
                   paramValues.put(paramNameBase+(paramIndex+1), convertToAttribubteType(Strategy.class,
                           f.getString("field"), f.getString("max")));
                   paramIndex += 2;
             }
            else if(f.getInt("filtertype") == Constants.Filter.FILTER_VALUE) { // basic or advanced value filter
               condition = " s." + f.getString("field") + " = :" + paramNameBase+paramIndex + "";
               paramValues.put(paramNameBase+paramIndex, convertToAttribubteType(Strategy.class,
                           f.getString("field"), f.getString("value")));
               paramIndex++;
            }
            else if(f.getInt("filtertype") == Constants.Filter.FILTER_BASIC_SEARCH)
            { // basic search condition filter
                if(Constants.Filter.FILTER_BASIC_TYPE1.equals(f.getString("field"))){
                        if(Constants.Filter.FILTER_BASIC_MAX_PROFIT.equals(f.getString("value")))
                        {
                            sortName ="annualreturn";
                            sortOrder = "desc";
                        }else if(Constants.Filter.FILTER_BASIC_MOST_ACTIVE.equals(f.getString("value"))){
                            sortName = "tradecount";
                            sortOrder = "desc";
                        }else if(Constants.Filter.FILTER_BASIC_TOP_LOOSER.equals(f.getString("value"))){
                            sortName = "annualreturn";
                            sortOrder = "asc";
                        }
                }else if(Constants.Filter.FILTER_BASIC_TYPE2.equals(f.getString("field")))
                {
                        if(Constants.Filter.FILTER_BASIC_LONG_TRACK.equals(f.getString("value"))) // long track record
                        {
                            condition = " s.age > :"+paramNameBase+paramIndex;
                            paramValues.put(paramNameBase+paramIndex, 90);
                            paramIndex++;
                        }else if(Constants.Filter.FILTER_BASIC_FREE_TRIAL.equals(f.getString("value"))){ // offer free trial
                            condition = " s.freetrial > :"+paramNameBase+paramIndex;
                            paramValues.put(paramNameBase+paramIndex, 0);
                            paramIndex++;
                        }else if(Constants.Filter.FILTER_BASIC_PROFITABLE_THREE_MONTHS.equals(f.getString("value"))){ // profitable last 3 months
                            condition = " s.3mreturn > :"+paramNameBase+paramIndex;
                            paramValues.put(paramNameBase+paramIndex, 0.0d);
                            paramIndex++;
                        }
                }else if(f.getString("field").equals("bs-market")) {
                    condition = " s.market = :" + paramNameBase+paramIndex + "";
                    paramValues.put(paramNameBase+paramIndex, f.getString("value"));
                    paramIndex++;
                }
                else if(f.getString("field").equals("bs-instrument")) {
                    condition = " s.instrumentoptions = :" + paramNameBase+paramIndex + "";
                    paramValues.put(paramNameBase+paramIndex, f.getString("value"));
                    paramIndex++;
                }
            }


            if(!where.equals("WHERE") && !condition.isEmpty())
                condition = " AND "+condition;
            where += condition;
        }
        if(where.equals("WHERE")) where = "";
       
        


        String sort = "ORDER BY s."+sortName +" "+sortOrder;
        
        if(where != null && where.trim().length() != 0) {
            if(!where.contains(sortName)){ //If the where clause does not include the sort name
                                            //Then, have to another is not null clause
                where += " AND s."+sortName+" IS NOT NULL";

            }
            sql += where+" "+ sort;//+ limit);
        }

        System.out.println("Query:" + sql);
        System.out.println("Param values:" +paramValues);
        
        
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery(sql);
            for(String param : paramValues.keySet()){
                query.setParameter(param, paramValues.get(param));
            }
            
            List<Object> strategies = query.getResultList();
            try{
                JSONArray sList = JPAUtil.getFlexiGridFormat(strategies, fields);
                JSONObject reply = new JSONObject();
                reply.put("page", page);
                reply.put("total", strategies.size());
                reply.put("rows", sList);
                return reply;
            }catch(Exception e){
                e.printStackTrace();
                throw new BullFinderException();
            }            
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }                    
    }    
    
    @Override
    public void emptyTrash()throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select o from com.tickertrek.jpa.entity.Trash o ");
            
            for(Trash t : (List<Trash>)query.getResultList()){
                if(Strategy.class.getName().equals(t.getType()) )
                    clearStrategy(t.getRefid());
                    
            }
                
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
   
    public void clearStrategy(String strategyID){
       
            EntityManager em = null;

        try {
            Query query = em.createQuery("delete from com.tickertrek.jpa.entity.Order o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            
            query = em.createQuery("delete from com.tickertrek.jpa.entity.Trade o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            
            query = em.createQuery("delete from com.tickertrek.jpa.entity.Ts o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            
            query = em.createQuery("delete from com.tickertrek.jpa.entity.Sector o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            
            query = em.createQuery("delete from com.tickertrek.jpa.entity.MonthlyReturn o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            
            
            query = em.createQuery("delete from com.tickertrek.jpa.entity.YearlyReturn o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            
            return;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public void clearSectors(String strategyID)throws BullFinderException{
        EntityManager em = null;

        try {
            Query query = em.createQuery("delete from com.tickertrek.jpa.entity.Sector o " +
                    "o.strategyid =:p1");
            query.setParameter("p1", strategyID);
            query.executeUpdate();
            return;
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public Ts getTs(String strategyID,Date currentDate)throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select o from com.tickertrek.jpa.entity.Ts o " +
              "where o.strategyid =:sid AND o.time=:t");
            
            query.setParameter("sid",strategyID);
            query.setParameter("t", currentDate);
            
            try{
                List<Ts> ls = query.getResultList();
                if(ls== null || ls.isEmpty()) return null;
                if(ls.size() > 1){
                    for(int i = 1; i< ls.size();i++)
                        em.remove(ls.get(i));
                    return ls.get(0);
                }
                
                return ls.get(0);
                
            }catch(Exception e){
                throw new BullFinderException();
            }
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public YearlyReturn getYearlyReturn(String strategyID,Date currentDate)throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select o from com.tickertrek.jpa.entity.YearlyReturn o " +
              "where o.strategyid =:sid AND o.time=:t");
            
            query.setParameter("sid",strategyID);
            query.setParameter("t", currentDate);
            
            try{
                List<YearlyReturn> ls = query.getResultList();
                if(ls== null || ls.isEmpty()) return null;
                if(ls.size() > 1){
                    for(int i = 1; i< ls.size();i++)
                        em.remove(ls.get(i));
                    return ls.get(0);
                }
                
                return ls.get(0);
                
            }catch(Exception e){
                throw new BullFinderException();
            }
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public MonthlyReturn getMonthlyReturn(String strategyID,Date currentDate)throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select o from com.tickertrek.jpa.entity.MonthlyReturn o " +
              "where o.strategyid =:sid AND o.time=:t");
            
            query.setParameter("sid",strategyID);
            query.setParameter("t", currentDate);
            
            try{
                List<MonthlyReturn> ls = query.getResultList();
                if(ls== null || ls.isEmpty()) return null;
                if(ls.size() > 1){
                    for(int i = 1; i< ls.size();i++)
                        em.remove(ls.get(i));
                    return ls.get(0);
                }
                
                return ls.get(0);
                
            }catch(Exception e){
                throw new BullFinderException();
            }
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONObject signupForChampionship(String userID, String champID, String nickName) throws BullFinderException {
        try {
            ChampUserProfile cup = this.getChampUserProfile(champID, userID);

            if( cup != null){
                //this.loger.info("ChampUserProfile:" +cup );
                throw new BullFinderException(Constants.ExceptionCode.DUPLICATE_CHAMP_SIGNUP,"You already signed up for this contest. Please look in your contest list.");
            }
            Championship champ = this.getEntity(Championship.class, champID);
            Strategy s = new Strategy();
            s.init();
            s.setUserid(userID);
            s.setStrategyname(champ.getName()+" ("+nickName+")");
            s.setMarket(champ.getMarket());
            s.setInstrumentoptions(champ.getInstrumentoptions());
            s.setCurrency(champ.getCurrency());
            s.setInitialbalance(champ.getInitialbalance());
            s.setPower(champ.getInitialbalance());
            s.setBalance(champ.getInitialbalance());
            s.setType(Constants.Strategy.TYPE_CHAMPIONSHIP);
            s.setListingtype(Constants.Strategy.LISTING_PRIVATE);

            this.persistStrategy(s);

            cup = new ChampUserProfile();
            cup.init();
            cup.setChampionshipid(champ.getId());
            cup.setStrategyid(s.getId());
            cup.setUserid(userID);
            cup.setNickname(nickName);

            this.persistEntity(cup);

            champ.setPcount(champ.getPcount()+1);

            this.persistEntity(champ);

            JSONObject js = JPAUtil.getJSON(s);
            js.put("relation",Constants.Strategy.OWNER);
            // get rid of the strategy ext...
            //strategy.put("strategyname", strategy.getString("strategyname"));
            return js;
        }catch (Exception exp){
            throw createCustomizedException(exp);
        }
    }

}
