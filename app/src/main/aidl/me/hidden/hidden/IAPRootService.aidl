// IAPRootService.aidl
package me.hidden.hidden;

import android.content.pm.PackageInfo;
import rikka.parcelablelist.ParcelableListSlice;

interface IAPRootService {
    ParcelableListSlice<PackageInfo> getPackages(int flags);
}