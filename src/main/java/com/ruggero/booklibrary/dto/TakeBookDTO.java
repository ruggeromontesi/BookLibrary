package com.ruggero.booklibrary.dto;

public class TakeBookDTO {

   private String guid;

   private String userId;

   private int periodOfDays;

   public TakeBookDTO() {
   }

   public String getGuid() {
      return guid;
   }

   public void setGuid(String guid) {
      this.guid = guid;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public int getPeriodOfDays() {
      return periodOfDays;
   }

   public void setPeriodOfDays(int periodOfDays) {
      this.periodOfDays = periodOfDays;
   }
}
