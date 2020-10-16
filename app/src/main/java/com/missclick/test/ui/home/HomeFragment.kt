package com.missclick.test.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.missclick.test.MainActivity
import com.missclick.test.R
import com.missclick.test.ui.ExpandableHeaderItem
import com.missclick.test.ui.FancyItem
import com.missclick.test.ui.SectionsPagerAdapter
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*

import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val excitingSection = Section()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupAdapter = GroupAdapter<ViewHolder>()
        val fancies = listOf(FancyItem(4, 5),
            FancyItem(4, 7),
            FancyItem(4, 3),
            FancyItem(4, 8)
        )
        val sect = Section(fancies)
        val exp = ExpandableGroup(ExpandableHeaderItem("expand")).apply {
            add(sect)
            groupAdapter.add(this)
        }
        //groupAdapter.add(section)
        //groupAdapter.add(section2)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity as MainActivity)
            adapter = groupAdapter
        }
        (activity as MainActivity).fab.setOnClickListener {
            Log.e("fab","works!")
//            exp.removeAll(sect)
//            groupAdapter.notifyDataSetChanged()
            val fancies1 = listOf(FancyItem(4, 4),
                FancyItem(4, 4)
            )
//            exp.onChanged(Section(fancies1))
//            exp.remove(sect)
//            exp.notifyChanged()
//            exp.removeAll()
            exp.notifyChanged()
            groupAdapter.notifyDataSetChanged()
//            exp.add(Section(fancies))
        }

    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}