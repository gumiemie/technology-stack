package com.guyang.spring.boot.core.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 自定义参数验证器，验证电话号
 * @date 2020-07-20 13:24
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {
    @Override
    public boolean isValid(final String phoneNumber, final ConstraintValidatorContext context) {
        if (phoneNumber==null||phoneNumber.isEmpty()){
            return true;
        }
        return phoneNumber.matches("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$") && phoneNumber.length() > 8 && phoneNumber.length() < 14;
    }
}
