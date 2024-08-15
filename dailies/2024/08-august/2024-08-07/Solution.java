import java.util.ArrayList;
import java.util.List;
import static java.util.Map.entry;

// Max: 2,147,483,647
class Solution {
    // Map to know what to append for each bucket
    Map<Integer, String> blockTranslation = Map.ofEntries(
        entry(0, ""),
        entry(1, "Thousand"),
        entry(2, "Million"),
        entry(3, "Billion")
    );

    // Basic digit to word case
    Map<Character, String> digitTranslation = Map.ofEntries(
        entry('0', ""),
        entry('1', "One"),
        entry('2', "Two"),
        entry('3', "Three"),
        entry('4', "Four"),
        entry('5', "Five"),
        entry('6', "Six"),
        entry('7', "Seven"),
        entry('8', "Eight"),
        entry('9', "Nine")
    );

    // Map of tens digits to their names
    Map<Character, String> tensTranslation = Map.ofEntries(
        entry('0', ""),
        entry('2', "Twenty"),
        entry('3', "Thirty"),
        entry('4', "Forty"),
        entry('5', "Fifty"),
        entry('6', "Sixty"),
        entry('7', "Seventy"),
        entry('8', "Eighty"),
        entry('9', "Ninety")
    );

    // Special case mapping for 1 in the tens position
    Map<Character, String> teensTranslation = Map.ofEntries(
        entry('0', "Ten"),
        entry('1', "Eleven"),
        entry('2', "Twelve"),
        entry('3', "Thirteen"),
        entry('4', "Fourteen"),
        entry('5', "Fifteen"),
        entry('6', "Sixteen"),
        entry('7', "Seventeen"),
        entry('8', "Eighteen"),
        entry('9', "Nineteen")
    );

    public String numberToWords(int num) {
        // This one just sucks
        // A bunch of mapping and accounting for edge cases
        // Get digits in batches of three starting from ones
        // Translate blocks into strings
        // 1234567 -> 1, 234, 567
        // (ones/hundreds) 567 -> seven (ones), sixty (tens), five (hundreds)
        //      five hundred sixty seven
        // (thousands) 234 -> four (ones), thirty (tens), two (hundred)
        //      two hundred thirty four thousand
        // (millions) 1 -> one
        //      one million

        // Special case for zero
        if (num == 0) return "Zero";
        
        // Split into block
        String[] blocks = createBlocks(num);

        // For each block build an output string
        StringBuilder sb = new StringBuilder();
        for (int i = blocks.length - 1; i >= 0; i--) {
            String converted = convertToWord(blocks[i], i);
            sb.append(converted);
            if (converted.length() > 0) {
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

    // Split number into reverse-ordered blocks of three chars
    private String[] createBlocks(int num) {
        StringBuilder sb = new StringBuilder(Integer.toString(num));
        
        // Revese so that we can start chunking from the beginning
        sb.reverse();

        return sb.toString().split("(?<=\\G.{3})");
    }

    private String convertToWord(String input, int block) {
        List<String> wordList = new ArrayList<String>();
        // Third digit is "hundreds" and doesn't need special handling
        if (input.length() == 3) {
            addToList(
                digitTranslation.get(input.charAt(2)),
                wordList
            );
            if (wordList.size() > 0) {
                wordList.add("Hundred");
            }
        }

        // If we have two or more digits handle them together so that we can capture the teen case
        if (input.length() >= 2) {
            // Teens case
            if (input.charAt(1) == '1') {
                addToList(
                    teensTranslation.get(input.charAt(0)),
                    wordList
                );
            } else {
                addToList(
                    tensTranslation.get(input.charAt(1)),
                    wordList
                );
                addToList(
                    digitTranslation.get(input.charAt(0)),
                    wordList
                );
            }
        }

        // If we have exactly one digit, just handle it by itself
        else if (input.length() == 1) {
            wordList.add(digitTranslation.get(input.charAt(0)));
        }

        // Only add the block's magnitude if there's numbers in this block
        if (wordList.size() != 0) {
            wordList.add(blockTranslation.get(block));
        }

        // Todo - actually format
        return String.join(" ", wordList).trim();
    }

    private void addToList(String s, List<String> list) {
        if (s != null && s.length() > 0) {
            list.add(s);
        }
    }
}