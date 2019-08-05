package com.github.torczuk.lisek.model

import kotlin.collections.List

data class List(val expressions: List<Expression> = emptyList()) : Expression {

    constructor(vararg expressions: Expression): this(expressions.toList())

    override fun toString() = "(" + expressions.forEach { it.toString() } + ")"
}