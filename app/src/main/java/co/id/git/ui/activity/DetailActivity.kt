package co.id.git.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.id.git.ui.viewmodel.DetailViewModel
import co.id.git.R
import co.id.git.adapter.TabsAdapter
import co.id.git.data.vo.User
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initTabs()
        initData()
        pb.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        if (user != null) {

            detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                DetailViewModel::class.java)
            detailViewModel.loadUser(applicationContext, user.login)
            detailViewModel.getDetailUser.observe(this, Observer {


                Glide.with(applicationContext)
                    .load(it.avatarUrl)
                    .into(findViewById(R.id.imgUser))
                tvName.text = it.name
                tvUsername.text = it.login

                if (it.bio.isNullOrEmpty()) {
                    tvEmail.visibility = View.GONE
                } else {
                    tvBio.text = it.bio
                    tvBio.visibility = View.VISIBLE
                }
                if (it.email.isNullOrEmpty()) {
                    tvEmail.visibility = View.GONE
                } else {
                    tvEmail.text = it.email
                    tvEmail.visibility = View.VISIBLE
                }
                if (it.blog.isNullOrEmpty()) {
                    tvBlog.visibility = View.GONE
                } else {
                    tvBlog.text = it.blog
                    tvBlog.visibility = View.VISIBLE
                }

                tvFollow.text =
                    it.followers.toString() + " Followers . " + it.following.toString() + " Following "

                imgUser.visibility = View.VISIBLE
                tvName.visibility = View.VISIBLE
                tvUsername.visibility = View.VISIBLE
                linear.visibility = View.VISIBLE
                pb.visibility = View.INVISIBLE
            })
        }
    }

    private fun initTabs() {
        viewpager2.adapter = TabsAdapter(this)
        TabLayoutMediator(tabs, viewpager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Followers"
                    1 -> tab.text = "Following"
                }
            }).attach()
    }
}