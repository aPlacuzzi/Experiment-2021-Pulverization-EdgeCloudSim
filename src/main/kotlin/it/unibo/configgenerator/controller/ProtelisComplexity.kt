package it.unibo.configgenerator.controller

import it.unibo.configgenerator.model.protelis.DummyExecutionContext
import it.unibo.configgenerator.model.protelis.DummyNetworkManager
import it.unibo.configgenerator.model.protelis.ProtelisProgramWrapper
import org.protelis.lang.ProtelisLoader
import org.protelis.vm.ProtelisVM

class ProtelisComplexity(program: String) {

    val netmgr = DummyNetworkManager()
    val programWrapper = ProtelisProgramWrapper(ProtelisLoader.parse(program))
    val protelisVm = ProtelisVM(programWrapper, DummyExecutionContext(netmgr))
    val msgSize: Double by lazy {
        protelisVm.runCycle()
        return@lazy netmgr.getMsgSize() / 1000.0
    }

    fun estimateProtelisComplexity(nbrMips: Double, defaultMips: Double = 1.0): Double =
        programWrapper.estimateMips(defaultMips, nbrMips)
}