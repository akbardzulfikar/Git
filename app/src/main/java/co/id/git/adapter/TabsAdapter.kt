package co.id.git.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.id.git.ui.fragment.FollowersFragment
import co.id.git.ui.fragment.FollowingFragment

class TabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FollowersFragment()
            1 -> return FollowingFragment()
        }
        return FollowingFragment()
    }

}