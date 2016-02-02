/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.jpa.dao.impl;

import com.bullfinder.exception.BullFinderException;
import com.tickertrek.dao.PublicDataHandleDAO;
import com.tickertrek.jpa.entity.BseNse;
import com.tickertrek.jpa.entity.ChampUserProfile;
import com.tickertrek.jpa.entity.Championship;
import com.tickertrek.jpa.entity.DiscussTopic;
import com.tickertrek.jpa.entity.MonthlyReturn;
import com.tickertrek.jpa.entity.Sector;
import com.tickertrek.jpa.entity.Strategy;
import com.tickertrek.jpa.entity.StrategyStat;
import com.tickertrek.jpa.entity.TagAssociation;
import com.tickertrek.jpa.entity.TagUpdate;
import com.tickertrek.jpa.entity.Ts;
import com.tickertrek.jpa.entity.UserProfile;
import com.tickertrek.jpa.util.JPAUtil;
import com.tickertrek.util.Constants;
import com.tickertrek.util.Utilities;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public class PublicDataHandleDAOImpl extends BaseDAOImpl implements PublicDataHandleDAO {

    @Override
    public JSONArray[] getTSDataLineChart(String strategyId) throws BullFinderException {
        //"SELECT `time`, equity, cash, drawdown FROM `ts` WHERE strategyid="+strategyid;
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select ts from com.tickertrek.jpa.entity.Ts ts " +
              "where ts.strategyid =:strategyid");
            query.setParameter("strategyid",strategyId);

            List<Ts> tss = query.getResultList();

            JSONArray[] dataSet = new JSONArray[3];
            dataSet[0] = new JSONArray();
            dataSet[1] = new JSONArray();
            dataSet[2] = new JSONArray();
            JSONArray row;

            for(Ts ts : tss){
                System.out.println(ts);
                row = new JSONArray();
                row.put(this.formatDateBack(ts.getTime()).getTime());
                row.put(ts.getEquity());
                dataSet[0].put(row);

                row = new JSONArray();
                row.put(this.formatDateBack(ts.getTime()).getTime());
                row.put(ts.getCash());
                dataSet[1].put(row);

                row = new JSONArray();
                row.put(this.formatDateBack(ts.getTime()).getTime());
                row.put(ts.getDrawdown());
                dataSet[2].put(row);
            }           

            return dataSet;

        } catch (Exception exp){
            exp.printStackTrace();
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONArray getROIMatrix(String strategyId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select m from com.tickertrek.jpa.entity.MonthlyReturn m " +
              "where m.time is not null and m.strategyid =:strategyid order by m.time desc");
            query.setParameter("strategyid",strategyId);

            List<MonthlyReturn> mrets = query.getResultList();

            String month = "['','Jan', 'Feb', 'Mar', 'Apr','May', 'Jun','Jul', 'Aug','Sep', 'Oct', 'Nov','Dec']";
            
            JSONArray mreturn = new JSONArray();
            JSONArray drow = new JSONArray(month);

            mreturn.put(drow);

            int year = -1, mon = -1;
            
            for(MonthlyReturn mret : mrets){
                if(getYearFromDate(this.formatDateBack(mret.getTime())) != year || year == -1){
                    drow = new JSONArray();
                    mreturn.put(drow);
                    drow.put(0, getYearFromDate(this.formatDateBack(mret.getTime()))+1900);
                    //following is to make sure that drow is always of length
                    // 13, in case there is no data for December, or for last few months
                    drow.put(12, "null");
                }
                year = getYearFromDate(this.formatDateBack(mret.getTime()));
                mon = getMonthFromDate(this.formatDateBack(mret.getTime()));
                drow.put(mon+1, Utilities.decimalFormatter.format(mret.getRoi()));
            }

            return mreturn;

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONArray getPIEChart(String strategyId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select s from com.tickertrek.jpa.entity.Sector s " +
              "where s.strategyid =:strategyid");
            query.setParameter("strategyid",strategyId);

            List<Sector> sectors = query.getResultList();

            JSONArray sector = new JSONArray();
            JSONObject s = new JSONObject();
            for(Sector sect : sectors){
                s = new JSONObject();
                s.put("label",sect.getSector());
                s.put("data",sect.getAllocation());
                sector.put(s);
            }
            
            return sector;

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONObject getStatistics(String strategyId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Strategy st = em.find(Strategy.class, strategyId);

            UserProfile profile = em.find(UserProfile.class, st.getUserid());
            
            JSONObject stat = JPAUtil.getJSON(st, new String[]{"strategyname"
                    , "type", "listingtype", "initialbalance", "currency"
                    , "instrumentoptions", "market", "started"
                    , "loosingtradecount", "ytdreturn", "annualreturn"
                    , "profittradecount", "tradecount", "profitfactor", "maxdrawdown"
                    , "age", "userid", "description", "sitescore", "avgwin"
                    , "avgloss", "viewtimes", "followers", "fee", "freetrial"
                    , "overallreturn", "balance"});

            stat.put("3mreturn", st.get3mreturn());
            stat.put("nickname", profile.getNickname());

            return stat;

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONObject getFilterMenu() throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select st from com.tickertrek.jpa.entity.StrategyStat st " +
              "where 1 = 1");

            List<StrategyStat> filters = query.getResultList();

            JSONObject reply = new JSONObject();
            JSONArray s = null;
            for(StrategyStat stat : filters){
                s = new JSONArray();
                s.put(stat.getMaxVal());
                s.put(stat.getMinVal());
                s.put(stat.getStep());
                reply.put(stat.getField(), s);
            }

            return reply;

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONArray getCompanyName(String term, String ex) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select b from com.tickertrek.jpa.entity.BseNse b " +
              "where b.isin_no <> :isin_no and ( b.name like :name or b.ticker like :ticker)");
            query.setParameter("name",term);
            query.setParameter("ticker",term);
            query.setParameter("isin_no","0");
            //query.setMaxResults(15);
            int maxResults = 15;

            List<BseNse> tickers = query.getResultList();

            JSONArray list = new JSONArray();
            for(BseNse ticker : tickers){
                if(maxResults == 0)
                    break;
                if((""+Constants.Exchange.BSE).equals(ex)){
                    list.put(ticker.getName()+"   (BSE:"+ticker.getTicker()+")");
                } else {
                    list.put(ticker.getName()+"   (NSE:"+ticker.getTicker()+")");
                }
                maxResults--;
            }

            return list;

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONObject getChampionshipList() throws BullFinderException {                
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select c from com.tickertrek.jpa.entity.Championship c " +
              "where c.status = :active or c.status = :ended");
            query.setParameter("active",Constants.Championship.ACTIVE);
            query.setParameter("ended",Constants.Championship.ENDED);
            
            List<Championship> champs = query.getResultList();
            JSONObject set = new JSONObject();
            JSONObject row;

            for(Championship champ : champs){
                row = JPAUtil.getJSON(champ);
                set.put(champ.getId(), row);
            }
            return set;
            
        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONArray getChampionshipRanking(String champId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select cu from com.tickertrek.jpa.entity.ChampUserProfile cu " +
              "where cu.championshipid=:championshipid order by cu.position desc");
            query.setParameter("championshipid",champId);
            //query.setMaxResults(5);
            int maxResults = 5;
            List<ChampUserProfile> champProfiles = query.getResultList();
            JSONArray set = new JSONArray();
            JSONObject row;

            for(ChampUserProfile champProf : champProfiles){
                if(maxResults == 0)
                    break;
                row = JPAUtil.getJSON(champProf);
                set.put(row);
                maxResults--;
            }
            return set;

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    @Override
    public JSONObject getChampInfo(String strategyId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select cu from com.tickertrek.jpa.entity.ChampUserProfile cu " +
              "where cu.strategyid=:strategyid ");
            query.setParameter("strategyid",strategyId);
            List<ChampUserProfile> champProfiles = query.getResultList();
            int count = (champProfiles == null) ? 0 : champProfiles.size();
            if(count != 1)
                throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    "Fatal error, no ChampUserProfile entity object "
                    + "found with strategy Id:"+strategyId);
            return JPAUtil.getJSON(champProfiles.get(0));

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    
    @Override
    public String    getSector(String symbol) throws BullFinderException{
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select t from com.tickertrek.jpa.entity.BseNse t " +
              "where t.ticker =:s ");
            query.setParameter("s",symbol);
            List<BseNse> bn = query.getResultList(); 
            if(bn != null && !bn.isEmpty() && !bn.get(0).getIndustry().isEmpty()) 
                return bn.get(0).getIndustry();
            return "Others";
                
            }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    public void deleteTagUpdate(String updateId) throws BullFinderException{
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            TagUpdate update = em.find(TagUpdate.class, updateId);
            em.remove(update);
            Query query = em.createQuery("select a from com.tickertrek.jpa.entity.TagAssociation a " +
              "where a.updateId = :updateId");
            query.setParameter("updateId", updateId);
            List<TagAssociation> assocs = query.getResultList();
            if(assocs.isEmpty())
                return;
            for(TagAssociation assoc : assocs){
                em.remove(assoc);
            }            
        }catch (Exception exp){
            exp.printStackTrace();
            throw createCustomizedException(exp);
        }finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    public JSONObject insertTagUpdate(Object update, String userId, String userName,
            String replyUpdateId, int expired, String tagId, String tagType) throws BullFinderException{
        EntityManager em = null;
        try {
            TagUpdate tagupdt = new TagUpdate();
            tagupdt.setUpdateValue(update.toString());
            tagupdt.setUpdatedById(userId);
            tagupdt.setUpdatedByUName(userName);
            tagupdt.setUpdateType(update.getClass().toString());
            tagupdt.setExpired(expired);
            tagupdt.setExpired(expired);
            tagupdt.setUpdateDate(this.getCurrentDateInFormat());

            em = factory.createEntityManager();

            em.persist(tagupdt);


            TagAssociation assoc = new TagAssociation();

            assoc.setTagId(tagId);
            if(Constants.Forum.TAG_TYPE_STRATEGY.equals(tagType))
                assoc.setTagType(Strategy.class.toString());
            else if(Constants.Forum.TAG_TYPE_USER.equals(tagType))
                assoc.setTagType(UserProfile.class.toString());
            else if(Constants.Forum.TAG_TYPE_DISCUSS_TOPIC.equals(tagType))
                assoc.setTagType(DiscussTopic.class.toString());
            assoc.setUpdateId(tagupdt.getId());
            assoc.setUpdateTime(tagupdt.getUpdateDate());

            em.persist(assoc);

            return JPAUtil.getJSON(tagupdt);

        } catch (Exception exp){
            exp.printStackTrace();
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }
    public JSONArray getUpdatesForTag(String tagId, int maxCount) throws BullFinderException{
        if(true)
            throw new BullFinderException(0,"Have to finish this method ordering in query"
                    + " is not working due to is not null not working. "
                    + "maxresult is not working. OR and AND operations combined is not working.");
        
        EntityManager em = null;
        try {
            JSONArray updates = new JSONArray();
            if(maxCount > 20) maxCount = 20;//Max no. of updates should be 20
            
            em = factory.createEntityManager();

            Query query = em.createQuery("select a from com.tickertrek.jpa.entity.TagAssociation a " +
              "where a.tagId = :tagId and a.updateTime is not null order by a.updateTime desc");
            query.setParameter("tagId", tagId);
            //query.setMaxResults(maxCount);
            List<TagAssociation> assocs = query.getResultList();
            if(assocs.isEmpty())
                return updates;

            StringBuffer queryBuffer = new StringBuffer("");
            queryBuffer.append("SELECT u from com.tickertrek.jpa.entity.TagUpdate u").
                    append(" WHERE u.updateDate IS NOT NULL AND ");
            Map<String, Object> paramValues = new HashMap<String, Object>();
            String paramIndexBase = "paramKey";
            int paramIndex = 1;

            for(TagAssociation assoc : assocs){
                System.out.println("Association:"+assoc);
                queryBuffer.append("u.id = :").append(paramIndexBase).
                        append(paramIndex).append(" OR ");
                paramValues.put(paramIndexBase+paramIndex, assoc.getUpdateId());
                paramIndex++;
            }
            queryBuffer.delete(queryBuffer.length()-4, queryBuffer.length());
            //queryBuffer.append(" )");
            //queryBuffer.append(" ORDER BY u.updateDate ASC");

            System.out.println("queryBuffer:"+queryBuffer+"\n param value map:"+paramValues);
            query = em.createQuery(queryBuffer.toString());

            for(String str : paramValues.keySet()){
                System.out.println("Setting paramter:"+str+", value:"+paramValues.get(str));
                query.setParameter(str, paramValues.get(str));
            }


            List<TagUpdate> updts = query.getResultList();
            for(TagUpdate updt : updts){
                updates.put(JPAUtil.getJSON(updt));
            }
            return updates;
        }catch (Exception exp){
            exp.printStackTrace();
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

    public JSONArray getUpdatesAfterTime(String tagId, Date lastTime,
            int count) throws BullFinderException{

        if(true)
            throw new BullFinderException(0,"Have to finish this method ordering in query"
                    + " is not working due to is not null not working. "
                    + "maxresult is not working. OR and AND operations combined is not working.");

        EntityManager em = null;
        try {
            JSONArray updates = new JSONArray();
            if(count > 20) count = 20;//Max no. of updates should be 20
            
            em = factory.createEntityManager();

            Query query = em.createQuery("select a from com.tickertrek.jpa.entity.TagAssociation a " +
              "where a.tagId = :tagId and a.updateTime > :lastTime and a.updateTime is not null "
              + "order by a.updateTime desc");

            query.setParameter("tagId", tagId);
            query.setParameter("lastTime", lastTime);
            //query.setMaxResults(count);
            
            List<TagAssociation> assocs = query.getResultList();
            if(assocs.isEmpty())
                return updates;

            StringBuffer queryBuffer = new StringBuffer("");
            queryBuffer.append("SELECT u from com.tickertrek.jpa.entity.TagUpdate u").
                    append(" WHERE u.updateDate IS NOT NULL AND ");
            Map<String, Object> paramValues = new HashMap<String, Object>();
            String paramIndexBase = "paramKey";
            int paramIndex = 1;
            
            for(TagAssociation assoc : assocs){
                System.out.println("Association:"+assoc);
                queryBuffer.append("u.id = :").append(paramIndexBase).
                        append(paramIndex).append(" OR ");
                paramValues.put(paramIndexBase+paramIndex, assoc.getUpdateId());
                paramIndex++;
            }
            queryBuffer.delete(queryBuffer.length()-4, queryBuffer.length());
            //queryBuffer.append(" )");
            //queryBuffer.append(" ORDER BY u.updateDate ASC");

            System.out.println("queryBuffer:"+queryBuffer+"\n param value map:"+paramValues);
            query = em.createQuery(queryBuffer.toString());

            for(String str : paramValues.keySet()){
                System.out.println("Setting paramter:"+str+", value:"+paramValues.get(str));
                query.setParameter(str, paramValues.get(str));
            }

            
            List<TagUpdate> updts = query.getResultList();
            for(TagUpdate updt : updts){
                updates.put(JPAUtil.getJSON(updt));
            }
            return updates;

        } catch (Exception exp){
            exp.printStackTrace();
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
    }

}
