/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.rain.men.abc.tracking.child;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.io.Serializable;

import sk.rain.men.abc.tracking.AntecedentFragment;
import sk.rain.men.abc.tracking.BehaviorFragment;
import sk.rain.men.abc.tracking.ChildrenActivity;
import sk.rain.men.abc.tracking.ConsequenceFragment;
import sk.rain.men.abc.tracking.MessageKey;
import sk.rain.men.abc.tracking.R;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleAbcFormFragmentPagerAdapter extends FragmentStatePagerAdapter implements Serializable {

    private Context context;
    private Long childId;

    public SimpleAbcFormFragmentPagerAdapter(FragmentManager fm, Context context, Long childId) {
        super(fm);
        this.context = context;
        this.childId = childId;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new AntecedentChildFragment();
        } else if (position == 1){
            f = new BehaviorChildFragment();
        } else {
            f = new ConsequenceChildFragment();
        }
        Bundle b = new Bundle();
        b.putLong(MessageKey.CHILD_ID_KEY, childId);
        b.putSerializable(MessageKey.ABC_FORM_PAGE_ADAPTER, this);
        f.setArguments(b);

        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.antecedent_tab_title);
            case 1:
                return context.getResources().getString(R.string.behavior_tab_title);
            case 2:
                return context.getResources().getString(R.string.consequence_tab_title);
            default:
                return "Unknown";
        }

    }
}
