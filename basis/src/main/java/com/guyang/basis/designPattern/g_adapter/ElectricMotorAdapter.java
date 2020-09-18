package com.guyang.basis.designPattern.g_adapter;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-09-10 16:10
 */
public class ElectricMotorAdapter implements Motor {

    private ElectricMotor electricMotor;

    public ElectricMotorAdapter() {
        electricMotor = new ElectricMotor();
    }

    @Override
    public void drive() {
        electricMotor.electircDrive();
    }
}
