package org.example.service;

import org.example.model.Member;

import java.util.HashMap;

public class MemberService {
    private static HashMap<String, Member> members;

    private static MemberService instance;
    private MemberService(){
        members=new HashMap<>();
    }
    public static HashMap<String, Member> getMembers(){
        if(members==null){
            members=new HashMap<>();
        }
        return members;
    }
    public static MemberService getInstance(){
        if(instance==null){
            return new MemberService();
        }
        return instance;
    }

    public String addMember(String id,String name,Integer coins){
        if(members.get(id)==null){
            if(coins<=0){
                return "Please give correct number of coins>0";
            }
            Member member=new Member(id,name,coins);
            members.put(id,member);
            return name+" added successfully.";
        }else{
            return "Member already there";
        }

    }
}
