package co.id.git.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.git.ui.viewmodel.BaseViewModel
import co.id.git.R
import co.id.git.adapter.UserAdapter

import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val query = intent.getStringExtra("QUERY")
        setSupportActionBar(findViewById(R.id.toolbar))

        if(query != null){
            baseViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                BaseViewModel::class.java)
            baseViewModel.loadUser(applicationContext, query)
            baseViewModel.getListUser.observe(this, Observer {
                rvUser.adapter = UserAdapter(applicationContext, it)
            })

            tvToolbar.text = query
            rvUser.layoutManager = LinearLayoutManager(this)
        }
    }
}