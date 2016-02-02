/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tickertrek.jpa.dao.impl;

import com.bullfinder.exception.BullFinderException;
import com.tickertrek.util.Constants;
import com.tickertrek.util.Utilities;
import com.tickertrek.dao.UserCommonActionDAO;
import com.tickertrek.jpa.entity.BseNse;
import com.tickertrek.jpa.entity.DiscussTopic;
import com.tickertrek.jpa.entity.Feedback;
import com.tickertrek.jpa.entity.ForgotPassword;
import com.tickertrek.jpa.entity.PublicUserProfile;
import com.tickertrek.jpa.entity.Registration;
import com.tickertrek.jpa.entity.TopicComment;
import com.tickertrek.jpa.entity.UserAuth;
import com.tickertrek.jpa.entity.UserProfile;
import com.tickertrek.jpa.util.JPAUtil;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.json.JSONObject;

/**
 *
 * @author Uttam
 */
public class UserCommonActionDAOImpl extends BaseDAOImpl implements UserCommonActionDAO {

    @Override
    public boolean doesUserEmailExist(String email) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select u from com.tickertrek.jpa.entity.UserAuth u " +
              "where u.email=:email ");
            query.setParameter("email",email);
            List<UserAuth> users = query.getResultList();
            int usercount = (users == null) ? 0 : users.size();
            if(usercount == 1)
                return true;
            if(usercount > 1)
            throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    "Fatal error, more than one user with email:"+email);
            return false;

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
    public boolean doesUserNicknameExist(String nickName) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select u from com.tickertrek.jpa.entity.UserAuth u " +
              "where u.userName=:userName ");
            query.setParameter("userName",nickName);
            
            List<UserAuth> users = query.getResultList();
            int usercount = (users == null) ? 0 : users.size();

            if(usercount == 1)
                return true;
            if(usercount > 1)
            throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    "Fatal error, more than one user with user name:"+nickName);
            return false;

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
    public boolean doesUserEmailExistWithName(String email, String fullname)
            throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select p from "
              + "com.tickertrek.jpa.entity.PublicUserProfile p "
              + "where p.email=:email and p.fullname=:fullname");
            query.setParameter("email",email);
            query.setParameter("fullname",fullname);
            
            List<PublicUserProfile> profiles = query.getResultList();
            int profilecount = (profiles == null)? 0 : profiles.size();

            if(profilecount == 1)
                return true;
            if(profilecount > 1)
            throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    "Fatal error, more than one user with email:"+email+" and full name:"+fullname);
            return false;

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
    public JSONObject registerAndLoginUserWithCode(String registerCode) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select r from com.tickertrek.jpa.entity.Registration r " +
              "where r.code=:code ");
            query.setParameter("code",registerCode);

            List<Registration> regs = query.getResultList();

            if(regs == null || regs.isEmpty())
                throw new BullFinderException(Constants.ExceptionCode.REGISTRATION_CODE_DOES_NOT_EXIST_ERROR,
                    Constants.ExceptionCode.REGISTRATION_CODE_DOES_NOT_EXIST_ERROR_MSG);

            Registration reg = regs.get(0);
            
            Date codeDate = reg.getCreatedate();
            Date currentDate = this.getCurrentDateInFormat();
            
            if((codeDate.getTime()+Constants.A_DAY_IN_MILLISEC) < currentDate.getTime()) {
                em.remove(reg);
                throw new BullFinderException(Constants.ExceptionCode.REGISTRATION_CODE_EXPIRED_ERROR,
                    Constants.ExceptionCode.REGISTRATION_CODE_EXPIRED_ERROR_MSG);
            }
            if (this.doesUserEmailExist(reg.getEmail()))
                throw new BullFinderException(Constants.ExceptionCode.REGISTRATION_USER_EMAIL_EXISTS_ERROR,
                Constants.ExceptionCode.REGISTRATION_USER_EMAIL_EXISTS_ERROR_MSG);

            UserAuth user = new UserAuth();

            user.setEmail(reg.getEmail());
            user.setPassword(reg.getPassword());
            user.setUserName(reg.getNickname());

            em.persist(user);

            UserProfile profile = new UserProfile();

            profile.setId(user.getId());
            profile.setFullname(reg.getFullname());
            profile.setEmail(reg.getEmail());
            profile.setNickname(reg.getNickname());
            profile.setContactemail(reg.getEmail());
            profile.setUserid(user.getId());

            em.persist(profile);

            return JPAUtil.getJSON(profile);
            
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
    public String createRegitrationCode(String email, String encryptedPassword, 
            String name, String nickName) throws BullFinderException {

        if (this.doesUserEmailExist(email))
            throw new BullFinderException(Constants.ExceptionCode.REGISTRATION_USER_EMAIL_EXISTS_ERROR,
                Constants.ExceptionCode.REGISTRATION_USER_EMAIL_EXISTS_ERROR_MSG);
        if (this.doesUserNicknameExist(nickName))
            throw new BullFinderException(Constants.ExceptionCode.REGISTRATION_USER_NICKNAME_EXISTS_ERROR,
                Constants.ExceptionCode.REGISTRATION_USER_NICKNAME_EXISTS_ERROR_MSG);

        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            String code = Utilities.generateRandomRegistrationCode(email, name);

            Registration reg = new Registration();
            reg.setCode(code);
            reg.setCreatedate(this.getCurrentDateInFormat());
            reg.setEmail(email);
            reg.setFullname(name);
            reg.setNickname(nickName);
            reg.setPassword(encryptedPassword);

            em.persist(reg);

            return code;

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
    public String createForgotPasswordCode(String email, String fullname) throws BullFinderException {
        
        if (!this.doesUserEmailExistWithName(email,fullname))
            throw new BullFinderException(Constants.ExceptionCode.
                FORGOT_PASSWORD_USER_EMAIL_DOES_NOT_EXIST_ERROR,
                Constants.ExceptionCode.FORGOT_PASSWORD_USER_EMAIL_DOES_NOT_EXIST_ERROR_MSG);

        EntityManager em = null;
        try {
            String code = Utilities.generateRandomForgotPasswordCode(email);
            em = factory.createEntityManager();

            ForgotPassword fpwd = new ForgotPassword();
            fpwd.setCode(code);
            fpwd.setCreatedate(this.getCurrentDateInFormat());
            fpwd.setEmail(email);

            em.persist(fpwd);

            return code;            

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
    public void changePasswordWithCode(String email, String code, String encryptedPassword) throws BullFinderException {
        
        if (!this.doesUserEmailExist(email))
            throw new BullFinderException(Constants.ExceptionCode.FORGOT_PASSWORD_USER_EMAIL_DOES_NOT_EXIST_ERROR,
                Constants.ExceptionCode.FORGOT_PASSWORD_USER_EMAIL_DOES_NOT_EXIST_ERROR_MSG);

        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select f from com.tickertrek.jpa.entity.ForgotPassword f " +
              "where f.email=:email and f.code=:code");
            query.setParameter("email",email);
            query.setParameter("code",code);
            List<ForgotPassword> fpwds = query.getResultList();

            if(fpwds == null || fpwds.size() != 1){
                throw new BullFinderException(Constants.ExceptionCode.FORGOT_PASSWORD_ERROR,
                    Constants.ExceptionCode.FORGOT_PASSWORD_ERROR_MSG);
            }
            ForgotPassword fpwd = fpwds.get(0);
            
            Date codeDate = formatDateBack(fpwd.getCreatedate());
            Date currentDate = new Date(System.currentTimeMillis());

            em.remove(fpwd);
            
            if((codeDate.getTime()+Constants.A_DAY_IN_MILLISEC) < currentDate.getTime()) {
                throw new BullFinderException(Constants.ExceptionCode.FORGOT_PASSWORD_CODE_EXPIRED_ERROR,
                    Constants.ExceptionCode.FORGOT_PASSWORD_CODE_EXPIRED_ERROR_MSG);
            }
            query = em.createQuery("select u from com.tickertrek.jpa.entity.UserAuth u " +
              "where u.email=:email ");
            query.setParameter("email",email);
            List<UserAuth> users = query.getResultList();
            if(users == null || users.size() != 1){
                throw new BullFinderException(Constants.ExceptionCode.FORGOT_PASSWORD_ERROR,
                    Constants.ExceptionCode.FORGOT_PASSWORD_ERROR_MSG);
            }

            UserAuth user = users.get(0);
            user.setPassword(encryptedPassword);
            em.persist(user);
            
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
    public void updatePassword(String userID, String encryptedOldPassword, String encryptedNewPassword) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            UserAuth user = em.find(UserAuth.class, userID);

            if(user == null)
                throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    "Fatal error, could not find user with userid:"+userID);

            if(!user.getPassword().equals(encryptedOldPassword))
                throw new BullFinderException(Constants.ExceptionCode.LOGIN_USER_EMAIL_AND_PASSWORD_NOT_MATCH_ERROR,
                    Constants.ExceptionCode.LOGIN_USER_EMAIL_AND_PASSWORD_NOT_MATCH_ERROR_MSG);

            user.setPassword(encryptedNewPassword);

            em.persist(user);
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
    public void insertFeedback(String userId, String email, Integer feedbackType, String feedbackShort, String feedbackLong) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Feedback fdb = new Feedback();
            fdb.setPostedById(userId);
            fdb.setPostedByemail(email);
            fdb.setFeedbackdate(this.getCurrentDateInFormat());
            fdb.setFeedbacklong(feedbackLong);
            fdb.setFeedbackshort(feedbackShort);
            fdb.setFeedbacktype(feedbackType);

            em.persist(fdb);

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
    public void initializeUserProfile(String userId, UserProfile userProfile) throws BullFinderException {
        throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                "This functionalilty is not supported as it is supposedly unnecessary.");
    }

    @Override
    public JSONObject authenticateUser(String email, String encryptedPassword) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select u from com.tickertrek.jpa.entity.UserAuth u " +
              "where u.email=:email and u.password=:password");
            query.setParameter("email",email);
            query.setParameter("password",encryptedPassword);

            List<UserAuth> users = query.getResultList();
            int usercount = (users == null) ? 0 : users.size();
            if(usercount != 1)
                throw new BullFinderException(Constants.ExceptionCode.LOGIN_USER_EMAIL_AND_PASSWORD_NOT_MATCH_ERROR,
                    Constants.ExceptionCode.LOGIN_USER_EMAIL_AND_PASSWORD_NOT_MATCH_ERROR_MSG);

            UserProfile profile = em.find(UserProfile.class, users.get(0).getId());

            return JPAUtil.getJSON(profile);

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
    public JSONObject retrieveUserProfile(String userid) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            UserProfile profile = em.find(UserProfile.class, userid);

            if(profile == null)
                throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR_MSG);

            return JPAUtil.getJSON(profile);

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
    public JSONObject updateUserProfile(JSONObject p, String userID) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            UserProfile profile = em.find(UserProfile.class, userID);
            if(!(p.getString("fullname") == null
                    || p.getString("fullname").trim().length() == 0))
                        profile.setFullname(p.getString("fullname"));
            if(!(p.getString("contactemail") == null
                    || p.getString("contactemail").trim().length() == 0))
                        profile.setContactemail(p.getString("contactemail"));
            if(!(p.getString("assetclass") == null
                    || p.getString("assetclass").trim().length() == 0))
                        profile.setAssetclass(p.getString("assetclass"));
            if(!(p.getString("style") == null
                    || p.getString("style").trim().length() == 0))
                        profile.setStyle(p.getString("style"));
            if(!(p.getString("holdingperiod") == null
                    || p.getString("holdingperiod").trim().length() == 0))
                        profile.setHoldingperiod(p.getString("holdingperiod"));
            if(!(p.getString("experience") == null
                    || p.getString("experience").trim().length() == 0))
                        profile.setExperience(p.getString("experience"));
            if(!(p.getString("aboutme") == null
                    || p.getString("aboutme").trim().length() == 0))
                        profile.setAboutme(p.getString("aboutme"));
            if(!(p.getString("profession") == null
                    || p.getString("profession").trim().length() == 0))
                        profile.setProfession(p.getString("profession"));

            em.persist(profile);

            return JPAUtil.getJSON(profile);

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
    public JSONObject retrieveRecentTopics() throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select t from com.tickertrek.jpa.entity.DiscussTopic t " +
              "where 1 = 1 order by t.createdate desc");
            //query.setMaxResults(5);
            int maxResults = 5;
            List<DiscussTopic> topics = query.getResultList();
            JSONObject objlist = new JSONObject();
            JSONObject object;
            for(DiscussTopic topic : topics){
                if(maxResults == 0)
                    break;
                maxResults--;
                object = new JSONObject();
                object.put(Constants.Forum.TOPIC_ID_KEY, topic.getId());
                    object.put(Constants.Forum.TOPIC_TITLE_KEY, topic.getTitle());
                    object.put(Constants.Forum.TOPIC_CREATEDBY_USERID_KEY,
                            topic.getCreatedbyId());
                    object.put(Constants.Forum.TOPIC_CREATEDBY_NICMNAME_KEY,
                            topic.getCreatedbyUName());
                    object.put(Constants.Forum.TOPIC_CREATEDATE_KEY,
                            this.formatDateBack(topic.getCreatedate()));
                    object.put(Constants.Forum.TOPIC_POSTCOUNT_KEY,topic.getPostcount());
                    object.put(Constants.Forum.TOPIC_LASTPOST_KEY,
                            this.formatDateBack(topic.getLastpost()));
                    objlist.put(topic.getId(), object);
            }
            return objlist;

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
    public JSONObject retrieveMostDiscussedTopics() throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select t from com.tickertrek.jpa.entity.DiscussTopic t " +
              "where 1 = 1 order by t.postcount desc");
            //query.setMaxResults(5);
            int maxResults = 5;
            List<DiscussTopic> topics = query.getResultList();
            JSONObject objlist = new JSONObject();
            JSONObject object;
            for(DiscussTopic topic : topics){
                if(maxResults == 0)
                    break;
                maxResults--;
                object = new JSONObject();
                object.put(Constants.Forum.TOPIC_ID_KEY, topic.getId());
                    object.put(Constants.Forum.TOPIC_TITLE_KEY, topic.getTitle());
                    object.put(Constants.Forum.TOPIC_CREATEDBY_USERID_KEY,
                            topic.getCreatedbyId());
                    object.put(Constants.Forum.TOPIC_CREATEDBY_NICMNAME_KEY,
                            topic.getCreatedbyUName());
                    object.put(Constants.Forum.TOPIC_CREATEDATE_KEY,
                            this.formatDateBack(topic.getCreatedate()));
                    object.put(Constants.Forum.TOPIC_POSTCOUNT_KEY,topic.getPostcount());
                    object.put(Constants.Forum.TOPIC_LASTPOST_KEY,
                            this.formatDateBack(topic.getLastpost()));
                    objlist.put(topic.getId(), object);
            }
            return objlist;

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
    public JSONObject createNewTopicWithComment(String title, String userId,
            String usernickname, String comment) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            DiscussTopic topic = new DiscussTopic();

            TopicComment topicComment = new TopicComment();

            topic.setCreatedate(this.getCurrentDateInFormat());
            topic.setCreatedbyId(userId);
            topic.setCreatedbyUName(usernickname);
            topic.setLastpost(this.getCurrentDateInFormat());
            topic.setPostcount(1);
            topic.setTitle(title);

            em.persist(topic);

            topicComment.setComment(comment);
            topicComment.setCommentdate(this.getCurrentDateInFormat());
            topicComment.setCommentedbyId(userId);
            topicComment.setCommentedbyUName(usernickname);
            topicComment.setTopic(topic);

            em.persist(topicComment);

            topic.setLastcommentId(topicComment.getId());

            em.persist(topic);

            JSONObject object = new JSONObject();
            object.put(Constants.Forum.TOPIC_ID_KEY, topic.getId());
            object.put(Constants.Forum.TOPIC_TITLE_KEY, topic.getTitle());
            object.put(Constants.Forum.TOPIC_CREATEDBY_USERID_KEY,
                    topic.getCreatedbyId());
            object.put(Constants.Forum.TOPIC_CREATEDBY_NICMNAME_KEY,
                    topic.getCreatedbyUName());
            object.put(Constants.Forum.TOPIC_CREATEDATE_KEY,
                    this.formatDateBack(topic.getCreatedate()));
            object.put(Constants.Forum.TOPIC_POSTCOUNT_KEY,topic.getPostcount());
            object.put(Constants.Forum.TOPIC_LASTPOST_KEY,
                    this.formatDateBack(topic.getLastpost()));

            JSONObject singleComment = new JSONObject();
            singleComment.put(Constants.Forum.COMMENT_ID_KEY, topicComment.getId());
            singleComment.put(Constants.Forum.COMMENT_DATE_KEY, topicComment.getCommentdate());
            singleComment.put(Constants.Forum.COMMENT_BY_USERID_KEY, userId);
            singleComment.put(Constants.Forum.COMMENT_BY_NICMNAME_KEY, usernickname);
            singleComment.put(Constants.Forum.COMMENT_TEXT_KEY, comment);
            singleComment.put(Constants.Forum.COMMENT_REPLY_ID_KEY, -1);

            JSONObject topicComments = new JSONObject();
            topicComments.put(topicComment.getId(), singleComment);

            object.put(Constants.Forum.TOPIC_COMMENTS_KEY, topicComments);

            return object;
            

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
    public JSONObject retrieveTopicNComments(String topicId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            DiscussTopic topic = em.find(DiscussTopic.class, topicId);

            JSONObject object = new JSONObject();
            object.put(Constants.Forum.TOPIC_ID_KEY, topic.getId());
            object.put(Constants.Forum.TOPIC_TITLE_KEY, topic.getTitle());
            object.put(Constants.Forum.TOPIC_CREATEDBY_USERID_KEY,
                    topic.getCreatedbyId());
            object.put(Constants.Forum.TOPIC_CREATEDBY_NICMNAME_KEY,
                    topic.getCreatedbyUName());
            object.put(Constants.Forum.TOPIC_CREATEDATE_KEY,
                    this.formatDateBack(topic.getCreatedate()));
            object.put(Constants.Forum.TOPIC_POSTCOUNT_KEY,topic.getPostcount());
            object.put(Constants.Forum.TOPIC_LASTPOST_KEY,
                    this.formatDateBack(topic.getLastpost()));

            JSONObject singleComment, topicComments;
            topicComments = new JSONObject();
            for(TopicComment topicComment : topic.getTopicComments()){
                singleComment = new JSONObject();
                singleComment.put(Constants.Forum.COMMENT_ID_KEY,
                        topicComment.getId());
                singleComment.put(Constants.Forum.COMMENT_DATE_KEY,
                        topicComment.getCommentdate());
                singleComment.put(Constants.Forum.COMMENT_BY_USERID_KEY, 
                        topicComment.getCommentedbyId());
                singleComment.put(Constants.Forum.COMMENT_BY_NICMNAME_KEY, 
                        topicComment.getCommentedbyUName());
                singleComment.put(Constants.Forum.COMMENT_TEXT_KEY,
                        topicComment.getComment());
                singleComment.put(Constants.Forum.COMMENT_REPLY_ID_KEY,
                        topicComment.getReplyTopicId());

                topicComments.put(topicComment.getId(), singleComment);
            }

            object.put(Constants.Forum.TOPIC_COMMENTS_KEY, topicComments);
            return object;

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
    public JSONObject postCommentOnTopic(String userId, String usernickname,
            String topicId, String comment, String replyId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            DiscussTopic topic = em.find(DiscussTopic.class, topicId);
            TopicComment topicComment = new TopicComment();

            topicComment.setComment(comment);
            topicComment.setCommentdate(this.getCurrentDateInFormat());
            topicComment.setCommentedbyId(userId);
            topicComment.setTopic(topic);
            topicComment.setCommentedbyUName(usernickname);
            topicComment.setReplyTopicId(replyId);
            
            em.persist(topicComment);

            topic.getTopicComments().add(topicComment);
            topic.setLastcommentId(topicComment.getId());
            topic.setLastpost(topicComment.getCommentdate());
            topic.setPostcount(topic.getPostcount()+1);

            em.persist(topic);

            JSONObject singleComment = new JSONObject();
            singleComment.put(Constants.Forum.COMMENT_ID_KEY,
                    topicComment.getId());
            singleComment.put(Constants.Forum.COMMENT_DATE_KEY,
                    topicComment.getCommentdate());
            singleComment.put(Constants.Forum.COMMENT_BY_USERID_KEY,
                    topicComment.getCommentedbyId());
            singleComment.put(Constants.Forum.COMMENT_BY_NICMNAME_KEY,
                    topicComment.getCommentedbyUName());
            singleComment.put(Constants.Forum.COMMENT_TEXT_KEY,
                    topicComment.getComment());
            singleComment.put(Constants.Forum.COMMENT_REPLY_ID_KEY,
                    topicComment.getReplyTopicId());

            return singleComment;
            

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
    public JSONObject searchTopics(String keyWrds) throws BullFinderException {
        throw new BullFinderException(Constants.ExceptionCode.
                BULLFINDER_GENERIC_ERROR,"Not supported yet.");
    }

    @Override
    public void postFeedBack(String userId, String email, String title,
            String comment, boolean isBug) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Feedback fb = new Feedback();

            fb.setPostedById(userId);
            fb.setPostedByemail(email);
            fb.setFeedbackdate(this.getCurrentDateInFormat());
            fb.setFeedbacklong(comment);
            fb.setFeedbackshort(title);
            fb.setFeedbacktype(-1);

            em.persist(fb);

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
    public JSONObject retrieveTickerTalks(String ticker) throws BullFinderException {
    throw new BullFinderException(Constants.ExceptionCode.
                BULLFINDER_GENERIC_ERROR,"Not supported yet.");
    }

    @Override
    public JSONObject postCommentOnTicker(String userId, String usernickname,
            String ticker, String comment, String meter, String replyId)
            throws BullFinderException {
    throw new BullFinderException(Constants.ExceptionCode.
                BULLFINDER_GENERIC_ERROR,"Not supported yet.");
    }

    @Override
    public JSONObject retrieveTopicInfo(String topicId) throws BullFinderException {
        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            DiscussTopic topic = em.find(DiscussTopic.class, topicId);

            JSONObject topicObject = new JSONObject();
            topicObject.put(Constants.Forum.TOPIC_ID_KEY, topic.getId());
            topicObject.put(Constants.Forum.TOPIC_TITLE_KEY, topic.getTitle());
            topicObject.put(Constants.Forum.TOPIC_CREATEDBY_USERID_KEY,
                    topic.getCreatedbyId());
            topicObject.put(Constants.Forum.TOPIC_CREATEDBY_NICMNAME_KEY,
                    topic.getCreatedbyUName());
            topicObject.put(Constants.Forum.TOPIC_CREATEDATE_KEY, 
                    topic.getCreatedate());
            topicObject.put(Constants.Forum.TOPIC_POSTCOUNT_KEY, 
                    topic.getPostcount());
            topicObject.put(Constants.Forum.TOPIC_LASTPOST_KEY, 
                    topic.getLastpost());
           return topicObject;
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
    public boolean isValidTicker(String ticker) throws BullFinderException {

        if(ticker == null || ticker.trim().length() == 0)
            return false;

        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select t from com.tickertrek.jpa.entity.BseNse t " +
              "where t.ticker=:ticker ");
            query.setParameter("ticker",ticker);
            List<BseNse> tickers = query.getResultList();

            if(tickers != null && tickers.get(0) != null
                    && ticker.equalsIgnoreCase(tickers.get(0).getTicker()))
                return true;
            
            return false;

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
    public JSONObject retrieveUserPublicProfile(String username) throws BullFinderException {

        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            Query query = em.createQuery("select p from com.tickertrek.jpa.entity.PublicUserProfile p " +
              "where p.nickname = :nickname ");
            query.setParameter("nickname",username);
            List<PublicUserProfile> profiles = query.getResultList();

            if(profiles == null && profiles.size() != 1)
                throw new BullFinderException(Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR,
                    Constants.ExceptionCode.BULLFINDER_GENERIC_ERROR_MSG);
            
            return JPAUtil.getJSON(profiles.get(0));

        } catch (Exception exp){
            throw createCustomizedException(exp);
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }        
    }



}
