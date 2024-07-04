class Solution {
    public long minimumHealth(int[] damage, int armor) {
        // Loop through array and keep track of highest number up to the armor value
        // If we ever hit a damage value that is exactly the armor value, 
        // then we've found an optimal place to use armor.
        // If we do not have a damage value that matches our armor value,
        // then we should use the armor on the highest.

        // Either way, sum up all damage values,
        // then based on if any single damage value was equal to or over the armor value:
        // true -> subratct armor value from total damage
        // false -> subtract highest damage value from total damage

        // Possible short circuit: if armor is 0, just sum up damage.

        // We must have at least 1 health.
        long healthRequired = 1;
        boolean willUseFullArmor = false;
        int highestDamageUnderArmor = 0;

        for (int i = 0; i < damage.length; i++) {
            healthRequired += damage[i];

            if (!willUseFullArmor) {
                if (damage[i] >= armor) {
                    willUseFullArmor = true;
                } else {
                    highestDamageUnderArmor = Math.max(damage[i], highestDamageUnderArmor);
                }
            }
        }

        if (willUseFullArmor) {
            healthRequired -= armor;
        } else {
            healthRequired -= highestDamageUnderArmor;
        }

        return healthRequired;
    }
}
