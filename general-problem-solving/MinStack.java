class MinStack {
    Stack<MinStackNode> elements;

    /** initialize your data structure here. */
    public MinStack() {
        elements = new Stack<MinStackNode>();
    }
    
    public void push(int x) {
        // Before we push a new MinStackNode onto our stack,
        // we need to see if the new number is the new min.
        int min;
        if (!this.elements.isEmpty()) {
            min = x < this.elements.peek().min ? 
                x :
                this.elements.peek().min;
        } else {
            min = x;
        }
        this.elements.push(new MinStackNode(x, min));
    }
    
    public void pop() {
        // Remove the top node from the stack and return it's value.
        // Since the next node on the stack has a min stored on it, no extra calculation is needed.
        this.elements.pop();
    }
    
    public int top() {
        return this.elements.peek().value;
    }
    
    public int getMin() {
        return this.elements.peek().min;
    }
    
    // An object that keeps track of the value at a given place in the stack,
    // and what the current minimum is.
    // This structure lets us keep track of what the min was when an element was added to the stack.
    class MinStackNode {
        public int value;
        public int min;
        
        MinStackNode(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */