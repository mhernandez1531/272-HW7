/******************************************************************
 *
 *   Mariana Hernandez / COMP 272-002 - Fall 2024
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param ascending     - if true,method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true (or ascending sort).
     */

    public  void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending ) {

        int n = values.length;

        for (int i = 0; i < n - 1; i++) {

            // YOU CODE GOES HERE -- COMPLETE THE INNER LOOP OF THIS
            // "SELECTION SORT" ALGORITHM.
            // DO NOT FORGET TO ADD YOUR NAME / SECTION ABOVE

            // Index variable keeps track of the position of the min/max element
            int index = i;

            // checks every element after 'i' to find the mix/max depending on the order
            for (int j = i + 1; j < n; j++) {

                // when sorting ascending we find min element
                // sorting descending we find max element
                if (ascending ? values[j] < values[index] : values[j] > values[index]) {
                    index = j; // Update  index of the min/max value
                }
            }

            // Swap values at positions 'i' and 'index' to place the smallest/largest element in correct spot
            int temp = values[index]; // Temp var to hold one of the values during the swap
            values[index] = values[i]; // Put the min/max at the front (position i)
            values[i] = temp; // Put  old value in new position
        }
    }

    /**
     *  Method mergeSortDivisibleByKFirst
     *
     *  This method will perform a merge sort algorithm. However, all numbers
     *  that are divisible by the argument 'k', are returned first in the sorted
     *  list. Example:
     *        values = { 10, 3, 25, 8, 6 }, k = 5
     *        Sorted result should be --> { 10, 25, 3, 6, 8 }
     *
     *        values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     *        Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     *
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values    - input array to sort per definition above
     * @param k         - value k, such that all numbers divisible by this value are first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0)  return;
        if (values.length <= 1)  return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length-1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right)
    {
        // YOUR CODE GOES HERE, THIS METHOD IS NO MORE THAN THE STANDARD MERGE PORTION
        // OF A MERGESORT, EXCEPT THE NUMBERS DIVISIBLE BY K MUST GO FIRST WITHIN THE
        // SEQUENCE PER THE DISCUSSION IN THE PROLOGUE ABOVE.
        //
        // NOTE: YOU CAN PROGRAM THIS WITH A SPACE COMPLEXITY OF O(1) OR O(N LOG N).
        // AGAIN, THIS IS REFERRING TO SPACE COMPLEXITY. O(1) IS IN-PLACE, O(N LOG N)
        // ALLOCATES AUXILIARY DATA STRUCTURES (TEMPORARY ARRAYS). IT WILL BE EASIER
        // TO CODE WITH A SPACE COMPLEXITY OF O(N LOG N), WHICH IS FINE FOR PURPOSES
        // OF THIS PROGRAMMING EXERCISES.
        // Temporary arrays for left and right subarrays

        // Create two temp arrays for left and right subarrays
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy data to temporary arrays
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[left + i]; // copy left half
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[mid + 1 + i]; // copy right half
        }

        // Indices for traversing the temporary arrays
        int i = 0, j = 0, kIndex = left;

        // First, merge all numbers divisible by k into the main array
        while (i < leftArr.length && j < rightArr.length) {
            // if left array  number is divisible by k and right array number is not
            if (leftArr[i] % k == 0 && rightArr[j] % k != 0) {
                arr[kIndex++] = leftArr[i++]; // Add left array number to main array
            }
            // if right array number is divisible by k and left array number is not
            else if (rightArr[j] % k == 0 && leftArr[i] % k != 0) {
                arr[kIndex++] = rightArr[j++]; // Add right array number to main array
            }
            // if both numbers are divisible by k, add smaller one to main array
            else if (leftArr[i] % k == 0) {
                arr[kIndex++] = leftArr[i++];
            }
            else if (rightArr[j] % k == 0) {
                arr[kIndex++] = rightArr[j++];
            }
            // if both numbers are not divisible by k, merge them in ascending order
            else if (leftArr[i] <= rightArr[j]) {
                arr[kIndex++] = leftArr[i++]; // Add smaller number from leftArr
            }
            else {
                arr[kIndex++] = rightArr[j++]; // Add smaller number from rightArr
            }
        }

        // if there are remaining elements from leftArray copy them
            while (i < leftArr.length) {
            arr[kIndex++] = leftArr[i++];
        }

        // if there is any copy remaining elements from rightArr
        while (j < rightArr.length) {
            arr[kIndex++] = rightArr[j++];
        }
    }


    /**
     * Method asteroidsDestroyed
     *
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     *
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     *
     * Example 1:
     *   Input: mass = 10, asteroids = [3,9,19,5,21]
     *   Output: true
     *
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     *
     * Example 2:
     *   Input: mass = 5, asteroids = [4,9,23,4]
     *   Output: false
     *
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     *
     * Constraints:
     *     1 <= mass <= 105
     *     1 <= asteroids.length <= 105
     *     1 <= asteroids[i] <= 105
     *
     * @param mass          - integer value representing the mass of the planet
     * @param asteroids     - integer array of the mass of asteroids
     * @return              - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {

        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT()

        // sort array of asteroids to try and destroy smallest ones first
        Arrays.sort(asteroids);

        // convert planet mass to long to avoid overflow if mass of asteroids is large
        long planetMass = mass; // long to avoid overflow
        // loop through all asteroids in the sorted array
        for (int asteroid : asteroids) {
            // If planet's mass is less than asteroid's mass, planet gets destroyed
            // return false because the planet won't survive this asteroid
            if (planetMass < asteroid) return false;
            // If planet survives, it gains the mass of the asteroid
            planetMass += asteroid;
        }
        return true;
    }


    /**
     * Method numRescueSleds
     *
     * You are given an array people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     *
     * Example 1:
     *    Input: people = [1,2], limit = 3
     *    Output: 1
     *    Explanation: 1 sled (1, 2)
     *
     * Example 2:
     *    Input: people = [3,2,2,1], limit = 3
     *    Output: 3
     *    Explanation: 3 sleds (1, 2), (2) and (3)
     *
     * Example 3:
     *    Input: people = [3,5,3,4], limit = 5
     *    Output: 4
     *    Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people    - an array of weights for people that need to go in a sled
     * @param limit     - the weight limit per sled
     * @return          - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {

        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT

        // sort array to easily pair the lightest and heaviest people together
        Arrays.sort(people);

        int left = 0; // left starts from the lightest person (start of the array)
        int right = people.length - 1;  // right starts from the heaviest person (end of the array)

        // Variable to count  number of sleds needed
        int sleds = 0;

        // Loop until all people are accounted for
        while (left <= right) {
            // If lightest and heaviest people together fit within the limit
            // pair them on a single sled, so move both pointers inward
            if (people[left] + people[right] <= limit) {
                left++;
            }
            // Move right pointer to consider  next heaviest person
            // This person gets their own sled if they can’t pair with 'left'
            right--;
            sleds++;
        }

        return sleds; // total number of sleds used
    }

} // End Class ProblemSolutions
