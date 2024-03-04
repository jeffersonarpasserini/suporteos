package com.curso.domains.enums;

public enum OrderStatus{

   LOW(0,"LOW"),MEDIUM(1,"MEDIUM"),HIGH(2,"HIGH");

   private Integer id;
   private String orderStatus;

   private OrderStatus(Integer id, String orderStatus) {
       this.id = id;
       this.orderStatus = orderStatus;
   }

   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
       return orderStatus;
    }

    public static OrderStatus toEnum(Integer id) {
        if(id==null) return null;
        for(OrderStatus x : OrderStatus.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}