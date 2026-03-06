package com.smartBankElite.accountService.Utlis;

import org.springframework.beans.BeanUtils;

/**
 * Generic Mapper Utility for converting objects from one type to another
 * Uses Spring's BeanUtils for automatic field mapping
 */
public class GenericMapper {

    /**
     * Generic method to convert source object to target object
     * Maps all properties from source to target based on matching field names
     *
     * @param source      - Source object to copy from
     * @param targetClass - Target class to convert to
     * @param <S>         - Source type
     * @param <T>         - Target type
     * @return - New instance of target class with properties from source
     */
    public static <S, T> T mapToTarget(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        try {
            // Create a new instance of the target class
            T target = targetClass.getDeclaredConstructor().newInstance();

            // Copy properties from source to target
            BeanUtils.copyProperties(source, target);

            return target;
        } catch (Exception ex) {
            throw new RuntimeException("Error mapping object from " + source.getClass().getSimpleName() +
                    " to " + targetClass.getSimpleName(), ex);
        }
    }

    /**
     * Generic method to convert source object to target object with property ignoring
     * Useful when you want to skip certain fields during mapping
     *
     * @param source           - Source object to copy from
     * @param targetClass      - Target class to convert to
     * @param ignoredProperties - Properties to ignore during mapping
     * @param <S>              - Source type
     * @param <T>              - Target type
     * @return - New instance of target class with properties from source (excluding ignored properties)
     */
    public static <S, T> T mapToTarget(S source, Class<T> targetClass, String... ignoredProperties) {
        if (source == null) {
            return null;
        }

        try {
            // Create a new instance of the target class
            T target = targetClass.getDeclaredConstructor().newInstance();

            // Copy properties from source to target, ignoring specified properties
            BeanUtils.copyProperties(source, target, ignoredProperties);

            return target;
        } catch (Exception ex) {
            throw new RuntimeException("Error mapping object from " + source.getClass().getSimpleName() +
                    " to " + targetClass.getSimpleName(), ex);
        }
    }
}

