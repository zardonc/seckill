package org.seckill.validator;

import org.apache.commons.lang3.StringUtils;
import org.seckill.utils.MobileValidUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.function.Predicate;

public class CheckMobileValidator implements ConstraintValidator<MobileValidator, String> {

    private boolean require = false;

    @Override
    public void initialize(MobileValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        require = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Predicate<String> predicate = MobileValidUtil::checkNumber;
        if (require) {
            return predicate.test(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return predicate.test(s);
            }
        }
    }
}
