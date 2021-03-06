package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("InfiniteLoopStatement")
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class.getName());

    private static final String ONE = "1";
    private static final String ZERO = "0";
    private static final String DOUBLE_ZERO = "00";

    public static void main(String[] args) {
        System.out.println("IMPORTANT: Allowed only number and sequence should be up to a three digit number");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                Set<String> result = new HashSet<>();
                System.out.print("Enter numbers: ");
                String[] input = reader.readLine().trim().split("\\s+");
                if (checkNumber(input)) {
                    calculateAmbiguities("", input, result);
                    validation(result);
                } else {
                    LOGGER.warn("ERROR: Allowed only number and sequence should be up to a three digit number");
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Check that the input are numbers and sequence up to a three digit number
     *
     * @param numbers array with splited phone number
     * @return boolean result
     */
    private static boolean checkNumber(String[] numbers) {
        return Arrays.stream(numbers).noneMatch(value -> value.length() > 3 || !value.matches("\\d+"));
    }

    /**
     * This function are using for validation and output our results to console
     *
     * @param numbers Set with all possible phone numbers
     */
    private static void validation(Set<String> numbers) {
        numbers.forEach(values -> {
            if ((values.length() == 10 && (values.startsWith("2") || values.startsWith("69"))) ||
                    (values.length() == 14 && (values.startsWith("00302") || values.startsWith("003069")))) {
                System.out.println(values + " - phone number: VALID");
            } else {
                System.out.println(values + " - phone number: INVALID");
            }
        });
    }

    /**
     * Recursive function which calculate all ambiguities in phone number
     *
     * @param ambiguities appended number
     * @param numbers     array of strings
     * @param result      Set with results of calculation
     */
    private static void calculateAmbiguities(String ambiguities, String[] numbers, Set<String> result) {
        //Check that splited number length more than 2
        if (numbers.length > 1 && numbers[0].length() >= 2) {
            if (numbers[0].length() == 3) {
                //Check that number with length 3 end with 00 and the next number length equals 2. Example 700 23, result 723
                if (numbers[0].endsWith(DOUBLE_ZERO) && numbers[1].length() == 2) {
                    calculateAmbiguities(ambiguities + numbers[0] + numbers[1], Arrays.copyOfRange(numbers, 2, numbers.length), result);
                    calculateAmbiguities(ambiguities + numbers[0].charAt(0) + numbers[1], Arrays.copyOfRange(numbers, 2, numbers.length), result);
                    //Check that number with length 3 end with 0 and the next number length equals 1. Example 560 2, result 562
                } else if (numbers[0].endsWith(ZERO) && numbers[1].length() == 1) {
                    calculateAmbiguities(ambiguities + numbers[0] + numbers[1], Arrays.copyOfRange(numbers, 2, numbers.length), result);
                    calculateAmbiguities(ambiguities + numbers[0].charAt(0) + numbers[1], Arrays.copyOfRange(numbers, 2, numbers.length), result);
                    //Check that number with length 3 don't end with 0 and between digital also 0. Example 406, result 46
                } else if (!numbers[0].endsWith(ZERO) && numbers[0].substring(0, 2).endsWith(ZERO)) {
                    calculateAmbiguities(ambiguities + numbers[0], Arrays.copyOfRange(numbers, 1, numbers.length), result);
                    calculateAmbiguities(ambiguities + numbers[0].replace(ZERO, ""), Arrays.copyOfRange(numbers, 1, numbers.length), result);
                    //For other numbers like 745,123 and others, numbers without zero digital
                } else {
                    calculateAmbiguities(ambiguities + numbers[0], Arrays.copyOfRange(numbers, 1, numbers.length), result);
                }
            } else {
                //Check that number with length 2 end with 0 and the next number length equals 1. Example 70 2, result 73
                if (numbers[0].endsWith(ZERO) && numbers[1].length() == 1 && !numbers[0].startsWith(ONE)) {
                    calculateAmbiguities(ambiguities + numbers[0] + numbers[1], Arrays.copyOfRange(numbers, 2, numbers.length), result);
                    calculateAmbiguities(ambiguities + numbers[0].charAt(0) + numbers[1], Arrays.copyOfRange(numbers, 2, numbers.length), result);
                    //Check that number don't end with 0. Example 23, result 203
                } else if (!numbers[0].endsWith(ZERO) && !numbers[0].startsWith(ONE)) {
                    calculateAmbiguities(ambiguities + numbers[0], Arrays.copyOfRange(numbers, 1, numbers.length), result);
                    calculateAmbiguities(ambiguities + new StringBuilder(numbers[0]).insert(1, ZERO), Arrays.copyOfRange(numbers, 1, numbers.length), result);
                    //For other numbers
                } else {
                    calculateAmbiguities(ambiguities + numbers[0], Arrays.copyOfRange(numbers, 1, numbers.length), result);
                }
            }
            //For other digital which number length equals 1. Example 1,2,3 and ect.
        } else if (numbers.length != 0) {
            calculateAmbiguities(ambiguities + numbers[0], Arrays.copyOfRange(numbers, 1, numbers.length), result);
        } else {
            result.add(ambiguities);
        }
    }
}