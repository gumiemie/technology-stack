package com.guyang.basis.designPattern.g_adapter;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-09-10 16:12
 */
public class GasolineMotorAdapter implements Motor {
    private GasolineMotor gasolineMotor;

    public GasolineMotorAdapter() {
        gasolineMotor = new GasolineMotor();
    }

    @Override
    public void drive() {
        gasolineMotor.gasolineDrive();
    }
}
