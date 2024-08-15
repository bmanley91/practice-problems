class Solution {
    public boolean lemonadeChange(int[] bills) {
        // Keep track of bills used to make change
        int fives = 0;
        int tens = 0;

        // Process each bill in order and make change appropriately
        // If at any point we do not have a bill on hand to make change, return false
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    // We don't have to make change for 5 so just add to register                    
                    fives++;
                    break;
                
                case 10:
                    // When a customer pays with 10, we need to give them back 5
                    // If we can't do that, return false
                    if (fives < 1) {
                        return false;
                    }
                    fives--;
                    
                    // Once we've made change, add the new 10 to the register
                    tens++;

                    break;

                case 20:
                    // If a customer gives us a 20, there are two ways that we can make change - three 5s or one 10 + one 5.
                    // Since 5s are more valuable, we want to try using 10 + 5 first
                    // We never actually use 20s to make change so we don't need to keep track of them

                    if (tens > 0 && fives > 0) {
                        tens--;
                        fives--;
                        
                    } else if (fives >= 3) {
                        fives -= 3;
                    } else {
                        return false;
                    }

                    break;
            
                default:
                    // Invalid input
                    return false;
            }
        }

        // If we reach the end of the input, then we've made change for all customers
        return true;
    }
}