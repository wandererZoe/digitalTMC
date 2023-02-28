package com.digitalTMC.service;

public interface UserService {
    int initialUser(String sapNumber);
    void login(String accountForm);
    void activeAccount(String accountForm);
    void resetAccount(String accountForm);
    void askToUnlockAccount(String sapNumber);
    void askToUnlockAccountAgain(String sapNumber);
    void unlockAccount(String sapNumber);
    void logout();
}
