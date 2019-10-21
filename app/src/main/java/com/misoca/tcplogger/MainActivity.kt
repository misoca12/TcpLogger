package com.misoca.tcplogger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.misoca.tcplogger.databinding.ActivityMainBinding
import android.R.string.cancel
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AlertDialog



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LogListViewModel
    private lateinit var adapter: TcpLogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        adapter = TcpLogAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(LogListViewModel::class.java)
        viewModel.logs.observe(this, Observer {
            adapter.logs = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.delete -> viewModel.delete()
            R.id.setting -> showSettingDialog()
        }
        return false
    }

    private fun showSettingDialog() {
        AlertDialog.Builder(this).run {
            title = "PortNo Setting"
            val input = EditText(applicationContext).apply {
                inputType = InputType.TYPE_CLASS_TEXT
                setText(SettingPref(context).portNo)
            }
            setView(input)
            setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                SettingPref(context).portNo = input.text.toString()
            })
            setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel() })
            show()
        }
    }
}
