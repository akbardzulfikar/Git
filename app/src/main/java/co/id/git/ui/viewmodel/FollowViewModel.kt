package co.id.git.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.git.data.RfConfig
import co.id.git.data.vo.User
import retrofit2.Call
import retrofit2.Response

class FollowViewModel : ViewModel() {

    private val listFollowers : MutableLiveData<List<User>> = MutableLiveData()
    private val listFollowing : MutableLiveData<List<User>> = MutableLiveData()

    fun loadFollowers(context: Context?, username:String, page : String){
        RfConfig.getRetrofit().getFollowersData(username,page ).enqueue(object : retrofit2.Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                listFollowers.value = response.body()
            }
        })
    }
    fun loadFollowing(context: Context?, username:String, page : String){
        RfConfig.getRetrofit().getFollowingData(username, page).enqueue(object : retrofit2.Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                listFollowing.value = response.body()
            }
        })
    }

    val getFollowers : LiveData<List<User>> = listFollowers
    val getFollowing : LiveData<List<User>> = listFollowing

}