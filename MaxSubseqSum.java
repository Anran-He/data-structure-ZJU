package com.ptaExercise;

import java.util.Scanner;

public class MaxSubseqSum {
	
	public static int maxSubseqSum(int[] a, int n) {
		
		int maxSum = 0, thisSum = 0;
		for(int i = 0; i < n; i++) {
			thisSum += a[i];
			if(thisSum > maxSum) {
				maxSum = thisSum;
			}else if(thisSum < 0) {
				thisSum = 0;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the number of elemets:");
		int n = sc.nextInt();
		int a[] = new int[n];
		System.out.println("please enter the elements:");
		for(int i = 0; i < n; i++) {
			a[i] = sc.nextInt(); 
		}
		
		int max = maxSubseqSum(a,n);
		System.out.println(max);
	}

}
