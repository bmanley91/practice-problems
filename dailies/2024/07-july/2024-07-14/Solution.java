class Solution {
    int index = 0;

    public String countOfAtoms(String formula) {
        // Recursively build a map of elements and their counts
        Map<String, Integer> elementCounts = processString(formula);

        // Build output
        return buildOutputString(elementCounts);
    }

    private Map<String, Integer> processString(String formula) {
        Map<String, Integer> elementMap = new TreeMap();
        
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

        return elementMap;
    }

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
}
