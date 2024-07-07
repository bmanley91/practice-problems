import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // Sort intervals by end time
        // Find the maximum number of non-overlapping meetings
        // At each time, take the meeting with the shortest duration
        // Then the new earliest start time becomes the end time of that meeting
        int n = intervals.length;

        // Sort the meetings end time asc
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        // We start by attending the meeting with the lowest end time
        int previousMeeting = 0;
        int meetingCount = 1;

        for (int i = 1; i < n; i++) {
            // If the current meeting's start time is >= the end time of the last one we attened,
            /// then we can attend this meeting
            /// It becomes the new previousMeeting and we increment our meeting count
            if (intervals[i][0] >= intervals[previousMeeting][1]) {
                meetingCount++;
                previousMeeting = i;
            }
        }

        // We actually want to return the number of meetings we _don't_ attend
        // So return the difference.
        return intervals.length - meetingCount;
    }
}
