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

## 2.1 线性表及其表示
### 什么是线性表
Linear List: 由同类型数据元素构成有序序列的线性结构
- 表中元素个数称为线性表的长度
- 线性表中没有元素时，称为空表
- 表起始位置称表头，表结束位置称表尾

#### 线性表的顺序存储实现
1. 初始化
```java
List<String> list1 = new ArrayList<String>();
```
2. 查找
```java
list1.add("morning");
list1.add("afternoon");
list1.add("evening");
// built-in method
System.out.println(list1.indexOf("evening"));
// self-defined method
public static int find(String s, ArrayList<String> l) {

		int i = 0;
		while(i < l.size() && l.get(i) != s) {
			i++;
		}
		while(i >= l.size()){
			return -1;
		}

		return i;
	}
```
3. 插入
```java
// built-in method
list1.add(1, "noon");
// self-defiend method
public static void insert(String s, int i, ArrayList<String> l) {

		if(i < 0 || i > l.size() + 1 ) {
			System.out.println("invalid index");
		}

		l.add(null);

		for(int j = l.size() - 2; j >= i; j--) {
			System.out.println(l.get(j));
			l.set(j+1, l.get(j));
		}

		l.set(i, s);
	}
```

4. 删除
```java
//built-in method
list1.remove(2);
// self-defined method
public static void delete(int i, ArrayList<String> l) {
  if(i < 0 || i > l.size()+1) {
    System.out.println("invalid index");
    return;
  }

  for(int j = i+1; j <= l.size()-1; j++) {
    l.set(j-1, l.get(j));
  }
  l.remove(l.size()-1);
}
```

#### 线性表的链式存储实现
```java
package com.datastructure;

public class MyLinkedList {

	Node head;

	class Node{
		int data;
		Node next;

		Node(int d){
			data = d;
			next = null;
		}
	}

	//length
	public int length(MyLinkedList ll) {
		Node h = ll.head;
		int i = 0;
		while(h != null) {
			h = h.next;
			i++;
		}
		return i;
	}

	//add node at the front
	public void push(int newData) {
		Node newNode = new Node(newData);
		newNode.next = head;
		head = newNode;
	}

	//insertion
	public void insert(Node exist, int newData) {

		// check if existing node is null
		if(exist == null) {
			System.out.println("the given code cannot be null");
			return;
		}


		// put newDate into a new node
		Node newNode = new Node(newData);

		newNode.next = exist.next;

		exist.next = newNode;
	}

	//add node in the end
	public void append(int newData) {
		Node newNode = new Node(newData);

		if(head == null) {
			head = newNode;
			return;
		}

		newNode.next = null;

		Node last = head;
		while(last.next != null) {
			last = last.next;
		}

		last.next = newNode;

	}

	//find by index
	public int findByIndex(int k, MyLinkedList ll) {
		Node temp = ll.head;
		int i = 0;
		while(temp != null && i < k) {
			temp = temp.next;
			i++;
		}
		if(i == k) {
			return temp.data;
		}else return (Integer) null;
	}

	//find by value
	public int findByValue(int d, MyLinkedList ll) {
		Node temp = ll.head;
		int i = 0;
		while(temp != null && temp.data != d) {
			temp = temp.next;
			i++;
		}
		return i;
	}

	//delete
	public void delete(int i) {
		Node temp = head, prev = null;

		if(temp != null && temp.data == i) {
			head = temp.next;
			return;
		}

		while(temp != null && temp.data != i) {
			prev = temp;
			temp = temp.next;
		}

		if(temp == null) {
			return;
		}

		if(temp.data == i) {
			prev.next = temp.next;
		}
	}

}

```




