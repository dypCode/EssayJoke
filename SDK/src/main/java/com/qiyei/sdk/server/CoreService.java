package com.qiyei.sdk.server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.qiyei.sdk.log.LogManager;

/**
 * Email: 1273482124@qq.com
 * Created by qiyei2015 on 2017/9/1.
 * Version: 1.0
 * Description: 核心服务，提供SDK的核心功能等
 */
public class CoreService extends BaseService {

    /**
     * 调试标志
     */
    private static final String TAG = CoreService.class.getSimpleName();
    /**
     * 名字
     */
    public static final String name = TAG;

    /**
     * context
     */
    private Context mContext;

    /**
     * 服务连接回调
     */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogManager.i(TAG,"onServiceConnected RemoteCoreService");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //先启动远程核心服务
            startService(new Intent(mContext,RemoteCoreService.class));
            bindService(new Intent(mContext, RemoteCoreService.class),mServiceConnection, Context.BIND_IMPORTANT);
            LogManager.i(TAG,"onServiceDisconnected RemoteCoreService");
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(mContext, RemoteCoreService.class),mServiceConnection, Context.BIND_IMPORTANT);
        LogManager.i(TAG,"bind RemoteCoreService");

        return START_STICKY;
    }


    @Override
    protected String getServiceName() {
        return name;
    }

    @Override
    protected void onServiceReady() {
        LogManager.i(TAG,"CoreService onServiceReady");
    }

    @Override
    protected void onClientDown(int id) {
        LogManager.i(TAG,"CoreService onClientDown id:" + id);
    }
}
