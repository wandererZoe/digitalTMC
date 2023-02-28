package com.digitalTMC.service.impl;

import com.digitalTMC.dao.MemberPathwayDao;
import com.digitalTMC.dao.MentorMenteeDao;
import com.digitalTMC.dao.impl.MemberPathwayDaoImpl;
import com.digitalTMC.dao.impl.MentorMenteeDaoImpl;
import com.digitalTMC.dao.po.MemberPathway;
import com.digitalTMC.dao.po.MentorMentee;
import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.service.EducationService;
import com.digitalTMC.service.vo.MemberPathwayVO;
import com.digitalTMC.util.enums.Pathway;
import org.springframework.stereotype.Service;

import java.sql.Connection;
@Service
public class EducationServiceImpl implements EducationService {

    MentorMenteeDao mentorMenteeDao;
    MemberPathwayDao memberPathwayDao;
    @Override
    public void openPathway(int memberId, Pathway pathway) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        MemberPathway memberPathway = new MemberPathway(memberId,pathway.getValue());
        memberPathwayDao = new MemberPathwayDaoImpl();
        memberPathwayDao.insert(memberPathway,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void updatePathwayLevel(int memberId, MemberPathwayVO vo) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        memberPathwayDao = new MemberPathwayDaoImpl();
        MemberPathway memberPathway = memberPathwayDao.searchSpecific(memberId,vo.getPathway().getValue(),connection);
        memberPathway.setLevel(vo.getPathwayLevel().getValue());
        memberPathway.setChangeOn();
        memberPathwayDao.update(memberPathway,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void specifyPathway(int memberId, Pathway pathway) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        memberPathwayDao = new MemberPathwayDaoImpl();
        MemberPathway memberPathway = memberPathwayDao.searchSpecific(memberId, Pathway.Dummy.getValue(),connection);
        memberPathway.setPathwayId(pathway.getValue());
        memberPathway.setChangeOn();
        memberPathwayDao.update(memberPathway,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void openDefaultPathway(int memberId) {
        openPathway(memberId,Pathway.Dummy);
    }

    @Override
    public void assignMentor(int menteeId, int mentorId) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        mentorMenteeDao = new MentorMenteeDaoImpl();
        MentorMentee mentorMentee = mentorMenteeDao.search(menteeId,connection);
        mentorMentee.setMentorId(mentorId);
        mentorMenteeDao.update(mentorMentee,connection);
        sqlConnection.disconnect(connection);
    }
}
