package com.github.torczuk.lisek.model

data class Lst(val expressions: List<Expression> = emptyList()) : Expression {

    constructor(vararg expressions: Expression): this(expressions.toList())

    override fun toString() = "(" + expressions.forEach { it.toString() } + ")"
}