package me.hidden.hidden

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

object Natives {
    init {
        System.loadLibrary("apjni")
    }

    @Immutable
    @Parcelize
    @Keep
    data class Profile(
        var uid: Int = 0,
        var toUid: Int = 0,
        var scontext: String = MyApp.DEFAULT_SCONTEXT,
    ) : Parcelable

    @Keep
    class KPMCtlRes {
        var rc: Long = 0
        var outMsg: String? = null

        constructor()

        constructor(rc: Long, outMsg: String?) {
            this.rc = rc
            this.outMsg = outMsg
        }
    }


    private external fun nativeSu(superKey: String, toUid: Int, scontext: String?): Int

    fun su(toUid: Int, scontext: String?): Boolean {
        return nativeSu(MyApp.superKey, toUid, scontext) == 0
    }

    fun su(): Boolean {
        return su(0, "")
    }

    external fun nativeReady(superKey: String): Boolean

    private external fun nativeSuPath(superKey: String): String

    fun suPath(): String {
        return nativeSuPath(MyApp.superKey)
    }

    private external fun nativeSuUids(superKey: String): IntArray

    fun suUids(): IntArray {
        return nativeSuUids(MyApp.superKey)
    }

    private external fun nativeKernelPatchVersion(superKey: String): Long
    fun kernelPatchVersion(): Long {
        return nativeKernelPatchVersion(MyApp.superKey)
    }

    private external fun nativeLoadKernelPatchModule(
        superKey: String, modulePath: String, args: String
    ): Long

    fun loadKernelPatchModule(modulePath: String, args: String): Long {
        return nativeLoadKernelPatchModule(MyApp.superKey, modulePath, args)
    }

    private external fun nativeUnloadKernelPatchModule(superKey: String, moduleName: String): Long
    fun unloadKernelPatchModule(moduleName: String): Long {
        return nativeUnloadKernelPatchModule(MyApp.superKey, moduleName)
    }

    private external fun nativeKernelPatchModuleNum(superKey: String): Long

    fun kernelPatchModuleNum(): Long {
        return nativeKernelPatchModuleNum(MyApp.superKey)
    }

    private external fun nativeKernelPatchModuleList(superKey: String): String
    fun kernelPatchModuleList(): String {
        return nativeKernelPatchModuleList(MyApp.superKey)
    }

    private external fun nativeKernelPatchModuleInfo(superKey: String, moduleName: String): String
    fun kernelPatchModuleInfo(moduleName: String): String {
        return nativeKernelPatchModuleInfo(MyApp.superKey, moduleName)
    }

    private external fun nativeControlKernelPatchModule(
        superKey: String, modName: String, jctlargs: String
    ): KPMCtlRes

    fun kernelPatchModuleControl(moduleName: String, controlArg: String): KPMCtlRes {
        return nativeControlKernelPatchModule(MyApp.superKey, moduleName, controlArg)
    }

    external fun nativeThreadSu(superKey: String, uid: Int, scontext: String?): Long

    private external fun nativeGrantSu(
        superKey: String, uid: Int, toUid: Int, scontext: String?
    ): Long

    fun grantSu(uid: Int, toUid: Int, scontext: String?): Long {
        return nativeGrantSu(MyApp.superKey, uid, toUid, scontext)
    }

    private external fun nativeRevokeSu(superKey: String, uid: Int): Long
    fun revokeSu(uid: Int): Long {
        return nativeRevokeSu(MyApp.superKey, uid)
    }

    private external fun nativeSetUidExclude(superKey: String, uid: Int, exclude: Int): Int
    fun setUidExclude(uid: Int, exclude: Int): Int {
        return nativeSetUidExclude(MyApp.superKey, uid, exclude)
    }

    private external fun nativeGetUidExclude(superKey: String, uid: Int): Int
    fun isUidExclude(uid: Int): Int {
        return nativeGetUidExclude(MyApp.superKey, uid)
    }

    private external fun nativeSuProfile(superKey: String, uid: Int): Profile

    fun suProfile(uid: Int): Profile {
        return nativeSuProfile(MyApp.superKey, uid)
    }

    private external fun nativeResetSuPath(superKey: String, path: String): Boolean
    fun resetSuPath(path: String): Boolean {
        return nativeResetSuPath(MyApp.superKey, path)
    }

    private external fun nativeGetSafeMode(superKey: String): Boolean
    fun getSafeMode(): Boolean {
        return nativeGetSafeMode(MyApp.superKey)
    }

}
