package com.digitalTMC.general;

import com.digitalTMC.app.Parameters;
import com.digitalTMC.service.*;
import com.digitalTMC.service.impl.*;
import com.digitalTMC.service.vo.MemberPathwayVO;
import com.digitalTMC.util.BusinessException;
import com.digitalTMC.util.enums.Pathway;
import com.digitalTMC.util.enums.PathwayLevel;
import com.digitalTMC.util.enums.ViewMode;

import org.junit.Test;


public class StepTest {

    @Test
    public void executeSteps(){
        login();
        System.out.println("username:"+ Parameters.currentDurationId);
        System.out.println("username:"+Parameters.userName);
        System.out.println("username:"+Parameters.userId);

        testAuthority();
    }

    private void message(){
        System.out.println("==============Message=================");
        DurationService durationService = new DurationServiceImpl();
        try{
            String json = "{\"startDate\":\"2022-10-01\",\"endDate\":\"2023-03-31\"}";
            durationService.createNewDuration(json);
        }catch (BusinessException e){
            System.out.println(e.getResultJson());
        }

        System.out.println("==============Message=================");
        System.out.println();
    }

    private void initAuthority(){
        System.out.println("==============InitAuthority=================");
        AuthorityService service = new AuthorityServiceImpl();
        System.out.println(service.checkCatalogConsistency());
        System.out.println("==============InitAuthority=================");
        System.out.println();
    }

    private void testAuthority(){
        System.out.println("==============testAuthority=================");
        AuthorityService service = new AuthorityServiceImpl();
        System.out.println(service.isReadable("authority-management"));
        System.out.println(service.isWritable("authority-management"));
        System.out.println("==============testAuthority=================");
        System.out.println();
    }

    private void login(){
        System.out.println("==============Login=================");
        UserService userService = new UserServiceImpl();
        String json = "{\"sapNumber\":\"I332387\",\"username\":\"zoetxt\"}";
        try{
            userService.login(json);
        }catch (BusinessException e){
            System.out.println(e.getResultJson());
        }
        System.out.println("==============Login=================");
        System.out.println();
    }

    private void newDuration(){
        System.out.println("==============NewDuration=================");
        DurationService durationService = new DurationServiceImpl();
        try{
            String json = "{\"startDate\":\"2023-04-01\",\"endDate\":\"2023-09-30\"}";
            durationService.createNewDuration(json);
        }catch (BusinessException e){
            System.out.println(e.getResultJson());
        }
        System.out.println("==============NewDuration=================");
    }

    private void initial() {
        System.out.println("==============Initial=================");
        MemberService service = new MemberServiceImpl();
        service.initialNewAccount("I000000");
        System.out.println("==============Initial=================");
        System.out.println();
    }

    private void activate() {
        System.out.println("==============Activate=================");
        UserService service = new UserServiceImpl();
        String json = "{\"sapNumber\":\"I123456\",\"username\":\"demo_user\"}";
        service.activeAccount(json);
        System.out.println("==============Activate=================");
        System.out.println();
    }

    private void requestReset() {
        System.out.println("==============Reset=================");
        UserService service = new UserServiceImpl();
        String json = "{\"sapNumber\":\"I123456\",\"username\":\"newname2\"}";
        try{
            service.askToUnlockAccount("I123456");
        }catch (BusinessException e){
            System.out.println(e.getResultJson());
        }
        service.unlockAccount("I123456");
        service.resetAccount(json);
        System.out.println("==============Reset=================");
        System.out.println();
    }

    private void updateProfile() {
        System.out.println("==============Update=================");
        MemberService service = new MemberServiceImpl();
        String json = "{\"name\":\"Zoe Tian\",\"sapNumber\":\"I332387\",\"emailAddress\":\"zoe.tian@sap.com\",\"wechatId\":\"Tayatayatxt\"}";
        service.updateMemberProfile(json);
        System.out.println("==============Update=================");
        System.out.println();
    }

    private void newMembership() {
        System.out.println("==============NewMembership=================");
        MemberService service = new MemberServiceImpl();
        String str = "{\"memberId\":\"18\",\"durationId\":\"25\",\"international\":\"true\"}";
        service.newMembership(str);
        System.out.println("==============NewMembership=================");
        System.out.println();
    }

    private void assignPost() {
        System.out.println("==============assignPost=================");
        MemberService service = new MemberServiceImpl();
        String str = "{\"memberId\":\"18\",\"durationId\":\"25\",\"postId\":\"0\"}";
        service.assignPost(str);
        System.out.println("==============assignPost=================");
        System.out.println();
    }

    private void assignMentor(){
        System.out.println("==============AssignMentor=================");
        EducationService service = new EducationServiceImpl();
        service.assignMentor(18,17);
        System.out.println("==============AssignMentor=================");
        System.out.println();
    }

    private void getList(){
        System.out.println("==============List=================");
        ViewPageService service = new ViewPageServiceImpl();
        System.out.println(service.getMembers(ViewMode.Activated_ALL.getValue()+""));
        System.out.println("==============List=================");
        System.out.println();
    }

    private void newPathway(){
        System.out.println("==============New=================");
        EducationService service = new EducationServiceImpl();
        service.openDefaultPathway(16);
        System.out.println("==============New=================");
        System.out.println();
    }

    private void updateLevel(){
        System.out.println("==============UpdateLevel=================");
        EducationService service = new EducationServiceImpl();
        MemberPathwayVO vo = new MemberPathwayVO(Pathway.Dummy, PathwayLevel.LEVEL_1_2_2,true);
        service.updatePathwayLevel(16,vo);
        System.out.println("==============UpdateLevel=================");
        System.out.println();
    }

    private void specifyPathway(){
        System.out.println("==============SpecifyPathway=================");
        EducationService service = new EducationServiceImpl();
        service.specifyPathway(16,Pathway.EC_Effective_Coaching);
        System.out.println("==============SpecifyPathway=================");
        System.out.println();
    }


    /**
     * Step 1: [President/VPM/Secretary] new duration
     * Input: *Start Date, *End Date
     * Alternative Json: {"startDate":"2022-03-01","endDate":"2022-09-30"}
     *
     * Step 2: [VPM] initialize new account
     * Input: *SAP Number
     *
     * Step 3: [Member] active account
     * Reuse scenario: [Member] reset username | [Member] login
     * Input: *SAP Number, *username
     * Alternative Json: {"sapNumber":"I332387","username":"zoe"}
     *
     * Step 4: [Member] update profile
     * Comments: "sapNumber" is READ-ONLY
     * Alternative Json: {"name":"Zoe Tian","sapNumber":"I332387","emailAddress":"zoe.tian@sap.com","wechatId":"Tayatayatxt"}
     *
     * Step 5: [VPM] maintain membership in new duration
     * Input:
     * Alternative Json:
     *
     * Step 6: [Secretary] assign officer
     *
     *
     * Step 7: [VPE] initial dummy pathway
     *
     *
     * Repeat step 1-6
     *
     *
     * Step 9: [VPE] specify the first pathway
     *
     *
     * Step 10: [VPE] new pathway
     *
     *
     * Step 11: [VPE] update level of the first pathway
     */
}
