package com.guyang.spring.boot.core.validator;


import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 自定义参数验证器，验证电话号
 * @date 2020-07-20 13:24
 */
public class PhoneNumberValidator extends AbstractValidator implements ConstraintValidator<PhoneNumber, String> {
    private final String pattern = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$";

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        String phoneNumberPattern = StringUtils.isEmpty(environment.getProperty("PhoneNumber_pattern")) ? pattern : environment.getProperty("PhoneNumber_pattern");
        return !StringUtils.isEmpty(value) && value.matches(phoneNumberPattern);
    }

}
