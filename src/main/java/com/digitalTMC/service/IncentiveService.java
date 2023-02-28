package com.digitalTMC.service;

public interface IncentiveService {
    void setIncentivePlan(String plan);
    boolean checkIncentivePlan(String memberId);
    void WriteOffIncentivePlan(String memberId);

    //get member list who have complete a level
}
