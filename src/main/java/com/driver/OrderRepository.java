package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
 Map<String,Order>ordermap=new HashMap<>();
 Map<String,DeliveryPartner>partnerMap=new HashMap<>();
 Map<String,String>orderpartnermap=new HashMap<>();
 Map<String, List<String>>partnerordermap=new HashMap<>();



 public  void addOrder(Order order)
 {
     ordermap.put(order.getId(),order);
 }
 public void addPartner(String partnerId)
 {
     partnerMap.put(partnerId,new DeliveryPartner(partnerId));
 }
 public void addOrderPartnerPair(String orderId,String partnerId)
 {
     if(ordermap.containsKey(orderId) && partnerMap.containsKey(partnerId)) {
         orderpartnermap.put(orderId, partnerId);
         List<String> listoforder = new ArrayList<>();
         if (partnerordermap.containsKey(partnerId)) {
             listoforder = partnerordermap.get(partnerId);
         }
         listoforder.add(orderId);
         partnerMap.get(partnerId).setNumberOfOrders(listoforder.size());
         partnerordermap.put(partnerId, listoforder);
     }
 }
 public Order getOrderById(String orderId)
 {
     return  ordermap.get(orderId);
 }
 public DeliveryPartner getPartnerById(String partnerId)
 {
     return  partnerMap.get(partnerId);
 }
 public Integer getOrderCountByPartnerId(String partnerId)
 {
     return partnerMap.get(partnerId).getNumberOfOrders();
 }
 public List<String> getOrdersByPartnerId(String partnerId)
 {
     return  partnerordermap.get(partnerId);
 }
 public List<String>getAllOrders()
 {
     List<String>allorder=new ArrayList<>();
     for (String order:ordermap.keySet())
     {
         allorder.add(order);
     }
     return allorder;
 }
 public Integer getCountOfUnassignedOrders()
 {
     return ordermap.size()-orderpartnermap.size();
 }
 public Integer getOrdersLeftAfterGivenTimeByPartnerId(Integer time,String partnerId)
 {
     Integer count=0;
     List<String>orders=partnerordermap.get(partnerId);
     for(String order:orders)
     {
        int deliverytime=ordermap.get(order).getDeliveryTime();
        if(deliverytime>time)
        {
            count++;
        }
     }
     return count;
 }
 public int getLastDeliveryTimeByPartnerId(String partnerId)
 {
     int maxtime=0;
     List<String>list=partnerordermap.get(partnerId);
     for (String order:list)
     {
         int time=ordermap.get(order).getDeliveryTime();
         maxtime=Math.max(maxtime,time);
     }
     return maxtime;
 }
 public void deletePartnerById(String partnerId)
 {
     partnerMap.remove(partnerId);
     List<String>orders=partnerordermap.get(partnerId);
     partnerordermap.remove(partnerId);
     for(String order:orders)
     {
         orderpartnermap.remove(order);
     }
 }
 public void  deleteOrderById(String orderId)
 {
     ordermap.remove(orderId);
     String partnerid=orderpartnermap.get(orderId);
     orderpartnermap.remove(orderId);
     partnerordermap.get(partnerid).remove(orderId);
 }
}
