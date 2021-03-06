package com.winside.lighting.mesh;

import android.content.Context;
import android.os.AsyncTask;

import com.winside.lighting.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class WTaskSwitchLight extends AsyncTask<Void, Integer, Boolean> {

    private final String mMac;
    private final byte[] mDestAddress;
    private final boolean mSwitchOn;
    private final BleClient mBleClient;

    public WTaskSwitchLight(Context context, String mac, byte[] destAddress, boolean switchOn) {
        this.mMac = mac;
        this.mDestAddress = destAddress;
        this.mSwitchOn = switchOn;
        this.mBleClient = new BleClient(context, mMac, WConstants.SERVICE_UUID, WConstants.CHARACTERISTIC_WRITE_UUID,
                WConstants.CHARACTERISTIC_READ_UUID, new WEncoder(mMac), new WDecoder());
    }

    private boolean init() {
        if (!mBleClient.connect()) {
            return false;
        }
        if (!mBleClient.discoverServices()) {
            return false;
        }
        if (!mBleClient.enableNotification()) {
            return false;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mBleClient.requestMtu();
    }

    private void destroy() {
        mBleClient.disconnect();
        mBleClient.release();
    }

    private boolean switchLight() {
        List<byte[]> list = new ArrayList<>();
        list.add(WConstants.Light.OP_CODE_SWITCH);
        list.add(new byte[]{mSwitchOn ? (byte) 0x01 : (byte) 0x00});
        byte[] message = Utils.concatForByte(list);
        assert message != null;
        return mBleClient.sendData(WPacketUtils.buildPacket(mDestAddress, message));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (init()) {
            publishProgress(50);
        } else {
            destroy();
            return false;
        }
        if (switchLight()) {
            publishProgress(100);
        } else {
            destroy();
            return false;
        }
        destroy();
        return true;
    }
}
