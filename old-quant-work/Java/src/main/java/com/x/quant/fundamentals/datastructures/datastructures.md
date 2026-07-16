# Data Structures
Used for organising, processing, retrieving, and storing data.

## Iterable
An iterable is the base of Lists, Queues, and Sets in Java.
A collection extends an iterable.
List Queue Set extend from a collection.

Implements: e.g. A class can implement an interface, which will define a set of variables 
and methods that the class must implement.
Extends: e.g. A class that extends a class gains all the methods and variables from 
the parent class. These can then be overridden or have new classes added.

## Lists
ArrayList - LinkedList - Vector.
All implement the List Collection.

##### ArrayList
- Dynamically resizable. Don't need to specify size during initialisation. This is relatively
memory efficient.
- Accepts a generic that defines the data type of the elements stored in the ArrayList
- Order of elements is maintained from the order they are added in.
- Allows for null elements.
- Implements List interface methods like add, remove, get, indexOf, and more.
- ArrayList is not synchronized, meaning it is not thread-safe.

```java
ArrayList<int> cryptocurrencies = new ArrayList<>();
```

##### LinkedList
- Dynamic sizing, can grow or shrink on demand. Memory efficient.
- Each element in a list contains data and a reference to the previous/next node.
- Faster insertion/deletion vs. ArrayList as only one element and references to that
element need to be replaced, rather than shifting multiple elements.
- Higher memory overhead vs. ArrayList as need to store the references.
- Not synchronized, meaning it is not thread-safe.

```java
LinkedList<int> cryptocurrencies = new LinkedList<>();
```

##### Vector & Stack
- Not typically used in modern Java, legacy DS.

##### Queue
- Follows FIFO (first in first out).
- Supports Enqueue (Adding) and Dequeue (Removing)
- LinkedBlockingQueue useful for concurrent programming

###### PriorityQueue
* PriorityQueue is an implementation of the Queue interface but adds the concept of priorities.
* Elements are ordered based on their natural order or a specified comparator during insertion.
* The element with the highest priority (smallest according to the specified ordering) is the first to be removed.

##### Deque (Double ended Queue)
- Able to add/remove elements to/from both ends of the queue
- Uses stack and Queue methods

###### ArrayDeque
ArrayDeque is a specific implementation of the Deque interface.
Internally, ArrayDeque uses a dynamically resizable array to store elements.
It provides constant time for adding or removing elements from both ends, making it a good choice for general-purpose deque operations.

#### Set
Extends the collection class.
- All elements are unique
- No specific order to a set. Therefore, no indexing to access elements.
- Several implementation methods.
- Typically, do not allow for null values.

##### SortedSet
- Ordered Set
- Has head and tail operations

##### HashSet
- Uses a Hash Table for storage.
- No order guarantee or duplicates.
- Fast operations.

##### LinkedHashSet
- Ordered, backed by linkedlist, no duplicates.
- Hybrid between HashSet and LinkedList.