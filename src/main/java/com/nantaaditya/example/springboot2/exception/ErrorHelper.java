package com.nantaaditya.example.springboot2.exception;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * created by pramuditya.anantanur
 **/
public class ErrorHelper {
  public static Map<String, List<String>> from(BindingResult result, MessageSource messageSource) {
    return from(result, messageSource, Locale.getDefault());
  }

  public static Map<String, List<String>> from(BindingResult result, MessageSource messageSource, Locale locale) {
    if (result.hasErrors()) {
      Map<String, List<String>> map = new HashMap<>();

      List<FieldError> fieldErrors = result.getFieldErrors();
      fieldErrors.stream()
          .forEach(e -> putEntry(map, e.getField(), e.getDefaultMessage()));

      return map;
    } else {
      return Collections.emptyMap();
    }
  }

  public static Map<String, List<String>> from(Set<ConstraintViolation<?>> constraintViolations) {
    Map<String, List<String>> map = new HashMap<>();

    constraintViolations.forEach(violation -> {
      for (String attribute : getAttributes(violation)) {
        putEntry(map, attribute, violation.getMessage());
      }
    });

    return map;
  }

  private static void putEntry(Map<String, List<String>> map, String key, String value) {
    if (!map.containsKey(key)) {
      map.put(key, new ArrayList<>());
    }
    map.get(key).add(value);
  }

  private static String[] getAttributes(ConstraintViolation<?> constraintViolation) {
    String[] values = (String[]) constraintViolation.getConstraintDescriptor().getAttributes().get("path");
    if (values == null || values.length == 0) {
      return getAttributesFromPath(constraintViolation);
    } else {
      return values;
    }
  }

  private static String[] getAttributesFromPath(ConstraintViolation<?> constraintViolation) {
    Path path = constraintViolation.getPropertyPath();

    StringBuilder builder = new StringBuilder();
    path.forEach(node -> {
      if (node.getName() != null) {
        if (builder.length() > 0) {
          builder.append(".");
        }

        builder.append(node.getName());
      }
    });

    return new String[]{builder.toString()};
  }
}
