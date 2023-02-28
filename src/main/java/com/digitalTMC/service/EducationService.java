package com.digitalTMC.service;

import com.digitalTMC.service.vo.MemberPathwayVO;
import com.digitalTMC.util.enums.Pathway;

public interface EducationService {
    void openPathway (int memberId, Pathway pathway);
    void openDefaultPathway (int memberId);
    void updatePathwayLevel (int memberId, MemberPathwayVO vo);
    void specifyPathway (int memberId, Pathway pathway);
    void assignMentor (int menteeId, int mentorId);
}
