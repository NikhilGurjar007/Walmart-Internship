import java.util.Arrays;
import java.util.NoSuchElementException;

public class PowerHeap {
    private double x;        // Exponent value
    private int size;         // Current size of the heap
    private int[] heapArray;  // Array to store the heap elements

    // Constructor
    public PowerHeap(double x, int capacity) {
        this.size = 0;
        heapArray = new int[capacity + 1];  // The heap is 1-indexed, so we add 1 to the capacity
        this.x = x;
        Arrays.fill(heapArray, -1);  // Initialize the heapArray with -1
    }

    // Function to get the parent index of a given index
    private int parent(int i) {
        return (int) ((i - 1) / Math.pow(2, x));
    }

    // Check if the heap is full
    public boolean isFull() {
        return size == heapArray.length;
    }

    // Insert a new element into the heap
    public void insert(int value) {
        if (isFull()) {
            throw new NoSuchElementException("Heap is full, no space to insert new element.");
        } else {
            heapArray[size++] = value;
            heapifyUp(size - 1);
        }
    }

    // Maintain heap property by moving the newly added element up
    private void heapifyUp(int i) {
        int tmp = heapArray[i];
        while (i > 0 && tmp > heapArray[parent(i)]) {
            heapArray[i] = heapArray[parent(i)];
            i = parent(i);
        }
        heapArray[i] = tmp;
    }

    // Remove and return the maximum element from the heap
    public int popMax() {
        int maxItem = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        heapArray[size - 1] = -1;
        size--;

        int i = 0;
        while (i < size - 1) {
            heapifyUp(i);
            i++;
        }

        return maxItem;
    }

    // Print the elements of the heap
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i]);
            System.out.print(',');
        }
        System.out.println();
    }

    // Main method for testing the PowerHeap class
    public static void main(String[] args) {
        double x = 2;          // Example value for x
        int capacity = 10;     // Example capacity

        PowerHeap heap = new PowerHeap(x, capacity);
        heap.insert(5);
        heap.insert(10);
        heap.insert(3);

        heap.print();

        int maxItem = heap.popMax();
        System.out.println("Max item: " + maxItem);

        heap.print();
    }
}
