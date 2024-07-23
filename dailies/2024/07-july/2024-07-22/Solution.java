import java.util.PriorityQueue;

class Solution {
    // Create a Person class to combine height and name into a sortable object
    class Person implements Comparable<Person>{
        int height;
        String name;

        public Person(int height, String name) {
            this.height = height;
            this.name = name;
        }

        public int compareTo(Person other) {
            return Integer.compare(other.height, this.height);
        }
    }

    public String[] sortPeople(String[] names, int[] heights) {
        // Create a Person object for each pair of names and heights
        // Put the Person into a max heap
        PriorityQueue<Person> maxHeap = new PriorityQueue<Person>();
        for (int i = 0; i < names.length; i++) {
            maxHeap.add(new Person(heights[i], names[i]));
        }

        // Poll from the heap and add the name of each Person to the output
        // This will give us an array of names starting with the tallest
        String[] output = new String[names.length];
        int index = 0;
        while(!maxHeap.isEmpty()) {
            output[index] = maxHeap.poll().name;
            index++;
        }

        return output;
    }
}
