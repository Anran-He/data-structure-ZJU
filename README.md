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


