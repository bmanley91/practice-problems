# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->
We can think of recent calls kind of like a sliding window. Rather than having a specific size, our window is bound by the _values_ of each element. Every time a new element is added, we want to remove old elements that have fallen out of the window. Since inputs are guaranteed to be given in increasing order, a queue naturally makes sense to model our window.

# Approach
<!-- Describe your approach to solving the problem. -->
First, we need to define and initialize the queue to model our window. We don't need any particularly specific queue implementation so we can use `LinkedList`.
```java
class RecentCounter {
    private Queue<Integer> previousCalls;

    public RecentCounter() {
        this.previousCalls = new LinkedList<Integer>();
    }
}
```

Next, we need to implement the `ping` function which takes in time `t` and returns the number of calls within the window.

Since we want to count the current call, add it to the queue first. Then, poll any elements from the front of the queue which are outside of the window (less than `t - 3000`). Note: We don't need to worry about an empty queue since `t` itself can't be outside of the window.

Once the window has been adjusted, we know that all of the calls within 3000 of t are in the queue. Return the queue's size.

```java
// ...
public int ping(int t) {
    previousCalls.add(t);

    while(previousCalls.peek() < t - 3000) {
        previousCalls.poll();
    }
    return previousCalls.size();
}

```


# Complexity
- ⏰ Time complexity:
    - Initialization of `RecentCounter` takes O(1) time since we are just creating an empty queue.
    - `ping(t)` takes up to `O(n)` time where `n` is all previous calls. Worst case scenario we need to remove all previous calls for inputs like `[1,2,3,200,5000]`. Best case scenario, we never actually need to remove anything from the window and work in `O(1)` time.
<!-- Add your time complexity here, e.g. $$O(n)$$ -->

- 💾 Space complexity: Overall, we use up to `O(n)` space where `n` is all of the provided calls. If we never need to remove any calls from the window, then the queue will grow by one for each new `t` provided.
<!-- Add your space complexity here, e.g. $$O(n)$$ -->

# Code
```
class RecentCounter {
    private Queue<Integer> previousCalls;

    public RecentCounter() {
        this.previousCalls = new LinkedList();
    }
    
    public int ping(int t) {
        previousCalls.add(t);

        while(previousCalls.peek() < t - 3000) {
            previousCalls.poll();
        }
        return previousCalls.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```
