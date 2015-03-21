package com.cueify.extension;

import java.util.HashMap;
import java.util.Map;
 
public class LocalDragboard {          
      private final Map<Class<?>, Object> contents ;
      
      private final static LocalDragboard instance = new LocalDragboard();
      
      private LocalDragboard() {
           this.contents = new HashMap<Class<?>, Object>();
      }
      
      public static LocalDragboard getInstance() {
           return instance ;
      }
      
      public <T> void putValue(Class<? extends Object> class1, T t) {
           contents.put(class1, class1.cast(t));
      }
      
      public <T> T getValue(Class<T> type) {
           return type.cast(contents.get(type));
      }
      
      public boolean hasType(Class<?> type) {
           return contents.keySet().contains(type);
      }
      
      public void clear(Class<?> type) {
           contents.remove(type);
      }
      
      public void clearAll() {
           contents.clear();
      }
 }