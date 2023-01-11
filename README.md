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

## 2.2 堆栈
具有一定操作约束的线性表，后入先出（Last In First Out) LIFO

### 栈的顺序存储实现
```java
package com.datastructure;

import java.util.Stack;

public class MyStack {

	private int arr[];
	private int top;
	private int capacity;

	//create stack
	MyStack(int size){
		arr = new int[size];
		capacity = size;
		top = -1;
	}

	//push
	void push(int x) {
		if(top == capacity - 1) {
			System.out.println("Stack Overflow");
		}else
			arr[++top] = x;
	}

	//pop
	int pop() {
		if(top == -1) {
			System.out.println("Stack is empty");
			System.exit(1);
	}
		return arr[top--];
	}

	//print
	void printStack() {
		for(int i=0; i<=top; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {

		MyStack stack = new MyStack(5);
		stack.push(1);
		stack.push(2);
		System.out.println(stack.pop());

		stack.printStack();

	}

}
```

### 实现两个堆栈
```java
package com.datastructure;

public class DStack {

	private int arr[];
	private int capacity;
	private int top1;
	private int top2;

	public DStack(int size) {
		arr = new int[size];
		capacity = size;
		top1 = -1;
		top2 = capacity;

	}

	public void push(int i, int tag) {
		if(top2 - top1 == 1) {
			System.out.println("full");
		}
		if(tag == 1) {
			arr[++top1] = i;
		}
		if(tag == 2) {
			arr[--top2] = i;
		}
	}

	public static void main(String[] args) {
		DStack ds = new DStack(5);
		ds.push(1, 1);
		ds.push(1, 2);
		ds.push(3, 2);
		ds.push(1, 1);
		ds.push(1, 1);
		ds.push(1, 1);


	}

}
```

### 栈的链式存储实现
插入和删除只能在链栈的栈顶实现，栈底可以插入但是无法删除
```java
package com.datastructure;

public class MyLinkedStack {

	Node head;
	int length;

	class Node{
		int data;
		Node next;

		Node(int d){
			data = d;
			next = null;
		}
	}


	public MyLinkedStack() {
		head = null;
		length = 0;
	}

	//push
	public void push(int newData) {
		Node temp = new Node(newData);
		temp.next = head;
		head = temp;
		length++;

	}

	//pop
	public int pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty!");
		}
		int result = head.data;
		head = head.next;
		return result;
	}

	public boolean isEmpty() {
		return (length==0);
	}

}

```

## 队列
队列：具有一定操作约束的线性表  
插入和删除操作：只能在一端插入，另一端删除  
先进先出：FIFO
### 队列的顺序存储实现
通常由一个一维数组和一个记录队列头元素位置的变量front以及记录队列尾元素位置的变量rear组成
```java
package com.datastructure;

public class MyQueue {
	int size;
	int items[];
	int front, rear;

	MyQueue(int size){
		this.size = size;
		front = -1;
		rear = -1;
		this.items = new int[size];
	}

	boolean isFull() {
		return(rear == size - 1);
	}

	boolean isEmpty() {
		return(front == -1);
	}

	void addQueue(int i) {
		if(isFull()) {
			System.out.println("Queue is full!");
			return;
		}else {
			//if the queue is empty, change front to 0 to denote first element of the queue
			if(front == -1) {
				front = 0;
			}
			rear++;
			items[rear] = i;
		}
	}

	int deQueue() {
		if(isEmpty()) {
			System.out.println("Queue is empty!");
			return -1;
		}else {
			int element = items[front];
			if(front == rear) {
				front = -1;
				rear = -1;
			}else front++;

			return element;
		}

	}

	void display() {
		int i;
		if(isEmpty()) {
			System.out.println("Empty queue!");
		}else {
			System.out.println("front index is " + front);
			System.out.println("items are ");
			for(i = front; i <= rear; i++) {
				System.out.println(items[i] + " ");
			}
			System.out.println("rear index is " + rear);
		}
	}

	public static void main(String[] args) {

		MyQueue q = new MyQueue(5);

		q.deQueue();

		q.addQueue(1);

		q.addQueue(2);

		q.deQueue();

		q.addQueue(2);
		q.addQueue(2);
		q.addQueue(2);
		q.deQueue();
		q.deQueue();
		q.deQueue();
		q.deQueue();
		q.display();

	}

}
```

### 队列的链式存储实现
front指向链表的头，rear指向链表的额尾
```java
package com.datastructure;

public class MyLinkedQueue {

	Node front, rear;

	class Node{
		int data;
		Node next;

		Node(int d){
			data = d;
			next = null;
		}
	}

	public MyLinkedQueue() {
		front = null;
		rear = null;
	}

	//insert in the end
	void addQ(int d) {

		Node temp = new Node(d);

		//if queue is empty, front and rear both should be temp
		if(rear == null) {
			front = rear = temp;
			return;
		}else {
			rear.next = temp;
			rear = temp;
		}
	}

	//delete at the front
	int deQ() {
		//if queue is empty, return null
		if(rear == null) {
			System.out.println("empty queue");
			return -1;
		}

		Node temp = front;
		front = front.next;

		if(front == null) {
			rear = null;
			System.out.println("now it is empty");
		}

		return temp.data;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyLinkedQueue q = new MyLinkedQueue();

		q.addQ(3);
		q.addQ(4);
		q.addQ(2);
		q.deQ();
		q.deQ();
		q.deQ();
		//System.out.println("front: " + q.front.data);
		//System.out.println("rear: " + q.rear.data);
	}
}
```

## 多项式加法运算
```java
package com.datastructure;

import java.util.Scanner;

public class PolyAdd2 {

	static class Node{
		int coef;
		int expon;
		Node next;

		Node(int coef, int expon){
			this.coef = coef;
			this.expon = expon;
			next = null;
		}
	}

	static Node polyAdd2(Node p1, Node p2) {

		Node head = new Node(0,0);
		Node result = head;
		Node a = p1, b = p2;

		while(p1!=null || p2!=null) {
			if(p1 == null) {
				result.next = p2;
				break;
			}else if(p2 == null) {
				result.next = p1;
				break;
			}else if(p1.expon > p2.expon) {
				result.next = new Node(p1.coef,p1.expon);
				p1 = p1.next;
			}else if(p1.expon < p2.expon) {
				result.next = new Node(p2.coef,p2.expon);
				p2 = p2.next;
			}else if(p1.expon == p2.expon) {
				result.next = new Node(p1.coef+p2.coef,p1.expon);
				p1 = p1.next;
				p2 = p2.next;
			}
			result = result.next;
		}
		return head.next;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//read information from 1st polynomial
		System.out.println("Please enter the number of terms of p1:");
		int n1 = sc.nextInt();
		int p1_coef[] = new int[n1];
		int p1_expon[] = new int[n1];

		System.out.println("Please enter coefficients:");
		for(int i=0; i<n1; i++) {
			p1_coef[i] = sc.nextInt();
		}

		System.out.println("Please enter exponents:");
		for(int i=0; i<n1; i++) {
			p1_expon[i] = sc.nextInt();
		}




		//store information in linkedlist
		Node front1 = null, front2 = null, rear1 = null, rear2 = null;

		for(int i=0; i<n1; i++) {
			Node ptr = new Node(p1_coef[i],p1_expon[i]);
			if(front1 == null){
				front1 = ptr;
				rear1 = ptr;
			}else {
				rear1.next = ptr;
				rear1 = ptr;
			}
		}

		//System.out.println(front1.coef);

		//read information from 2ed polynomial
		System.out.println("Please enter the number of terms of p2:");
		int n2 = sc.nextInt();
		int p2_coef[] = new int[n2];
		int p2_expon[] = new int[n2];

		System.out.println("Please enter coefficients:");
		for(int i=0; i<n2; i++) {
			p2_coef[i] = sc.nextInt();
		}

		System.out.println("Please enter exponents:");
		for(int i=0; i<n2; i++) {
			p2_expon[i] = sc.nextInt();
		}

		System.out.println("p2 done");

		//sc.close();


		//store information in linkedlist
		for(int i=0; i<n2; i++) {
			Node ptr = new Node(p2_coef[i],p2_expon[i]);
			if(front2 == null){
				front2 = ptr;
				rear2 = ptr;
			}else {
				rear2.next = ptr;
				rear2 = ptr;
			}
		}


		Node sum = polyAdd2(front1,front2);


		//int count = 0;
		while(sum!=null) {
			System.out.println(sum.coef+"x^"+sum.expon);


			while(sum.next!=null) {

				System.out.println(" + ");
				break;

			}
			sum = sum.next;

		}
	}
}

```
## 3.1 树与树的表示
对于任何一棵非空树 (n>0)，它具备以下性质：
- 树中有一个称为“根” (Root)的特殊结点，用r表示
- 其余结点又可分为m (m>0)个互不相交的有限集，其中每个集合又是一棵树，称为原来树的子树 (sub tree)

术语：
- 结点的度degree：结点的子树个数
- 树的度：树的所有结点中最大的度数
- 叶结点leaf：度为0的结点
- 父结点parent：有子树的结点是其子树的根结点的父结点
- 子结点child: 有子树的结点是其子树的根结点的父结点
- 兄弟结点sibling：具有同一父结点的各结点彼此是兄弟结点
- 路径和路径长度：从结点n1到nk的路径为一个结点序列n1 , n2,… , nk
, ni是ni+1的父结点。路径所包含边的个数为路径的长度。
- 祖先结点ancestor：沿树根到某一结点路径上的所有结点都是这个结点的祖先结点
- 子孙结点descendant：某一结点的子树中的所有结点是这个结点的子孙
- 结点的层次level：规定根结点在1层，其它任一结点的层数是其父结点的层数加1
- 树的深度depth：树中所有结点中的最大层次是这棵树的深度

## 二叉树及存储结构
二叉树是由根节点和其称为左子树和右子树的两个不相交的二叉树组成；  
二叉树的子树有左右顺序之分  

特殊二叉树
- 斜二叉树 skewed binary tree
- 完美二叉树perfect binary tree，满二叉树full binary tree
- 完全二叉树complete binary tree

二叉树的几个重要性质
- 一个二叉树第i层的最大结点数为：2^(i-1);
- 深度为k的二叉树有最大结点总数为：2^k - 1;
- n0表示叶结点的个数，n2是度为2的非叶结点的个数，则n0=n2+1;

### 二叉树的存储结构
1. 顺序存储结构（可以用数组来实现）
可以用来存储完全二叉树，因为子结点的个数固定，是2个。  
- 非根结点（序号i>1）的父结点的序号是[i/2]
- 结点（序号为i）的左孩子的结点的序号是2i
- 结点（序号为i）的右孩子的结点的序号是2i+1
一般二叉树也可以采用这种结构，但会造成空间浪费
2. 链表存储结构
每个结点中包括三个filed：left, data, right

```java
package com.datastructure;

public class BinaryTree {

	public class Node{
		int data;
		Node left;
		Node right;

		Node(int data){
			this.data = data;
			left = null;
			right = null;
		}
	}

}
```





