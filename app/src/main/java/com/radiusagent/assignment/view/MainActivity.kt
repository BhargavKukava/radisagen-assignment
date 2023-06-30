package com.radiusagent.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiusagent.assignment.adapter.FacilityAdapter
import com.radiusagent.assignment.databinding.ActivityMainBinding
import com.radiusagent.assignment.model.AssignmentModel
import com.radiusagent.assignment.model.ExclusionsModel
import com.radiusagent.assignment.model.FacilitiesModel
import com.radiusagent.assignment.model.OptionsModel
import com.radiusagent.assignment.presenter.facility.FacilityPresenter

class MainActivity : AppCompatActivity(), FacilityPresenter.View{
    private lateinit var binding:ActivityMainBinding
    private lateinit var facilities: List<FacilitiesModel>
    private lateinit var exclutions: List<List<ExclusionsModel>>
    private var presenter= FacilityPresenter()
    private lateinit var rvAdapter: FacilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)
        setupRecyclerView()
        presenter.getListFromApi()
    }

    override fun notifyDataSetChanged(assignmentModel: AssignmentModel) {
        rvAdapter.list = assignmentModel.facilities
        rvAdapter.exclusionlist=assignmentModel.exclusions
        rvAdapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        facilities= emptyList()
        exclutions= emptyList()
        rvAdapter = FacilityAdapter(this,facilities,exclutions)

        binding.rvFacility.layoutManager = LinearLayoutManager(this)
        binding.rvFacility.adapter = rvAdapter
        binding.rvFacility.setHasFixedSize(true)
    }

    override fun showError() {
        Toast.makeText(this, "Somthing wrong with api", Toast.LENGTH_SHORT).show()
    }


}