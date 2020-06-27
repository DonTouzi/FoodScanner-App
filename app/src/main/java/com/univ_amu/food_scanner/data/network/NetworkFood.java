package com.univ_amu.food_scanner.data.network;

import java.util.List;

public class NetworkFood {
    public String code;
    public String name;
    public String brands;
    public String nutriscore;
    public List<NetworkQuantity> quantities;

    public String toString(){
        return "code : " + code + ", name : " + name + ", brands : " + brands +
                ", nutriscore : " + nutriscore + ", quantities : \n" + quantities.toString();
    }

    public static class NetworkQuantity {
        public String name;
        public int rank;
        public int level;
        public double quantity;
        public String unit;

        public String toString(){
            return "name : " + name + ", rank : " + rank + ", level : " + level +
                    ", quantity : " + quantity + ", unit : " + unit;
        }
    }
}