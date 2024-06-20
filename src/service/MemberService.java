package service;

import model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private List<Member> members;

    public MemberService() {
        this.members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Member> getMembers() {
        return members;
    }
}
