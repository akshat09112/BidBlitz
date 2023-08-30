package org.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class BiddingService {
    private static HashMap<String,HashMap<String, HashSet<Integer>>> bids;
    private static BiddingService biddingService=new BiddingService();
    private BiddingService(){
        bids=new HashMap<>();
    }
    public static BiddingService getInstance(){
        if(biddingService==null){
            return new BiddingService();
        }
        return biddingService;
    }
    public static HashMap<String,HashMap<String, HashSet<Integer>>> getBids(){
        if(bids==null){
            bids=new HashMap<>();
        }
        return bids;
    }
    public static String placeBid(String member_id, String event_id, List<Integer> bid){
        if(MemberService.getMembers().get(member_id)==null){
            return "Member not present in database";
        }
        if(EventService.getEvents().get(event_id)==null){
            return "Event not registered in database";
        }
        if(RegisterService.getRegisteredMembers().get(member_id)==null){
            return "Member not registered for any event";
        }
        if(!RegisterService.getRegisteredMembers().get(member_id).contains(event_id)){
            return "Member not registered for this event";
        }
        if(bid.get(bid.size() - 1) >MemberService.getMembers().get(member_id).getCoins()){
            return "Not enough coins to bid for member";
        }
        if(bids.get(event_id)==null){
            HashMap<String, HashSet<Integer>> memberBid=new HashMap<>();
            bids.put(event_id,memberBid);
        }
        if(bids.get(event_id).get(member_id)==null){
            HashSet<Integer> allBids=new HashSet<>();
            bids.get(event_id).put(member_id,allBids);
        }
        for(int i=0;i<bid.size();i++){
            bids.get(event_id).get(member_id).add(bid.get(i));
        }
        return "BIDS SUBMITTED SUCCESSFULLY";
    }

    public static String displayWinner(String event_id){
        if(bids.get(event_id)==null){
            return "No such event, so no winner";
        }
        HashMap<String, HashSet<Integer>> members=bids.get(event_id);
        int minBid=100000000;
        String name="";
        for(Map.Entry<String, HashSet<Integer>> entry: members.entrySet()){
            String member_id=entry.getKey();
            HashSet<Integer> bids=entry.getValue();
            List<Integer> finalBids=bids.stream().toList();
            int minbid= finalBids.get(0);
            int maxbid= finalBids.get(0);
            for (int j=0;j<finalBids.size();j++){
                if(finalBids.get(j)<minbid){
                    minbid=finalBids.get(j);
                }
                if (finalBids.get(j)>maxbid){
                    maxbid=finalBids.get(j);
                }
            }
            if(minbid<minBid){
                minBid=minbid;
                name=MemberService.getMembers().get(member_id).getName();
                int coins=MemberService.getMembers().get(member_id).getCoins();
                MemberService.getMembers().get(member_id).setCoins(coins-maxbid);
            }
        }
        return name+" wins the "+EventService.getEvents().get(event_id).getPrize_name()+" the lowest bid "+String.valueOf(minBid);
    }
}
