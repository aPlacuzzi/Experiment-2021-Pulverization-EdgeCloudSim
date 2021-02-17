package it.unibo.configgenerator.model.protelis

import com.fasterxml.jackson.databind.ObjectMapper
import org.protelis.lang.datatype.DeviceUID
import org.protelis.vm.CodePath
import org.protelis.vm.NetworkManager
import org.nustaq.serialization.FSTConfiguration
import org.protelis.vm.impl.HashingCodePathFactory
import java.util.*

class DummyNetworkManager: NetworkManager {

    private val fst = FSTConfiguration.createDefaultConfiguration()
    private val jackson = ObjectMapper()
    private var msgSize: Int = 0

    override fun getNeighborState(): MutableMap<DeviceUID, MutableMap<CodePath, Any>> =
        emptyMap<DeviceUID, MutableMap<CodePath, Any>>().toMutableMap()

    override fun shareState(toSend: MutableMap<CodePath, Any>) {
        val json = jackson.writeValueAsString(toSend.mapKeys { (k, _) -> Base64.getEncoder().encode((k as HashingCodePathFactory.HashingCodePath).hash) })
        msgSize = fst.asByteArray(json).size
    }

    fun getMsgSize() = msgSize
}