package com.example.basketballapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class TeamsAdaper(
    private val context: Context,
    private val teams: ArrayList<Team>
) : ArrayAdapter<Team>(context, R.layout.item_team, teams) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.item_team, null)

        val name : TextView = view.findViewById(R.id.tvTeamName)
        val city : TextView = view.findViewById(R.id.tvTeamCity)
        val conference : TextView = view.findViewById(R.id.tvTeamConference)

        name.text = teams[position].name
        city.text = teams[position].city
        conference.text = teams[position].conference

        return view
    }
}