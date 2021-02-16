package it.unibo.configgenerator.model.protelis

import org.protelis.lang.interpreter.ProtelisAST
import org.protelis.lang.interpreter.impl.Constant
import org.protelis.lang.interpreter.impl.JvmConstant
import org.protelis.vm.ExecutionContext
import org.protelis.vm.ProtelisProgram
import org.protelis.vm.impl.SimpleProgramImpl

data class ProtelisProgramWrapper(private val protelisProgram: ProtelisProgram): ProtelisProgram {

    private val prog: ProtelisAST<*>

    init {
        val field = SimpleProgramImpl::class.java.getDeclaredField("prog")
        field.isAccessible = true
        prog = field.get(protelisProgram) as ProtelisAST<*>
    }

    override fun getCurrentValue(): Any = protelisProgram.currentValue

    override fun compute(context: ExecutionContext?) = protelisProgram.compute(context)

    override fun getName(): String = protelisProgram.name

    fun estimateMips() = flatAst(prog).map { mipsByInstruction(it) }.sum()

    private fun flatAst(ast: ProtelisAST<*>): List<ProtelisAST<*>> = listOf(ast) + ast.branches.flatMap { flatAst(it) }

    private fun mipsByInstruction(ast: ProtelisAST<*>) = when (ast) {
        is Constant<*>, is JvmConstant -> 1.0
        else -> 50.0
    }
}