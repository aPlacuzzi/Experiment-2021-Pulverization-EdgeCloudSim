package it.unibo.configgenerator.model.protelis

import com.fasterxml.jackson.databind.ObjectMapper
import org.protelis.lang.datatype.DeviceUID
import org.protelis.vm.CodePath
import org.protelis.vm.NetworkManager
import org.nustaq.serialization.FSTConfiguration

class DummyNetworkManager: NetworkManager {

    private val conf = FSTConfiguration.createDefaultConfiguration()
    private val jackson = ObjectMapper()
    private var msgSize: Int = 0

    override fun getNeighborState(): MutableMap<DeviceUID, MutableMap<CodePath, Any>> =
        emptyMap<DeviceUID, MutableMap<CodePath, Any>>().toMutableMap()

    override fun shareState(toSend: MutableMap<CodePath, Any>?) {
        msgSize = jackson.writeValueAsBytes(toSend).size //conf.asByteArray(jackson.writeValueAsString(toSend)).size
    }

    fun getMsgSize() = msgSize
}