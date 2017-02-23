package io.github.rakshakhegde.observpref

import android.content.Context
import android.content.SharedPreferences
import android.databinding.ObservableBoolean
import android.preference.PreferenceManager

/**
 * Created by rakshakhegde on 23/02/17.
 */
class ObservableBoolPref @JvmOverloads constructor(ctx: Context, val key: String, val default: Boolean = false) :
		ObservableBoolean(default) {

	private val appCtx = ctx.applicationContext
	protected val preferences = PreferenceManager.getDefaultSharedPreferences(appCtx)

	private val prefListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, _key ->
		if (_key == key) {
			notifyChange()
		}
	}

	init {
		preferences.registerOnSharedPreferenceChangeListener(prefListener)
	}

	override fun get(): Boolean = preferences.getBoolean(key, default)

	override fun set(value: Boolean) {
		preferences.edit().putBoolean(key, value).apply()
	}
}