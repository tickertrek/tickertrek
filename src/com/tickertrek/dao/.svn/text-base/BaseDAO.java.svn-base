/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.dao;

import com.bullfinder.exception.BullFinderException;

/**
 *
 * @author Uttam
 */
public interface BaseDAO {

    public void persistEntity(Object e) throws BullFinderException;

    public <T> T getEntity(Class<T> tClass, String key) throws BullFinderException;
    public <T> void deleteEntity(Class<T> tClass, String key) throws BullFinderException;
    public void clearAllDomains() throws BullFinderException;

}
