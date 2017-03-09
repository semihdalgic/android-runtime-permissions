package permission.semihd.com.permissionapp.util;

public class StringUtil {

    /**
     * It takes two string array and concatenates them
     *
     * @param arrays Each string array
     * @return Concatenated string array
     */
    public static String[] concatArrays(String[]... arrays) {
        int len = 0;
        // calculate total size of array
        for (String[] array : arrays) {
            len += array.length;
        }

        // crete result array
        String[] result = new String[len];
        // create an index
        int index = 0;
        for (String[] array : arrays) {
            for (String item : array) {
                result[index] = item;
                index++;
            }
        }

        return result;
    }
}
