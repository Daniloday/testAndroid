package com.missclick.test.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.missclick.test.MainActivity
import com.missclick.test.R
import com.missclick.test.ui.ExpandableHeaderItem
import com.missclick.test.ui.FancyItem
import com.missclick.test.ui.SectionsPagerAdapter
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val boringFancyItems = generateFancyItems(6)
        val excitingFancyItems = generateFancyItems(12)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }
        val rv = findViewById<RecyclerView>(R.id.recycler_view)

        recycler_view.apply {
            layoutManager = GridLayoutManager((activity as MainActivity), groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        ExpandableGroup(ExpandableHeaderItem("Boring Group"), true).apply {
            add(Section(boringFancyItems))
            groupAdapter.add(this)
        }

        ExpandableGroup(ExpandableHeaderItem("Exciting Group"), false).apply {
            excitingSection.addAll(excitingFancyItems)
            add(excitingSection)
            groupAdapter.add(this)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                activity as MainActivity,
                childFragmentManager
            )
        viewPager.adapter = sectionsPagerAdapter


    }

    private fun generateFancyItems(count: Int): MutableList<FancyItem>{
        val rnd = Random()
        return MutableList(count){
            val color = Color.argb(255, rnd.nextInt(256),
                rnd.nextInt(256), rnd.nextInt(256))
            FancyItem(color, rnd.nextInt(100))
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