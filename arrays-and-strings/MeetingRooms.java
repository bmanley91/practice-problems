class Solution {
    // Sort meetings by start time ascending
    // Then iterate through the meetings
    // Check to see if the end time for the current meeting is after the start time of the next one.
    // If so, then we return false.
    // If we get to the end of the meeting array without issue, then we return true
    public boolean canAttendMeetings(int[][] intervals) {
        final int START = 0;
        final int END = 1;
        
        // Sort meetings by start time ascending
        Arrays.sort(
            intervals,
            new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[START] - b[START];
                }
            }
        );
        
        // Then iterate through the meetings
        // We only want to go up to the second to last meeting 
        // since the last meeting's end time doesn't really matter.
        for(int i = 0; i < intervals.length - 1; i++) {
            // Check to see if the end time for the current meeting is after the start time of the next one.
            if (intervals[i][END] > intervals[i+1][START]) {
                // If so, then we return false.
                return false;
            }
        }
        // If we get to the end of the meeting array without issue, then we return true
        return true;
    }
}