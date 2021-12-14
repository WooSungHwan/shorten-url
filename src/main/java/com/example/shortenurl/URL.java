package com.example.shortenurl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.example.shortenurl.URL.*;
import static com.example.shortenurl.URL.UrlAnnotationValidator;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = { UrlAnnotationValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface URL {

    String message() default "url 형식을 확인해주시기 바랍니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] schemes() default {"http", "https"};

    public static class UrlAnnotationValidator implements ConstraintValidator<URL, String> {
        private UrlValidator urlValidator;

        @Override
        public void initialize(URL constraintAnnotation) {
            this.urlValidator = new UrlValidator(constraintAnnotation.schemes());
        }

        @Override
        public boolean isValid(String originUrl, ConstraintValidatorContext context) {
            if (StringUtils.isBlank(originUrl)) {
                return true;
            }
            return urlValidator.isValid(originUrl);
        }
    }
}
