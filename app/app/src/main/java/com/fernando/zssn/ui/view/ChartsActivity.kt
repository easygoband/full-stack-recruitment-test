package com.fernando.zssn.ui.view

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fernando.zssn.R
import com.fernando.zssn.databinding.ActivityChartsBinding
import com.fernando.zssn.data.network.response.ResponseItemList
import com.fernando.zssn.data.network.response.ResponseList
import com.fernando.zssn.ui.viewmodel.ChartsViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.card_average_items.view.*
import kotlinx.android.synthetic.main.card_percent_infected.view.*

class ChartsActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart

    private lateinit var foodAverage: TextView

    private lateinit var waterAverage: TextView

    private lateinit var ammunitionAverage: TextView

    private lateinit var medicationAverage: TextView

    private lateinit var survivorsResponse: ResponseList

    private lateinit var itemsResponse: ResponseItemList

    private lateinit var chartsViewModel: ChartsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChartsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chartsViewModel = ViewModelProvider(this).get(ChartsViewModel::class.java)

        pieChart = binding.dashboard.pieChartInfected
        foodAverage = binding.dashboard.foodAverage
        waterAverage = binding.dashboard.waterAverage
        ammunitionAverage = binding.dashboard.ammunitionAverage
        medicationAverage = binding.dashboard.medicationAverage

        buildInfectedChart()
        buildAverageItemsCards()

    }

    private fun buildInfectedChart(){

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.dragDecelerationFrictionCoef = 0.95f

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.rotationAngle = 0f

        // enable rotation of the pieChart by touch
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart

        chartsViewModel.getAllSurvivors()


        chartsViewModel.survivorsResponse.observe(this, Observer { survivorsResponse ->
            val infectedSurvivor = survivorsResponse.data.filter {
                it.isInfected.toString() == "true"
            }

            val notInfectedSurvivor = survivorsResponse.data.filter {
                it.isInfected.toString() == "false"
            }

            val infectedSurvivorPercent = (infectedSurvivor.size * 100)/survivorsResponse.totalResults
            val notInfectedSurvivorPercent = (notInfectedSurvivor.size * 100)/survivorsResponse.totalResults

            val entries: ArrayList<PieEntry> = ArrayList()

            entries.add(PieEntry(infectedSurvivorPercent.toFloat()))
            entries.add(PieEntry(notInfectedSurvivorPercent.toFloat()))
            // on below line we are setting pie data set

            val dataSet = PieDataSet(entries, "Mobile OS")
            // on below line we are setting icons.
            dataSet.setDrawIcons(false)

            // on below line we are setting slice for pie
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f

            // add a lot of colors to list
            val colors: ArrayList<Int> = ArrayList()
            colors.add(resources.getColor(R.color.orange))
            colors.add(resources.getColor(R.color.dark))

            // on below line we are setting colors.
            dataSet.colors = colors

            // on below line we are setting pie data set
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(15f)
            data.setValueTypeface(Typeface.DEFAULT_BOLD)
            data.setValueTextColor(Color.WHITE)
            pieChart.data = data

            // undo all highlights
            pieChart.highlightValues(null)

            // loading chart
            pieChart.invalidate()
        })

    }

    private fun buildAverageItemsCards() {
        val waterItemsCount: MutableList<Int> = mutableListOf()
        val foodItemsCount: MutableList<Int> = mutableListOf()
        val ammunitionItemsCount: MutableList<Int> = mutableListOf()
        val medicationItemsCount: MutableList<Int> = mutableListOf()

        chartsViewModel.getAllItems()

        chartsViewModel.itemsResponse.observe(this, Observer { itemsResponse ->

            itemsResponse.data.forEach {
                when(it.type){
                    "Water" -> {
                        waterItemsCount.add(it.quantity)
                    }
                    "Food" -> {
                        foodItemsCount.add(it.quantity)
                    }
                    "Medication" -> {
                        medicationItemsCount.add(it.quantity)
                    }
                    "Ammunition" -> {
                        ammunitionItemsCount.add(it.quantity)
                    }
                }
            }

            ammunitionAverage.text = ammunitionItemsCount.average().toString()
            foodAverage.text = foodItemsCount.average().toString()
            waterAverage.text = waterItemsCount.average().toString()
            medicationAverage.text = medicationItemsCount.average().toString()
        })

    }
}

