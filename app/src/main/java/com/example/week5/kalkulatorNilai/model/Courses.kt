package com.example.week5.kalkulatorNilai.model

class Courses(
    var sksTotal: Int,
    var ipk: Double
) {
    var subjects: HashMap<String, Subject> = HashMap<String, Subject>()

    fun calculateCourse(){
        var tempSksTotal = 0
        var tempScoreTotal: Double = 0.00

        subjects.forEach { name, subject ->
            tempSksTotal += subject.sks
            tempScoreTotal += (subject.score * subject.sks )
        }

        if (tempSksTotal == 0){
            sksTotal = 0
            ipk = 0.00;
        }else{
            sksTotal = tempSksTotal
            ipk = tempScoreTotal / tempSksTotal
        }


    }

    fun addSubject(subject: Subject){
        subjects.put(subject.name,subject)
    }
    fun getSubject(name: String): Subject? {
        return subjects.get(name)
    }

    fun deleteSubject(name: String){
        subjects.remove(name)
    }
}