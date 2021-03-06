/*
 * Copyright (C) 2019 Peng fei Pan <panpfpanpf@outlook.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.mikaelzero.mojito.view.sketch.core.display;

import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.mikaelzero.mojito.view.sketch.core.SketchView;

import java.util.Locale;

/**
 * 由小到大图片显示器
 */
public class ZoomInImageDisplayer implements ImageDisplayer {
    private static final String KEY = "ZoomInImageDisplayer";
    private static final float DEFAULT_FROM = 0.5f;

    private int duration;
    private float fromX;
    private float fromY;
    @Nullable
    private Interpolator interpolator;
    private boolean alwaysUse;

    public ZoomInImageDisplayer(float fromX, float fromY, @Nullable Interpolator interpolator, int duration, boolean alwaysUse) {
        this.duration = duration;
        this.fromY = fromY;
        this.fromX = fromX;
        this.interpolator = interpolator;
        this.alwaysUse = alwaysUse;
    }

    public ZoomInImageDisplayer(float fromX, float fromY, @Nullable Interpolator interpolator, int duration) {
        this(fromX, fromY, interpolator, duration, false);
    }

    public ZoomInImageDisplayer(float fromX, float fromY, @Nullable Interpolator interpolator, boolean alwaysUse) {
        this(fromX, fromY, interpolator, DEFAULT_ANIMATION_DURATION, alwaysUse);
    }

    public ZoomInImageDisplayer(float fromX, float fromY, @Nullable Interpolator interpolator) {
        this(fromX, fromY, interpolator, DEFAULT_ANIMATION_DURATION, false);
    }

    public ZoomInImageDisplayer(float fromX, float fromY, boolean alwaysUse) {
        this(fromX, fromY, new AccelerateDecelerateInterpolator(), DEFAULT_ANIMATION_DURATION, alwaysUse);
    }

    public ZoomInImageDisplayer(float fromX, float fromY) {
        this(fromX, fromY, new AccelerateDecelerateInterpolator(), DEFAULT_ANIMATION_DURATION, false);
    }

    public ZoomInImageDisplayer(@Nullable Interpolator interpolator, boolean alwaysUse) {
        this(DEFAULT_FROM, DEFAULT_FROM, interpolator, DEFAULT_ANIMATION_DURATION, alwaysUse);
    }

    public ZoomInImageDisplayer(@Nullable Interpolator interpolator) {
        this(DEFAULT_FROM, DEFAULT_FROM, interpolator, DEFAULT_ANIMATION_DURATION, false);
    }

    public ZoomInImageDisplayer(int duration, boolean alwaysUse) {
        this(DEFAULT_FROM, DEFAULT_FROM, new AccelerateDecelerateInterpolator(), duration, alwaysUse);
    }

    public ZoomInImageDisplayer(int duration) {
        this(DEFAULT_FROM, DEFAULT_FROM, new AccelerateDecelerateInterpolator(), duration, false);
    }

    public ZoomInImageDisplayer(boolean alwaysUse) {
        this(DEFAULT_FROM, DEFAULT_FROM, new AccelerateDecelerateInterpolator(), DEFAULT_ANIMATION_DURATION, alwaysUse);
    }

    public ZoomInImageDisplayer() {
        this(DEFAULT_FROM, DEFAULT_FROM, new AccelerateDecelerateInterpolator(), DEFAULT_ANIMATION_DURATION, false);
    }

    @Override
    public void display(@NonNull SketchView sketchView, @NonNull Drawable newDrawable) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, 1.0f, fromY, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(interpolator);
        scaleAnimation.setDuration(duration);
        sketchView.clearAnimation();
        sketchView.setImageDrawable(newDrawable);
        sketchView.startAnimation(scaleAnimation);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.US, "%s(duration=%d,fromX=%s,fromY=%s,interpolator=%s,alwaysUse=%s)",
                KEY, duration, fromX, fromY, interpolator != null ? interpolator.getClass().getSimpleName() : null, alwaysUse);
    }

    @Override
    public boolean isAlwaysUse() {
        return alwaysUse;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public float getFromX() {
        return fromX;
    }

    public float getFromY() {
        return fromY;
    }

    @Nullable
    public Interpolator getInterpolator() {
        return interpolator;
    }
}
