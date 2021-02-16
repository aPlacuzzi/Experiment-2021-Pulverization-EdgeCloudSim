package it.unibo.configgenerator.model.protelis

import org.protelis.lang.interpreter.ProtelisAST
import org.protelis.lang.interpreter.impl.*
import org.protelis.parser.protelis.Share
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

    private fun flatAst(ast: ProtelisAST<*>): Sequence<ProtelisAST<*>> = sequenceOf(ast) +
        ast.branches.flatMap { flatAst(it) } +
        when(ast) {
            is Invoke -> flatAst(ast.leftExpression)
            is FunctionCall -> flatAst(ast.functionDefinition.body)
            is ShareCall<*, *> -> ast.yieldExpression.transform { flatAst(it!!) }.or(emptySequence())
            is If<*> -> flatAst(ast.conditionExpression) + flatAst(ast.thenExpression) + flatAst(ast.elseExpression)
            else -> emptySequence()
        }

    private fun mipsByInstruction(ast: ProtelisAST<*>): Double = when (ast) {
        is Constant<*>, is JvmConstant, is Env, is Self, is FunctionCall -> 1.0
        else -> 50.0
    }
}