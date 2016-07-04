
/*
Remember that you can always turn a max heap into a min heap by inserting negated values 
and re-negating the values after they are removed from the heap
*/

interface IPQueue <T> {
  
  public boolean contains(T elem);
  public boolean remove(T elem);
  public boolean isEmpty();
  public void clear();
  public void add(T elem);
  public int size();
  public T peek();
  public T poll();

}

class PQueue <T extends Comparable<T> > implements IPQueue <T> {

  int heap_size = 0;
  Array <T> heap = null;

  public PQueue () {
    heap = new Array<>();
  }

  public PQueue(int sz) {
    heap_size = sz;
    heap = new Array<>(sz);
  }

  // Heapify 
  public PQueue (T[] elems) {
    this(elems.length);
    for (int i = 0; i < elems.length; i++ )
      add(elems[i]);
  }

  public boolean isEmpty() {
    return heap_size == 0;
  }

  public boolean remove(T elem) {
    heap_size--;
    return false;
  }

  public void clear() {
    heap = new Array<>();
    heap_size = 0;
  }
  public int size() {
    return heap_size;
  }

  // Tests if an element is in the heap in O(log(n)) time
  public boolean contains(T elem) {
    int k = 0;
    while(2*k < heap_size) {
      
      // Found element
      if ( heap.get(k).equals(elem) )
        return true;
      
      // Dig into heap
      int j = 2*k;
      if (j < heap_size && less(j, j+1)) j++;
      swap(k, j);
      k = j;

    }
    return heap.get(k).equals(elem);
  }

  public void add(T elem) {
    heap.add(elem);
    swim(heap_size++); // bubble up element
  }

  public T peek() {
    if (isEmpty())
      throw new IllegalStateException("Priority queue is empty, cannot peek");
    return heap.get(0);
  }

  public T poll() {
    if (!isEmpty()) {
      T root = heap.get(0);
      swap(0, heap_size--);
      heap.set(heap_size+1, null);
      sink(0); // Restore heap property
      return root;
    } else throw new IllegalStateException("Priority queue is empty, cannot poll");
  }

  // Swap Two nodes 
  private void swap(int i, int j) {
    T tmp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, tmp);
  }

  // Test if node i < node j
  private boolean less(int i, int j) {

    // Assume child1 & child2 are not null
    T child1 = heap.get(i);
    T child2 = heap.get(j);

    return child1.compareTo(child2) < 0;
    
  }

  // Bottom up re-heapify 
  private void swim(int k) {
    while(k > 0 && less(k/2, k)) {
      swap(k/2, k);
      k /= 2;
    }
  }

  // Top down re-heapify
  private void sink(int k) {
    while(2*k < heap_size) {
      int j = 2*k;
      if (j < heap_size && less(j, j+1)) j++;
      swap(k, j);
      k = j;
    }
  }

}














