###ArrayList
####实现原理
1. 基于Object[]实现,数组默认容量为 DEFAULT_CAPACITY = 10 ，size表示数组中元素数量
2. 数组容量不足时会动态扩充,扩充值为: size>>1 ，即 原容量的1.5倍

####常用方法
1. - public ArrayList(int initialCapacity) 指定容量
   - public ArrayList() 默认容量
   - public ArrayList(Collection<? extends E> c) 复制参数中的所有元素到数组中
2. public void trimToSize() 缩小数组容量到size
```java
public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
              ? EMPTY_ELEMENTDATA
              : Arrays.copyOf(elementData, size);
        }
    }
```
3. public void ensureCapacity(int minCapacity) 重新设置数组容量
```java
   private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
   
    private void grow(int minCapacity) {
            // overflow-conscious code
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);
            // minCapacity is usually close to size, so this is a win:
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

```
4. public boolean contains(Object o) 是否包含
5. public int indexOf(Object o) 参数在数组中的下标
```java
   public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
```
6. public int lastIndexOf(Object o) 参数在数组中的最后一个下标,方法同上,遍历从后向前
7. public Object clone() 复制
8. public Object[] toArray() 转成数组
9. public <T> T[] toArray(T[] a) 所有元素复制到目标数组中
10. public E get(int index) 获取元素
11. public E set(int index, E element) 将目标元素设置到数组中，返回原元素
12. public boolean add(E e) 添加元素到size+1处,如果容量不足,将数组容量扩展到size+size>>1
13. public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) 拷贝
14. public void add(int index, E element) 添加元素 
15. public E remove(int index) 删除元素,如需要，将后面的元素前移
16. public boolean remove(Object o) 删除元素
17. public void clear() 清空所有元素
18. public boolean addAll(Collection<? extends E> c) 添加所有元素
19. public boolean addAll(int index, Collection<? extends E> c) 添加目标集合中所有元素,从index开始
20. public ListIterator<E> listIterator(int index) 获取迭代器,光标从index开始
21. public ListIterator<E> listIterator() 获取迭代器
22. public boolean removeIf(Predicate<? super E> filter) 如果符合规则就删除
23. public void sort(Comparator<? super E> c) 排序
24. public List<E> subList(int fromIndex, int toIndex) 获取子集

###LinkedList
####实现原理
1. 基于双向链表实现，链表中每个节点，都持有上个节点和下个节点的引用。
```java

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```
2. public LinkedList() public LinkedList(Collection<? extends E> c) 

####常用方法 
1. public E getFirst() 获取首个节点
2. public E getLast() 获取最后节点
3. public E removeFirst() 删除首个节点，第二个节点（如果有）变成first
4. public E removeLast() 删除末尾节点，倒数第二个节点（如果有）变成last
5. public void addFirst(E e) 添加节点在链首
6. public void addLast(E e) 添加节点在末尾
7. public boolean contains(Object o)
8. public boolean add(E e) 添加元素
9. public boolean remove(Object o) 删除元素
10. public boolean addAll(Collection<? extends E> c) 
11. public void clear() 清空
12. public E get(int index) 获取元素
```java
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
```
13. public E set(int index, E element) 修改元素的值
```java
    public E set(int index, E element) {
            checkElementIndex(index);
            Node<E> x = node(index);
            E oldVal = x.item;
            x.item = element;
            return oldVal;
        }
```

14. public void add(int index, E element) 插入元素
```java

    public void add(int index, E element) {
            checkPositionIndex(index);
    
            if (index == size)
                linkLast(element);
            else
                linkBefore(element, node(index));
        }

```

15. public E remove(int index) 删除元素
16. public int indexOf(Object o) 元素所在位置
17. public int lastIndexOf(Object o) 元素最后一次出现的位置
18. public E peek() 获取first ,同 getFirst方法的区别是list为空不报异常
19. public E element() 获取first，同 getFirst方法一样
20. public E poll() 返回first并移除
21. public E remove() 删除first
22. public boolean offer(E e) 同add
23. public boolean offerFirst(E e) 同addFirst
24. public E peekFirst() 同peek
25. public E peekLast() 同getLast
26. public E pollFirst() 同peek
27. public E pollLast() 
28. public void push(E e) 同addFirst
29. public E pop() 同removeFirst
30. public Object[] toArray()