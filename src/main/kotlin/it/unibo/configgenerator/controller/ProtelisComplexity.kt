package it.unibo.configgenerator.controller

import it.unibo.configgenerator.model.protelis.DummyExecutionContext
import it.unibo.configgenerator.model.protelis.DummyNetworkManager
import it.unibo.configgenerator.model.protelis.ProtelisProgramWrapper
import org.protelis.lang.ProtelisLoader
import org.protelis.vm.ProtelisVM

object ProtelisComplexity {
    fun estimateProtelisComplexity(program: String): Pair<Double, Double> {
        val netmgr = DummyNetworkManager()
        val programWrapper = ProtelisProgramWrapper(ProtelisLoader.parse(program))
        val protelisVm = ProtelisVM(programWrapper, DummyExecutionContext(netmgr))
        protelisVm.runCycle()
        return Pair(programWrapper.estimateMips(), netmgr.getMsgSize() / 1000.0)
    }
}