/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tickertrek.jpa.util;


import com.bullfinder.exception.BullFinderException;
import com.tickertrek.jpa.entity.PublicStrategy;
import com.tickertrek.jpa.entity.Strategy;
import com.tickertrek.util.Constants;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author rajesroy
 */
public class JPAUtil {
    // Return a JSONObject with all fields
    public static JSONObject getJSON(Object o) throws BullFinderException{
        
        JSONObject json = new JSONObject();
        try{
        for (PropertyDescriptor pd : Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {
            if(pd.getName().equals("callbacks")) continue;
            else if(pd.getPropertyType() ==  Date.class)
                json.put(pd.getName(), ((Date)pd.getReadMethod().invoke(o)).getTime());
            else if (pd.getReadMethod() != null && !"class".equals(pd.getName()) && pd.getReadMethod().invoke(o)!=null){
              { //System.out.println(pd.getName()+pd.getReadMethod().invoke(o));
             // if(pd.getPropertyType().getName().equals("java.lang.Double"))
               //   json.put(pd.getName(),  Constants.decimalFormatter.format(pd.getReadMethod().invoke(o)));
              //else
                json.put(pd.getName(), pd.getReadMethod().invoke(o));
              }
               
          }
        }
        }catch(Exception e){
            e.printStackTrace();
            throw new BullFinderException(0,"Error while converting to JSON");
        }
        return json;
        
    }
    // Return a JSON Object with the specified fields...
    public static JSONObject getJSON(Object o, String[] columns) throws Exception{
        
        
        JSONObject json = new JSONObject();
        for (int i=0; i < columns.length;i ++) {
            PropertyDescriptor pd = new PropertyDescriptor(columns[i], o.getClass());
            
            if(pd.getName().equals("callbacks")) continue;
            else if(pd.getPropertyType() ==  Date.class)
                json.put(pd.getName(), ((Date)pd.getReadMethod().invoke(o)).getTime());
            else json.put(columns[i],  pd.getReadMethod().invoke(o));
          
        }
        
        return json;
        
    }
    
    // Return a JSON Object with the specified fields...
    public static void setField(Object o, String field, Object value) throws Exception{
        try{
        PropertyDescriptor pd = new PropertyDescriptor(field, o.getClass());
        if(pd.getWriteMethod() !=null)
        pd.getWriteMethod().invoke(o,value);
        else System.out.println("No method found for: "+field);
        }catch(Exception e){
            System.out.println("No field found for: "+field);
        }
       
    }
    
    // Set dummy data for all vars except id
    public static Object setDummyData(Object o) throws Exception{
        Random gen = new Random();
        for (PropertyDescriptor pd : Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {
          if (pd.getWriteMethod() != null && !"class".equals(pd.getName())){
              if(pd.getPropertyType() == Integer.class)
                pd.getWriteMethod().invoke(o, new Integer(gen.nextInt(1000)));
              else if(pd.getPropertyType() ==  Date.class)
                pd.getWriteMethod().invoke(o, new Date());
              else if(pd.getPropertyType() ==  Double.class)
                pd.getWriteMethod().invoke(o, new Double(gen.nextDouble()));
              else if(pd.getPropertyType() ==  Double.class)
                pd.getWriteMethod().invoke(o, new Double(gen.nextDouble()));
              else if(pd.getPropertyType() ==  String.class && !pd.getName().equals("id"))
                pd.getWriteMethod().invoke(o, new String("0"+gen.nextInt(10)));
              
              
            //System.out.println(pd.getName());
          }
        }
       return o; 
    }
    
    public static  JSONArray getFlexiGridFormat(List<Object> list, List<String> fields)throws Exception{
                    JSONArray slist = new JSONArray();
                    for(Object be : list)
                    {   System.out.println(be.toString());
                        JSONObject s = new JSONObject();
                        s.put("id",new PropertyDescriptor("id", be.getClass()).getReadMethod().invoke(be));
                        JSONArray cell = new JSONArray();

                        for(int i=1; i<fields.size(); i++ ){
                             PropertyDescriptor pd = new PropertyDescriptor(fields.get(i), be.getClass());
                            Object value = pd.getReadMethod().invoke(be);
                            if(pd.getPropertyType() ==  Date.class){
                                cell.put(((Date)value).getTime());
                                continue;
                            }
                            if(fields.get(i).equals("market")){
                                cell.put(Constants.Market.MARKET_CODE[Integer.parseInt((String)value)]);
                                
                            }else if(fields.get(i).equals("instrumentoptions")){
                                cell.put(Constants.Instrument.INSTRUMENT_CODE[Integer.parseInt((String)value)]);
                                
                            }else
                                cell.put(value);
                            
                             //cell.put(rs.getObject(i).toString()); // start index is 2 as index=1 is strategyid
                        }
                        s.put("cell", cell);

                        slist.put(s);
                    }
                    return slist;

    }
    
    
}
