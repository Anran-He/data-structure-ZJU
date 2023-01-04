# data-structure-ZJU
Notes and codes of data structure course from ZJU.  
Here is the course link: https://www.icourse163.org/course/ZJU-93001?tid=1468825451

## 1.1 什么是数据结构
1. print from 1 to N
- 循环
```java
package com.datastructure;
import java.util.Scanner;
public class PrintFromOneToNUsingLoop {

	public static void printN(int n) {
		for(int i=1; i<=n; i++) {
			System.out.print(i+"\n");

		}
	}

	public static void main(String[] args) {

		System.out.println("Please enter N:");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		printN(n);
	}
}
```

- 递归
```java
package com.datastructure;
import java.util.Scanner;
public class PrintFromOneToNUsingResursion {

	public static void printN(int n) {
		if(n > 0) {
			printN(n-1);
			System.out.println(n);
		}else {
			return;
		}

	}

	public static void main(String[] args) {

		System.out.println("Please enter N:");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		printN(n);
	}
}
```

但是递归所占空间比循环更大，导致解决问题的效率更低。
> 解决问题方法的效率，跟空间的利用效率有关。

2. 计算多项式的值
- 常规做法
```java
package com.datastructure;
public class BadPolynomial {

	public static double f(int n, double[] a, double x) {

		double p = a[0];
		for(int i=1;i<=n;i++) {
			p += a[i] * Math.pow(x,i);
		}

		return p;
	}

	public static void main(String[] args) {

		double a[] = new double[] {1.1,2.2,3.3,4.54};

		long start = System.nanoTime();
		double result = f(3,a,1.1);
		long end = System.nanoTime();

		System.out.print(result);
		System.out.println("\ntime difference: " + (end - start));

	}
}
```
time difference为：14600

- 更高效的做法
```java
package com.datastructure;
public class GoodPolynomial {

	public static double f(int n, double[] a, double x) {

		double p = a[n];
		for(int i=n;i>0;i--) {
			p = a[i-1] + x*p;
		}

		return p;
	}

	public static void main(String[] args) {

		double a[] = new double[] {1.1,2.2,3.3,4.54};
		long start = System.nanoTime();
		double result = f(3,a,1.1);
		long end = System.nanoTime();

		System.out.print(result);
		System.out.println("\ntime difference: " + (end - start));

	}
}
```
time difference为: 4400
> 解决问题方法的效率，跟算法的巧妙程度有关。

## 1.3 应用
计算最大子列和的不同算法

- O(n^3)
```java
package com.datastructure;
public class MaxSubseqSum1 {

	public static int maxSubseqSum1(int[] a, int n) {

		int thisSum, maxSum = 0;

		for(int i = 0; i < n; i++) {
			for(int j = i; j < n; j++) {
				thisSum = 0;
				for(int k = i; k <= j; k++) {
					thisSum += a[k];
				}
				if(thisSum > maxSum) {
					maxSum = thisSum;
				}

			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int a[] = new int[] {-1,2,3,-4,5};
		int n = 5;
		int max = maxSubseqSum1(a,n);
		System.out.println(max);
	}
}
```

- O(n^2)
```java
package com.datastructure;
public class MaxSubseqSum2 {

	public static int maxSubseqSum2(int[] a, int n) {

		int thisSum, maxSum = 0;
		for(int i = 0; i < n; i++) {
			thisSum = 0;
			for(int j = i; j < n; j++) {
				thisSum += a[j];
			}
			if(thisSum > maxSum) {
				maxSum = thisSum;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int a[] = new int[] {-1,2,3,-4,5};
		int n = 5;
		int max = maxSubseqSum2(a,n);
		System.out.println(max);
	}
}
```


- O(nlogn)
```java
package com.datastructure;
public class MaxSubseqSum3 {
	
	public static int max3(int a, int b, int c) {
		
		return a > b ? a > c ? a : c : b > c ? b : c;
		
	}
	
	public static int maxSubseqSum3(int[] list, int left, int right) {
		
		int maxLeftSum, maxRightSum, maxLeftBorderSum, maxRightBorderSum;
		int leftBorderSum, rightBorderSum;
		int center, i;
		
		if(left == right) {
			if(list[left] > 0) {
				return list[left];
			}else {
				return 0;
			}
		}
		
		center = (left + right) / 2;
		
		maxLeftSum = maxSubseqSum3(list, left, center);
		maxRightSum = maxSubseqSum3(list, center + 1, right);
		
		maxLeftBorderSum = 0;
		leftBorderSum = 0;
		
		for(i = center; i >= left; i--) {
			leftBorderSum += list[i];
			if(leftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = leftBorderSum;
			}
		}
		
		maxRightBorderSum = 0;
		rightBorderSum = 0;
		
		for(i = center + 1; i <= right; i++) {
			rightBorderSum += list[i];
			if(rightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = rightBorderSum;
			}
		}
		
		return max3(maxLeftSum, maxRightSum, maxRightBorderSum+maxLeftBorderSum);
	}
	
	public static void main(String[] args) {
		
		int a[] = new int[] {-1,2,3,-4,5};
		int n = 5;
		int max = maxSubseqSum3(a,0,n-1);
		System.out.println(max);

	}
}
```

- O(n)
```java
package com.datastructure;
public class MaxSubseqSum4 {

	public static int maxSubseqSum4(int[] a, int n) {

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
		int a[] = new int[] {-1,2,3,-4,5};
		int n = 5;
		int max = maxSubseqSum4(a,n);
		System.out.println(max);
	}
}
```



