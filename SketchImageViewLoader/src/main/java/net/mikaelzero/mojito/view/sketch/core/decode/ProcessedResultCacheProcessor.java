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

package net.mikaelzero.mojito.view.sketch.core.decode;

import androidx.annotation.NonNull;

import net.mikaelzero.mojito.view.sketch.core.request.LoadRequest;


/**
 * 缓存经过处理的图片，方便下次直接读取，加快速度
 */
public class ProcessedResultCacheProcessor implements ResultProcessor {

    @Override
    public void process(@NonNull LoadRequest request, @NonNull DecodeResult result) {
        if (result.isBanProcess()) {
            return;
        }

        if (!(result instanceof BitmapDecodeResult)) {
            return;
        }

        ProcessedImageCache processedImageCache = request.getConfiguration().getProcessedImageCache();
        if (!processedImageCache.canUse(request.getOptions())) {
            return;
        }

        if (!result.isProcessed()) {
            return;
        }

        BitmapDecodeResult bitmapDecodeResult = (BitmapDecodeResult) result;
        processedImageCache.saveToDiskCache(request, bitmapDecodeResult.getBitmap());
    }
}
