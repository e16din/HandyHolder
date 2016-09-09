package com.e16din.handyholder;


import android.support.v7.widget.RecyclerView;

public class AlreadyBox<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL>
        extends ChainBox<ADAPTER, HOLDER, MODEL> {

    private boolean mAlreadyInited = false;
    private boolean mAlreadySetRippleEffect = false;
    private boolean mAlreadySetEmptyStubSize = false;
    private boolean mAlreadySetAsyncInflating = false;
    private boolean mAlreadySetRecyclable = false;

    @Override
    public AlreadyBox<ADAPTER, HOLDER, MODEL> emptyStubSize(int width, int height) {
        mAlreadySetEmptyStubSize = true;
        return (AlreadyBox<ADAPTER, HOLDER, MODEL>) super.emptyStubSize(width, height);
    }

    @Override
    public AlreadyBox<ADAPTER, HOLDER, MODEL> isRecyclable(boolean recyclable) {
        mAlreadySetRecyclable = true;
        return (AlreadyBox<ADAPTER, HOLDER, MODEL>) super.isRecyclable(recyclable);
    }


    @Override
    public AlreadyBox<ADAPTER, HOLDER, MODEL> asyncInflating(boolean asyncInflating) {
        mAlreadySetAsyncInflating = true;
        return (AlreadyBox<ADAPTER, HOLDER, MODEL>) super.asyncInflating(asyncInflating);
    }

    @Override
    public AlreadyBox<ADAPTER, HOLDER, MODEL> rippleEffect(boolean rippleEffect) {
        mAlreadySetRippleEffect = true;
        return (AlreadyBox<ADAPTER, HOLDER, MODEL>) super.rippleEffect(rippleEffect);
    }

    @Override
    public HOLDER init() {
        mAlreadyInited = true;
        return super.init();
    }

    public boolean isAlreadyInited() {
        return mAlreadyInited;
    }

    public boolean isAlreadySetRippleEffect() {
        return mAlreadySetRippleEffect;
    }

    public boolean isAlreadySetEmptyStubSize() {
        return mAlreadySetEmptyStubSize;
    }

    public boolean isAlreadySetAsyncInflating() {
        return mAlreadySetAsyncInflating;
    }

    public boolean isAlreadySetRecyclable() {
        return mAlreadySetRecyclable;
    }
}
