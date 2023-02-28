package com.digitalTMC.controller;

import com.digitalTMC.service.MemberService;
import com.digitalTMC.service.ViewPageService;
import com.digitalTMC.service.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    ViewPageService viewPageService;

    @RequestMapping(value = "/members/{id}/member-detail", method = RequestMethod.GET)
    public ResponseEntity<MemberVO> getMemberDetail(@PathVariable(name="id") int id) {
        return new ResponseEntity<>(memberService.getMemberDetail(id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/members/add-member", method = RequestMethod.POST )
    public void newMember(){
        memberService.initialNewAccount("I332387");
    }

    @RequestMapping(value = "/members/new-membership", method = RequestMethod.POST )
    public void newMemberShip(){
        memberService.newMembership("{\"memberId\":\"22\",\"durationId\":\"31\",\"international\":\"true\"}");
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public ResponseEntity<List<MemberVO>> getAll() {
        viewPageService.init();
        return new ResponseEntity<>(viewPageService.getAllMembers(), HttpStatus.ACCEPTED);
    }
}