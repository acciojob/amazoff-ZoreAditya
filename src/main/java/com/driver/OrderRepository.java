package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        // your code here
        orderMap.put(order.getId(),order);
    }

    public void savePartner(String partnerId){
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        // your code here
        // create a new partner with given partnerId and save it
        partnerMap.put(partnerId,deliveryPartner);
        partnerToOrderMap.put(partnerId,new HashSet<>());
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            partnerToOrderMap.get(partnerId).add(orderId);
            orderToPartnerMap.put(orderId,partnerId);
            partnerMap.get(partnerId).setNumberOfOrders(partnerMap.get(partnerId).getNumberOfOrders()+1);
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
        }
    }

    public Order findOrderById(String orderId){
        return orderMap.getOrDefault(orderId,null);
        // your code here
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        return partnerMap.getOrDefault(partnerId,null);
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        return partnerMap.getOrDefault(partnerId,null).getNumberOfOrders();
        // your code here
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        HashSet<String> list=partnerToOrderMap.getOrDefault(partnerId,null);
        List<String> ans=new ArrayList<>();
        for(String string:list){
            ans.add(string);
        }
        return ans;

    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        List<String> ans=new ArrayList<>();
        for(String map:orderMap.keySet()){
            ans.add(map);
        }
        return ans;



    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        HashSet<String> orders = partnerToOrderMap.get(partnerId);

        if (orders != null) {
            for (String orderId : orders) {
                orderToPartnerMap.remove(orderId);   // Orders become unassigned
            }
        }

        partnerToOrderMap.remove(partnerId);
        partnerMap.remove(partnerId);
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        orderMap.remove(orderId);
        orderToPartnerMap.remove(orderId);
    }

    public Integer findCountOfUnassignedOrders(){
        int count=0;
        for(Map.Entry<String,Order> map:orderMap.entrySet()){
            if(!orderToPartnerMap.containsKey(map.getKey())){
                count++;
            }
        }
        return count;
        // your code here
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        HashSet<String> hashSet=partnerToOrderMap.get(partnerId);
        String str[]=timeString.split(":");
        int hh=Integer.parseInt(str[0]);
        int mm=Integer.parseInt(str[1]);

        int totalTime=hh*60+mm;

        int count=0;
        for(String orderId:hashSet){
            if(orderMap.get(orderId).getDeliveryTime()>totalTime){
                count++;
            }
        }
        return count;

    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        HashSet<String> hashSet=partnerToOrderMap.get(partnerId);
        int max=Integer.MIN_VALUE;
        for(String orderId:hashSet){
            if(orderMap.get(orderId).getDeliveryTime()>max){
                max=orderMap.get(orderId).getDeliveryTime();
            }
        }
        int hh=max/60;
        int mm=max%60;
        return hh+":"+mm;

    }
}