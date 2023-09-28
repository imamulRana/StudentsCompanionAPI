package com.imstudio.studentscompanion.repository

import com.imstudio.studentscompanion.model.Batch
import com.imstudio.studentscompanion.model.Bus
import com.imstudio.studentscompanion.model.Classes
import com.imstudio.studentscompanion.model.Department
import com.imstudio.studentscompanion.model.Section
import com.imstudio.studentscompanion.network.RetrofitInstance

class StuCompRepo {
    suspend fun getAllDept(): List<Department> {
        return RetrofitInstance.retrofitService.getAllDept()
    }

    suspend fun getAllBatch(setDept: Int): List<Batch> {
        return RetrofitInstance.retrofitService.getAllBatch(departmentId = setDept)
    }

    suspend fun getAllSection(dept: Int, bat: Int): List<Section> {
        return RetrofitInstance.retrofitService.getAllSection(departmentId = dept, batchId = bat)
    }

    suspend fun getAllClass(dept: Int, bat: Int, sec: Int): List<Classes> {
        return RetrofitInstance.retrofitService.getAllClass(
            departmentId = dept,
            batchId = bat,
            sectionId = sec
        )
    }

    suspend fun getUpData(): List<Bus> {
        return RetrofitInstance.retrofitService.getUpRouteData()
    }

    suspend fun getDownData(): List<Bus> {
        return RetrofitInstance.retrofitService.getDownRouteData()
    }
}