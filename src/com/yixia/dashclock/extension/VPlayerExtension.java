/*
 * Copyright 2013 YIXIA Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yixia.dashclock.extension;

import com.yixia.dashclock.extension.R;
import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import java.util.List;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public class VPlayerExtension extends DashClockExtension {

    public static final String VPLAYER = "me.abitno.vplayer.t";

    @Override
    protected void onUpdateData(int reason) {
        
    		Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.vplayer_market_prefix) + VPLAYER));
        PackageManager pm = getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : packageInfos) {
	        if (VPLAYER.equalsIgnoreCase(packageInfo.packageName)) {
	        	intent = pm.getLaunchIntentForPackage(VPLAYER);
	        	break;
	        }
        }
        
        // Publish the extension data update.
        publishUpdate(new ExtensionData()
                .visible(true)
                .icon(R.drawable.ic_extension_vplayer)
                .status(getString(R.string.expanded_title))
                .expandedTitle(getString(R.string.expanded_title))
                .expandedBody(getString(R.string.expanded_body))
                .clickIntent(intent));
    }
}
