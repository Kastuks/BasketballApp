package com.example.basketballapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.basketballapp.databinding.FragmentHomeBinding
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var list = getFilledArray()

    private val sortByTeamList = arrayOf("Name", "City", "Conference")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val sortButton = view.findViewById<Button>(R.id.button2)

        sortButton.setOnClickListener {
            addSortingPopup(view, sortButton)
        }
        val teamlist = view.findViewById<ListView>(R.id.teamList)
        teamlist.adapter = TeamsAdaper(view.context, this.list)
        return view
    }

    fun addSortingPopup(v : View, b : Button){
        val popupMenu: PopupMenu = PopupMenu(this.context, b)
        popupMenu.menuInflater.inflate(R.menu.team_sort_popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sortTeamByName -> {
                    this.list = ArrayList(this.list.sortedBy { team -> team.name });
                    b.text = "Name"
                }

                R.id.sortTeamByCity -> {
                    this.list = ArrayList(this.list.sortedBy { team -> team.city })
                    b.text = "City"
                }

                R.id.sortTeamByConference -> {
                    this.list = ArrayList(this.list.sortedBy { team -> team.conference })
                    b.text = "Conference"
                }
            }
            val teamList = v.findViewById<ListView>(R.id.teamList)
            teamList.adapter = TeamsAdaper(v.context, this.list)
            true
        }

        popupMenu.show()
    }


    private fun getFilledArray(): ArrayList<Team> {
        return arrayListOf<Team>(
            Team("Atlanta", "Georgia", "any"),
            Team("Lakers", "Lithuania", "based"),
            Team("Zalgiris", "Kaunas", "vussin"),
            Team("Rytas", "Vilnius", "epic"),
            Team("Wolves", "Vilnius", "yeeaa"),
            )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}