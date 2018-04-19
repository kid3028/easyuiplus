package com.easyui.util;



public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{4,3,7,8,2};
        System.out.println(test(arr));
    }


    public static int test(int[] arr) {
        for (int i = 0; i < 2; i++) {
            int k = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] > arr[k]) {
                    k = j;
                }
            }
            if(k != i) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        return arr[1];
    }

}
