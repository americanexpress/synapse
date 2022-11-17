package io.americanexpress.synapse.utilities.common.miscellaneous;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;


/**
 * {@code StringCommonUtils} contains methods for string conversions and string cleanup.
 */
public class StringCommonUtils {

    private StringCommonUtils() {
        // Private constructor to prevent instantiation of utility class.
    }

    /**
     * Replaces dashes and spaces with underscores and uppercase the value as to create an enum like casing
     *
     * @param value a string that needs to be enumirfy
     * @return an enumerate string
     */
    public static String convertSpaceAndDashToUnderscore(String value){
        if(StringUtils.isNotBlank(value)){
            value = value.trim().replaceAll("\\s+|-+","_");
        }
        return value;
    }

    /**
     * Removes special characters
     *
     * @param value a string containind special characters
     * @return a string with the special characters removed
     */
    public static String removeSpecialCharacters(String value){
        if(StringUtils.isNotBlank(value)){
            value = value.trim().replaceAll("\\\\+|\\\"|\\:|\\@|\\/|\\.|\\#|\\(|\\)|\\%|\\$|\\^|\\+|\\=|\\!|\\'|\\<|\\>|\\?|\\/|\\;","");
        }
        return value;
    }

    /**
     * Enunerifies a Strings
     *
     * @param value a string that needs to be enumirfied
     * @return an enumerate like formatted string
     */
    public static String enumerate(String value){
        if(StringUtils.isNotBlank(value)){
            value = removeSpecialCharacters(value);
            value = convertSpaceAndDashToUnderscore(value);
            value = value.toUpperCase();
        }
        return value;
    }

    /**
     * Converts a string from snake_case to PascalCase.
     *
     * @param value a string that needs to be converted
     * @return an PascalCase string
     */
    public static String convertSnakeCaseToPascalCase(String value){
        if(StringUtils.isNotBlank(value)){
            value = CaseUtils.toCamelCase(value, true, '_');
        }
        return value;
    }

    /**
     * Checks if a string is blank and if value of string equals null.
     *
     * @param value a string that needs to be checked if null
     * @return boolean true if string is not blank and value does not equal null
     */
    public static boolean checkForNullString(String value){
        return StringUtils.isNotBlank(value) && !"null".equals(value);
    }
}

