package com.digitalTMC.service;

import com.digitalTMC.service.vo.MemberVO;

import java.util.ArrayList;

public interface ViewPageService {
    String getMembers(String criteria);
    ArrayList<MemberVO> getAllMembers();
    void init();
}
