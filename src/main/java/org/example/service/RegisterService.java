package org.example.service;

import java.util.HashMap;
import java.util.HashSet;

public class RegisterService {
    private static HashMap<String,HashSet<String>> registeredMembers;
    private static RegisterService registerService;
    private RegisterService(){
        registeredMembers=new HashMap<>();
    }
    public static RegisterService getInstance(){
        if(registerService==null){
            registerService=new RegisterService();
        }
        return registerService;
    }
    public static HashMap<String,HashSet<String>> getRegisteredMembers(){
        if(registeredMembers==null){
            registeredMembers=new HashMap<>();
            return registeredMembers;
        }
        return registeredMembers;
    }

    public String registerMember(String member_id,String event_id) {
        String memberName;
        try {
            memberName = MemberService.getMembers().get(member_id).getName();
        } catch (Exception e) {
            return "Member not present in database";
        }
        String eventName;
        try {
            eventName = EventService.getEvents().get(event_id).getEvent_name();
        } catch (Exception e) {
            return "Event not present in database";
        }

        if (registeredMembers == null) {
            registeredMembers = new HashMap<>();
        }
        if (registeredMembers.get(member_id) == null) {
            HashSet<String> events = new HashSet<>();
            registeredMembers.put(member_id,events);
        }
        if (registeredMembers.get(member_id).contains(event_id)) {
            return "Member " + member_id + " already registed for event " + event_id;
        }
        registeredMembers.get(member_id).add(event_id);

        return memberName + " registered to the " + eventName + " successfully.";
    }
}
