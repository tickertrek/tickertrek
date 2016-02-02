/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.util;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.DeleteDomainRequest;
import com.tickertrek.dao.BaseDAO;
import com.tickertrek.dao.StrategyDAO;
import com.tickertrek.jpa.dao.impl.BaseDAOImpl;
import com.tickertrek.jpa.dao.impl.StrategyDAOImpl;
import com.tickertrek.jpa.entity.BseNse;
import com.tickertrek.jpa.entity.ChampUserProfile;
import com.tickertrek.jpa.entity.Championship;
import com.tickertrek.jpa.entity.MonthlyReturn;
import com.tickertrek.jpa.entity.TradeOrder;
import com.tickertrek.jpa.entity.Sector;
import com.tickertrek.jpa.entity.Strategy;
import com.tickertrek.jpa.entity.StrategyStat;
import com.tickertrek.jpa.entity.StrategyUserProfile;
import com.tickertrek.jpa.entity.Trade;
import com.tickertrek.jpa.entity.Ts;
import com.tickertrek.jpa.entity.UserAuth;
import com.tickertrek.jpa.entity.UserProfile;
import com.tickertrek.jpa.entity.YearlyReturn;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rajesroy
 */
public class MySQLMigrate {
    private static Connection conn = null;
    private static Statement stmt;
    private static Hashtable<String, UserProfile> uProfiles = new Hashtable<String, UserProfile>();
    private static Hashtable<String, Strategy> strat = new Hashtable<String, Strategy>();
    private static Hashtable<String, Championship> champs = new Hashtable<String, Championship>();
    private static BaseDAO simpleDAO = new BaseDAOImpl();
    private static StrategyDAO strategyDAO = new StrategyDAOImpl();
    public static void connect(){
        try {
            //Properties properties = new Properties();
            //properties.load(new FileInputStream("/jdbc.properties"));
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bullfinder",
                    "root", "bullfinder");
            stmt = conn.createStatement();
        } catch (Exception exp) {
            exp.printStackTrace();
            System.exit(0);
        }
    }
    public static void closeConnection(){
        try {
            stmt.close();
            conn.close();
        } catch (Exception exp){
            exp.printStackTrace();
            System.exit(1);
        }
    }
    public static boolean executeSQL(String sql) throws Exception{
        try{
            return stmt.execute(sql);
        }catch(Exception e){
            System.out.println(sql);
            e.printStackTrace();
            throw e;
        }
    }
    public static void main(String[] args) throws Exception {
        connect();
        //simpleDAO.clearAllDomains();
        
        deleteDomains();
        
        //convertBseNse();
        convertUserAuth();
        convertStrategyUserProfile();
        //convertStrategyStat();
        
        
        convertChamp();
        convertChampUserProfile();
        
        
        //convertMYSQLtoSimpleDB();
    }
    public static void convertUserAuth()throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `user_auth`");
         List<UserAuth> uas = new ArrayList<UserAuth>();  
         int count = 0;
            while(rs.next()){
                count++;
                UserAuth ua = new UserAuth();
                convertMYSQLtoSimpleDB(rs,ua);
                simpleDAO.persistEntity(ua);
                uas.add(ua);
                
             }
            System.out.println("Total number user:"+uas.size());
            for(int i=0; i < uas.size();i++){
                System.out.println("Converting user auth:"+uas.get(i).getEmail());
                convertUserProfile( uas.get(i));
                
            }
            System.out.println("Total User migrated:"+uas.size());
    }
    public static void convertUserProfile(UserAuth user)throws Exception{
        ResultSet rs = stmt.executeQuery("select * from `user_profile` where email='"+user.getEmail()+"'");
        UserProfile u = new UserProfile();
        String uid = "";
        if(rs.next()){
            u = new UserProfile();
            u.init();
            u.setId(user.getId());
            convertMYSQLtoSimpleDB(rs, u);
            uid = u.getUserid();
            u.setUserid(u.getId());
            simpleDAO.persistEntity(u);
            
            uProfiles.put(uid, u);
            convertStrategy(u,uid);
            System.out.println("User Profile converted:"+u.getFullname());
            
         }else{
            System.out.println("User profile not found for email:"+user.getEmail());
            System.exit(0);
            
        }
        
            
            
            //simpleDAO.persistEntity(userProfiles.get(i));
            
           
    }
    public static void convertStrategy(UserProfile u, String uid)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `strategy` where userid="+uid);
         List<String> strategies = new ArrayList<String>();   
         while(rs.next()){
                Strategy s = new Strategy();
                s.init();
                convertMYSQLtoSimpleDB(rs,s);
                String mysqlStrategyID = s.getStrategyid();
                 s.setUserid(u.getId());
                strategyDAO.persistStrategy(s);
                strategies.add(mysqlStrategyID);
                //System.out.println("Putting s for id:"+s.getStrategyid());
                strat.put(mysqlStrategyID+"", s);
                System.out.println("Strategy:"+JPAUtil.getJSON(s));
                
             }
             //Set<String> set = strat.keySet();
             System.out.println("Total strategy found for user:"+u.getFullname()+ " is #:"+strategies.size());
            //Iterator<String> itr = set.iterator();
            for(String str: strategies){
                
                Strategy s = strat.get(str);
                System.out.println("Converting straegy:"+s.getStrategyname());
                convertOrder( str, s);
                convertTrade(str, s);
                convertSector(str, s);
                convertTs(str, s);
                convertMonthlyReturn(str, s);
                convertYearlyReturn(str, s);
            }
    }
    
    public static void convertOrder(String str, Strategy s)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `order` where strategyid="+str);
         
         int count = 0;
            while(rs.next()){
                
                TradeOrder o = new TradeOrder();
               
                convertMYSQLtoSimpleDB(rs,o);
                 o.setStrategyid(s.getId());
                simpleDAO.persistEntity(o);
                count ++;
             }
            System.out.println("Orders found for strategy "+ s.getStrategyname()+ " Order count:"+count);
    }
    public static void convertTrade(String str,Strategy s)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `trade` where strategyid="+str);
            while(rs.next()){
                Trade o = new Trade();
               
                convertMYSQLtoSimpleDB(rs,o);
                o.setStrategyid(s.getId());
                simpleDAO.persistEntity(o);
             }
    }
    public static void convertTs(String str,Strategy s)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `ts` where strategyid="+str);
            while(rs.next()){
                Ts o = new Ts();
                o.setStrategyid(s.getId());
                convertMYSQLtoSimpleDB(rs,o);
                o.setStrategyid(s.getId());
                simpleDAO.persistEntity(o);
             }
    }
    public static void convertSector(String str,Strategy s)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `sector` where strategyid="+str);
            while(rs.next()){
                Sector o = new Sector();
                o.setStrategyid(s.getId());
                convertMYSQLtoSimpleDB(rs,o);
                o.setStrategyid(s.getId());
                simpleDAO.persistEntity(o);
             }
    }
    public static void convertMonthlyReturn(String str,Strategy s)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `monthlyreturn` where strategyid="+str);
            while(rs.next()){
                MonthlyReturn o = new MonthlyReturn();
                o.setStrategyid(s.getId());
                convertMYSQLtoSimpleDB(rs,o);
                o.setStrategyid(s.getId());
                simpleDAO.persistEntity(o);
             }
    }
    public static void convertYearlyReturn(String str,Strategy s)throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `yearlyreturn` where strategyid="+str);
            while(rs.next()){
                YearlyReturn o = new YearlyReturn();
                o.setStrategyid(s.getId());
                convertMYSQLtoSimpleDB(rs,o);
                o.setStrategyid(s.getId());
                simpleDAO.persistEntity(o);
             }
    }
    
    public static void convertStrategyUserProfile()throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `following`");
            while(rs.next()){
                StrategyUserProfile o = new StrategyUserProfile();
                if(strat.get(rs.getString("strategyid")) == null)
                    System.out.println("Scould not find strategy in strat:"+rs.getString("strategyid"));
                o.setStrategyid(strat.get(rs.getString("strategyid")).getId());
                o.setUserid(uProfiles.get(rs.getString("userid")).getId());
                o.setRelation(rs.getString("relation"));
                simpleDAO.persistEntity(o);
             }
    }
    
    public static void convertChampUserProfile()throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `participation`");
            while(rs.next()){
                ChampUserProfile o = new ChampUserProfile();
                if(champs.get(rs.getString("championshipid")) == null)
                System.out.println("Couldnot found champ:"+rs.getString("championshipid"));
                o.setChampionshipid(champs.get(rs.getString("championshipid")).getId());
                o.setUserid(uProfiles.get(rs.getString("userid")).getId());
                o.setStrategyid(strat.get(rs.getString("strategyid")).getId());
                o.setPosition(new Integer(rs.getInt("position")));
                o.setOverallreturn(rs.getDouble("overallreturn"));
                o.setNickname(rs.getString("nickname"));
                simpleDAO.persistEntity(o);
             }
    }
    
    
    public static void convertBseNse()throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `bse_nse`");
         int i = 0;
            while(rs.next() ){
                i++;
                BseNse ua = new BseNse();
                
                convertMYSQLtoSimpleDB(rs,ua);
               
                simpleDAO.persistEntity(ua);
             }
    }
    
    public static void convertStrategyStat()throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `strategystat`");
         int i = 0;
            while(rs.next() ){
                i++;
                StrategyStat ua = new StrategyStat();
                
                convertMYSQLtoSimpleDB(rs,ua);
                ua.setMaxVal(rs.getDouble("max"));
                ua.setMinVal(rs.getDouble("min"));
                simpleDAO.persistEntity(ua);
             }
    }
    
    public static void convertChamp()throws Exception{
         ResultSet rs = stmt.executeQuery("select * from `championship`");
            while(rs.next()){
                Championship ua = new Championship();
                
                convertMYSQLtoSimpleDB(rs,ua);
                String champID = ua.getChampionshipid();
                strategyDAO.persistChamp(ua);
                //System.out.println("putting champ"+ua.getChampionshipid());
                champs.put(champID, ua);
             }
    }
    
    public static void convertMYSQLtoSimpleDB(ResultSet rs, Object o)throws Exception{
        
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    if(rs.getObject(i) == null){
                        System.out.println("NULL value in "+rs.getMetaData().getColumnName(i));
                        
                    }else{
                        try{
                            PropertyDescriptor pd = new PropertyDescriptor(convertColumnName(rs.getMetaData().getColumnName(i)), o.getClass());
                        
                       // System.out.println(rs.getMetaData().getColumnName(i)+"::"+rs.getObject(i).getClass().getName()+"::"+pd.getPropertyType().getName());
                        
                        if(convertColumnName(rs.getMetaData().getColumnName(i)).equals("id"))
                            JPAUtil.setField(o, convertColumnName(rs.getMetaData().getColumnName(i)), rs.getString(i));
                         
                        else if(pd.getPropertyType().getName().equals("java.lang.Integer") ){
                            JPAUtil.setField(o, rs.getMetaData().getColumnName(i), new Integer(rs.getInt(i)));
                            //PropertyDescriptor pd = new PropertyDescriptor(convertColumnName(rs.getMetaData().getColumnName(i)), o.getClass());
                            //pd.getWriteMethod().invoke(o,new Integer(rs.getInt(i)));
                        }
                        else if( pd.getPropertyType().getName().equals("java.util.Date"))
                            JPAUtil.setField(o, convertColumnName(rs.getMetaData().getColumnName(i)), (Date)rs.getDate(i) );
                        else if( pd.getPropertyType().getName().equals("java.lang.Double"))
                            JPAUtil.setField(o, convertColumnName(rs.getMetaData().getColumnName(i)), (Double)rs.getDouble(i) );
                                 else if( pd.getPropertyType().getName().equals("java.lang.Double"))
                            JPAUtil.setField(o, convertColumnName(rs.getMetaData().getColumnName(i)), (Double)rs.getDouble(i) );
                       
                        else if( pd.getPropertyType().getName().equals("java.lang.String"))
                            JPAUtil.setField(o, convertColumnName(rs.getMetaData().getColumnName(i)), rs.getString(i));
                        }catch(Exception e){
                            System.out.println("No field exist: "+rs.getMetaData().getColumnName(i));
                        }
                    }
                }
               // System.out.println(JPAUtil.getJSON(o));
                //simpleDAO.persistEntity(o);

            
        
    }
    
    public static void deleteDomains() throws Exception {
        AmazonSimpleDB sdb = new AmazonSimpleDBClient(new PropertiesCredentials(
                MySQLMigrate.class.getResourceAsStream("AwsCredentials.properties")));
        String prefix = "dev-";
        String domains[] = {"PublicUserProfile","UserAuth","PublicStrategy","StrategyUserProfile","Championship","ChampUserProfile",
                                "MonthlyReturn", "YearlyReturn","Sector","Ts","Trade","TradeOrder"};
        try {
            
            for(int i =0 ; i < domains.length ; i++){
                System.out.println("Deleting " + domains[i] + " domain.\n");
                sdb.deleteDomain(new DeleteDomainRequest(prefix+domains[i]));
            }
           
           
        }catch(Exception e){
            e.printStackTrace();
            
        
        }
    }
    public static String convertColumnName(String str){
        //if(str.equals("userid") ||  str.equals("strategyid") ) return "id";
         return str;
    }

}
