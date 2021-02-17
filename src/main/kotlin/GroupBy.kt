import org.protelis.lang.datatype.DatatypeFactory
import org.protelis.lang.datatype.Field
import org.protelis.lang.datatype.Tuple
import kotlin.streams.asSequence

object GroupBy {
    @JvmStatic
    fun updateKeyBy(update: Int, key: Int, stamp: Int, field: Field<Tuple>): Field<Tuple> = field.map { uid ->
        val currentValue = field[uid]
        val currentKey = currentValue[key]
        val updated = field.valueStream()
            .asSequence()
            .filter { it[key] == currentKey }
            .maxBy { (it[stamp] as Number).toDouble() }
            ?.get(update) ?: currentValue[update]
        currentValue.set(update, updated)
    }
}