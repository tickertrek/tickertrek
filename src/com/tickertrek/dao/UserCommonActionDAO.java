/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.dao;

import com.bullfinder.exception.BullFinderException;
import com.tickertrek.jpa.entity.UserProfile;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public interface UserCommonActionDAO extends BaseDAO {

    public boolean doesUserEmailExist(String email) throws BullFinderException;

    public boolean doesUserNicknameExist(String nickName) throws BullFinderException;

    public boolean doesUserEmailExistWithName(String email, String fullname) throws BullFinderException;

    public JSONObject registerAndLoginUserWithCode(String registerCode) throws BullFinderException;

    public String createRegitrationCode(String email, String encryptedPassword,
            String name, String nickName) throws BullFinderException;

    public String createForgotPasswordCode(String email, String fullname)
            throws BullFinderException;

    public void changePasswordWithCode(String email, String code,
            String encryptedPassword) throws BullFinderException;

    public void updatePassword(String userID, String encryptedOldPassword, String encryptedNewPassword)
            throws BullFinderException;

    public void insertFeedback(String userId, String email, Integer feedbackType,
            String feedbackShort, String feedbackLong) throws BullFinderException;

    public void initializeUserProfile(String userId, UserProfile userProfile)
            throws BullFinderException;

    public JSONObject authenticateUser(String email, String encryptedPassword)
            throws BullFinderException;

    public JSONObject retrieveUserProfile(String userid)
            throws BullFinderException;

    public JSONObject retrieveUserPublicProfile(String username)
            throws BullFinderException;

    public JSONObject updateUserProfile(JSONObject p, String userID)
            throws BullFinderException;

    public JSONObject retrieveRecentTopics() throws BullFinderException;

    public JSONObject retrieveMostDiscussedTopics() throws BullFinderException;

    public JSONObject createNewTopicWithComment(String title, String userId,
            String usernickname, String comment) throws BullFinderException;

    public JSONObject retrieveTopicNComments(String topicId)
            throws BullFinderException;

    public JSONObject postCommentOnTopic(String userId, String usernickname,
            String topicId, String comment, String replyId) throws BullFinderException;

    public JSONObject searchTopics(String keyWrds) throws BullFinderException;

    public void postFeedBack(String userId, String email, String title,
            String comment, boolean isBug) throws BullFinderException;

    public JSONObject retrieveTickerTalks(String ticker) throws BullFinderException;
    public JSONObject postCommentOnTicker(String userId, String usernickname,
            String ticker, String comment, String meter, String replyId)
            throws BullFinderException;

    public JSONObject retrieveTopicInfo(String topicId) throws BullFinderException;

    public boolean isValidTicker(String ticker) throws BullFinderException;

}
