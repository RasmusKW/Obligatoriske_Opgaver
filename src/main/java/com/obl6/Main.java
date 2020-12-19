package com.obl6;

public class Main
{
    public static void main(String[] args)
        {
            int arr[] ={3,60,35,2,45,320,5};

            System.out.println("Array Before Bubble Sort");
            for(int i=0; i < arr.length; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();

            bubbleSort(arr);//sorting array elements using bubble sort

            System.out.println("Array After Bubble Sort");
            for(int i=0; i < arr.length; i++){
                System.out.print(arr[i] + " ");
            }

        }

    /**
     * Using this algorithm, larger values get pushed to the end of the array faster than smaller values.
     * Similarly, under water, larger bubbles rise to the top faster than smaller bubbles.
     * So, it’s fitting that this algorithm would be called Bubble Sort!
     * @param array
     */
    static void bubbleSort(int[] array) {
        int n = array.length;
        int temp = 0;
        for(int i=0; i < n; i++)
        {
            for(int j=1; j < (n-i); j++)
            {
                if(array[j-1] > array[j])
                {
                    //swap elements
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**Big O analysis
     * three for loops
     *
     * Best:
     * big-O = n
     * for a already sorted arry, the bubble sort will jut iterate over the array once.
     *
     * Average
     * big-O = n²
     * for randomly sorted array, bubble sort will iterate the items in a loop (n) an a nested loop(n²) giving the formular : n+n²
     * which gives n², because of the mechanics og big-O analysis.
     *
     * Worst
     * Big-O = n²
     * same reason as above with average.
     */
}
