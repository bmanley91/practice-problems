# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->
This problem has a similar general outline to other string processing problems like [Decode String](https://leetcode.com/problems/decode-string/description/).
We need to process substrings as subproblems while processing the full formula. This problem requires processing substrings which can contain their own substrings.
Because of this, recursion immediately comes to mind for our high-level approach.
Recursively processing the string may become tricky though - we need to carefully keep track of what characters we've processed so that we don't double count or miss counting anything.

# Approach
<!-- Describe your approach to solving the problem. -->
The bulk of the work for this problem is efficiently and accurately counting elements. Once we've done that, we just need to properly format an output string.

```java
public String countOfAtoms(String formula) {
    // Recursively build a map of elements and their counts
    Map<String, Integer> elementCounts = processString(formula);

    // Build output
    return buildOutputString(elementCounts);
}
```

## Recursive String Processing
First we need to define our recursive function. Since we need to keep track of how far along in the input string we are as we make multiple recursive calls, 
we can define a class variable `index` so that we don't need to worry about passing it into and returning it from recursive calls. This means our recursive function only needs the input string as a parameter. We want to keep track of counts for each element, so a Map comes to mind. Since we need to output the elements in sorted order, we can use a `TreeMap` which will keep the keys (element names) in order for us.
```java
int index = 0;
private Map<String, Integer> processString(String formula) {
    Map<String, Integer> elementMap = new TreeMap();
}
```

Next we need to handle three possible cases for the start of our string, open parenthesis, close parenthesis, and capital letter.

### Capital Letter Case
The simplest case is that we have an element to process. We can use helper functions to advance the shared pointer and parse out the element and its count.
```java
// Helper function to parse element names
// Continuously advances the shared pointer
// Returns the element name (e.g. Hg, H, Ubb)
private String getElement(String formula) {
    StringBuilder sb = new StringBuilder();
    sb.append(formula.charAt(index));
    index++;
    while(
        index < formula.length() &&
        formula.charAt(index) >= 'a' &&
        formula.charAt(index) <= 'z'
    ) {
        sb.append(formula.charAt(index));
        index++;
    }
    return sb.toString();
}

// Helper function to parse a number from the formula
// Meant to be called after reaching the end of a given element or set of parens
// Advances the shared pointer until it reaches a number that is not a digit
// If there are no digits to be processed (e.g. first char is paren/letter or we reached the end of the string)
/// this function returns 0, and the shared pointer does not move
private int getCount(String formula) {
    StringBuilder sb = new StringBuilder();
    while (
        index < formula.length() &&
        formula.charAt(index) >= '0' &&
        formula.charAt(index) <= '9'
    ) {
        sb.append(formula.charAt(index));
        index++;
    }
    if (sb.length() == 0) {
        return 1;
    }

    return Integer.valueOf(sb.toString());
}
```

These functions will help keep our main recursive function clean and easy to follow. We can use their outputs to update the element count map that we're building.

```java
while (index < formula.length()) {
    // ...
    // Case: Default - This will always be the start of a new element
    else {
        String elementName = getElement(formula);

        // After getting the element name, the shared pointer is at the char after the element.
        // We need to determine if there's a number paired with this element
        int elementCount = getCount(formula);

        // Put the element and its count into the map
        elementMap.put(
            elementName,
            elementMap.getOrDefault(elementName, 0) + elementCount
        );
    }
}
```

### Open Parenthesis Case
If we reach a `(` then we know we need to process the substring inside.
So we increment the pointer so that we don't double process the `(`. Then we recursively call `processString`.
Once the substring is processed, we need to merge it with the map we're currently building.
```java
while (index < formula.length()) {
    // Case: Open Paren - start new recursion
    if (formula.charAt(index) == '(') {
        // Advance pointer so we don't double process the '('
        index++;
        // Get the element map for the substring and merge it with the parent we are currently processing
        Map<String, Integer> subMap = processString(formula);

        // Merge maps
        subMap.forEach(
            (key, value) -> elementMap.merge(key, value,
                (v1, v2) -> v1 + v2
            )
        );
    }
    // Other cases...
}
```

### Close Parenthesis Case
If we reach a `)`, then we've completed the current substring. Before we return, we need to check if there is a number after this substring. If there is, we need to multiply the counts in our map before returning. To do so, we can use the `getCount` helper function that we created earlier.

```java
while (index < formula.length()) {
    // ...
    // Case: Close Paren - figure out if there's a multiplier to apply and finish this recursion
    else if (formula.charAt(index) == ')') {
        // Advance pointer so we don't look at this ')' again
        index++;
        int multiplier = getCount(formula);
        if (multiplier != 1) {
            for (String element : elementMap.keySet()) {
                elementMap.put(element, elementMap.get(element) * multiplier);
            }
        }
        return elementMap;
    }
    //...
}
```

## Build Output
Once we have the element count map properly built, we need to put together our output string. Since the map we've built is sorted by key, we don't need to worry about specifically sorting while building the output. The only thing we have to look out for is if an element has a count of 1. We do _not_ want to add the count to the output string in this cases.

```java
private String buildOutputString(Map<String, Integer> elementCounts) {
    // We're using a TreeMap, so we can just process keys in order
    StringBuilder sb = new StringBuilder();
    for (String element : elementCounts.keySet()) {
        sb.append(element);
        if (elementCounts.get(element) > 1) {
            sb.append(elementCounts.get(element));
        }
    }
    return sb.toString();
}
```

# Complexity
- ⏰ Time complexity:
    - If the string is straightforward and does not contain many nested sub problems, then we have a best case of `O(n)`.
    - For each sub problem, we look at each unique element if we have to apply a multiplier. In the worst case scenario where we have an input like `((((ABCDEF)2)3)4)5` we need to process all of the inner elements times the number of parentheses pairs. This isn't exactly <code>O(n<sup>2</sup>)</code>, but it's around that.
$$
(elementCount)*(subProblemCount) \approx O(n^2)
$$


- 💾 Space complexity:
    - In the best case scenario, we build a map containing each unique element and use that to build a string with each unique element. This requires ~ `O(n)` space.
    - For each sub problem, we build another map. So similarly to the time complexity calculation the space required is based on the number of unique elements and the number of sub problems that we have to process. This also isn't exactly <code>O(n<sup>2</sup>)</code>, but it's the closest simple approximation.

# Code
```java
class Solution {
    int index = 0;
    public String countOfAtoms(String formula) {
        Map<String, Integer> elementCounts = processString(formula);
        return buildOutputString(elementCounts);
    }
    private Map<String, Integer> processString(String formula) {
        Map<String, Integer> elementMap = new TreeMap();
        while (index < formula.length()) {
            if (formula.charAt(index) == '(') {
                index++;
                Map<String, Integer> subMap = processString(formula);
                subMap.forEach(
                    (key, value) -> elementMap.merge(key, value,
                        (v1, v2) -> v1 + v2
                    )
                );
            } else if (formula.charAt(index) == ')') {
                index++;
                int multiplier = getCount(formula);
                if (multiplier != 1) {
                    for (String element : elementMap.keySet()) {
                        elementMap.put(element, elementMap.get(element) * multiplier);
                    }
                }
                return elementMap;
            } else {
                String elementName = getElement(formula);
                int elementCount = getCount(formula);
                elementMap.put(
                    elementName,
                    elementMap.getOrDefault(elementName, 0) + elementCount
                );
            }
        }
        return elementMap;
    }
    private String getElement(String formula) {
        StringBuilder sb = new StringBuilder();
        sb.append(formula.charAt(index));
        index++;
        while(
            index < formula.length() &&
            formula.charAt(index) >= 'a' &&
            formula.charAt(index) <= 'z'
        ) {
            sb.append(formula.charAt(index));
            index++;
        }
        return sb.toString();
    }
    private int getCount(String formula) {
        StringBuilder sb = new StringBuilder();
        while (
            index < formula.length() &&
            formula.charAt(index) >= '0' &&
            formula.charAt(index) <= '9'
        ) {
            sb.append(formula.charAt(index));
            index++;
        }
        if (sb.length() == 0) {
            return 1;
        }
        return Integer.valueOf(sb.toString());
    }
    private String buildOutputString(Map<String, Integer> elementCounts) {
        StringBuilder sb = new StringBuilder();
        for (String element : elementCounts.keySet()) {
            sb.append(element);
            if (elementCounts.get(element) > 1) {
                sb.append(elementCounts.get(element));
            }
        }
        return sb.toString();
    }
}
```
