package app.tp136.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator
        implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.firstFieldName();
        this.secondFieldName = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object field, ConstraintValidatorContext constraintValidatorContext) {
        Object firstFieldValue = new BeanWrapperImpl(field)
                .getPropertyValue(firstFieldName);
        Object secondFieldValue = new BeanWrapperImpl(field)
                .getPropertyValue(secondFieldName);

        return Objects.equals(firstFieldValue, secondFieldValue);
    }
}
