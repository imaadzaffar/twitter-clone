/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.zafaris.twitterclone

import android.app.Application
import com.parse.Parse

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("dJwlBzkh5E5RxomMNV2YtX3SvqTdXyiNn2nfP7y1") // if desired
                .clientKey("EhqRglTTxJt3Zv7sFYwsEyGk0YTy5DrknIqFaE38")
                .server("https://parseapi.back4app.com/")
                .build()
        )
    }
}