package org.example;

import org.example.service.BiddingService;
import org.example.service.EventService;
import org.example.service.MemberService;
import org.example.service.RegisterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        MemberService memberService=MemberService.getInstance();
        EventService eventService=EventService.getInstance();
        RegisterService registerService=RegisterService.getInstance();
        BiddingService biddingService=BiddingService.getInstance();
        while (scanner.hasNext()){
            String command=scanner.nextLine();
            String[] splitCommands=command.split(" ");
            if(splitCommands[0].equals("ADD_MEMBER")){
                String id=splitCommands[1];
                String name=splitCommands[2];
                Integer coins=Integer.parseInt(splitCommands[3]);
                String result=memberService.addMember(id,name,coins);
                System.out.println(result);
            }else if(splitCommands[0].equals("ADD_EVENT")){
                String id=splitCommands[1];
                String name=splitCommands[2];
                String prize=splitCommands[3];
                String date=splitCommands[4];
                String result=eventService.addEvent(id,name,prize,date);
                System.out.println(result);
            }else if(splitCommands[0].equals("REGISTER_MEMBER")){
                String member_id=splitCommands[1];
                String event_id=splitCommands[2];
                String result=registerService.registerMember(member_id,event_id);
                System.out.println(result);
            }else if(splitCommands[0].equals("SUBMIT_BID")){
                String member_id=splitCommands[1];
                String event_id=splitCommands[2];
                List<Integer> bids=new ArrayList<>();
                for(int i=3;i<splitCommands.length;i++){
                    Integer bid=Integer.parseInt(splitCommands[i]);
                    bids.add(bid);
                }
                if(bids.size()>5 || bids.size()<=0){
                    System.out.println("Invalid Bids");
                }else{
                    String result= BiddingService.placeBid(member_id,event_id,bids);
                    System.out.println(result);
                }
            }else{
                String event_id=splitCommands[1];
                String result=BiddingService.displayWinner(event_id);
                System.out.println(result);
            }
        }
        System.out.println("Hello world!");
        scanner.close();;
    }
}