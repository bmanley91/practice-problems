class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int[] maxFlowers = new int[flowerbed.length];
        
        maxFlowers[0] = canPlaceAtIndex(flowerbed, 0) ? 1 : 0;

        if (flowerbed.length == 1) {
            return maxFlowers[0] >= n;
        }

        maxFlowers[1] = canPlaceAtIndex(flowerbed, 1) ? 1 : 0;

        int index = 2;
        while (index < flowerbed.length) {
            maxFlowers[index] = maxFlowers[index-2] + (canPlaceAtIndex(flowerbed, index) ? 1 : 0);
            if (maxFlowers[index] >= n) {
                return true;
            }
            index++;
        }
        return maxFlowers[flowerbed.length-1] >= n;
    }

    private boolean canPlaceAtIndex(int[] flowerbed, int index) {
        if (flowerbed[index] == 1) {
            return false;
        } else if (
            index == 0 &&
            index == flowerbed.length - 1
        ) {
            return true;
        } else if (
            index == 0
        ) {
            return flowerbed[index+1] == 0;
        } else if (
            index == flowerbed.length - 1
        ) {
            return flowerbed[index-1] == 0;
        } else {
            return flowerbed[index-1] == 0 && flowerbed[index+1] == 0;
        }
    }
}
