package it.unibo.configgenerator.model.protelis

import org.protelis.lang.datatype.DeviceUID
import org.protelis.lang.datatype.impl.StringUID
import org.protelis.vm.NetworkManager
import org.protelis.vm.impl.AbstractExecutionContext
import org.protelis.vm.impl.SimpleExecutionEnvironment

class DummyExecutionContext(
    netmgr: NetworkManager,
): AbstractExecutionContext<DummyExecutionContext>(SimpleExecutionEnvironment(), netmgr) {

    override fun getCurrentTime(): Number = 0

    override fun getDeviceUID(): DeviceUID = StringUID("0")

    override fun nextRandomDouble(): Double = 0.0

    override fun instance(): DummyExecutionContext = this
}