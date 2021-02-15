package it.unibo.configgenerator.model

data class OutputEdgeConfig(
    val edge_devices: List<DataCenter>
) {
    companion object {
        fun fromInputEdgeConfig(inputEdgesConfig: InputEdgesConfig): OutputEdgeConfig {
            var wlanId = 0
            val vms = IntRange(1, inputEdgesConfig.edgeVwCount).map {
                VM(
                    core = inputEdgesConfig.edgeCore / inputEdgesConfig.edgeVwCount,
                    mips = inputEdgesConfig.edgeMips / inputEdgesConfig.edgeVwCount,
                    ram = inputEdgesConfig.edgeRam / inputEdgesConfig.edgeVwCount,
                    storage = inputEdgesConfig.edgeStorage / inputEdgesConfig.edgeVwCount,
                )
            }
            val hosts = IntRange(1, inputEdgesConfig.edgeCount).map {
                Host(
                    inputEdgesConfig.edgeCore,
                    inputEdgesConfig.edgeMips,
                    inputEdgesConfig.edgeRam,
                    inputEdgesConfig.edgeStorage,
                    vms,
                )
            }
            val edgeDevices = inputEdgesConfig.locations.map { inputLocation ->
                DataCenter(
                    costPerBw = inputEdgesConfig.costPerBw,
                    costPerMem = inputEdgesConfig.costPerMem,
                    costPerSec = inputEdgesConfig.costPerSec,
                    costPerStorage = inputEdgesConfig.costPerStorage,
                    location = Location(
                        x_pos = inputLocation.x,
                        y_pos = inputLocation.y,
                        wlan_id = wlanId++,
                        attractiveness = inputLocation.attractiveness,
                    ),
                    hosts = hosts,
                )
            }
            return OutputEdgeConfig(edgeDevices)
        }
    }

    fun toXml(): String =
        "<?xml version=\"1.0\"?>\n" +
        "<edge_devices>\n" +
        edge_devices.map { it.toXml("\t") }.reduce {s1, s2 -> "$s1\n$s2"} + "\n" +
        "</edge_devices>"
}

data class DataCenter (
    val arch: String = "x86",
    val os: String = "Linux",
    val vmm: String = "Xen",
    val costPerBw: Double,
    val costPerSec: Double,
    val costPerMem: Double,
    val costPerStorage: Double,
    val location: Location,
    val hosts: List<Host>,
) {
  fun toXml(indent: String) =
      "$indent<datacenter arch=\"$arch\" os=\"$os\" vmm=\"$vmm\">\n" +
      "$indent\t<costPerBw>$costPerBw</costPerBw>\n" +
      "$indent\t<costPerSec>$costPerSec</costPerSec>\n" +
      "$indent\t<costPerMem>$costPerMem</costPerMem>\n" +
      "$indent\t<costPerStorage>$costPerStorage</costPerStorage>\n" +
      location.toXml("$indent\t") +
      "$indent\t<hosts>\n" +
      hosts.map { it.toXml("$indent\t\t") }.reduce {s1, s2 -> "$s1\n$indent$s2"} + "\n" +
      "$indent\t</hosts>\n" +
      "$indent</datacenter>"
}

data class Location(
    val x_pos: Int,
    val y_pos: Int,
    val wlan_id: Int,
    val attractiveness: Int,
) {
    fun toXml(indent: String) =
        "$indent<location>\n" +
        "$indent\t<x_pos>$x_pos</x_pos>\n" +
        "$indent\t<y_pos>$y_pos</y_pos>\n" +
        "$indent\t<wlan_id>$wlan_id</wlan_id>\n" +
        "$indent\t<attractiveness>$attractiveness</attractiveness>\n" +
        "$indent</location>\n"
}

data class VM (
    val vmm: String = "Xen",
    val core: Int,
    val mips: Int,
    val ram: Int,
    val storage: Int,
) {
    fun toXml(indent: String) =
        "$indent<VM vmm=\"$vmm\">\n" +
        "$indent\t<core>$core</core>\n" +
        "$indent\t<mips>$mips</mips>\n" +
        "$indent\t<ram>$ram</ram>\n" +
        "$indent\t<storage>$storage</storage>\n" +
        "$indent</VM>"
}

data class Host(
    val core: Int,
    val mips: Int,
    val ram: Int,
    val storage: Int,
    val vms: List<VM>,
) {
    fun toXml(indent: String) =
        "$indent<host>\n" +
        "$indent\t<core>$core</core>\n" +
        "$indent\t<mips>$mips</mips>\n" +
        "$indent\t<ram>$ram</ram>\n" +
        "$indent\t<storage>$storage</storage>\n" +
        "$indent\t<VMs>\n" +
        vms.map { it.toXml("$indent\t\t") }.reduce {s1, s2 -> "$s1\n$s2"} + "\n" +
        "$indent\t</VMs>\n" +
        "$indent</host>"
}