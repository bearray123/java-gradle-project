/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.annotation.devices;

/**
 * Created by xionglei01@baidu.com on 2022/7/28.
 */

@DeviceAbility(name = {AbiType.FINDDEVICE, AbiType.MAP, AbiType.VOICENOTE})
class BracketDevice implements IDevice {

   @Override public void onCreate() {

   }

   @Override public void onStateChanged(State state) {

   }

   @Override public void onFoucsChangled(boolean focusOn) {

   }

   @Override public void onDestroy() {

   }
}
