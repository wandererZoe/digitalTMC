package com.digitalTMC.service;

import com.digitalTMC.service.vo.MemberVO;

public interface MemberService {
    void initialNewAccount(String sapNumber);
    void updateMemberProfile(String memberProfile);
    void newMembership(String membershipForm);
    void assignPost(String postForm);
    MemberVO getMemberDetail(int memberId);
}
