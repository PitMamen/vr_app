package com.mygt.vrapp.api;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created on 2017/5/15.
 *
 * @author lkuan
 */

public class GlideCacheModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 缓存最大50M
        int cacheSize = 50 * 1024 * 1024;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize));

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int memoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int bitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(bitmapPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
