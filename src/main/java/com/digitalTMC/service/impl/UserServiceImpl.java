package com.digitalTMC.service.impl;

import com.digitalTMC.app.Parameters;
import com.digitalTMC.dao.UserChangeLogDao;
import com.digitalTMC.dao.UserDao;
import com.digitalTMC.dao.impl.UserChangeLogDaoImpl;
import com.digitalTMC.dao.impl.UserDaoImpl;
import com.digitalTMC.dao.po.User;
import com.digitalTMC.dao.po.UserChangeLog;
import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.service.DurationService;
import com.digitalTMC.service.UserService;
import com.digitalTMC.service.vo.form.AccountForm;
import com.digitalTMC.util.BusinessException;
import com.digitalTMC.util.DateFormatUtil;
import com.digitalTMC.util.JackJsonUtil;
import com.digitalTMC.util.enums.SystemMessage;
import com.digitalTMC.util.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.sql.Connection;
@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;
    UserChangeLogDao userChangeLogDao;
    DurationService durationService;

    @Override
    public int initialUser(String sapNumber) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        User user = new User(sapNumber);
        userDao = new UserDaoImpl();
        int newId = userDao.insert(user,connection);
        sqlConnection.disconnect(connection);
        return newId;
    }

    @Override
    public void login(String accountForm) {
        login(new JackJsonUtil<AccountForm>().jsonToObject(accountForm, AccountForm.class));
    }

    @Override
    public void activeAccount(String accountForm) {
        activeAccount(new JackJsonUtil<AccountForm>().jsonToObject(accountForm, AccountForm.class));
    }

    @Override
    public void resetAccount(String accountForm) {
        resetAccount(new JackJsonUtil<AccountForm>().jsonToObject(accountForm,AccountForm.class));
    }

    private void login(AccountForm accountForm) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        userDao = new UserDaoImpl();
        User user = userDao.searchBySapNumber(accountForm.getSapNumber(),connection);
        sqlConnection.disconnect(connection);
        if(user==null)
            throw new BusinessException(SystemMessage.UNKNOWN_USER);
        if(user.getActivated()== UserStatus.Inactivated.getValue())
            throw new BusinessException(SystemMessage.INACTIVATED_USER);
        if(!accountForm.getUsername().equals(user.getUsername()))
            throw new BusinessException(SystemMessage.WRONG_USERNAME_OR_PWD);
        else{
            durationService = new DurationServiceImpl();
            Parameters.setCurrentDurationId(durationService.getCurrentDurationId());
            Parameters.setUserId(user.getMemberId());
            Parameters.setUserName(user.getUsername());
        }
    }

    private void activeAccount(AccountForm accountForm) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        String username = accountForm.getUsername();
        String sapNumber = accountForm.getSapNumber();
        userDao = new UserDaoImpl();
        User user = userDao.searchBySapNumber(sapNumber,connection);
        user.setUsername(username);
        user.setActivated(UserStatus.ActivatedAndLocked.getValue());
        user.setLockUsername(UserStatus.ActivatedAndLocked.getValue());
        user.setActiveOn(new DateFormatUtil().getCurrentDateTime());
        userDao.update(user,connection);
        sqlConnection.disconnect(connection);
    }

    private UserStatus checkAccountStatus(String sapNumber) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        userChangeLogDao = new UserChangeLogDaoImpl();
        UserChangeLog changeLog = userChangeLogDao.searchProcessedRequestBySapNumber(sapNumber,connection);
        if(changeLog != null){
            sqlConnection.disconnect(connection);
            return UserStatus.Unlocked;
        }
        changeLog = userChangeLogDao.searchOpenRequestBySapNumber(sapNumber,connection);
        if(changeLog != null){
            sqlConnection.disconnect(connection);
            return UserStatus.UsernameChangeRequested;
        }
        sqlConnection.disconnect(connection);
        return UserStatus.ActivatedAndLocked;
    }

    @Override
    public void askToUnlockAccount(String sapNumber) {
        UserStatus status = checkAccountStatus(sapNumber);
        if(status==UserStatus.ActivatedAndLocked) {
            SQLConnection sqlConnection = new SQLConnection();
            Connection connection = sqlConnection.connect();
            UserChangeLog changeLog = new UserChangeLog();
            changeLog.setRequestBy(sapNumber);
            changeLog.setRequestOn(new DateFormatUtil().getCurrentDateTime());
            changeLog.setStatus(UserStatus.UsernameChangeRequested.getValue());
            changeLog.setProcessBy("");
            changeLog.setProcessOn(new DateFormatUtil().getInitialDateTime());
            userChangeLogDao = new UserChangeLogDaoImpl();
            userChangeLogDao.insert(changeLog, connection);
            sqlConnection.disconnect(connection);
        }else if(status==UserStatus.UsernameChangeRequested){
            throw new BusinessException(SystemMessage.ALREADY_REQUESTED);
        }else{
            throw new BusinessException(SystemMessage.ALREADY_UNLOCK);
        }
    }

    @Override
    public void askToUnlockAccountAgain(String sapNumber) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        userChangeLogDao = new UserChangeLogDaoImpl();
        UserChangeLog changeLog = userChangeLogDao.searchOpenRequestBySapNumber(sapNumber,connection);
        changeLog.setStatus(UserStatus.RequestExpire.getValue());
        userChangeLogDao.update(changeLog,connection);
        sqlConnection.disconnect(connection);
        askToUnlockAccount(sapNumber);
    }

    @Override
    public void unlockAccount(String sapNumber) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();

        userDao = new UserDaoImpl();
        User user = userDao.searchBySapNumber(sapNumber,connection);
        user.setLockUsername(UserStatus.Unlocked.getValue());
        user.setActiveOn(new DateFormatUtil().getCurrentDateTime());
        userDao.update(user,connection);

        userChangeLogDao = new UserChangeLogDaoImpl();
        UserChangeLog changeLog = userChangeLogDao.searchOpenRequestBySapNumber(sapNumber,connection);
        changeLog.setProcessBy(Parameters.userId+"");
        changeLog.setProcessOn(new DateFormatUtil().getCurrentDateTime());
        changeLog.setStatus(UserStatus.Unlocked.getValue());
        userChangeLogDao.update(changeLog,connection);

        sqlConnection.disconnect(connection);
    }

    private void resetAccount(AccountForm accountForm) {
        activeAccount(accountForm);
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        userChangeLogDao = new UserChangeLogDaoImpl();
        UserChangeLog changeLog = userChangeLogDao.searchProcessedRequestBySapNumber(accountForm.getSapNumber(),connection);
        changeLog.setStatus(UserStatus.UsernameChangeCompleted.getValue());
        userChangeLogDao.update(changeLog,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void logout() {
        durationService = new DurationServiceImpl();
        Parameters.setCurrentDurationId(durationService.getCurrentDurationId());
        Parameters.setUserId(2);
        Parameters.setUserName("visitor");
    }
}