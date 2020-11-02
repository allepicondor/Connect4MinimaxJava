public class assistant {
    public static int[][] CopyArray(int[][] array){
        int[][] NewArray = new int[array.length][array[0].length];
        for (int level = 0; level < array.length; level++) {
            int[] newList = new int[array[0].length];
            for (int i = 0; i < array[1].length; i++) {
                newList[i] = array[level][i];
            }
            NewArray[level] = newList;
        }
        return NewArray;
    }
    public static int argmax(double[] list){
        double max = Double.NEGATIVE_INFINITY;
        int maxIndex = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > max) {
                max = list[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static int argmin(double[] list){
        double min = Double.POSITIVE_INFINITY;
        int maxIndex = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] < min) {
                min = list[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
