package com.example.android_task4.data

import android.content.Context
import androidx.preference.PreferenceManager
import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefsManager = PreferenceManager.getDefaultSharedPreferences(context)
    private val flowPreferences = FlowSharedPreferences(prefsManager)

    private val sortModePref = flowPreferences.getString(SORTING_KEY, "sort_title")
    private val useRoomPref = flowPreferences.getBoolean(USE_ROOM_KEY, true)

    val sortModeFlow: Flow<SortMode>
        get() = sortModePref.asFlow().distinctUntilChanged()
            .map { modeString ->
                when (modeString) {
                    "sort_title" -> SortMode.SORT_BY_TITLE
                    "sort_author" -> SortMode.SORT_BY_AUTHOR
                    "sort_page_numbers" -> SortMode.SORT_BY_PAGE_NUMBERS
                    else -> error("Unknown sort option found: $modeString")
                }
            }

    val useRoomFlow: Flow<Boolean>
        get() = useRoomPref.asFlow().distinctUntilChanged()

    private companion object {
        const val SORTING_KEY = "pref_sorting"
        const val USE_ROOM_KEY = "pref_use_room"
    }
}